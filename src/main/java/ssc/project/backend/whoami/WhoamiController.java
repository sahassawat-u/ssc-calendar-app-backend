package ssc.project.backend.whoami;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ssc.project.backend.User;
import ssc.project.backend.UserRepository;

/**
 * A controller to retrieve currently logged-in user.
 */
@RestController
public class WhoamiController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Make sure that all API path begins with /api. This will be useful when we do proxy
     */
    @GetMapping("/api/whoami")
    public WhoamiDTO whoami(){
        // Put try around the statement because we use nested dot notation which could raise a NullPointException
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal!=null && principal instanceof org.springframework.security.core.userdetails.User){
                // user is logged in
                org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) principal;
                User u = userRepository.findFirstByUsername(user.getUsername());
                return WhoamiDTO.builder()
                        .loggedIn(true)
                        .name(u.getUsername())
                        .role(u.getRole())
                        .username(u.getUsername())
                        .build();
            }
        } catch (Exception e){

        }
        // if user is not logged in
        return WhoamiDTO.builder()
                .loggedIn(false)
                .build();
    }
}