package UseCases.User;

import Entities.Interfaces.IUser;
import UseCases.DataAccessInterfaces.UserRepository;
import UseCases.IHasher;

public class ChangePasswordUseCase implements ChangePasswordInputBoundary{
    UserRepository userRepository;
    IHasher hasher;


    @Override
    public boolean changePassword(String userToken, String newPassword) {
        IUser user = userRepository.getUserFromToken(userToken);
        if(user != null){
            String hashedPasswd = hasher.generateHash(newPassword);
            userRepository.setPassword(userToken, hashedPasswd);
            return true;
        }
        return false;
    }
}
