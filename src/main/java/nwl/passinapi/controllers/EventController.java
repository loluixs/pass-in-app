package nwl.passinapi.controllers;

import lombok.RequiredArgsConstructor;
import nwl.passinapi.dto.attendee.AttendeeIdDTO;
import nwl.passinapi.dto.attendee.AttendeeRequestDTO;
import nwl.passinapi.dto.attendee.AttendeesListRespondeDTO;
import nwl.passinapi.dto.event.EventIdDTO;
import nwl.passinapi.dto.event.EventRequestDTO;
import nwl.passinapi.dto.event.EventResponseDTO;
import nwl.passinapi.services.AttendeeService;
import nwl.passinapi.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final AttendeeService attendeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id) {
        EventResponseDTO event = this.eventService.getEventDetail(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
        EventIdDTO eventIdDTO = this.eventService.createEvent(body);

        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();

        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @PostMapping("/{eventId}/attendees")
    public ResponseEntity<AttendeeIdDTO> registerParticipant(@PathVariable String eventId, @RequestBody AttendeeRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        AttendeeIdDTO  attendeeIdDTO = this.eventService.registerAttendeeOnEvent(eventId, body);

        var uri = uriComponentsBuilder.path("/attendees/{attendId}/badge").buildAndExpand(attendeeIdDTO.attendeeId()).toUri();

        return ResponseEntity.created(uri).body(attendeeIdDTO);
    }

    @GetMapping("/attendees/{id}")
    public ResponseEntity<AttendeesListRespondeDTO> getEventAttendees(@PathVariable String id) {
        AttendeesListRespondeDTO attendeesListRespondeDTO = this.attendeeService.getEventsAttendee(id);
        return ResponseEntity.ok(attendeesListRespondeDTO);
    }
}
