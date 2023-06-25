package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

public class Kotak extends Object{
    float centerX,centerY,centerZ;
    float radiusX, radiusY, radiusZ;
    float rilRadiusX, rilRadiusY, rilRadiusZ;
    public Kotak(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices,
                 Vector4f color, float centerX, float centerY, float centerZ,
                 float radiusX, float radiusY, float radiusZ) {
        super(shaderModuleDataList, vertices, color);
        this.centerX = centerX;
        this.centerY = centerY;
        this.centerZ = centerZ;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.radiusZ = radiusZ;

    }



    public void createKotak(){
        vertices.clear();
        rilRadiusX = radiusX/2;
        rilRadiusY = radiusY/2;
        rilRadiusZ = radiusZ/2;

        vertices.add(new Vector3f(centerX - rilRadiusX, centerY - rilRadiusY, centerZ - rilRadiusZ));
        vertices.add(new Vector3f(centerX + rilRadiusX, centerY - rilRadiusY, centerZ - rilRadiusZ));
    }
}
