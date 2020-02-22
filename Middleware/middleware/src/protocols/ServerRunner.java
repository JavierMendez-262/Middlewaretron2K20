/*
 * serverRunner.java
 * 
 * Creado en Febrero 17, 2020. 01:59.
 */
package protocols;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author javie
 */
public class ServerRunner {

    private static final int portNumber = 777;
    private static ServerSocket serverSocket;
    public static ArrayList<ClientThread> clients = new ArrayList<>();

    public static void main(String[] args) {
        serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
            acceptClients();
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + portNumber);
            System.exit(1);
        }
    }
    
    public static void acceptClients() {
        while (true) {            
            try {
                Socket socket = serverSocket.accept();
                ClientThread client = new ClientThread(socket);
                Thread thread = new Thread(client);
                thread.start();
                clients.add(client);
            } catch (IOException e) {
                System.out.println("Accepted failed on: " + portNumber);
            }
        }
    }
}
