import java.util.Scanner;
/**
 * This class is responsible for input from the user.
 * This class creates a scanner object that can accept user input.
 *
 * @author Eirik Karlsen
 * @version 1.0
 */
public class UserInput
{
    private Scanner reader;
    /**
     * Create a new InputReader that reads text from the text terminal.
     */
    public UserInput()
    {
        reader = new Scanner(System.in);
    }
    
    /**
     * Method getInput
     *
     * @return The return value
     */
    public String getInput() 
    {
        return getInput("> ");
    }
    
    /**
     * Method getInput
     * Gets input from the user.
     * @param prompt
     * @return input
     */
    public String getInput(String prompt) {
        System.out.print(prompt);                // print prompt
        String inputLine = reader.nextLine();
        return inputLine;
    }
    
    /**
     * Method getMultilineInput
     * Gets a multilineinput from user.
     * @param prompt
     * @return result
     */
    public String getMultilineInput(String prompt) {
        System.out.println(prompt);
        
        String result = "";
        while(true) {
            String inputLine = reader.nextLine();
            if(inputLine.equals("")) {
                return result;
            } else {
                result += inputLine + "\n";
            }
        }
    }
}
