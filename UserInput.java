import java.util.Scanner;
/**
 * Write a description of class UserInput here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
     *
     * @param prompt A parameter
     * @return The return value
     */
    public String getInput(String prompt) {
        System.out.print(prompt);                // print prompt
        String inputLine = reader.nextLine();
        return inputLine;
    }
    
    /**
     * Method getMultilineInput
     *
     * @param prompt A parameter
     * @return The return value
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
