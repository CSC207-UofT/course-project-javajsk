package businessrules.dai;

/**
 * Interface for hashers to implement
 */
public interface Hasher {
    /**
     * Method for hashing a string
     *
     * @param plainText the string to hash
     * @return the hashed version of the string
     */
    String hash(String plainText);
}
