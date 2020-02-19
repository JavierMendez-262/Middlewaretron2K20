/**
 * RecepcionDatos.java
 */
package datos;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import objetosnegocio.*;
import presentacion.InterfazGrafica;
import sockets.ClientSocketData;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class RecepcionDatos implements Runnable {

    private ClientSocketData cliente;

    public RecepcionDatos(ClientSocketData cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
            String datosAlumno = (String) cliente.waitData();
            Alumno estudiante = recibeDatos(datosAlumno);
            String estudianteFormato = "Nombre: " + estudiante.getNombre() + "\n"
                    + "Apellido: " + estudiante.getApellido() + "\n"
                    + "Nombre de tutor: " + estudiante.getNombreTutor() + "\n"
                    + "Dirección: " + estudiante.getDireccion() + "\n"
                    + "Edad: " + estudiante.getEdad() + "\n"
                    + "Semestre: " + estudiante.getSemestre() + "\n"
                    + "Promedio: " + estudiante.getPromedio() + "\n"
                    + "Carrera: " + estudiante.getCarrera();
            InterfazGrafica.ventanaEmergente(estudianteFormato);
        } catch (IOException ex) {
            System.out.println("Error en la espera de datos.");
        }
    }

    public Alumno recibeDatos(String datosAlumno) {
        Alumno alumno = new Alumno();
        String datosAlumnoAux = datosAlumno;

        for (int i = 0; datosAlumnoAux.contains("|"); i++) {
            String datos = datosAlumnoAux.substring(0, datosAlumnoAux.indexOf("|")).trim();
            datosAlumnoAux = datosAlumnoAux.substring(datosAlumnoAux.indexOf("|") + 1);

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
