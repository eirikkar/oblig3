import java.util.Scanner;

/**
 * Client functions as an interface between a user and the network. The 
 * user interacts with the client by typing commands. The user can send messages 
 * to other users.
 * 
 * @author     Truls Pedersen
 * @version    1.0 (2017.10.10)
 */
public class Client
{
    private Scanner reader;
    private Network network;
    private Person user;
    
    /**
     * Create a new InputReader that reads text from the text terminal.
     */
    public Client()
    {
        reader = new Scanner(System.in);
        network = Network.getNetwork();
    }
    
    public static void main(String[] args) {
        Client nc = new Client();
        nc.start();
        
    }
    
    /**
     * Method start runs the client-program. It consists of two main
     * phases: login and working. 
     * 
     * During the login-phase the user will be asked to identify herself. 
     * The username given must match an existing person exactly. 
     * 
     * During the working-phase the user will send commands to the client. 
     * The client can send messages (initiated by the command "message") and 
     * quit (initiated by the command "bye"). 
     *
     * The method is incomplete. See assignments 2 and 4.
     */
    public void start() {
        boolean loggedIn = false;
        while(!loggedIn) {
            String username = getInput("Username: ");
            user = network.lookupPerson(username);
            loggedIn = user != null;
        }
        
        printWelcome();
        
        boolean finished = false;
        while(!finished) {
            String userInput = getInput();
            
            if(userInput.equals("bye")) {
                finished = true;
            } else if(userInput.equals("message")) {
                String recipientName = getInput("To: ");
                Person recipient = network.lookupPerson(recipientName);
                if (recipient != null) {
                    String messageBody = getMultilineInput("Message: ");
                    Message message = new Message(user, recipient, messageBody);
                    MessageDatabase.addMessage(message);
                }
            } else if (userInput.equals("help")){
                printHelp();
            } else if (userInput.equals("read")) {
                printMessages();
            }
        }
        
        printGoodbye();
    }
    
    private void printWelcome() {
        System.out.println("Welcome to the messaging client, " 
            + user.getName() + ".");
        System.out.println("Type 'help' to get instructions.");
    }
    private void printMessages() {
        for (Message m : MessageDatabase.getMessagesTo(user)) {
            if (user.equals(m.getRecipient())) {
                System.out.println("From: " + m.getSender().getName());
                System.out.println("To: " + m.getRecipient().getName());
                System.out.println(m.getMessageBody());
            }
            
        }
    }
    private void printGoodbye() {
        System.out.println("Good bye, " + user.getName() + "!");
    }

    private void printHelp() {
        System.out.println("Type 'message' to send a message");
        System.out.println("Type 'read' to read recived messages");
        System.out.println("Type 'bye' to exit the program");
    }
    private String getInput() 
    {
        return getInput("> ");
    }
    
    private String getInput(String prompt) {
        System.out.print(prompt);                // print prompt
        String inputLine = reader.nextLine();
        return inputLine;
    }
    
    private String getMultilineInput(String prompt) {
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
