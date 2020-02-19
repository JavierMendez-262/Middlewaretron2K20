/**
 * Pruebas.java
 */
package datos;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class Pruebas {

    public static void main(String[] args) {
        String str = "Enrique|Mendoza|José Mendoza|Luis Echeverría|21|6|9.0|ISW";
        String result = "";
        char[] messChar = str.toCharArray();

        for (int i = 0; i < messChar.length; i++) {
            result += Integer.toBinaryString(messChar[i]) + " ";
        }
        System.out.println(result);
    }
}
