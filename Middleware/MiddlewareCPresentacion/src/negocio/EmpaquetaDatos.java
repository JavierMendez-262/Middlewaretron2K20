/**
 * EmpaquetaDatos.java
 */
package negocio;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.Socket;
import objetosnegocio.*;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class EmpaquetaDatos implements Runnable {

    private String hostname = "189.192.194.192";
    private int port = 777;
    private Socket cliente;
    private GsonFramer gsonFramer;

    public EmpaquetaDatos() throws IOException {
        cliente = new Socket(hostname, port);
        gsonFramer = new GsonFramer(cliente.getInputStream());
    }

    public void enviaDatos(String name) throws IOException {
        gsonFramer.frameMsg(name, cliente.getOutputStream());
    }

    public void enviaDatos(Alumno alumno) throws IOException {
        Gson gson = gsonFormato(alumno);
    }

    @Override
    public void run() {

    }

    private Gson gsonFormato(Alumno alumno) {
        Gson gson = new Gson();
        gson.toJson(alumno);
        return gson;
    }

    private Alumno gsonAlumnoFormato(String json) {
        Gson gson = new Gson();
        Alumno alumno = (Alumno) gson.fromJson(json, Alumno.class);
        return alumno;
    }
}
