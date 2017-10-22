import java.util.ArrayList;

/**
 * Write a description of class Network here. Remember to write 
 * a good class description, and your self as the author.
 *
 * @author Truls Pedersen
 * @version 2017.10.10
 */
public class Network
{
    // This is an implementation of a Singleton-class. We encountered this
    // in the first lecture (the figures-project). This static field
    // contains a reference to the only instance of a network.
    private static Network networkSingleton;
    
    // this variable is instantiated and set by the constructor
    private ArrayList<Person> persons;

    public static Network getNetwork() {
        if(networkSingleton == null) {
            networkSingleton = new Network();
        }
        return networkSingleton;
    }
    
    /**
     * Constructor for objects of class Network
     */
    private Network()
    {
        // initialise instance variables
        persons = new ArrayList<>();
        persons.add(new Person("Aage"));
        persons.add(new Person("Bård"));
        persons.add(new Person("Christian"));
        persons.add(new Person("Dag"));
        persons.add(new Person("Even"));
        persons.add(new Person("Fridtjof"));
        persons.add(new Person("Gerda"));
        persons.add(new Person("Hulda"));
        persons.add(new Person("Ingrid"));
        persons.add(new Person("Jorunn"));
        persons.add(new Person("Kerstin"));
        persons.add(new Person("Liv"));
        
        // load the network (edges)
        loadNetwork();
    }

    /**
     * Method loadNetwork loads every agents' list of friend. (1.b)
     */
    private void loadNetwork() {
        for (Person person : persons) {
            ArrayList<String> friendNames = friends(person.getName());
            for (String friendName : friendNames) {
                person.addFriend(lookupPerson(friendName));
            }
        }
    }

    /**
     * Method lookupPerson searches through our collection of agents
     * and returns the agent which has the same name as the given parameter.
     * Part of exercise (1.a).
     *
     * @param agentName The name of the agent we are searching for
     * @return The agent we searched for, or null if none is found
     */
    public Person lookupPerson(String personName) {
        for(Person person : persons) {
            if(person.getName().equals(personName)){
                return person;
            }
        }
        return null;
    }
    
    /**
     * Method friends returns a list of a given person's friends.
     *
     * @param personName A name of the person whose friends you want to find
     * @return A list containing the names of the given person's friends
     */
    public ArrayList<String> friends(String personName) {
        ArrayList<String> result = new ArrayList<>();
        if (personName.equals("Aage")) {
            result.add("Bård");
            result.add("Dag");
        } else if (personName.equals("Bård")) {
            result.add("Aage");
            result.add("Christian");
        } else if (personName.equals("Christian")) {
            result.add("Bård");
            result.add("Dag");
            result.add("Even");
        } else if (personName.equals("Dag")) {
            result.add("Aage");
            result.add("Christian");
            result.add("Liv");
        } else if (personName.equals("Even")) {
            result.add("Christian");
            result.add("Fridtjof");
            result.add("Gerda");
            result.add("Hulda");
            result.add("Ingrid");
            result.add("Jorunn");
        } else if (personName.equals("Fridtjof")) {
            result.add("Even");
            result.add("Gerda");
            result.add("Hulda");
        } else if (personName.equals("Gerda")) {
            result.add("Even");
            result.add("Jorunn");
            result.add("Ingrid");
        } else if (personName.equals("Hulda")) {
            result.add("Even");
            result.add("Fridtjof");
        } else if (personName.equals("Gerda")) {
            result.add("Even");
            result.add("Jorunn");
            result.add("Ingrid");
        } else if (personName.equals("Ingrid")) {
            result.add("Even");
            result.add("Gerda");
            result.add("Jorunn");
            result.add("Kerstin");
            result.add("Liv");
        } else if (personName.equals("Jorunn")) {
            result.add("Even");
            result.add("Gerda");
            result.add("Ingrid");
        } else if (personName.equals("Kerstin")) {
            result.add("Ingrid");
            result.add("Liv");
        } else if (personName.equals("Liv")) {
            result.add("Dag");
            result.add("Ingrid");
            result.add("Kerstin");
        }
        return result;
    }
    
    /**
     * Method printNetwork prints the network. It prints the name of every person
     * where each name is immediately preceded by the names of their friends.
     */
    public void printNetwork() {
        for (Person person : persons) {
            ArrayList<Person> friends = person.getFriends();
            System.out.println(person.getName() + " has " + friends.size() + " friends:");
            for (Person friend : friends) {
                System.out.println(" * " + friend.getName());
            }
        }
    }
}
