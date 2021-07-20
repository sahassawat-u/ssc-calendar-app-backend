package ssc.project.backend.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ssc.project.backend.*;
import ssc.project.backend.whoami.WhoamiDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    /**
     * Make sure that all API path begins with /api. This will be useful when we do proxy
     */
    @GetMapping("/api/events")
    public EventDTO getEvents(){
//        System.out.println("hi from /api/events");
        // Put try around the statement because we use nested dot notation which could raise a NullPointException
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            System.out.println("hi before if in /api/events");
            if (principal!=null && principal instanceof org.springframework.security.core.userdetails.User){
                // user is logged in
                System.out.println("hi from /api/events");
                org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) principal;
                System.out.println(user.getUsername());
                Events e = eventRepository.findFirstByUsername(user.getUsername());
//                System.out.println("hi after Events e assigned");
//                EventClass eventClass = new EventClass();
//
//                eventClass.setName("Sleep");
//                eventClass.setDetails("Sleep until tomorrow");
//                eventClass.setStart("2021-07-13");
//                eventClass.setStart("2021-07-15");
//                eventClass.setColor("#1976D2");
//                e.eve
                System.out.println(user.getUsername());
//                List<EventClass> eventList = new ArrayList<>();

//                eventList.add(new EventClass("Sleep","Sleep until tomorrow","2021-07-13","2021-07-15","#1976D2"));
//                eventList.add(new EventClass("Business","Business until tomorrow","2021-07-10","2021-07-12","#1976D2"));
//                eventList.add(new EventClass("Eat","Eat until tomorrow","2021-07-08","2021-07-09","#1976D2"));
//                eventList.add(eventClass);
                return EventDTO.builder()
                        .username(e.getUsername())
                        .eventList(e.getEvents())
                        .build();
            }
        } catch (Exception e){

        }
        // if user is not logged in
        return EventDTO.builder()
                .build();
    }
}
