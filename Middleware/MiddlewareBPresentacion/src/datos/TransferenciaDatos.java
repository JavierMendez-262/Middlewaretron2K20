/**
 * TransferenciaDatos.java
 */
package datos;

import java.io.IOException;
import objetosnegocio.*;
import sockets.ClientSocketData;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class TransferenciaDatos {

    private ClientSocketData cliente;

    public TransferenciaDatos(ClientSocketData cliente) {
        this.cliente = cliente;
    }

    public void enviaDatos(Alumno alumno) throws IOException {
        String datos = alumno.getNombre() + "|"
                + alumno.getApellido() + "|"
                + alumno.getNombreTutor() + "|"
                + alumno.getDireccion() + "|"
                + alumno.getEdad() + "|"
                + alumno.getSemestre() + "|"
                + alumno.getPromedio() + "|"
                + alumno.getCarrera();
        cliente.sendData(datos);
    }
}
