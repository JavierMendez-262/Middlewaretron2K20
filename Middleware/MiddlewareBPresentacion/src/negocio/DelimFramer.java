/**
 * DelimFramer.java
 */
package negocio;

import constantes.Constantes;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import static constantes.Constantes.DELIM_CHAR;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class DelimFramer implements Framer {

    private InputStream in;

    public DelimFramer(InputStream in) {
        this.in = in;
    }

    @Override
    public void frameMsg(byte[] msg, OutputStream out) throws IOException {
        String studentString = new String(msg, StandardCharsets.UTF_8);
        System.out.println(studentString);
        out.write(msg.length);
        out.write(msg);
        out.write(DELIM_CHAR);
        out.flush();
    }

    @Override
    public void frameMsg(String msg, OutputStream out) throws IOException {
        new ObjectOutputStream(out).writeObject(msg + Constantes.DELIM_FRAME);
        out.flush();
    }

    @Override
    public byte[] nextMsg() throws IOException {
        ByteArrayOutputStream msgBuffer = new ByteArrayOutputStream();
        int nextByte;

        while ((nextByte = in.read()) != DELIM_CHAR) {
            if (nextByte == -1) {
                if (msgBuffer.size() == 0) {
                    return null;
                } else {
                    throw new EOFException("Mensaje no vacío sin delimitador.");
                }
            }
        }
        return msgBuffer.toByteArray();
    }
}
