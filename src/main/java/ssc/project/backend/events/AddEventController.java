package ssc.project.backend.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ssc.project.backend.EventClass;
import ssc.project.backend.EventRepository;
import ssc.project.backend.Events;
import ssc.project.backend.User;
//import ssc.project.backend.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AddEventController {

    @Autowired
    private EventRepository eventRepository;

    @PostMapping("/api/addEvent")
    public void register(HttpServletRequest request){
        String name = request.getParameter("name");
        String details = request.getParameter("details");
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String color = request.getParameter("color");
        String id = request.getParameter("id");
        System.out.println("from /api/addEvent");
        // logging in twice has error
        // check if there is a current user logged in, if so log that user out first
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null && principal instanceof org.springframework.security.core.userdetails.User){
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) principal;
            Events e = eventRepository.findFirstByUsername(user.getUsername());
            EventClass event = new EventClass();
            event.setName(name);
            event.setDetails(details);
            event.setStart(start);
            event.setEnd(end);
            event.setColor(color);
//            int idx = e.getEvents().size();
//            if(e.getEvents() == null){
//                e.getEvents() = new ArrayList<>();
//            }
            e.getEvents().add(event);
            eventRepository.save(e);
//                request.logout();
        }
    }
}
