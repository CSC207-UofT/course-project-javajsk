package adapters;

public interface TokenSigner {
    String generateToken(String userId);

    String getIdFromToken(String token);
}
