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
        // Put try around the statement because we use nested dot notation which could raise a NullPointException
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal!=null && principal instanceof org.springframework.security.core.userdetails.User){
                // user is logged in
                System.out.println("hi from /api/events");
                org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) principal;
                System.out.println(user.getUsername());
                Events e = eventRepository.findFirstByUsername(user.getUsername());

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
