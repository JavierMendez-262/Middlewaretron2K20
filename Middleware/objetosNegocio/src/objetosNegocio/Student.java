/*
 * Student.java
 * 
 * Creado en Febrero 16, 2020. 19:46.
 */

package objetosNegocio;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class Student {
    
    private String fullName;
    private String sex;
    private byte age;
    private String address;
    private String cellPhoneNumber;
    private boolean work;
    
    public Student() {
        this.fullName = "Indefinido";
        this.sex = "Indefinido";
        this.age = -1;
        this.address = "Indefinido";
        this.cellPhoneNumber = "Indefinido";
        this.work = false;
    }
    
    public Student(String fullName, String sex, byte age, String address, String cellPhoneNumber, boolean work) {
        this.fullName = fullName;
        this.sex = sex;
        this.age = age;
        this.address = address;
        this.cellPhoneNumber = cellPhoneNumber;
        this.work = work;
    }
    
    public Student(String fullName, String sexo, byte age) {
        this.fullName = fullName;
        this.sex = sexo;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public boolean isCurrentlyWorking() {
        return work;
    }

    public void setWork(boolean work) {
        this.work = work;
    }

    @Override
    public String toString() {
        return "Student{" + "fullName=" + fullName + ", sex=" + sex + ", age=" + age + ", address=" + address + ", cellPhoneNumber=" + cellPhoneNumber + ", work=" + work + '}';
    }
}
