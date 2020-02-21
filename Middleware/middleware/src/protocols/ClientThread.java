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

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class ClientThread implements Runnable {

    private Socket socket;
    private InputStream in;
    private OutputStream out;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = socket.getOutputStream();
            in = socket.getInputStream();
            int nextByte;

            while (!socket.isClosed()) {
                ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream();
                int messageLength = in.read();
                for (int i = 0; i < messageLength; i++) {
                    nextByte = in.read();
                    messageBuffer.write(nextByte);
                }
                Interpreter formatInterpreter = new Interpreter();
                formatInterpreter.interpret(messageBuffer.toByteArray());
                
                //Send to the specified one

//                nextByte = in.read();
//                Interpreter formatInterpreter = new Interpreter();
//                switch (nextByte) {
//                    case Constants.FIXED_FRAME:
//                        ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream();
//                        for (int i = 0; i < Constants.FIXED_MAX_LENGTH; i++) {
//                            nextByte = in.read();
//                            messageBuffer.write(nextByte);
//                        }
//                        formatInterpreter.interpret(messageBuffer.toByteArray());
//                        
//                        break;
//                    case Constants.DELIM_FRAME:
//                        
//                        break;
//                    case Constants.GSON_FRAME:
//                        
//                        break;
//                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public OutputStream getOutputStream() {
        return out;
    }
}
