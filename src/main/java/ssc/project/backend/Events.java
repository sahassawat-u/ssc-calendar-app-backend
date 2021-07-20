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
    @Column(name = "events", columnDefinition="LONGBLOB")
    @Convert(converter = EventConverter.class)
    private List<EventClass> events;

}
