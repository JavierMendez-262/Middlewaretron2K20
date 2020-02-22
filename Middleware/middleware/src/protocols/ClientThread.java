/*
 * clientThread.java
 * 
 * Creado en Febrero 17, 2020. 00:25.
 */
package protocols;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import constants.Constants;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class ClientThread implements Runnable {

    private Socket socket;
    private InputStream in;
    private OutputStream out;
    private String identifierName;
    private String formatManaged;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = socket.getOutputStream();
            in = socket.getInputStream();
            int nextByte;
            Object inputObj;
            String addresse = "";

            while (!socket.isClosed()) {//Receive the addressee
                ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream();
                if ((inputObj = new ObjectInputStream(in).readObject()) != null) {
                    if (inputObj.getClass() == String.class) {
                        if (this.identifierName == null) {
                            setIdentifierName(inputObj.toString());
                            System.out.println(identifierName);
                        } else {
                            addresse = inputObj.toString().substring(0, inputObj.toString().length() - 1);

                            int messageLength = in.read();
                            for (int i = 0; i < messageLength; i++) {
                                nextByte = in.read();
                                messageBuffer.write(nextByte);
                            }
                            Interpreter formatInterpreter = new Interpreter();
                            int formatToParse = -1;
                            
                            System.out.println(addresse);////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            for (ClientThread client : ServerRunner.clients) {
                                if (client.getIdentifierName().contains(addresse)) {
                                    System.out.println(client.getIdentifierName());////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                    formatToParse = Integer.parseInt(Character.toString(client.getIdentifierName().charAt(client.getIdentifierName().length() - 1)));
                                }
                            }
                            
                            System.out.println(formatToParse);
                            byte[] message = formatInterpreter.interpret(messageBuffer.toByteArray(), formatToParse);
                            System.out.println(new String(message, StandardCharsets.UTF_8));
                            
                            //Send to the specified one
                            for (ClientThread client : ServerRunner.clients) {
                                if (client.getIdentifierName().contains(addresse)) {
                                    System.out.println(client.getIdentifierName());
                                    client.getOutputStream().write(message);
                                    client.getOutputStream().flush();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found");
        }
    }

    public void setIdentifierName(String identifierName) {
        this.identifierName = identifierName;
    }

    public String getIdentifierName() {
        return identifierName;
    }

    public OutputStream getOutputStream() {
        return out;
    }
}
