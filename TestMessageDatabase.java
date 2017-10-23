import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
/**
 * Write a description of class TestMessageDatabase here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestMessageDatabase
{
    private Network network;
    /**
     * Constructor for objects of class TestMessageDatabase
     */
    public TestMessageDatabase()
    {
        testReset();
    }
    
    /**
     * Method testReset
     * 
     */
    @Test
    public void testReset()
    {
        ArrayList<Message> empty = new ArrayList<>();
        MessageDatabase.setMessages(empty);
        assertEquals(0, MessageDatabase.getMessages().size());
    }
    
    /**
     * 
     */
    @Test
    public void testIncrement()
    {
        network = Network.getNetwork();
        Message test = new Message(network.lookupPerson("Dag"), network.lookupPerson("Liv"), "test");
        for (int i = 1 ; i < 11; i++) {
            MessageDatabase.addMessage(test);
        }
        assertEquals(10, MessageDatabase.getMessages().size());
        
    }

}
