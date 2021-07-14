package ssc.project.backend.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ssc.project.backend.SimpleResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationController {

    @GetMapping("/api/test")
    public String test(){
        return "If this message is shown, it means login is successful";
    }
    @PostMapping("/api/login")
    public SimpleResponse login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + password);
        try {
            // logging in twice has error
            // check if there is a current user logged in, if so log that user out first
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal != null && principal instanceof org.springframework.security.core.userdetails.User){
                request.logout();
            }
            request.login(username,password);
            return SimpleResponse.builder()
                            .success(true)
                            .message("You are logged in successfully")
                            .build();

        } catch (ServletException e) {
            return SimpleResponse.builder()
                            .success(true)
                            .message(e.getMessage())
                            .build();
        }
//        return "Login";
    }

    @GetMapping("/api/logout")
    public SimpleResponse logout(HttpServletRequest request){
        try {
            request.logout();
            return SimpleResponse.builder()
                    .success(true)
                    .message("You are successfully logout")
                    .build();
        } catch (ServletException e) {
//            e.printStackTrace();
            return SimpleResponse.builder()
                    .success(true)
                    .message(e.getMessage())
                    .build();
        }
    }
}
