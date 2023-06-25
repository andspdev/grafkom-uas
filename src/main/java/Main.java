import Engine.*;
import Engine.Object;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class Main {
    private Window window =
            new Window
    (800,800,"Hello World");
    private ArrayList<Object> objects
            = new ArrayList<>();
    private ArrayList<Object> objectsRectangle
            = new ArrayList<>();

    private ArrayList<Object> objectsPointsControl
            = new ArrayList<>();
    private MouseInput mouseInput;
    int countDegree = 0;
    Projection projection = new Projection(window.getWidth(),window.getHeight());
    Camera camera = new Camera();
    public void init(){
        window.init();
        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        glDepthMask(true);
        glDepthFunc(GL_LEQUAL);
        glDepthRange(0.0f, 0.5f);

        mouseInput = window.getMouseInput();
        camera.setPosition(0,0,1.0f);
        camera.setRotation((float)Math.toRadians(0.0f),(float)Math.toRadians(30.0f));
        //code
//        objects.add(new Object2d(
//            Arrays.asList(
//                //shaderFile lokasi menyesuaikan objectnya
//                new ShaderProgram.ShaderModuleData
//                ("resources/shaders/scene.vert"
//                , GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData
//                ("resources/shaders/scene.frag"
//                , GL_FRAGMENT_SHADER)
//            ),
//            new ArrayList<>(
//                List.of(
//                    new Vector3f(0.0f,0.5f,0.0f),
//                    new Vector3f(-0.5f,-0.5f,0.0f),
//                    new Vector3f(0.5f,-0.5f,0.0f)
//                )
//            ),
//            new Vector4f(0.0f,1.0f,1.0f,1.0f)
//        ));
//        objects.add(new Object(
//            Arrays.asList(
//                //shaderFile lokasi menyesuaikan objectnya
//                new ShaderProgram.ShaderModuleData
//                ("resources/shaders/" +
//                    "sceneWithVerticesColor.vert"
//                        , GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData
//                    ("resources/shaders/" +
//                    "sceneWithVerticesColor.frag"
//                            , GL_FRAGMENT_SHADER)
//        ),
//        new ArrayList<>(
//                List.of(
//                    new Vector3f(0.0f,0.5f,0.0f),
//                    new Vector3f(-0.5f,-0.5f,0.0f),
//                    new Vector3f(0.5f,-0.5f,0.0f)
//                )
//            ),
//        new ArrayList<>(
//            List.of(
//                new Vector3f(1.0f,0.0f,0.0f),
//                new Vector3f(0.0f,1.0f,0.0f),
//                new Vector3f(0.0f,0.0f,1.0f)
//            )
//        )
//        ));
//        objectsRectangle.add(new Rectangle(
//            Arrays.asList(
//                //shaderFile lokasi menyesuaikan objectnya
//                new ShaderProgram.ShaderModuleData
//                ("resources/shaders/scene.vert"
//                , GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData
//                ("resources/shaders/scene.frag"
//                , GL_FRAGMENT_SHADER)
//            ),
//            new ArrayList<>(
//                List.of(
//                    new Vector3f(0.0f,0.0f,0.0f),
//                    new Vector3f(0.5f,0.0f,0.0f),
//                    new Vector3f(0.0f,0.5f,0.0f),
//                    new Vector3f( 0.5f,0.5f,0.0f)
//                )
//            ),
//            new Vector4f(0.0f,1.0f,1.0f,1.0f),
//            Arrays.asList(0,1,2,1,2,3)
//
//        ));
//        objectsPointsControl.add(new Object(
//            Arrays.asList(
//                //shaderFile lokasi menyesuaikan objectnya
//                new ShaderProgram.ShaderModuleData
//                ("resources/shaders/scene.vert"
//                , GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData
//                ("resources/shaders/scene.frag"
//                , GL_FRAGMENT_SHADER)
//            ),
//            new ArrayList<>(),
//            new Vector4f(0.0f,1.0f,1.0f,1.0f)
//        ));

        Vector4f warnaMejaTv = new Vector4f(112/255f, 70/255f, 35/255f, 1f);
//        Vector4f warnaLantai = new Vector4f(150/255f, 133/255f, 112/255f, 1f);
        Vector4f warnaTembok = new Vector4f(252/255f, 238/255f, 217/255f,1.f);
        Vector4f warnaTv = new Vector4f(3/255f, 3/255f, 3/255f,1.f);
        Vector4f warnaSofa = new Vector4f(79/255f, 110/255f, 126/255f, 1.f);


        List<ShaderProgram.ShaderModuleData> shader = Arrays.asList(
                //shaderFile lokasi menyesuaikan objectnya
                new ShaderProgram.ShaderModuleData
                        ("resources/shaders/scene.vert"
                                , GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData
                        ("resources/shaders/scene.frag"
                                , GL_FRAGMENT_SHADER)
        );

        // Tanah
        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaMejaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Tanah/Tanah.obj"

        ));


        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTembok),
                new ArrayList<>(),
                "resources/Aset/ABlend/Env/Bangunan Dalam/a.obj"

        ));
        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTembok),
                new ArrayList<>(),
                "resources/Aset/ABlend/Env/Bangunan Dalam/b.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTembok),
                new ArrayList<>(),
                "resources/Aset/ABlend/Env/Bangunan Dalam/c.obj"

        ));
        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTembok),
                new ArrayList<>(),
                "resources/Aset/ABlend/Env/Bangunan Dalam/d.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTembok),
                new ArrayList<>(),
                "resources/Aset/ABlend/Env/Bangunan Dalam/e.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTembok),
                new ArrayList<>(),
                "resources/Aset/ABlend/Env/Bangunan Dalam/f.obj"

        ));



        // Bangunan Luar
        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(186/255f, 164/255f, 151/255f, 1f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Env/Bangunan Luar/0.obj"

        ));
        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(186/255f, 164/255f, 151/255f, 1f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Env/Bangunan Luar/0-5.obj"

        ));
        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTembok),
                new ArrayList<>(),
                "resources/Aset/ABlend/Env/Bangunan Luar/a.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTembok),
                new ArrayList<>(),
                "resources/Aset/ABlend/Env/Bangunan Luar/b.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTembok),
                new ArrayList<>(),
                "resources/Aset/ABlend/Env/Bangunan Luar/c.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTembok),
                new ArrayList<>(),
                "resources/Aset/ABlend/Env/Bangunan Luar/d.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTembok),
                new ArrayList<>(),
                "resources/Aset/ABlend/Env/Bangunan Luar/e.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTembok),
                new ArrayList<>(),
                "resources/Aset/ABlend/Env/Bangunan Luar/f.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTembok),
                new ArrayList<>(),
                "resources/Aset/ABlend/Env/Bangunan Luar/g.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTembok),
                new ArrayList<>(),
                "resources/Aset/ABlend/Env/Bangunan Luar/h.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTembok),
                new ArrayList<>(),
                "resources/Aset/ABlend/Env/Bangunan Luar/i.obj"

        ));


        // Lemari Kayu
        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaMejaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Lemari Kayu/Lemari Kayu.obj"

        ));

        // Lemari Pakaian
        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaMejaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Lemari Pakaian/Lemari Pakaian.obj"

        ));

        // Meja Gaming
        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja Gaming/CPU.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(153/255f, 0/255f,28/255f,1.0f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja Gaming/headset.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja Gaming/keyboard.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja Gaming/monitor.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(153/255f, 0/255f,28/255f,1.0f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja Gaming/mouse.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(0, 0, 0,1.0f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja Gaming/mousepad.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(181/255f, 181/255f, 181/255f,1.0f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja Gaming/table.obj"

        ));

        // Meja Makan

//        objects.add(new Sphere(
//                shader,
//                new ArrayList<>(),
//                new Vector4f(17/255f, 114/255f, 54/255f,1.0f),
//                new ArrayList<>(),
//                "resources/Aset/ABlend/Meja Makan/Bunga.obj"
//
//        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaMejaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja Makan/Kursi.obj"

        ));


        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaMejaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja Makan/Kursi1.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaMejaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja Makan/Kursi2.obj"

        ));


        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaMejaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja Makan/Kursi3.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaMejaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja Makan/Kursi4.obj"
        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaMejaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja Makan/Kursi5.obj"
        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaMejaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja Makan/Kursi6.obj"
        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaMejaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja Makan/Kursi7.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(181/255f, 181/255f,181/255f, 1f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja Makan/Meja Besar.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaMejaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja Makan/Meja 1.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(163/255f, 160/255f, 52/255f,1.0f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja Makan/Tempat Minum 1.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(163/255f, 160/255f, 52/255f,1.0f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja Makan/Tempat Minum 2.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(0, 0, 0, 1f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja Makan/Vas.obj"

        ));

        // Meja TV

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaMejaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja TV/Meja TV.obj"

        ));

        // Sofa

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(1.0f,1.0f,1.0f,1.0f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Sofa/Bantal Sofa.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaSofa),
                new ArrayList<>(),
                "resources/Aset/ABlend/Sofa/Sofa.obj"

        ));

        // Tempat Tidur

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(180/255f, 162/255f, 105/255f, 1.0f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Tempat Tdr/Bantal.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(180/255f, 162/255f, 105/255f,1.0f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Tempat Tdr/Kasur.obj"

        ));


        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Tempat Tdr/Penyangga.obj"

        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(94/255f, 48/255f, 22/255f, 1.0f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Tempat Tdr/Seprai.obj"

        ));

        // TV

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/TV/TV.obj"

        ));


        // Kulkas
        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Kulkas/Kulkas.obj"
        ));

        // Kitchen Set
        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(252/255f, 228/255f, 191/255f, 1.f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Kitchen Set/Kitchen Set.obj"
        ));


        // Lampu Keluarga
        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(252/255f, 228/255f, 191/255f, 1.f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Lampu Keluarga/Cartridge.obj"
        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaMejaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Lampu Keluarga/Tiang Lampu.obj"
        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(0, 0, 0, 1f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Lampu Keluarga/Kaki Lampu.obj"
        ));


        // Meja Kamar
        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaMejaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Meja Kamar/Meja Kamar.obj"
        ));




        // Lampu Kamar
        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(252/255f, 228/255f, 191/255f, 1.f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Lampu Kamar/Cartridge.obj"
        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaMejaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Lampu Kamar/Tiang.obj"
        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(0, 0, 0, 1f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Lampu Kamar/Kaki.obj"
        ));







        // Lampu Metal
        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(94/255f, 88/255f, 90/255f, 1f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Lampu Metal/Metal.obj"
        ));


        // Lampu Metal 1

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(94/255f, 88/255f, 90/255f, 1f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Lampu Metal 1/Metal.obj"
        ));


        // Dokument
        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(218/255f, 186/255f, 129/255f, 1f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Dokument/Dokumen.obj"
        ));


        // Setan 1
        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(1, 1, 1, 1f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Setan 1/Badan.obj"
        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(0, 0, 0, 1f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Setan 1/Pakaian.obj"
        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(0, 0, 0, 1f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Setan 1/Rambut.obj"
        ));




        // Batu Nisan
        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(237/255f, 237/255f, 237/255f, 1f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Batu Nisan/Nisan 1.obj"
        ));


        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(237/255f, 237/255f, 237/255f, 1f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Batu Nisan/Nisan 2.obj"
        ));


        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(237/255f, 237/255f, 237/255f, 1f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Batu Nisan/Nisan 3.obj"
        ));



        // Welcome Sign
        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaMejaTv),
                new ArrayList<>(),
                "resources/Aset/ABlend/Welcome Sign/Tiang.obj"
        ));


        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(20/255f, 76/255f, 11/255f, 1f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Welcome Sign/Rumput.obj"
        ));

        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(94/255f, 88/255f, 90/255f, 1f),
                new ArrayList<>(),
                "resources/Aset/ABlend/Welcome Sign/Lampu.obj"
        ));


        objects.add(new Sphere(
                shader,
                new ArrayList<>(),
                new Vector4f(warnaTembok),
                new ArrayList<>(),
                "resources/Aset/ABlend/Atap/Atap.obj"
        ));





        // Penanda Posisi Lampu
//        objects.add(new Sphere(shader,
//            new ArrayList<>(),
//            new Vector4f(1.0f,1.0f,1.0f,1.0f),
//            Arrays.asList(20f, 4f, 16.5f),
//            1f, 1f,1f,
//        new ArrayList<>()));
    }


    public void input(){
        float move = 0.1f;
        if (window.isKeyPressed(GLFW_KEY_LEFT_SHIFT)) {
            camera.moveForward(move);
        }
        if (window.isKeyPressed(GLFW_KEY_LEFT_CONTROL)) {
            camera.moveBackwards(move);
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            camera.moveLeft(move);
        }
        if (window.isKeyPressed(GLFW_KEY_D)) {
            camera.moveRight(move);
        }
        if (window.isKeyPressed(GLFW_KEY_W)){
            camera.moveUp(move);
        }
        if (window.isKeyPressed(GLFW_KEY_S)){
            camera.moveDown(move);
        }
        if (window.isKeyPressed(GLFW_KEY_I)){
            camera.addRotation(move/10,0f);
        }
        if (window.isKeyPressed(GLFW_KEY_K)){
            camera.addRotation(-move/10,0f);
        }
        if (window.isKeyPressed(GLFW_KEY_J)){
            camera.addRotation(0,-move/10);
        }
        if (window.isKeyPressed(GLFW_KEY_L)){
            camera.addRotation(0,move/10);
        }

        if(mouseInput.isLeftButtonPressed())
        {
            Vector2f displayVec = window.getMouseInput().getDisplVec();
            camera.addRotation((float)Math.toRadians(displayVec.x * 0.2f),
                    (float)Math.toRadians(displayVec.y * 0.2f));
        }

        if(mouseInput.isRightButtonPressed()){
            System.out.println(window.getMouseInput().getCurrentPos());
        }


        if(window.getMouseInput().getScroll().y != 0){
            projection.setFOV(projection.getFOV()- (window.getMouseInput().getScroll().y*0.01f));
            window.getMouseInput().setScroll(new Vector2f());
        }
        if (window.isKeyPressed(GLFW_KEY_1)) {
            objects.get(0).translateObject(0.0f,1.0f,0.0f);

        }

        if (window.isKeyPressed(GLFW_KEY_2)){

        }
    }
    public void loop(){
        while (window.isOpen()) {
            window.update();
            glClearColor(0.0f,
                    0.0f, 0.0f,
                    0.0f);
            GL.createCapabilities();
            input();

            //code
            for(Object object: objects){
                object.draw(camera,projection);
            }
//            for(Object object: objectsRectangle){
//                object.draw();
//            }
//            for(Object object: objectsPointsControl){
//                object.drawLine();
//            }

            // Restore state
            glDisableVertexAttribArray(0);

            // Poll for window events.
            // The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }
    public void run() {

        init();
        loop();

        // Terminate GLFW and
        // free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
    public static void main(String[] args) {
        new Main().run();
    }
}
