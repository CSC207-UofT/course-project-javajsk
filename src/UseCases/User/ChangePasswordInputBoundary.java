package UseCases.User;

public interface ChangePasswordInputBoundary {
    boolean changePassword(String userId, String newPassword);
}
