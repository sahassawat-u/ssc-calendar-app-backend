package ssc.project.backend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ssc.project.backend.UserRepository;

@Service
public class OurUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ssc.project.backend.User u = userRepository.findFirstByUsername(s);
        if(u != null){
            return User.withUsername(u.getUsername()).password(u.getPassword()).roles(u.getRole()).build();
        } else {
            throw new UsernameNotFoundException("Invalid username or password!!");
        }
    }
}
