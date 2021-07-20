package ssc.project.backend.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ssc.project.backend.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitApplicationRunner implements ApplicationRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
