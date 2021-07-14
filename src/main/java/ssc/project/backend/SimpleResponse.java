package ssc.project.backend;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SimpleResponse {

    private boolean success;
    private String message;

}
