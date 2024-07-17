package singleton;

// TODO: Implement/Modify the Singleton class to ensure that only one instance of the class can be created
public class Singleton {

    /**
     * Supposing this is the only material method needed, which is to get the identifier of the instance of Singleton class.
     */
    public String getInstanceIdentifier() {
        return Singleton.class.getName() + "@" + Integer.toHexString(System.identityHashCode(this));
    }
}
