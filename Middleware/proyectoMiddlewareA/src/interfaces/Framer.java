/*
 * Framer.java
 * 
 * Creado en Febrero 20, 2020. 16.25.
 */
package interfaces;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public interface Framer {
    void frameMsg(byte[] message, OutputStream out) throws IOException;
    byte[] nextMsg() throws IOException;
}
