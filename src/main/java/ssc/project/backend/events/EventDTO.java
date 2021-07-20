package ssc.project.backend.events;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ssc.project.backend.EventClass;

import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EventDTO {

    private String username;

    private List<EventClass> eventList;
}
