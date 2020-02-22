/**
 * Framer.java
 */
package negocio;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public interface Framer {

    public void frameMsg(byte[] msg, OutputStream out) throws IOException;

    public void frameMsg(String msg, OutputStream out) throws IOException;

    public byte[] nextMsg() throws IOException;
}
