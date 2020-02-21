/*
 * FixedFramer.java
 * 
 * Creado en Febrero 20, 2020. 16.25.
 */
package logicaNegocios;

import interfaces.Framer;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import constants.Constants;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class FixedFramer implements Framer{

    private InputStream in;
    
    public FixedFramer(InputStream in) {
        this.in = in;
    }
    
    @Override
    public void frameMsg(byte[] message, OutputStream out) throws IOException {
        if(message.length != Constants.FIXED_MAX_LENGTH) {
            throw new IOException("Message doesn't match the fixed length");
        }
        //Send who is going to receive the message
        out.write(message.length);
        out.write(message);
        out.flush();
    }

    @Override
    public byte[] nextMsg() throws IOException {
        ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream();
        int nextByte;
        
        for (int i = 0; i < Constants.FIXED_MAX_LENGTH; i++) {
            nextByte = in.read(); //Talvés cause errores
            if (nextByte == -1) {
                if (messageBuffer.size() == 0) {
                    return null;
                } else {
                    System.out.println("FixedFramer.java -49");
                    throw new EOFException("Non-empty message without delimiter");
                }
            }
            messageBuffer.write(nextByte);
        }
        return messageBuffer.toByteArray();
    }
    
}
