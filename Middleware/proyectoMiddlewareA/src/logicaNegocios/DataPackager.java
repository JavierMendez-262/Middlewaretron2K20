/*
 * DataPackager.java
 * 
 * Creado en Febrero 19, 2020. 22:41.
 */
package logicaNegocios;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosNegocio.Student;
import presentacion.InterfazGrafica;
import constants.Constants;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class DataPackager implements Runnable {

    private String hostName = "localhost";
    private int portNumber = 777;
    private FixedFramer fixedFramer;
    private Socket clientSocket;

    public DataPackager() throws IOException {
        clientSocket = new Socket(hostName, portNumber);
        fixedFramer = new FixedFramer(clientSocket.getInputStream());
    }

    public void sendPackage(Student student) throws IOException {
        String fixedFormat = toFixedFormat(student);
        byte[] byteArray = fixedFormat.getBytes();

        fixedFramer.frameMsg(byteArray, clientSocket.getOutputStream());
    }

    public void run() {
        byte[] byteArray = null;
        try {
            byteArray = fixedFramer.nextMsg();
        } catch (IOException ex) {
            Logger.getLogger(DataPackager.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (byteArray != null) {
            Student newStudent = toStudent(byteArray);
            InterfazGrafica.ventanaEmergente(newStudent);
        }
    }

    //No acentos que truena plis.
    private String toFixedFormat(Student student) {
        String currentInfo = "";
        String fixedString = "";

        int[] fixedLengths = {Constants.FULL_NAME_FIXED_LENGTH, Constants.SEX_FIXED_LENGTH, Constants.AGE_FIXED_LENGTH, Constants.ADDRESS_FIXED_LENGTH, Constants.CELL_PHONE_NUMBER_FIXED_LENGTH, Constants.WORK_FIXED_LENGTH};

        for (int i = 0; i < fixedLengths.length; i++) {
            
            switch (i) {
                case 0:
                    currentInfo = student.getFullName();
                    break;
                case 1:
                    currentInfo = student.getSex();
                    break;
                case 2:
                    currentInfo = Byte.toString(student.getAge());
                    break;
                case 3:
                    currentInfo = student.getAddress();
                    break;
                case 4:
                    currentInfo = student.getCellPhoneNumber();
                    break;
                case 5:
                    currentInfo = Boolean.toString(student.isCurrentlyWorking());
                    break;
            }
            
            while (currentInfo.length() < fixedLengths[i]) {
                currentInfo += Constants.FILLING_CHARACTER;
            }
            fixedString += currentInfo;
        }

        return fixedString;
    }

    private Student toStudent(byte[] byteArray) {
        String studentString = new String(byteArray, StandardCharsets.UTF_8);
        String currentInfo;

        int[] fixedLengths = {Constants.FULL_NAME_FIXED_LENGTH, Constants.SEX_FIXED_LENGTH, Constants.AGE_FIXED_LENGTH, Constants.ADDRESS_FIXED_LENGTH, Constants.CELL_PHONE_NUMBER_FIXED_LENGTH, Constants.WORK_FIXED_LENGTH};
        Student student = new Student();

        for (int i = 0; i < fixedLengths.length; i++) {
            currentInfo = studentString.substring(0, fixedLengths[i]).replaceAll("[" + Character.toString(Constants.FILLING_CHARACTER) + "]", "");
            studentString = studentString.substring(fixedLengths[i]);

            switch (i) {
                case 0:
                    student.setFullName(currentInfo);
                    break;
                case 1:
                    student.setSex(currentInfo);
                    break;
                case 2:
                    student.setAge(Byte.parseByte(currentInfo));
                    break;
                case 3:
                    student.setAddress(currentInfo);
                    break;
                case 4:
                    student.setCellPhoneNumber(currentInfo);
                    break;
                case 5:
                    student.setWork(Boolean.parseBoolean(currentInfo));
                    break;
            }
        }

        return student;
    }
}
