package blogapp.Controller;


import blogapp.Model.User;
import blogapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String createAccount(@RequestBody User user){
        return userService.createAccount(user);
    }
    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }
}
