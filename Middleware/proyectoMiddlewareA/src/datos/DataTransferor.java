/*
 * dataTransferor.java
 * 
 * Creado en Febrero 16, 2020. 21:54.
 */
package datos;

import java.io.IOException;
import objetosNegocio.Student;
import sockets.ClientSocketData;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class DataTransferor {

    private ClientSocketData client;
    
    public DataTransferor(ClientSocketData client) {
        this.client = client;
    }

    public void sendData(Student student) throws IOException {
        String studentData = student.getFullName() + "|"
                + student.getSex() + "|"
                + student.getAge() + "|"
                + student.getAddress() + "|"
                + student.getCellPhoneNumber() + "|"
                + student.isWork();
        
        client.sendData(studentData);
    }
}
