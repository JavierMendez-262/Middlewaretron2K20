/*
 * serverSocket.java
 * 
 * Creado en Febrero 17, 2020. 00:20
 */
package sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class ServerSocketData {

    private int portNumber;

    public ServerSocketData(int portNumber) {
        this.portNumber = portNumber;
    }

    public Socket receiveData() {
        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
        ) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                return clientSocket;
            }
        } catch (IOException e) {
            System.err.println("Excepción I/O al aceptar la conexión con el cliente");
            System.exit(1);
        }
        return null;
    }
}
