package se331.lab.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Event {
    Long id;
    String category, title, description, location, date, time, organizer;
    Boolean petAllowed;
}
