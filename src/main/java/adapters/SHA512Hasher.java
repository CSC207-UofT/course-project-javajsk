package adapters;

import businessrules.dai.Hasher;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Concrete hasher used for user account passwords
 */
public class SHA512Hasher implements Hasher {
    /**
     * Instantiates a new Sha 512 hasher.
     */
    public SHA512Hasher() {
    }

    /**
     * Method hashes given string
     *
     * @param plainText the string to hash
     * @return hashed string
     */
    @Override
    public String hash(String plainText) {
        // CODE taken from GEEKSFORGEEKS:
        // https://www.geeksforgeeks.org/sha-512-hash-in-java/
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(plainText.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            StringBuilder hashtext = new StringBuilder(no.toString(16));

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext.insert(0, "0");
            }

            // return the HashText
            return hashtext.toString();
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
