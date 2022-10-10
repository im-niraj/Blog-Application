package blogapp.Service;

import blogapp.Config.CustomUser;
import blogapp.Model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    public String createAccount(User user);
    public String forgetPassword(User user);

}
