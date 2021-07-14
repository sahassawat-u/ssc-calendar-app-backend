package ssc.project.backend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ssc.project.backend.SimpleResponse;
import ssc.project.backend.User;
import ssc.project.backend.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
public class RegistrationController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/register")
    public void register(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("from register " + username + password);
        try {
            // logging in twice has error
            // check if there is a current user logged in, if so log that user out first
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal != null && principal instanceof org.springframework.security.core.userdetails.User){
                request.logout();
            }

            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setRole("USER");
            userRepository.save(newUser);
//            request.reg(username,password);
//            return SimpleResponse.builder()
//                    .success(true)
//                    .message("You are logged in successfully")
//                    .build();

        } catch (ServletException e) {
//            return SimpleResponse.builder()
//                    .success(true)
//                    .message(e.getMessage())
//                    .build();
        }
//        return "Login";
    }
}
