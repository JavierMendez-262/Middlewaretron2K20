/*
 * clientSocket.java
 * 
 * Creado en Febrero 17, 2020. 00:19
 */
package sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class ClientSocketData {
    
    private String hostName;
    private int portNumber;
    
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    public ClientSocketData(String hostName, int portNumber) throws IOException {
        this.hostName = hostName;
        this.portNumber = portNumber;
        this.clientSocket = new Socket(hostName, portNumber);
        this.out = new ObjectOutputStream(clientSocket.getOutputStream());
        this.in = new ObjectInputStream(clientSocket.getInputStream());
    }
    
    public void sendData(Object data)  {
        try {
            out.writeObject(data);
        } catch (UnknownHostException e) {
            System.err.println("No se encontró el host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("No se puedo obtener I/O para la conexión a " + hostName);
            System.exit(1);
        }
    }
    
    public Object waitData() {
        try {
            Object inputObj;
            while (true) {
                 if ((inputObj = in.readObject()) != null) {
                    return inputObj;
                 }
            }
        } catch (UnknownHostException e) {
            System.err.println("No se encontró el host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("No se pudo obtener I/O para la conexión a " + hostName);
            System.exit(1);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientSocketData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }   
    
}
