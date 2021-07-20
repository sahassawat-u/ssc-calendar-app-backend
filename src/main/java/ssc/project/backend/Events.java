package ssc.project.backend;
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@Table(name = "user_event_tlb")
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;

//    @Lob
//    private Blob events;
//    @Lob
    @Column(name = "events", columnDefinition="LONGBLOB")
    @Convert(converter = EventConverter.class)
    private List<EventClass> events;
//    @JsonCreator
//    public Events(@JsonProperty("name") String name,
//                @JsonProperty("details") String details,
//                @JsonProperty("start") String start,
//                @JsonProperty("end") String end,
//                @JsonProperty("color") String color) {
//
//        this.name = name;
//        this.details = details;
//        this.start = start;
//        this.end = end;
//        this.color = start;
//    }

//    @Column(unique = true)
//    private JsonArray events;
//    private long uid;

//    private String role;
}
