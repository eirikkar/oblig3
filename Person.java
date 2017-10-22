import java.util.ArrayList;

/**
 * Write a description of class Person here. Remember to write 
 * a good class description, and your self as the author.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Person
{
    // the person's name
    private String name;

    // a list of this person's friends
    private ArrayList<Person> friends;
    
    /**
     * Constructor for objects of class Person. When the object
     * is created, the person's list of friends is empty.
     */
    public Person(String name)
    {
        // initialise instance variables
        this.name = name;
        friends = new ArrayList<>();
    }

    /**
     * Method getName returns the person's name.
     *
     * @return The person's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Method getFriends returns an ArraList of Person-objects 
     * containing the person's friends. 
     *
     * @return The list of the person's friends
     */
    public ArrayList<Person> getFriends() {
        return friends;
    }
    
    /**
     * Method addFriend adds the given friend-object to this
     * person's friend list. 
     *
     * @param friend A new friend (added to the list of friends)
     */
    public void addFriend(Person friend) {
        friends.add(friend);
    }
}
