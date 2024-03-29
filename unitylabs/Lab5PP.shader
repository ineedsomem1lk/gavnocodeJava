Shader "Custom/Lab5PP"
{
	Properties
	{
		_MainTex("Texture", 2D) = "white" {}
		_IntensityX("Intensity", Range(0,10000)) = 1000.0
		_IntensityY("Intensity", Range(0,10000)) = 1000.0
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
					float2 uv : TEXCOORD0;
					float4 vertex : SV_POSITION;
				};

				v2f vert(appdata v)
				{
					v2f o;
					o.vertex = UnityObjectToClipPos(v.vertex);
					o.uv = v.uv;
					return o;
				}

				float random(float2 x)
				{
					return frac(sin(dot(x.xy ,float2(12.9898,78.233))) * 43758.5453);  //������� �������,��� ���� ��� ����� ���� ���� ����������				
				}

				sampler2D _MainTex;
				float _IntensityX;
				float _IntensityY;

				float4 frag(v2f i) : SV_Target
				{
					float iBx = (i.uv.x * _IntensityX) + random(i.uv) * 5.0 * 2.0 - 5.0; //��������� ���� ��������� �� �� X
					float iBy = (i.uv.y * _IntensityY) + random(float2(i.uv.y, i.uv.x)) * 5.0 * 2.0 - 5.0; //��������� ���� ��������� �� �� Y
					return tex2D(_MainTex, float2(iBx / _IntensityX, iBy / _IntensityY)); 
				}

				ENDCG
			}
		}
}