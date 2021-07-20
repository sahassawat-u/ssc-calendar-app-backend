package ssc.project.backend;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EventClass implements Serializable {

    private String name;

    private String details;

    private String start;

    private String end;

    private String color;

    public EventClass(){
    }
    public EventClass(String name, String details,String start, String end, String color){
        this.name = name;
        this.details = details;
        this.start = start;
        this.end = end;
        this.color = color;

    }
}
