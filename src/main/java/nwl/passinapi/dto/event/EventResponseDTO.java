package nwl.passinapi.dto.event;

import lombok.Getter;
import nwl.passinapi.domain.event.Event;

@Getter
public class EventResponseDTO {

    EventDetailDTO event;

    public EventResponseDTO(Event event, Integer numberOfAttendees) {
        this.event = new EventDetailDTO(event.getId(),
                event.getTitle(), event.getDetails(), event.getSlug(),
                event.getMaximumAttendees(), numberOfAttendees);
    }
}
