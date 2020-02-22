/**
 * Alumno.java
 */
package objetosnegocio;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class Alumno {

    public String nombre;
    public String apellido;
    public String nombreTutor;
    public String direccion;
    public int edad;
    public int semestre;
    public float promedio;
    public String carrera;

    public Alumno(String nombre, String apellido, String nombreTutor, String direccion, int edad, int semestre, float promedio, String carrera) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreTutor = nombreTutor;
        this.direccion = direccion;
        this.edad = edad;
        this.semestre = semestre;
        this.promedio = promedio;
        this.carrera = carrera;
    }

    public Alumno() {
        this.nombre = "Indefinido";
        this.apellido = "Indefinido";
        this.nombreTutor = "Indefinido";
        this.direccion = "Indefinido";
        this.edad = -1;
        this.semestre = -1;
        this.promedio = -1;
        this.carrera = "Indefinido";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public float getPromedio() {
        return promedio;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Apellido: " + apellido + ", Nombre de tutor: " + nombreTutor + ", Dirección: " + direccion + ", Edad: " + edad + ", Semestre: " + semestre + ", Promedio: " + promedio + ", Carrera: " + carrera;
    }
}
