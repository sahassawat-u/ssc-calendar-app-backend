package ssc.project.backend;


//import javax.persistence.Convert;
import org.springframework.security.core.parameters.P;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

@Converter
public class EventConverter implements AttributeConverter<List<EventClass>, String> {

    private static final String SEPARATOR = ", ";
    private static final String SM_SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(List<EventClass> eventList) {

        if(eventList == null)return null;
        StringBuilder sb = new StringBuilder();
        for(EventClass e : eventList){
//            sb.append("{ ");
            sb.append(e.getName());
            sb.append(SM_SEPARATOR);
            sb.append(e.getDetails());
            sb.append(SM_SEPARATOR);
            sb.append(e.getStart());
            sb.append(SM_SEPARATOR);
            sb.append(e.getEnd());
            sb.append(SM_SEPARATOR);
            sb.append(e.getColor());
            sb.append(SEPARATOR);
        }

        return sb.toString();
//        return null;
    }

    @Override
    public List<EventClass> convertToEntityAttribute(String dbEventList) {

        if (dbEventList == null || dbEventList.isEmpty()) {
            return null;
        }
        List<EventClass> events = new ArrayList<>();
        String[] pieces = dbEventList.split(SEPARATOR);
        for(int i=0;i<pieces.length;i++){
            String[] sm_pieces = pieces[i].split(SM_SEPARATOR);
            EventClass event = new EventClass();
            event.setName(sm_pieces[0]);
            event.setDetails(sm_pieces[1]);
            event.setStart(sm_pieces[2]);
            event.setEnd(sm_pieces[3]);
            event.setColor(sm_pieces[4]);
            events.add(event);
        }

        return events;
//        List<EventClass> eventList = new ArrayList<>();
//
////
//        if (pieces == null || pieces.length == 0) {
//            return null;
//        }
//        for(String s : pieces){
//
//        }
//
//        EventClass event = new EventClass();
//        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
//        if (dbEvent.contains(SEPARATOR)) {
//            event.setName(firstPiece);
//
//            if (pieces.length >= 2 && pieces[1] != null
//                    && !pieces[1].isEmpty()) {
//                event.setDetails(pieces[1]);
//            }
//            if (pieces.length >= 3 && pieces[2] != null
//                    && !pieces[2].isEmpty()) {
//                event.setStart(pieces[2]);
//            }
//            if (pieces.length >= 4 && pieces[3] != null
//                    && !pieces[3].isEmpty()) {
//                event.setEnd(pieces[3]);
//            }
//            if (pieces.length >= 5 && pieces[4] != null
//                    && !pieces[4].isEmpty()) {
//                event.setColor(pieces[4]);
//            }
//        } else {
//            event.setName(firstPiece);
//        }
//
//        return event;
    }
}
