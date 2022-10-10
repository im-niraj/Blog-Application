package blogapp.Service;

import blogapp.Model.User;
import blogapp.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService{

    @Autowired
    private UserRepo userRepo;


    @Override
    public String createAccount(User user) {
        userRepo.save(user);
        return "Account Created";
    }

    @Override
    public String forgetPassword(User user) {
        return null;
    }
}
