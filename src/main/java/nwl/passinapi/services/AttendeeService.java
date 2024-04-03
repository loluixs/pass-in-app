package nwl.passinapi.services;

import lombok.RequiredArgsConstructor;
import nwl.passinapi.domain.attendee.Attendee;
import nwl.passinapi.domain.checkin.CheckIn;
import nwl.passinapi.dto.attendee.AttendeeDetails;
import nwl.passinapi.dto.attendee.AttendeesListRespondeDTO;
import nwl.passinapi.repositories.AttendeeRepository;
import nwl.passinapi.repositories.CheckinRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;
    private final CheckinRepository checkinRepository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId) {
        return this.attendeeRepository.findByEventId(eventId);
    }

    public AttendeesListRespondeDTO getEventsAttendee(String eventId) {
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
            Optional<CheckIn> checkIn = this.checkinRepository.findByAttendeeId(attendee.getId());
            LocalDateTime checkedInAt = checkIn.<LocalDateTime>map(CheckIn::getCreatedAt).orElse(null);
            return new AttendeeDetails(attendee.getId(),
                    attendee.getName(), attendee.getEmail(),
                    attendee.getCreatedAt(), checkedInAt);
        }).toList();

        return new AttendeesListRespondeDTO(attendeeDetailsList);
    }
}
