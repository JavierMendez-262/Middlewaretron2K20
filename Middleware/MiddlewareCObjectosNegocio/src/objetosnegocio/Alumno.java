/**
 * Alumno.java
 */
package objetosnegocio;

import java.io.Serializable;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class Alumno {

    public String nombre;
    public String id;
    public String tel;
    public String sexo;
    public String ciudad;

    public Alumno(String nombre, String id, String tel, String sexo, String ciudad) {
        this.nombre = nombre;
        this.id = id;
        this.tel = tel;
        this.sexo = sexo;
        this.ciudad = ciudad;
    }

    public Alumno() {
        this.nombre = "Indefinido";
        this.id = "Indefinido";
        this.tel = "Indefinido";
        this.sexo = "Indefinido";
        this.ciudad = "Indefinido";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", ID: " + id + ", Número de teléfono: " + tel + ", Sexo: " + sexo + ", Ciudad: " + ciudad;
    }
}
