// https://www.youtube.com/c/OnigiriScience/videos -- Автор хороших гайдів на цю тему в ютубі.  

Shader "RaymarchingShader"
{
    Properties
    {
        _MainTex("Texture", 2D) = "white" {}
    }

   SubShader
    {
        Cull Off
        ZWrite Off
        ZTest Always

        Pass
        {
            CGPROGRAM
            #pragma vertex vert
            #pragma fragment frag
            #include "UnityCG.cginc"

            struct appdata
            {
                float4 vertex : POSITION;
                float2 uv : TEXCOORD0;
            };

            struct v2f
            {
                float4 pos : SV_POSITION;
                float2 uv : TEXCOORD0;
                float3 ray : TEXCOORD1;
            };

            //Робота з скріптом Raymarching.cs
            uniform float4x4 _FrustumCornersES;
            uniform sampler2D _MainTex;
            uniform float4 _MainTex_TexelSize;
            uniform float4x4 _CameraInvViewMatrix;
            uniform float3 _CameraWS;

            //Пусті обьекти,яким ми надемо виду за допомогою коду(див.ниже)
            uniform float3 _Sphere;
            uniform float3 _Sphere2;
            uniform float3 _Cube;
            uniform float3 _Torus;
            uniform float3 _Torus2;

            //сфера
            float sp(float4 s, float3 p){return (length(p - s.xyz) - s.w);}

            //куб
            float cb(float4 s, float3 p){
                float3 q = abs(p - s.xyz) - s.w;
                return length(max(q, 0)) + min(max(q.x, max(q.y, q.z)), 0);
            }

            //Кільце
            float trs(float3 e, float3 c, float r1, float r2){
                float2 q = float2(length((e - c).xz) - r1, e.y - c.y);
                return length(q) - r2;
            }

            //Робимо так,щоб наші обьекти входили один в одного плавно
            float sftmn(float a, float b, float k){
                float h = clamp(0.5 + 0.5 * (b - a) / k, 0.0, 1.0);
                return lerp(b, a, h) - k * h * (1.0 - h);
            }

            //Створюємо фігури 
            float map(float3 p){

                //p.xyz = (p.xyz % 32 + 32) % 32;
                float dsphere = sp(float4(_Sphere, 2.8), p);
                float dsphere2 = sp(float4(_Sphere2, 1.4), p);
                float dcube = cb(float4(_Cube, 2.4), p);
                float dtorus = trs(_Torus, p, 5, 0.7);
                float dtorus2 = trs(_Torus2, p, 5, 0.7);

                return sftmn(sftmn(sftmn(max(-dsphere, dcube),dtorus,0.5),dtorus2,0.5),dsphere2,0.5);
            }


            //враховуємо нормаль
            float3 calcNormal(float3 p) {
                float d = map(p);
                float2 e = float2(0.001, 0);
                float3 n = d - float3(map(p - e.xyy), map(p - e.yxy), map(p - e.yyx));

                return normalize(n);
            }

            float lightRaymarching(float3 ro, float3 rd){
                float dO = 0;
                float md = 1;
                for (int i = 0; i < 20; i++)
                {
                    float3 p = ro + rd * dO;
                    float dS = map(p);
                    md = min(md, dS);
                    dO += dS;
                    if (dO > 50 || dS < 0.1) break;
                }
                return md;
            }

            float4 light(float3 p, float3 ro, int i, float3 lightPos){
                float3 l = normalize(lightPos - p);
                float3 n = calcNormal(p);
                float dif = clamp(dot(n, l) * 0.5 + 0.5, 0.1, 0.8);
                float d = lightRaymarching(p + n * 0.1 * 10, l);

                //Колір та світло
                d += 1;
                d = clamp(d, 0, 1);
                dif *= d;
                float4 col = float4(dif, dif, dif, 1) *float4(2,0.7,5,1);  //-- *float4(2,0.7,5,1) міняємо колір нашої фігури

                //ао(оклюзія)
                float ao = (float(i) / 1000 * 2);
                ao = 1 - ao;
                ao *= ao;
                col.rgb *= ao;
                
                return col;
            }

            float4 Raymarching(float3 ro, float3 rd){
                float3 p = ro;
                for (int i = 0; i < 1000; i++)
                {
                    float d = map(p);// Зразок поля відстані (див. map())
                    if (d > 100) return 0;
                    p += rd * d; // Світовий простір положення зразка 
                    // Якщо d <= 0, ми в щось потрапили (див. map()).
                    if (d < 0.001) { return light(p, ro, i, float3(0, 50, 0)); }
                }
                return 0;
            }

            v2f vert(appdata v){
                v2f o;
                //Індекс передається за допомогою спеціальної функції blit в Raymarching.cs(назва мого скріпта)
                half index = v.vertex.z;
                v.vertex.z = 0.1;
                o.pos = UnityObjectToClipPos(v.vertex);
                o.uv = v.uv.xy;

                #if UNITY_UV_STARTS_AT_TOP
                    if (_MainTex_TexelSize.y < 0) o.uv.y = 1 - o.uv.y;
                #endif

                // Беремо промінь (normalized)
                o.ray = _FrustumCornersES[(int)index].xyz;

                o.ray = mul(_CameraInvViewMatrix, o.ray);
                return o;
            }

            fixed4 frag(v2f i) : SV_Target
            {
                // напрямок променя
                float3 rd = normalize(i.ray.xyz);
                // початок променя (положення камери)
                float3 ro = _CameraWS;
                float4 c = Raymarching(ro, rd);

                c = c * c.a + tex2D(_MainTex, i.uv) * (1 - c.a);

                fixed4 col = fixed4(c.r, c.g, c.b, 1);

                return col;
            }
            ENDCG
        }
    }
  FallBack "Diffuse"
}