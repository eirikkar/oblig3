
/**
 * Write a description of class Message here.
 *
 * @author Truls Pedersen
 * @version 2017.09.20
 */
public class Message
{
    // instance variables - replace the example below with your own
    private Person sender;
    private Person recipient;
    private String messageBody;

    /**
     * Constructor for objects of class Message
     */
    public Message(Person sender, Person recipient, String messageBody)
    {
        // initialise instance variables
        this.sender = sender; 
        this.recipient = recipient;
        this.messageBody = messageBody;
    }
    
    public Person getSender() {
        return sender;
    }
    
    public Person getRecipient() {
        return recipient;
    }
    
    public String getMessageBody() {
        return messageBody;
    }
}
