/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocols;

import interfaces.IExpression;
import interpreter.TerminalExpression;
import java.nio.charset.StandardCharsets;
import constants.Constants;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author javie
 */
public class Interpreter {

    private IExpression isFixedFormat;
    private IExpression isDelimFormat;

    public Interpreter() {
        isFixedFormat = getFixedFrameExpression();
        isDelimFormat = getDelimFrameExpression();
    }

    public Object interpret(byte[] byteArray) {//int typeToParse
        String unknownFormat = new String(byteArray, StandardCharsets.UTF_8);
        if (isFixedFormat.interpret(unknownFormat)) {
            System.out.println("Fixed Format");
            parseToNeutral(unknownFormat, Constants.FIXED_FRAME);
            System.out.println(Arrays.toString(parseToNeutral(unknownFormat, Constants.FIXED_FRAME)));
        } else if (isDelimFormat.interpret(unknownFormat)) {
            System.out.println("Delimited Format");

        } else {
            System.out.println("Nada");
            
            return null;
        }
//        if (isFixedFormat.interpret(unknownFormat)) {
//            return parseToDelimFrame(unknownFormat);
//        } else if (isDelimFormat.interpret(unknownFormat)) {
//            return parseToFixedFrame(unknownFormat);
//        } else {
//            return null;
//        }
        return null;
    }

    private IExpression getFixedFrameExpression() {
        IExpression studentA = new TerminalExpression("(?i)"
                + "[a-z " + Constants.FILLING_CHARACTER + "]{50}"
                + "(masculino|femenino[" + Constants.FILLING_CHARACTER + "])"
                + "([\\d]{2}|[\\d]{1}[" + Constants.FILLING_CHARACTER + "]{1})"
                + "[\\w #" + Constants.FILLING_CHARACTER + ".]{20}"
                + "[\\d*]{10}"
                + "(true[" + Constants.FILLING_CHARACTER + "]|false){1}");
        return studentA;
    }

    private IExpression getDelimFrameExpression() {
        IExpression studentB = new TerminalExpression("");//Se necesita la expresión regular
        return studentB;
    }

    private Object[] parseToNeutral(String data, int formatType) {
        ArrayList<String> neutralFormat = new ArrayList<>();

        switch (formatType) {
            case Constants.FIXED_FRAME:
                int[] fixedLengths = {Constants.FULL_NAME_FIXED_LENGTH, Constants.SEX_FIXED_LENGTH, Constants.AGE_FIXED_LENGTH, Constants.ADDRESS_FIXED_LENGTH, Constants.CELL_PHONE_NUMBER_FIXED_LENGTH, Constants.WORK_FIXED_LENGTH};
                for (int i = 0; i < fixedLengths.length; i++) {
                    neutralFormat.add(data.substring(0, fixedLengths[i]).replaceAll("[" + Character.toString(Constants.FILLING_CHARACTER) + "]", ""));
                    data = data.substring(fixedLengths[i]);
                }
                break;
            case Constants.DELIM_FRAME:

                break;
            case Constants.GSON_FRAME:

                break;
        }
        return neutralFormat.toArray();
    }

    private byte[] parseToFixedFrame(String[] data) {
        return null;
    }

    private byte[] parseToDelimFrame(String[] data) {
        return null;
    }

    private String parseToGson(String[] data) {
        return null;
    }
}
