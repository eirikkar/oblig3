import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
/**
 * A class to read and write TSV-style records of messages.
 * 
 * @author Truls Pedersen
 * @version 2017.10.10
 */
public class MessageDatabase
{
    private static final String filename = "messages.tsv";
    
    private static final int SENDER = 0;
    private static final int RECIPIENT = 1;
    private static final int MESSAGEBODY = 2;
    
    private static Network network = Network.getNetwork();
    
    /**
     * A private constructor ensures that this object is not
     * instantiated. We only offer static methods.
     */    
    private MessageDatabase(){
    }
    
    /**
     * Method encodeMessage encodes a message-object as a 
     * String. This encoding is used when saving messages
     * to a file.
     *
     * @param message The message we want to encode
     * @return A text representation of a message
     */
    public static String encodeMessage(Message message) {
        String result = message.getSender().getName() + "\t";
        result += message.getRecipient().getName() + "\t";
        result += message.getMessageBody().replace("\n", "%0A");
        return result;
    }
    
    /**
     * Method decodeMessage decodes a (valid) message-encoding
     * and instantiates a message-object that it represents. 
     * This decoding is used when loading messages from a file.
     *
     * @param encoded The textual representation of a message-object
     * @return The message encoded by the parameter
     */
    public static Message decodeMessage(String encoded) {
        try {
            String[] parts = encoded.split("\t");
            String senderName = parts[SENDER].trim();
            String recipientName = parts[RECIPIENT].trim();
            String messageBody = parts[MESSAGEBODY];
            
            Person sender = network.lookupPerson(senderName);
            Person recipient = network.lookupPerson(recipientName);
            messageBody = messageBody.replace("%0A", "\n");
            
            return new Message(sender, recipient, messageBody);
        } catch(IndexOutOfBoundsException e) {
            System.out.println("Not well-encoded message: " + encoded);
            return null;
        }
    }
    
    /**
     * Method getMessages a list of all the stored messages.
     *
     * @return An array list of all stored messages.
     */
    public static ArrayList<Message> getMessages() {
        try{
            return Files.lines(Paths.get(filename))
                .map(MessageDatabase::decodeMessage)
                .filter(message -> message != null)
                .collect(Collectors.toCollection(ArrayList::new));
        } catch(IOException e) {
            System.out.println("Unable to open " + filename);
            return new ArrayList<Message>();
        }
    }
    
    /**
     * Method getMessagesTo returns all messages sent to recipient (given
     * as a parameter).
     * 
     * This method is incomplete. See assignment 3.a.
     *
     * @param recipient Person whose messages we return
     * @return All messages to the given recipient
     */
    public static ArrayList<Message> getMessagesTo(Person recipient) {
        for (Message m : getMessages() ) {
            ArrayList<Message> allMessages = getMessages();
            return allMessages;
        }
        return null;
    }
    
    /**
     * Method getMessagesFromFriendsTo returns all messages sent to recipient
     * (given as a parameter) from one of her/his friends.
     * 
     * This method is incomplete. See assignment 3.b.
     *
     * @param recipient Person whose messages we return
     * @return All messages to the given recipient from her/his friends
     */
    public static ArrayList<Message> getMessagesFromFriendsTo(Person recipient) {
        ArrayList<Message> allMessages = getMessages();
        return allMessages;
    }
    
    /**
     * Method setMessages saves a given list of messages as the 
     * entire set of stored messages. We can use this method to 
     * clear the database, but sending an empty list as a parameter.
     *
     * @param messages The list containing the messages which constitutes the database
     */
    public static void setMessages(ArrayList<Message> messages) {
        try{
            Files.write(Paths.get(filename),
                messages.stream()
                    .map(MessageDatabase::encodeMessage)
                    .collect(Collectors.toCollection(ArrayList::new)));
        } catch(IOException e) {
            System.out.println("Unable to write to file " + filename);
        }
    }
    
    /**
     * Method addMessage adds a message to the end of our list of
     * stored messages. 
     *
     * @param message A message to be added to the database
     */
    public static void addMessage(Message message) {
        ArrayList<Message> messages = MessageDatabase.getMessages();
        messages.add(message);
        MessageDatabase.setMessages(messages);
    }
}

