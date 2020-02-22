/**
 * GsonFramer.java
 */
package negocio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class GsonFramer implements Framer {

    private InputStream in;

    public GsonFramer(InputStream in) {
        this.in = in;
    }

    @Override
    public void frameMsg(String msg, OutputStream out) throws IOException {
        
    }

    @Override
    public byte[] nextMsg() throws IOException {
        
        return null;
    }
}
