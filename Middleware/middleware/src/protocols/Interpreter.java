/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocols;

import interfaces.IExpression;
import interpreter.TerminalExpression;

/**
 *
 * @author javie
 */
public class Interpreter {

    private IExpression isFormatA;
    private IExpression isFormatB;
    
    public Interpreter() {
        isFormatA = getStudentAExpression();
        isFormatB = getStudentBExpression();
    }

    public String interpret(String unknownFormat) {
        if (isFormatA.interpret(unknownFormat)) {
            return parseToFormatB(unknownFormat);
        } else if (isFormatB.interpret(unknownFormat)) {
            return parseToFormatA(unknownFormat);
        } else {
            return null;
        }
    }

    private IExpression getStudentAExpression() {
        IExpression studentA = new TerminalExpression("[\\w áéíóúÁÉÍÓÚ]*[|]{1}[\\w]*[|]{1}[\\d]*[|]{1}[\\w áéíóúÁÉÍÓÚ#\\d]*[|]{1}[\\d]*[|]{1}[truefals]*");
        return studentA;
    }

    private IExpression getStudentBExpression() {
        IExpression studentB = new TerminalExpression("[\\w áéíóúÁÉÍÓÚ]*[|]{1}[\\w áéíóúÁÉÍÓÚ]*[|]{1}[\\w áéíóúÁÉÍÓÚ]*[|]{1}[\\w áéíóúÁÉÍÓÚ]*[|]{1}[\\d]*[|]{1}[\\d][|]{1}[\\d.]*[|]{1}[A-Z]*");
        return studentB;
    }

    private String parseToFormatB(String format) {
        String[] studentInfo = format.split("\\|");
        return studentInfo[0].substring(0, studentInfo[0].indexOf(" ", studentInfo[0].indexOf(" ") + 1)).trim() + "|"
                + studentInfo[0].substring(studentInfo[0].indexOf(" ", studentInfo[0].indexOf(" ") + 1)).trim() + "|"
                + "Indefinido" + "|"
                + studentInfo[3] + "|"
                + studentInfo[2] + "|"
                + "-1" + "|"
                + "-1" + "|"
                + "Indefinido" + "|";
    }

    private String parseToFormatA(String format) {
        String[] studentInfo = format.split("\\|");
        return studentInfo[0] + " " + studentInfo[1] + "|"
                + "Indefinido" + "|"
                + studentInfo[4] + "|"
                + studentInfo[3] + "|"
                + "-1" + "|"
                + "Indefinido";
    }
}
