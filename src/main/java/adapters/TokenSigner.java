package adapters;

/**
 * Token Signer Interface
 */
public interface TokenSigner {
    /**
     * Method generates token from given user id
     *
     * @param userId id of user
     * @return string containing user token
     */
    String generateToken(String userId);

    /**
     * Method returns id associated with given user token
     *
     * @param token token of user
     * @return string id of user
     */
    String getIdFromToken(String token);
}
