package blogapp.Service;

import blogapp.Config.CustomUser;
import blogapp.Exceptions.BlogException;
import blogapp.Model.User;
import blogapp.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepo.findByEmail(username);
        if(user == null){
            throw new BlogException("user not found");
        }
        return new CustomUser(user);
    }
}
