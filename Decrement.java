import java.util.ArrayList;

/**
 * Class contains a method that decrements the value of a variable.
 */
public class Decrement {

    /**
     * Method that decrements a variable
     *
     * @param currentLine The line from the input file that is currently accessed
     * @param variables An ArrayList that contains the name of the variables used in the input
     * @param values An ArrayList that contains the values of the variables used in the input
     */
    public static void decrementVariable(String currentLine, ArrayList<String> variables,
                                         ArrayList<Integer> values) {

        ArrayList<String> currentWords = new ArrayList<>();
        for (String s : currentLine.split(" ")) { // splits the line into words
            currentWords.add(s);
        }

        if (currentWords.size() != 2 || !(currentWords.get(0).equals("decr"))) {
            System.out.println("Decrement syntax incorrect");
            System.exit(-1); // exits program if syntax is incorrect
        }

        String variableToDecrement = currentWords.get(1);
        int i;
        for (i = 0; i < variables.size(); i++) {
            if (variableToDecrement.equals(variables.get(i))) { // checks whether variable exists
                break;
            }
        }
        if (i == variables.size()) {
            variables.add(variableToDecrement); // if it doesn't, declares it and sets value to -1
            values.add(-1);
        } else {
            int valueToDecrement = values.get(i);
            values.set(i, valueToDecrement - 1); // if it exists, decrements it
        }

        System.out.println(variables.get(i) + " = " + values.get(i));
    }
}
