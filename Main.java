import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * An advanced BareBones interpreter
 *
 * Variables are integers, including negative ones.
 *
 * Variables can be declared through any instruction.
 * A variable declared through a "clear" instruction will be set to the default value (i.e. 0).
 * A variable declared through an "incr" instruction will be set to the default value and immediately incremented (i.e. set to 1).
 * A variable declared through an "decr" instruction will be set to the default value and immediately decremented (i.e. set to -1).
 * A variable declared through a "while" instruction will be set to the default value (i.e. 0). The loop will not run at all.
 * A variable declared through an arithmetic operation (+, -, *, /) will be set to the default value (i.e. 0). In the case of a divisor, this will cause a "Division by 0" exception.
 *
 * An instruction can be written on several lines by adding the keyword "continue" at the end of every line except for the last one.
 *
 * Single line comments can be added by writing the syntax "// <i>content of comment</i>" at the end of a line.
 * Multiple line comments can be added by writing the syntax "/* <i>One or more lines of code</i> *\/" at the end of a line.
 */
public class Main {

    public static void main(String[] args) {

        try {
            File inputFile = new File("src/input.txt");
            FileReader input = new FileReader(inputFile);
            BufferedReader bufferedInput = new BufferedReader(input);

            String currentLine;
            ArrayList<String> fileContent = new ArrayList<>();

            currentLine = bufferedInput.readLine();
            while (currentLine != null) {
                if (currentLine.length() == 0) {
                    currentLine = bufferedInput.readLine();
                    continue;
                } // verifies if line is empty

                while (currentLine.charAt(0) == ' ' || currentLine.charAt(0) == '\r' ||
                        currentLine.charAt(0) == '\n' || currentLine.charAt(0) == '\t') {
                    currentLine = currentLine.substring(1);
                } // removes whitespace from the beginning of a line

                if (currentLine.contains("//")) {
                    currentLine = currentLine.substring(0, currentLine.indexOf("//"));
                } // comments are removed at runtime

                if (currentLine.contains("/*")) {
                    if (currentLine.contains("*/")) {
                        currentLine = currentLine.substring(0, currentLine.indexOf("/*"));
                    } else {
                        currentLine = currentLine.substring(0, currentLine.indexOf("/*"));
                        String nextLine = bufferedInput.readLine();
                        while (!nextLine.contains("*/")) {
                            nextLine = bufferedInput.readLine();
                        }
                        nextLine = nextLine.substring(nextLine.indexOf("*/") + 2);
                        if (nextLine.length() != 0) {
                            // if line is not empty after removing comments
                            while (nextLine.charAt(0) == ' ' || nextLine.charAt(0) == '\r' ||
                                    nextLine.charAt(0) == '\n' || nextLine.charAt(0) == '\t') {
                                nextLine = nextLine.substring(1);
                            } // removes whitespace from the beginning of a line

                            if (nextLine.length() != 0) {
                                // if line did not consist only of whitespace
                                if (currentLine.length() == 0) {
                                    currentLine = nextLine;
                                } else if (currentLine.contains(" continue")) {
                                    currentLine = currentLine.substring(0,
                                            currentLine.indexOf(" continue"));
                                    currentLine = currentLine + " " + nextLine;
                                } else {
                                    fileContent.add(currentLine);
                                    currentLine = nextLine;
                                }
                            }
                        }
                    }
                }

                if (currentLine.length() == 0) {
                    currentLine = bufferedInput.readLine();
                    continue;
                } // verifies if line is empty after removing comments

                while (currentLine.contains(" continue")) {
                    currentLine = currentLine.substring(0, currentLine.indexOf(" continue"));
                    String nextLine = bufferedInput.readLine();

                    while (nextLine.charAt(0) == ' ' || nextLine.charAt(0) == '\r' ||
                            nextLine.charAt(0) == '\n' || nextLine.charAt(0) == '\t') {
                        nextLine = nextLine.substring(1);
                    } // removes whitespace from the beginning of a line
                    if (nextLine.contains("//")) {
                        nextLine = nextLine.substring(0, nextLine.indexOf("//"));
                    } // comments are removed at runtime
                    currentLine = currentLine + " " + nextLine;
                } // adds all lines of an instruction to the same element of the fileContent

                fileContent.add(currentLine);
                currentLine = bufferedInput.readLine();
            }

            ArrayList<String> variables = new ArrayList<>();
            ArrayList<Integer> values = new ArrayList<>();

            for (int i = 0; i < fileContent.size(); i++) { // goes through the file
                currentLine = fileContent.get(i); // and checks which operation to do
                if (currentLine.contains("incr")) {
                    Increment.incrementVariable(currentLine, variables, values);
                } else if (currentLine.contains("decr")) {
                    Decrement.decrementVariable(currentLine, variables, values);
                } else if (currentLine.contains("clear")) {
                    Clear.clearVariable(currentLine, variables, values);
                } else if (currentLine.contains("while")) {
                    i = WhileLoop.whileLoop(fileContent, currentLine, i, variables, values);
                } else if (currentLine.contains(" + ")) {
                    Operations.add(currentLine, variables, values);
                } else if (currentLine.contains(" - ")) {
                    Operations.subtract(currentLine, variables, values);
                } else if (currentLine.contains(" * ")) {
                    Operations.multiply(currentLine, variables, values);
                } else if (currentLine.contains(" / ")) {
                    Operations.divide(currentLine, variables, values);
                } else if (currentLine.contains(" = ")) {
                    Operations.set(currentLine, variables, values);
                } else {
                    System.out.println(currentLine);
                    System.out.println("Instruction not understood");
                    System.exit(-1); // if line does not match an instruction, exits program
                }
            }

            System.out.println();
            System.out.println("Final values:");
            for (int i = 0; i < variables.size(); i++) {
                System.out.println(variables.get(i) + " = " + values.get(i));
            }
        } catch (IOException e) {
            System.out.println("IOException");
            System.exit(-1);
        }
    }
}
