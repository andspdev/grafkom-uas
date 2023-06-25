package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

public class Sphere extends Circle{
    float radiusZ;
    int stackCount;
    int sectorCount;
    List<Vector3f> normal;
    int nbo,pick;
    int tekstur;
    String path;


    public Vector3f[] _pointLightPositions = new Vector3f[5];






    public Sphere(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Vector3f> normal,
                  String path) {
        super(shaderModuleDataList, vertices, color);
        this.path = path;
        this.normal = normal;
        this.vertices = vertices;

        loadObject();
        setupVAOVBO();
    }

    public Sphere(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color,
                  List<Float> centerPoint, Float radiusX, Float radiusY,
                  float radiusZ,List<Vector3f> normal) {
        super(shaderModuleDataList, vertices, color, centerPoint, radiusX, radiusY);
        this.radiusZ = radiusZ;
        this.normal = normal;
        createBox();
        setupVAOVBO();

    }

    public void loadObject(){
        System.out.println("Code done");
        vertices.clear();
        normal = new ArrayList<>();
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();

        Model n = new Model();

        try {
            n = ObjLoader.loadModel(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }




        for (Face face : n.faces){
            Vector3f n1 = n.normals.get((int) face.normal.x - 1);
            normal.add(n1);
            Vector3f v1 = n.vertices.get((int) face.vertex.x - 1);
            vertices.add(v1);
            Vector3f n2 = n.normals.get((int) face.normal.y - 1);
            normal.add(n2);
            Vector3f v2 = n.vertices.get((int) face.vertex.y - 1);
            vertices.add(v2);

            Vector3f n3 = n.normals.get((int) face.normal.z - 1);
            normal.add(n3);
            Vector3f v3 = n.vertices.get((int) face.vertex.z - 1);
            vertices.add(v3);

        }

    }


    public void createBox(){
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();
        //TITIK 1
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 2
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 3
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 4
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 5
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 6
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 7
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 8
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();

        vertices.clear();
        //kotak yg sisi belakang
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));
        //kotak yg sisi depan
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(7));
        //kotak yg sisi kiri
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(3));
        //kotak yg sisi kanan
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(2));
        //kotak yg sisi atas
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(4));
        //kotak yg sisi bawah
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(6));

        normal = new ArrayList<>(Arrays.asList(
                //belakang
                new Vector3f(0.0f,0.0f,-1.0f),
                new Vector3f(0.0f,0.0f,-1.0f),
                new Vector3f(0.0f,0.0f,-1.0f),
                new Vector3f(0.0f,0.0f,-1.0f),
                //depan
                new Vector3f(0.0f,0.0f,1.0f),
                new Vector3f(0.0f,0.0f,1.0f),
                new Vector3f(0.0f,0.0f,1.0f),
                new Vector3f(0.0f,0.0f,1.0f),
                //kiri
                new Vector3f(-1.0f,0.0f,0.0f),
                new Vector3f(-1.0f,0.0f,0.0f),
                new Vector3f(-1.0f,0.0f,0.0f),
                new Vector3f(-1.0f,0.0f,0.0f),
                //kanan
                new Vector3f(1.0f,0.0f,0.0f),
                new Vector3f(1.0f,0.0f,0.0f),
                new Vector3f(1.0f,0.0f,0.0f),
                new Vector3f(1.0f,0.0f,0.0f),
                //atas
                new Vector3f(0.0f,1.0f,0.0f),
                new Vector3f(0.0f,1.0f,0.0f),
                new Vector3f(0.0f,1.0f,0.0f),
                new Vector3f(0.0f,1.0f,0.0f),
                //bawah
                new Vector3f(0.0f,-1.0f,0.0f),
                new Vector3f(0.0f,-1.0f,0.0f),
                new Vector3f(0.0f,-1.0f,0.0f),
                new Vector3f(0.0f,-1.0f,0.0f)
        ));
    }
    public void createBoxVertices()
    {
        System.out.println("code");
        vertices.clear();
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();
        //Titik 1 kiri atas belakang
        temp.x = centerPoint.get(0) - radiusX / 2;
        temp.y = centerPoint.get(1) + radiusY / 2;
        temp.z = centerPoint.get(2) - radiusZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 2 kiri bawah belakang
        temp.x = centerPoint.get(0) - radiusX / 2;
        temp.y = centerPoint.get(1) - radiusY / 2;
        temp.z = centerPoint.get(2) - radiusZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 3 kanan bawah belakang
        temp.x = centerPoint.get(0) + radiusX / 2;
        temp.y = centerPoint.get(1) - radiusY / 2;
        temp.z = centerPoint.get(2) - radiusZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 4 kanan atas belakang
        temp.x = centerPoint.get(0) + radiusX / 2;
        temp.y = centerPoint.get(1) + radiusY / 2;
        temp.z = centerPoint.get(2) - radiusZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 5 kiri atas depan
        temp.x = centerPoint.get(0) - radiusX / 2;
        temp.y = centerPoint.get(1) + radiusY / 2;
        temp.z = centerPoint.get(2) + radiusZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 6 kiri bawah depan
        temp.x = centerPoint.get(0) - radiusX / 2;
        temp.y = centerPoint.get(1) - radiusY / 2;
        temp.z = centerPoint.get(2) + radiusZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 7 kanan bawah depan
        temp.x = centerPoint.get(0) + radiusX / 2;
        temp.y = centerPoint.get(1) - radiusY / 2;
        temp.z = centerPoint.get(2) + radiusZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 8 kanan atas depan
        temp.x = centerPoint.get(0) + radiusX / 2;
        temp.y = centerPoint.get(1) + radiusY / 2;
        temp.z = centerPoint.get(2) + radiusZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();

        //kotak belakang
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(2));

        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(0));
        //kotak depan
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));

        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(4));
        //kotak samping kiri
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(4));

        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(4));
        //kotak samping kanan
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(2));

        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(7));
        //kotak bawah
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));

        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(1));
        //kotak atas
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(7));

        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(3));

        normal = new ArrayList<>(Arrays.asList(
                new Vector3f(0.0f,  0.0f, -1.0f),
                new Vector3f(0.0f,  0.0f, -1.0f),
                new Vector3f(0.0f,  0.0f, -1.0f),
                new Vector3f(0.0f,  0.0f, -1.0f),
                new Vector3f(0.0f,  0.0f, -1.0f),
                new Vector3f(0.0f,  0.0f, -1.0f),

                new Vector3f(0.0f,  0.0f,  1.0f),
                new Vector3f(0.0f,  0.0f,  1.0f),
                new Vector3f(0.0f,  0.0f,  1.0f),
                new Vector3f(0.0f,  0.0f,  1.0f),
                new Vector3f(0.0f,  0.0f,  1.0f),
                new Vector3f(0.0f,  0.0f,  1.0f),

                new Vector3f(-1.0f,  0.0f,  0.0f),
                new Vector3f(-1.0f,  0.0f,  0.0f),
                new Vector3f(-1.0f,  0.0f,  0.0f),
                new Vector3f(-1.0f,  0.0f,  0.0f),
                new Vector3f(-1.0f,  0.0f,  0.0f),
                new Vector3f(-1.0f,  0.0f,  0.0f),

                new Vector3f(1.0f,  0.0f,  0.0f),
                new Vector3f(1.0f,  0.0f,  0.0f),
                new Vector3f(1.0f,  0.0f,  0.0f),
                new Vector3f(1.0f,  0.0f,  0.0f),
                new Vector3f(1.0f,  0.0f,  0.0f),
                new Vector3f(1.0f,  0.0f,  0.0f),

                new Vector3f(0.0f, -1.0f,  0.0f),
                new Vector3f(0.0f, -1.0f,  0.0f),
                new Vector3f( 0.0f, -1.0f,  0.0f),
                new Vector3f(0.0f, -1.0f,  0.0f),
                new Vector3f(0.0f, -1.0f,  0.0f),
                new Vector3f(0.0f, -1.0f,  0.0f),

                new Vector3f(0.0f,  1.0f,  0.0f),
                new Vector3f(0.0f,  1.0f,  0.0f),
                new Vector3f(0.0f,  1.0f,  0.0f),
                new Vector3f(0.0f,  1.0f,  0.0f),
                new Vector3f(0.0f,  1.0f,  0.0f),
                new Vector3f(0.0f,  1.0f,  0.0f)
        ));
    }
    public void setupVAOVBO(){
        super.setupVAOVBO();

        //set nbo
        nbo = glGenBuffers();


        glBindBuffer(GL_ARRAY_BUFFER, nbo);
        glBufferData(GL_ARRAY_BUFFER,
                Utils.listoFloat(normal),
                GL_STATIC_DRAW);

//        uniformsMap.createUniform("lightColor");
//        uniformsMap.createUniform("lightPos");

    }

    public void drawSetup(Camera camera, Projection projection){
        super.drawSetup(camera,projection);

        // Bind VBO
        glEnableVertexAttribArray(1);

        glBindBuffer(GL_ARRAY_BUFFER, nbo);
        glVertexAttribPointer(1, 3,
                GL_FLOAT,
                false,
                0, 0);


        //directional Light
        uniformsMap.setUniform("dirLight.direction", new Vector3f(-1f,10f,0f));
        uniformsMap.setUniform("dirLight.ambient", new Vector3f(0.0f,0.0f,0.0f ));
        uniformsMap.setUniform("dirLight.diffuse", new Vector3f(0.2f,0.2f,0.2f));
        uniformsMap.setUniform("dirLight.specular", new Vector3f(0,0,0));



        //posisi pointLight
        this._pointLightPositions = new Vector3f[] {
                // Setan
                new Vector3f(-5.7f, 4.2f, 8f),

                // Ruuang Keluarga
                new Vector3f(6.5f, 3.5f, 7f),

                // Deket Pintu Masuk
                new Vector3f(8.5f, 4.6f, 4.5f),

                // Kamar
                new Vector3f(22.8f, 3.6f, -0.5f),

                // Dekat Meja Komputer
                new Vector3f(4.5f, 4.5f, -8f),


                // Kamar belakang
                new Vector3f(20.8f, 5.6f, -8.5f),


                // Kamar tengah
                new Vector3f(10.5f, 5.6f, -5.5f),


                // Meja Repat
                new Vector3f(-5.5f, 5.6f, -11.5f),


                // Welcome Sign
                new Vector3f(20f, 4f, 16.5f)
        };


        // Setan 1
        uniformsMap.setUniform("pointLights[0].position", _pointLightPositions[0]);
        uniformsMap.setUniform("pointLights[0].ambient", new Vector3f(1f,1f,1f));
        uniformsMap.setUniform("pointLights[0].diffuse", new Vector3f(1f,1f,1f));
        uniformsMap.setUniform("pointLights[0].specular", new Vector3f(1f,1f,1f));
        uniformsMap.setUniform("pointLights[0].constant",1.0f );
        uniformsMap.setUniform("pointLights[0].linear", 5f);
        uniformsMap.setUniform("pointLights[0].quadratic", 5f);


        // Ruang Keluarga
        uniformsMap.setUniform("pointLights[1].position",_pointLightPositions[1]);
        uniformsMap.setUniform("pointLights[1].ambient", new Vector3f(1f,.95f,.75f));
        uniformsMap.setUniform("pointLights[1].diffuse", new Vector3f(1f,.95f,.75f));
        uniformsMap.setUniform("pointLights[1].specular", new Vector3f(1f,.95f,.75f));
        uniformsMap.setUniform("pointLights[1].constant",1.0f);
        uniformsMap.setUniform("pointLights[1].linear", 3f);
        uniformsMap.setUniform("pointLights[1].quadratic", 3f);


        // Deket Pintu Masuk
        uniformsMap.setUniform("pointLights[2].position",_pointLightPositions[2]);
        uniformsMap.setUniform("pointLights[2].ambient", new Vector3f(1f,.95f,.75f));
        uniformsMap.setUniform("pointLights[2].diffuse", new Vector3f(1f,.95f,.75f));
        uniformsMap.setUniform("pointLights[2].specular", new Vector3f(1f,.95f,.75f));
        uniformsMap.setUniform("pointLights[2].constant",1.0f);
        uniformsMap.setUniform("pointLights[2].linear", 1.2f);
        uniformsMap.setUniform("pointLights[2].quadratic", 1.2f);


        // Kamar
        uniformsMap.setUniform("pointLights[3].position",_pointLightPositions[3]);
        uniformsMap.setUniform("pointLights[3].ambient", new Vector3f(1f,.95f,.75f));
        uniformsMap.setUniform("pointLights[3].diffuse", new Vector3f(1f,.95f,.75f));
        uniformsMap.setUniform("pointLights[3].specular", new Vector3f(1f,.95f,.75f));
        uniformsMap.setUniform("pointLights[3].constant",1.0f);
        uniformsMap.setUniform("pointLights[3].linear", 1.8f);
        uniformsMap.setUniform("pointLights[3].quadratic", 1.8f);


        // Deket Meja Komputer
        uniformsMap.setUniform("pointLights[4].position",_pointLightPositions[4]);
        uniformsMap.setUniform("pointLights[4].ambient",new Vector3f(1f,.95f,.75f));
        uniformsMap.setUniform("pointLights[4].diffuse", new Vector3f(1f,.95f,.75f));
        uniformsMap.setUniform("pointLights[4].specular", new Vector3f(1f,.95f,.75f));
        uniformsMap.setUniform("pointLights[4].constant",1.0f);
        uniformsMap.setUniform("pointLights[4].linear", 1.2f);
        uniformsMap.setUniform("pointLights[4].quadratic", 1.2f);



        // Kamar Belakang
        uniformsMap.setUniform("pointLights[5].position",_pointLightPositions[5]);
        uniformsMap.setUniform("pointLights[5].ambient", new Vector3f(1f,.95f,.75f));
        uniformsMap.setUniform("pointLights[5].diffuse", new Vector3f(0, 0, 0));
        uniformsMap.setUniform("pointLights[5].specular", new Vector3f(0, 0, 0));
        uniformsMap.setUniform("pointLights[5].constant",1.0f);
        uniformsMap.setUniform("pointLights[5].linear", .35f);
        uniformsMap.setUniform("pointLights[5].quadratic", .35f);



        // Kamar Tengah
        uniformsMap.setUniform("pointLights[6].position",_pointLightPositions[6]);
        uniformsMap.setUniform("pointLights[6].ambient", new Vector3f(1f,.95f,.75f));
        uniformsMap.setUniform("pointLights[6].diffuse", new Vector3f(0, 0, 0));
        uniformsMap.setUniform("pointLights[6].specular", new Vector3f(0, 0, 0));
        uniformsMap.setUniform("pointLights[6].constant",1.0f);
        uniformsMap.setUniform("pointLights[6].linear", .5f);
        uniformsMap.setUniform("pointLights[6].quadratic", .5f);



        // Meja Rapat
        uniformsMap.setUniform("pointLights[7].position",_pointLightPositions[7]);
        uniformsMap.setUniform("pointLights[7].ambient", new Vector3f(1f,.95f,.75f));
        uniformsMap.setUniform("pointLights[7].diffuse", new Vector3f(0, 0, 0));
        uniformsMap.setUniform("pointLights[7].specular", new Vector3f(0, 0, 0));
        uniformsMap.setUniform("pointLights[7].constant",1.0f);
        uniformsMap.setUniform("pointLights[7].linear", 1.45f);
        uniformsMap.setUniform("pointLights[7].quadratic", 1.45f);



        // Welcome Sign
        uniformsMap.setUniform("pointLights[8].position",_pointLightPositions[8]);
        uniformsMap.setUniform("pointLights[8].ambient", new Vector3f(1f,.95f,.75f));
        uniformsMap.setUniform("pointLights[8].diffuse", new Vector3f(1, 1, 1));
        uniformsMap.setUniform("pointLights[8].specular", new Vector3f(0, 0, 0));
        uniformsMap.setUniform("pointLights[8].constant",1.0f);
        uniformsMap.setUniform("pointLights[8].linear", .15f);
        uniformsMap.setUniform("pointLights[8].quadratic", .15f);



        //spotlight
        uniformsMap.setUniform("spotLight.position", camera.getPosition());
        uniformsMap.setUniform("spotLight.direction", camera.getDirection());
        uniformsMap.setUniform("spotLight.ambient",new Vector3f(0.0f,0.0f,0.0f));
        uniformsMap.setUniform("spotLight.diffuse",new Vector3f(1.0f,1.0f,1.0f));
        uniformsMap.setUniform("spotLight.specular",new Vector3f(1.0f,1.0f,1.0f));
        uniformsMap.setUniform("spotLight.constant",1.0f);
        uniformsMap.setUniform("spotLight.linear",0.09f);
        uniformsMap.setUniform("spotLight.quadratic",0.07f);
        uniformsMap.setUniform("spotLight.cutOff",(float)Math.cos(Math.toRadians(8f)));
        uniformsMap.setUniform("spotLight.outerCutOff",(float)Math.cos(Math.toRadians(8f)));


        uniformsMap.setUniform("viewPos",camera.getPosition());
    }



//    public void draw(){
//        drawSetup();
//        glLineWidth(2); //ketebalan garis
//        glPointSize(2); //besar kecil vertex
//        glDrawArrays(GL_LINE_STRIP,
//                0,
//                vertices.size());
//    }

    public void createSphere(){
        float pi = (float)Math.PI;

        float sectorStep = 2 * (float)Math.PI / sectorCount;
        float stackStep = (float)Math.PI / stackCount;
        float sectorAngle, StackAngle, x, y, z;

        for (int i = 0; i <= stackCount; ++i)
        {
            StackAngle = pi / 2 - i * stackStep;
            x = radiusX * (float)Math.cos(StackAngle);
            y = radiusY * (float)Math.cos(StackAngle);
            z = radiusZ * (float)Math.sin(StackAngle);

            for (int j = 0; j <= sectorCount; ++j)
            {
                sectorAngle = j * sectorStep;
                Vector3f temp_vector = new Vector3f();
                temp_vector.x = centerPoint.get(0) + x * (float)Math.cos(sectorAngle);
                temp_vector.y = centerPoint.get(1) + y * (float)Math.sin(sectorAngle);
                temp_vector.z = centerPoint.get(2) + z;
                vertices.add(temp_vector);
            }
        }
    }



}
