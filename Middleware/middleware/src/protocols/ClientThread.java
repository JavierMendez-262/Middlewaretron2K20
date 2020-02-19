/*
 * clientThread.java
 * 
 * Creado en Febrero 17, 2020. 00:25.
 */
package protocols;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class ClientThread implements Runnable {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            Object inputObj;

            while (!socket.isClosed()) {
                if ((inputObj = in.readObject()) != null) {
                    Interpreter formatInterpreter = new Interpreter();
                    for (ClientThread client : ServerRunner.clients) {
                        if (!this.equals(client)) {
                            client.getOutputStream().writeObject(formatInterpreter.interpret((String) inputObj));
                            client.getOutputStream().flush();//Sin probar aun
                        }
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Object's class don't recognized");
        }
    }

    public ObjectOutputStream getOutputStream() {
        return out;
    }
}
