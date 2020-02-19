/*
 * dataReceptor.java
 * 
 * Creado en Febrero 16, 2020. 21:54.
 */
package datos;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import objetosNegocio.Student;
import presentacion.InterfazGrafica;
import sockets.ClientSocketData;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class DataReceptor implements Runnable {

    private ClientSocketData client;

    public DataReceptor(ClientSocketData client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            String studentData = (String) client.waitData();
            Student estudiante = receiveData(studentData);
            String estudianteFormato = "Nombre: " + estudiante.getFullName() + "\n"
                    + "Sexo: " + estudiante.getSex() + "\n"
                    + "Edad: " + estudiante.getAge() + "\n"
                    + "Dirección: " + estudiante.getAddress() + "\n"
                    + "Teléfono: " + estudiante.getCellPhoneNumber() + "\n"
                    + "Trabaja: " + (estudiante.isWork() ? "Sí" : "No");
            
            InterfazGrafica.ventanaEmergente(estudianteFormato);
        } catch (IOException ex) {
            System.out.println("Error en la espera de datos");
        }
    }

    private Student receiveData(String studentData) {
        Student student = new Student();
        String studentDataAux = studentData;

        for (int i = 0; studentDataAux.contains("|"); i++) {
            String data = studentDataAux.substring(0, studentDataAux.indexOf("|"));
            studentDataAux = studentDataAux.substring(studentDataAux.indexOf("|") + 1);

            switch (i) {
                case 0:
                    student.setFullName(data);
                    break;
                case 1:
                    student.setSex(data);
                    break;
                case 2:
                    student.setAge(Byte.parseByte(data));
                    break;
                case 3:
                    student.setAddress(data);
                    break;
                case 4:
                    student.setCellPhoneNumber(data);
                    break;
                case 5:
                    student.setWork(Boolean.parseBoolean(data));
                    break;
                default:
                    break;
            }
        }
        return student;
    }
}
