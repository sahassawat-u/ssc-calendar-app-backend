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
        // add default admin user and password if missing
//        User admin = userRepository.findFirstByUsername("admin");
//        if (admin == null){
//            admin = new User();
//            admin.setUsername("test1");
//            admin.setPassword(passwordEncoder.encode("12345"));
//            admin.setRole("USER");
//            userRepository.save(admin);
//            String name = "Business";
//            String details = "Business Trip";
//
////            EventClass event = new EventClass();
////            event.setName(name);
////            event.setDetails(details);
////            event.setStart("2010-07-13");
////            event.setEnd("2010-07-14");
////            event.setColor("#1976D2");
////            event.setId("-1");
////            Events events = new Events();
////            List<EventClass> eventClassList = new ArrayList<>();
////            eventClassList.add(event);
////            events.setUsername(admin.getUsername());
////            System.out.println(eventClassList);
////            events.setEvents(eventClassList);
////            eventRepository.save(events);
//        }
//        else{
//            Events dbEvent = session.createNativeQuery(
//                    "select * from PersonTable p where p.id = :id", Person.class)
//                    .setParameter("id", id)
//                    .getSingleResult();
//
//            assertEquals(dbEvent.getPersonName()
//                    .getName(), name);
//            assertEquals(dbEvent.getPersonName()
//                    .getSurname(), surname);
//
//        }
    }
}
