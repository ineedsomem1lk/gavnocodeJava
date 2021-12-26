Shader "Custom/Lab5PP2"
{
    Properties
    {
        _TopColor("Color", Color) = (1,1,1,1)
        _BotColor("Color", Color) = (1,1,1,1)
        _MainTex ("Albedo (RGB)", 2D) = "white" {}
        _Speed ("Speed",Range(0,100)) = 1
    }
    SubShader
    {
        Tags { "RenderType"="Opaque" }
        LOD 200

        CGPROGRAM
        #pragma surface surf Standard fullforwardshadows
        #pragma target 3.0

        sampler2D _MainTex;

        struct Input
        {
            float2 uv_MainTex;
            float4 screenPos;
        };

        fixed4 _TopColor;
        fixed4 _BotColor;
        float _Speed;

        void surf (Input IN, inout SurfaceOutputStandard o)
        {
            float2 screenUV = IN.screenPos.xy / IN.screenPos.w; 
            fixed4 col = tex2D(_MainTex, IN.uv_MainTex) * lerp(_BotColor, _TopColor, screenUV.y);
            o.Albedo = col.rgb * sin(_Time + _Speed)/2;
            o.Alpha = col.a;
        }
        ENDCG
    }
    FallBack "Diffuse"
}
