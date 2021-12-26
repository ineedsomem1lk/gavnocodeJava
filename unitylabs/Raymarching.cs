using UnityEngine;

[ExecuteInEditMode]
[RequireComponent(typeof(Camera))]
[AddComponentMenu("Rendering/Raymarching")]

public class Raymarching : SceneViewFilter
{
    [SerializeField]
    private Shader CoreShader;

    public GameObject Cube;
    public GameObject Sphere;
    public GameObject Sphere2;
    public GameObject Torus;
    public GameObject Torus2;

    private Material _MainMaterial;
    public Material MainMaterial
    {
        get
        {
            if (!_MainMaterial && CoreShader)
            {
                _MainMaterial = new Material(CoreShader);
                _MainMaterial.hideFlags = HideFlags.HideAndDontSave;
            }
            return _MainMaterial;
        }
    }

    private Camera _CurrentCamera;
    public Camera CurrentCamera
    {
        get
        {
            if (!_CurrentCamera)
                _CurrentCamera = GetComponent<Camera>();

            return _CurrentCamera;
        }
    }

    private Matrix4x4 GetFrustumCorners(Camera cam)
    {
        float camFov = cam.fieldOfView;
        float camAspect = cam.aspect;
        Matrix4x4 frustumCorners = Matrix4x4.identity;
        float fovWHalf = camFov * 0.5f;
        float tan_fov = Mathf.Tan(fovWHalf * Mathf.Deg2Rad);
        Vector3 toRight = Vector3.right * tan_fov * camAspect;
        Vector3 toTop = Vector3.up * tan_fov;
        Vector3 topLeft = (-Vector3.forward - toRight + toTop);
        Vector3 topRight = (-Vector3.forward + toRight + toTop);
        Vector3 bottomRight = (-Vector3.forward + toRight - toTop);
        Vector3 bottomLeft = (-Vector3.forward - toRight - toTop);

        frustumCorners.SetRow(0, topLeft);
        frustumCorners.SetRow(1, topRight);
        frustumCorners.SetRow(2, bottomRight);
        frustumCorners.SetRow(3, bottomLeft);

        return frustumCorners;
    }

    static void CustomGraphicsBlit(RenderTexture source, RenderTexture dest, Material fxMaterial, int passNr)
    {
        RenderTexture.active = dest;
        fxMaterial.SetTexture("_MainTex", source);
        GL.PushMatrix();
        GL.LoadOrtho();

        fxMaterial.SetPass(passNr);
        GL.Begin(GL.QUADS);
        GL.MultiTexCoord2(0, 0.0f, 0.0f);
        GL.Vertex3(0.0f, 0.0f, 3.0f); // BL
        GL.MultiTexCoord2(0, 1.0f, 0.0f);
        GL.Vertex3(1.0f, 0.0f, 2.0f); // BR
        GL.MultiTexCoord2(0, 1.0f, 1.0f);
        GL.Vertex3(1.0f, 1.0f, 1.0f); // TR
        GL.MultiTexCoord2(0, 0.0f, 1.0f);
        GL.Vertex3(0.0f, 1.0f, 0.0f); // TL
        GL.End();
        GL.PopMatrix();
    }



    [ImageEffectOpaque]
    void OnRenderImage(RenderTexture source, RenderTexture destination)
    {
        MainMaterial.SetMatrix("_FrustumCornersES", GetFrustumCorners(CurrentCamera));
        MainMaterial.SetMatrix("_CameraInvViewMatrix", CurrentCamera.cameraToWorldMatrix);
        MainMaterial.SetVector("_CameraWS", CurrentCamera.transform.position);

        MainMaterial.SetVector("_Sphere", Sphere.transform.position);
        MainMaterial.SetVector("_Sphere2", Sphere.transform.position);
        MainMaterial.SetVector("_Cube", Cube.transform.position);
        MainMaterial.SetVector("_Torus", Torus.transform.position);
        MainMaterial.SetVector("_Torus2", Torus2.transform.position);

        CustomGraphicsBlit(source, destination, MainMaterial, 0);
    }

}