import java.lang.NumberFormatException;
import java.lang.NullPointerException;
import java.util.ArrayList;

/**
 * Class contains methods that implement four arithmetic operations (+, -, *, /), a method that sets the value of a variable and a method that checks if a given String is an integer or not.
 */
public class Operations {

    /**
     * Method that checks whether a String is an integer or not
     *
     * @param possibleInteger A String that may be an integer
     * @return Returns true if the parameter is an integer
     */
    private static boolean isInteger (String possibleInteger) {

        try {
            Integer.parseInt(possibleInteger);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    /**
     * Method that adds the values of two operands
     * Instruction must be of the form X = Y + Z, where X is a variable and Y and Z are either integers or variables. The value of X will become the sum.
     *
     * @param currentLine The line from the input file that is currently accessed
     * @param variables An ArrayList that contains the name of the variables used in the input
     * @param values An ArrayList that contains the values of the variables used in the input
     */
    public static void add (String currentLine, ArrayList<String> variables,
                     ArrayList<Integer> values) {

        ArrayList<String> currentWords = new ArrayList<>();
        for (String s : currentLine.split(" ")) { // splits the line into words
            currentWords.add(s);
        }

        if (currentWords.size() != 5 || !(currentWords.get(1).equals("=")) ||
                !(currentWords.get(3).equals("+"))) {
            System.out.println("Addition syntax incorrect");
            System.exit(-1); // exits program if syntax is incorrect
        }

        String variableToSet = currentWords.get(0); // variable that will be changed
        int i;
        for (i = 0; i < variables.size(); i++) {
            if (variableToSet.equals(variables.get(i))) { // checks whether variable exists
                break;
            }
        }
        if (i == variables.size()) {
            variables.add(variableToSet);
            // if it doesn't, declares it and sets value to default
            values.add(0);
        }

        int j;
        String leftOperand = currentWords.get(2);
        int leftValue;
        if (isInteger(leftOperand)) {
            leftValue = Integer.parseInt(leftOperand);
        } else {
            for (j = 0; j < variables.size(); j++) {
                if (leftOperand.equals(variables.get(j))) { // checks whether variable exists
                    break;
                }
            }
            if (j == variables.size()) {
                variables.add(leftOperand);
                // if it doesn't, declares it and sets value to default
                values.add(0);
            }
            leftValue = values.get(j);
        }

        String rightOperand = currentWords.get(4);
        if (isInteger(rightOperand)) { // checks if operand is integer or variable
            int rightValue = Integer.parseInt(rightOperand);
            values.set(i, leftValue + rightValue);
        } else {
            for (j = 0; j < variables.size(); j++) {
                if (rightOperand.equals(variables.get(j))) { // checks whether variable exists
                    break;
                }
            }
            if (j == variables.size()) {
                variables.add(rightOperand);
                // if it doesn't, declares it and sets value to default
                values.add(0);
            }
            int rightValue = values.get(j);

            values.set(i, leftValue + rightValue);
            // sets value of first variable to value of addition between the two operands
        }
        System.out.println(variables.get(i) + " = " + values.get(i));
    }

    /**
     * Method that subtracts the value of one operand from another
     * Instruction must be of the form X = Y - Z, where X is a variable and Y and Z are either integers or variables. The value of X will become the difference.
     *
     * @param currentLine The line from the input file that is currently accessed
     * @param variables An ArrayList that contains the name of the variables used in the input
     * @param values An ArrayList that contains the values of the variables used in the input
     */
    public static void subtract (String currentLine, ArrayList<String> variables,
                            ArrayList<Integer> values) {

        ArrayList<String> currentWords = new ArrayList<>();
        for (String s : currentLine.split(" ")) { // splits the line into words
            currentWords.add(s);
        }

        if (currentWords.size() != 5 || !(currentWords.get(1).equals("=")) ||
                !(currentWords.get(3).equals("-"))) {
            System.out.println("Subtraction syntax incorrect");
            System.exit(-1); // exits program if syntax is incorrect
        }

        String variableToSet = currentWords.get(0); // variable that will be changed
        int i;
        for (i = 0; i < variables.size(); i++) {
            if (variableToSet.equals(variables.get(i))) { // checks whether variable exists
                break;
            }
        }
        if (i == variables.size()) {
            variables.add(variableToSet);
            // if it doesn't, declares it and sets value to default
            values.add(0);
        }

        int j;
        String leftOperand = currentWords.get(2);
        int leftValue;
        if (isInteger(leftOperand)) {
            leftValue = Integer.parseInt(leftOperand);
        } else {
            for (j = 0; j < variables.size(); j++) {
                if (leftOperand.equals(variables.get(j))) { // checks whether variable exists
                    break;
                }
            }
            if (j == variables.size()) {
                variables.add(leftOperand);
                // if it doesn't, declares it and sets value to default
                values.add(0);
            }
            leftValue = values.get(j);
        }

        String rightOperand = currentWords.get(4);
        if (isInteger(rightOperand)) { // checks if operand is integer or variable
            int rightValue = Integer.parseInt(rightOperand);
            values.set(i, leftValue - rightValue);
        } else {
            for (j = 0; j < variables.size(); j++) {
                if (rightOperand.equals(variables.get(j))) { // checks whether variable exists
                    break;
                }
            }
            if (j == variables.size()) {
                variables.add(rightOperand);
                // if it doesn't, declares it and sets value to default
                values.add(0);
            }
            int rightValue = values.get(j);

            values.set(i, leftValue - rightValue);
            // sets value of first variable to value of the difference of the two operands
        }
        System.out.println(variables.get(i) + " = " + values.get(i));
    }

    /**
     * Method that multiplies the values of two operands
     * Instruction must be of the form X = Y * Z, where X is a variable and Y and Z are either integers or variables. The value of X will become the product.
     *
     * @param currentLine The line from the input file that is currently accessed
     * @param variables An ArrayList that contains the name of the variables used in the input
     * @param values An ArrayList that contains the values of the variables used in the input
     */
    public static void multiply (String currentLine, ArrayList<String> variables,
                                 ArrayList<Integer> values) {

        ArrayList<String> currentWords = new ArrayList<>();
        for (String s : currentLine.split(" ")) { // splits the line into words
            currentWords.add(s);
        }

        if (currentWords.size() != 5 || !(currentWords.get(1).equals("=")) ||
                !(currentWords.get(3).equals("*"))) {
            System.out.println("Multiplication syntax incorrect");
            System.exit(-1); // exits program if syntax is incorrect
        }

        String variableToSet = currentWords.get(0); // variable that will be changed
        int i;
        for (i = 0; i < variables.size(); i++) {
            if (variableToSet.equals(variables.get(i))) { // checks whether variable exists
                break;
            }
        }
        if (i == variables.size()) {
            variables.add(variableToSet);
            // if it doesn't, declares it and sets value to default
            values.add(0);
        }

        int j;
        String leftOperand = currentWords.get(2);
        int leftValue;
        if (isInteger(leftOperand)) {
            leftValue = Integer.parseInt(leftOperand);
        } else {
            for (j = 0; j < variables.size(); j++) {
                if (leftOperand.equals(variables.get(j))) { // checks whether variable exists
                    break;
                }
            }
            if (j == variables.size()) {
                variables.add(leftOperand);
                // if it doesn't, declares it and sets value to default
                values.add(0);
            }
            leftValue = values.get(j);
        }

        String rightOperand = currentWords.get(4);
        if (isInteger(rightOperand)) { // checks if operand is integer or variable
            int rightValue = Integer.parseInt(rightOperand);
            values.set(i, leftValue * rightValue);
        } else {
            for (j = 0; j < variables.size(); j++) {
                if (rightOperand.equals(variables.get(j))) { // checks whether variable exists
                    break;
                }
            }
            if (j == variables.size()) {
                variables.add(rightOperand);
                // if it doesn't, declares it and sets value to default
                values.add(0);
            }
            int rightValue = values.get(j);

            values.set(i, leftValue * rightValue);
                // sets value of first variable to value of multiplication between the two operands
        }
        System.out.println(variables.get(i) + " = " + values.get(i));
    }

    /**
     * Method that divides the value of one operand by the value of another
     * Instruction must be of the form X = Y / Z, where X is a variable and Y and Z are either integers or variables. The value of X will become the quotient.
     * If the value of Z is 0, the program will end due to a "Division by 0" exception.
     *
     * @param currentLine The line from the input file that is currently accessed
     * @param variables An ArrayList that contains the name of the variables used in the input
     * @param values An ArrayList that contains the values of the variables used in the input
     */
    public static void divide (String currentLine, ArrayList<String> variables,
                                 ArrayList<Integer> values) {

        ArrayList<String> currentWords = new ArrayList<>();
        for (String s : currentLine.split(" ")) { // splits the line into words
            currentWords.add(s);
        }

        if (currentWords.size() != 5 || !(currentWords.get(1).equals("=")) ||
                !(currentWords.get(3).equals("/"))) {
            System.out.println("Division syntax incorrect");
            System.exit(-1); // exits program if syntax is incorrect
        }

        String variableToSet = currentWords.get(0); // variable that will be changed
        int i;
        for (i = 0; i < variables.size(); i++) {
            if (variableToSet.equals(variables.get(i))) { // checks whether variable exists
                break;
            }
        }
        if (i == variables.size()) {
            variables.add(variableToSet);
            // if it doesn't, declares it and sets value to default
            values.add(0);
        }

        int j;
        String dividend = currentWords.get(2);
        int dividendValue;
        if (isInteger(dividend)) {
            dividendValue = Integer.parseInt(dividend);
        } else {
             for (j = 0; j < variables.size(); j++) {
                if (dividend.equals(variables.get(j))) { // checks whether variable exists
                    break;
                }
            }
            if (j == variables.size()) {
                variables.add(dividend);
                // if it doesn't, declares it and sets value to default
                values.add(0);
            }
            dividendValue = values.get(j);
        }

        String divisor = currentWords.get(4);
        if (isInteger(divisor)) { // checks if operand is integer or variable
            int divisorValue = Integer.parseInt(divisor);
            if (divisorValue == 0) {
                System.out.println("Division by 0");
                System.exit(-1);
            }
            values.set(i, dividendValue / divisorValue);
        } else {
            for (j = 0; j < variables.size(); j++) {
                if (divisor.equals(variables.get(j))) { // checks whether variable exists
                    break;
                }
            }
            if (j == variables.size()) {
                variables.add(divisor);
                    // if it doesn't, declares it and sets value to default
                values.add(0);
            }
            int divisorValue = values.get(j);

            if (divisorValue == 0) {
                System.out.println("Division by 0");
                System.exit(-1);
            } else {
                values.set(i, dividendValue / divisorValue);
                // sets value of first variable to value of division between dividend by divisor
            }
        }
        System.out.println(variables.get(i) + " = " + values.get(i));
    }

    /**
     * Method that sets the value of a variable
     * Instruction must be of the form X = Y, where X is a variable and Y is either an integer or a variable. The value of X will become the value of Y.
     *
     * @param currentLine The line from the input file that is currently accessed
     * @param variables An ArrayList that contains the name of the variables used in the input
     * @param values An ArrayList that contains the values of the variables used in the input
     */
    public static void set (String currentLine, ArrayList<String> variables,
                            ArrayList<Integer> values) {

        ArrayList<String> currentWords = new ArrayList<>();
        for (String s : currentLine.split(" ")) { // splits the line into words
            currentWords.add(s);
        }

        if (currentWords.size() != 3 || !(currentWords.get(1).equals("="))) {
            System.out.println("Setting syntax incorrect");
            System.exit(-1); // exits program if syntax is incorrect
        }

        String variableToSet = currentWords.get(0); // variable that will be changed
        int i;
        for (i = 0; i < variables.size(); i++) {
            if (variableToSet.equals(variables.get(i))) { // checks whether variable exists
                break;
            }
        }
        if (i == variables.size()) {
            variables.add(variableToSet);
            // if it doesn't, declares it and sets value to default
            values.add(0);
        }

        String operandSetTo = currentWords.get(2); // other operand
        if (isInteger(operandSetTo)) { // checks if operand is integer or variable
            values.set(i, Integer.parseInt(operandSetTo));
        } else {
            int j;
            for (j = 0; j < variables.size(); j++) {
                if (operandSetTo.equals(variables.get(j))) { // checks whether variable exists
                    break;
                }
            }
            if (j == variables.size()) {
                variables.add(operandSetTo);
                // if it doesn't, declares it and sets value to default
                values.add(0);
            }
            int operandValue = values.get(j);

            values.set(i, operandValue);
        }
        System.out.println(variables.get(i) + " = " + values.get(i));
    }
}
