/**
 * EmpaquetaDatos.java
 */
package negocio;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosnegocio.Alumno;
import presentacion.InterfazGrafica;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class EmpaquetaDatos implements Runnable {

    private String hostname = "189.192.194.192";
    private int port = 777;
    private Socket cliente;
    private DelimFramer delimFramer;

    public EmpaquetaDatos() throws IOException {
        cliente = new Socket(hostname, port);
        delimFramer = new DelimFramer(cliente.getInputStream());
    }

    public void enviaDatos(Alumno alumno) throws IOException {
        String delimita = delimitaFormato(alumno);
        byte[] byteArray = delimita.getBytes();

        delimFramer.frameMsg(byteArray, cliente.getOutputStream());
    }

    public void enviaDatos(String name) throws IOException {
        delimFramer.frameMsg(name, cliente.getOutputStream());
    }

    @Override
    public void run() {
        byte[] byteArray = null;
        try {
            byteArray = delimFramer.nextMsg();
        } catch (IOException ex) {
            Logger.getLogger(EmpaquetaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (byteArray != null) {
            Alumno alumno = alumnoFormato(byteArray);
            InterfazGrafica.ventanaEmergente(alumno);
        }
    }

    private String delimitaFormato(Alumno alumno) {
        String formato = alumno.getNombre() + "|"
                + alumno.getApellido() + "|"
                + alumno.getNombreTutor() + "|"
                + alumno.getDireccion() + "|"
                + alumno.getEdad() + "|"
                + alumno.getSemestre() + "|"
                + alumno.getPromedio() + "|"
                + alumno.getCarrera() + "|";
        return formato;
    }

    private Alumno alumnoFormato(byte[] byteArray) {
        Alumno alumno = new Alumno();
        String alumnoCadena = new String(byteArray, StandardCharsets.UTF_8);

        for (int i = 0; alumnoCadena.contains("|"); i++) {
            String datos = alumnoCadena.substring(0, alumnoCadena.indexOf("|")).trim();
            alumnoCadena = alumnoCadena.substring(alumnoCadena.indexOf("|") + 1);

            switch (i) {
                case 0:
                    alumno.setNombre(datos);
                    break;
                case 1:
                    alumno.setApellido(datos);
                    break;
                case 2:
                    alumno.setNombreTutor(datos);
                    break;
                case 3:
                    alumno.setDireccion(datos);
                    break;
                case 4:
                    alumno.setEdad(Integer.parseInt(datos));
                    break;
                case 5:
                    alumno.setSemestre(Integer.parseInt(datos));
                    break;
                case 6:
                    alumno.setPromedio(Float.parseFloat(datos));
                    break;
                case 7:
                    alumno.setCarrera(datos);
                    break;
                default:
                    break;
            }
        }
        return alumno;
    }
}
