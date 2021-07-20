package ssc.project.backend.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ssc.project.backend.EventRepository;
import ssc.project.backend.Events;

import javax.servlet.http.HttpServletRequest;


@RestController
public class DeleteEventController {
    @Autowired
    private EventRepository eventRepository;

    @PostMapping("/api/deleteEvent")
    public void register(HttpServletRequest request){
//        String name = request.getParameter("name");
        String thisEle = request.getParameter("thisEle");
//        String editingId = request.getParameter("editingId");
//        String start = request.getParameter("start");
//        String end = request.getParameter("end");
//        String color = request.getParameter("color");
        System.out.println("from /api/deleteEvent");
//        System.out.println(details);

//        System.out.println(name+details+start+end+color);
//        System.out.println("from register " + username + password);
        // logging in twice has error
        // check if there is a current user logged in, if so log that user out first
        System.out.println(thisEle);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null && principal instanceof org.springframework.security.core.userdetails.User){
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) principal;
            Events e = eventRepository.findFirstByUsername(user.getUsername());
//            System.out.println(e.getEvents().get(Integer.parseInt(id)).getName());
//            e.getEvents().remove(Integer.parseInt(id)).getName();
            for(int i=0;i<e.getEvents().size();i++){
                String hash = e.getEvents().get(i).getDetails()+e.getEvents().get(i).getStart();
                if(hash.equals(thisEle)){
                    e.getEvents().remove(i);
                }

            }
//            eventRepository.deleteById(Integer.parseInt(id));
//            e.getEvents().get(Integer.parseInt(id)).setDetails(id);
//            System.out.println("found events from username!");
//            EventClass event = new EventClass();
//            event.setName(name);
//            event.setDetails(details);
//            event.setStart(start);
//            event.setEnd(end);
//            event.setColor(color);
//            e.getEvents().se

//            System.out.println("finish setting up event");
//            if(e.getEvents() == null){
//                e.getEvents() = new ArrayList<>();
//            }
//            e.getEv
//            System.out.println(e.getEvents().get(1).getDetails());
//            e.getEvents().add(event);
//            System.out.println(e);
//            System.out.println(e.getEvents().get(Integer.parseInt(id)).getDetails());
            eventRepository.save(e);
//                request.logout();
        }
//            String name = "Business";
//            String details = "Business Trip";
//        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) principal;
//        Events e = eventRepository.findFirstByUsername(user.getUsername());
//        System.out.println("found events from username!");
//        EventClass event = new EventClass();
//        event.setName(name);
//        event.setDetails(details);
//        event.setStart(start);
//        event.setEnd(end);
//        event.setColor(color);
//        e.getEvents().add(event);
////            System.out.println(e.getEvents().get(1).getDetails());
//        System.out.println(e);
//        eventRepository.save(e);
//            Events events = new Events();
//            List<EventClass> events =
//            List<EventClass> eventClassList = new ArrayList<>();
//            eventClassList.add(event);
//            events.setUsername("test1");
//            System.out.println(eventClassList);
//            events.setEvents(eventClassList);
//            eventRepository.save(events);
//            User newUser = new User();
//            newUser.setUsername(username);
//            newUser.setPassword(passwordEncoder.encode(password));
//            newUser.setRole("USER");
//            userRepository.save(newUser);
//            request.reg(username,password);
//            return SimpleResponse.builder()
//                    .success(true)
//                    .message("You are logged in successfully")
//                    .build();
//        }
        //        return "Login";
    }
}
