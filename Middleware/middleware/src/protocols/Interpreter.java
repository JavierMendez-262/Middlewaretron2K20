/*
 * clientThread.java
 * 
 * Creado en Febrero 17, 2020. 01:04.
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
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class Interpreter {

    private IExpression isFixedFormat;
    private IExpression isDelimFormat;

    public Interpreter() {
        isFixedFormat = getFixedFrameExpression();
        isDelimFormat = getDelimFrameExpression();
    }

    public byte[] interpret(byte[] byteArray, int typeToParse) {//int typeToParse
        String unknownFormat = new String(byteArray, StandardCharsets.UTF_8);
        System.out.println(unknownFormat);
        if (isFixedFormat.interpret(unknownFormat)) {
            System.out.println("Fixed Format");
            if (typeToParse == Constants.FIXED_FRAME) {
                return byteArray;
            }
            Object[] neutral = parseToNeutral(unknownFormat, Constants.FIXED_FRAME);
            String[] neutralString = Arrays.copyOf(neutral, neutral.length, String[].class);
            switch (typeToParse) {
                case Constants.DELIM_FRAME:
                    return parseToDelimFrame(neutralString, Constants.FIXED_FRAME);
                case Constants.GSON_FRAME:
                    break;
            }
        } else if (isDelimFormat.interpret(unknownFormat)) {
            System.out.println("Delimited Format");
            if (typeToParse == Constants.DELIM_FRAME) {
                return byteArray;
            }
            Object[] neutral = parseToNeutral(unknownFormat, Constants.DELIM_FRAME);
            String[] neutralString = Arrays.copyOf(neutral, neutral.length, String[].class);
            switch (typeToParse) {
                case Constants.FIXED_FRAME:
                    return parseToFixedFrame(neutralString, Constants.DELIM_FRAME);
                case Constants.GSON_FRAME:
                    break;
            }
        } else {
            System.out.println("Nada");
        }
        return null;
    }

    private IExpression getFixedFrameExpression() {
        IExpression student = new TerminalExpression("(?i)"
                + "[a-z " + Constants.FILLING_CHARACTER + "]{50}"
                + "(masculino|femenino[" + Constants.FILLING_CHARACTER + "])"
                + "([\\d]{2}|[\\d]{1}[" + Constants.FILLING_CHARACTER + "]{1})"
                + "[\\w #" + Constants.FILLING_CHARACTER + ".]{20}"
                + "[\\d" + Constants.FILLING_CHARACTER + "]{10}"
                + "(true[" + Constants.FILLING_CHARACTER + "]|false){1}");
        return student;
    }

    private IExpression getDelimFrameExpression() {
        IExpression student = new TerminalExpression("(?i)"
                + "[ a-z]*[" + Constants.DELIM_CHARACTER + "]{1}"
                + "[ a-z]*[" + Constants.DELIM_CHARACTER + "]{1}"
                + "[ a-z]*[" + Constants.DELIM_CHARACTER + "]{1}"
                + "[ \\w#.]*[" + Constants.DELIM_CHARACTER + "]{1}"
                + "[\\d]{1,2}[" + Constants.DELIM_CHARACTER + "]{1}"
                + "([\\d]|(10)){1}[" + Constants.DELIM_CHARACTER + "]{1}"
                + "([\\d]|(10)){1}[.]{1}[\\d]{1}[" + Constants.DELIM_CHARACTER + "]{1}"
                + "[\\w]*[" + Constants.DELIM_CHARACTER + "]{1}");
        return student;
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
                return data.substring(0, data.length() - 1).split("\\|");
            case Constants.GSON_FRAME:

                break;
        }
        return neutralFormat.toArray();
    }

    private byte[] parseToFixedFrame(String[] data, int dataType) {
        String currentInfo = "";
        String fixedString = "";

        int[] fixedLengths = {Constants.FULL_NAME_FIXED_LENGTH, Constants.SEX_FIXED_LENGTH, Constants.AGE_FIXED_LENGTH, Constants.ADDRESS_FIXED_LENGTH, Constants.CELL_PHONE_NUMBER_FIXED_LENGTH, Constants.WORK_FIXED_LENGTH};

        switch (dataType) {
            case Constants.DELIM_FRAME:
                for (int i = 0; i < fixedLengths.length; i++) {
                    switch (fixedLengths.length) {
                        case 0:
                            currentInfo = data[0] + " " + data[1];
                            break;
                        case 1:
                            currentInfo = "Indefinido";
                            break;
                        case 2:
                            currentInfo = "Indefinido";
                            break;
                        case 3:
                            currentInfo = data[4];
                            break;
                        case 4:
                            currentInfo = data[3];
                            break;
                        case 5:
                            currentInfo = "false";
                            break;
                    }
                    System.out.println(currentInfo);
                    while (currentInfo.length() < fixedLengths[i]) {
                        currentInfo += Constants.FILLING_CHARACTER;
                    }
                    fixedString += currentInfo;
                }
                break;
            case Constants.GSON_FRAME:
                //GSON
                break;
        }
        System.out.println(fixedString);
        return fixedString.getBytes();
    }

    private byte[] parseToDelimFrame(String[] data, int dataType) {
        String delimString = "";
        switch (dataType) {
            case Constants.FIXED_FRAME:
                for (String singleData : data) {
                    delimString += singleData + "|";
                }
                break;
            case Constants.GSON_FRAME:
                //GSON
                break;
        }
        return delimString.getBytes();
    }

    private String parseToGson(String[] data, int dataType) {
        return null;
    }
}
