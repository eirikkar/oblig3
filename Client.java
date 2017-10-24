
/**
 * Client functions as an interface between a user and the network. The 
 * user interacts with the client by typing commands. The user can send messages 
 * to other users. And recive messages from users and friends.
 * 
 * @author     Eirik Karlsen
 * @version    1.0 (2017.10.24)
 */
public class Client
{
    private Network network;
    private Person user;
    /**
     * 
     */
    public Client()
    {
        network = Network.getNetwork();
    }
    
    /**
     * main Method
     *
     * @param args A parameter
     */
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
     * The client can send messages (initiated by the command "message"), recive message (with the command
     * "read) and quit (initiated by the command "bye"). 
     *
     * 
     */
    public void start() {
        UserInput ui = new UserInput();
        boolean loggedIn = false;
        while(!loggedIn) {
            String username = ui.getInput("Username: ");
            user = network.lookupPerson(username);
            loggedIn = user != null;
        }
        
        printWelcome();
        
        boolean finished = false;
        while(!finished) {
            String userInput = ui.getInput();
            
            if(userInput.equals("bye")) {
                finished = true;
            } else if(userInput.equals("message")) {
                String recipientName = ui.getInput("To: ");
                Person recipient = network.lookupPerson(recipientName);
                if (recipient != null) {
                    String messageBody = ui.getMultilineInput("Message: ");
                    Message message = new Message(user, recipient, messageBody);
                    MessageDatabase.addMessage(message);
                }
            } else if (userInput.equals("help")){
                printHelp();
            } else if (userInput.equals("read")) {
                printMessages();
            } else if (userInput.equals("friends")) {
                printMessagesFromFriends();
            }
        }
        
        printGoodbye();
    }
    
    /**
     * Method printWelcome
     * Prints a welcome message to the user.
     */
    private void printWelcome() {
        System.out.println("Welcome to the messaging client, " 
            + user.getName() + ".");
        System.out.println("Type 'help' to get instructions.");
    }
    
    /**
     * Method printMessages
     * Prints messages recived to the logged in user.
     */
    private void printMessages() {
        for (Message m : MessageDatabase.getMessagesTo(user)) {
            if (user.equals(m.getRecipient())) {
                System.out.println("From: " + m.getSender().getName());
                System.out.println("To: " + m.getRecipient().getName());
                System.out.println(m.getMessageBody());
            }
        }
    }
    
    /**
     * Method printMessagesFromFriends
     * Prints messages from friends of the logged in user.
     */
    private void printMessagesFromFriends() {
        for (Message m : MessageDatabase.getMessagesTo(user)) {
            for (Person friend : user.getFriends()) {
                if (user.equals(m.getRecipient()) && m.getSender().equals(friend)) {
                    System.out.println("From: " + m.getSender().getName());
                    System.out.println("To: " + m.getRecipient().getName());
                    System.out.println(m.getMessageBody());
                }
            }
        }
    }
    
    /**
     * Method printGoodbye
     * Prints goodbye message.
     */
    private void printGoodbye() {
        System.out.println("Good bye, " + user.getName() + "!");
    }

    /**
     * Method printHelp
     * Prints the help menu.
     */
    private void printHelp() {
        System.out.println("Type 'message' to send a message");
        System.out.println("Type 'read' to read recived messages");
        System.out.println("Type 'bye' to exit the program");
    }
    
}
