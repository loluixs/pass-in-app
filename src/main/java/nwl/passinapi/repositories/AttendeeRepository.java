package nwl.passinapi.repositories;

import nwl.passinapi.domain.attendee.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepository extends JpaRepository<Attendee, String> {
}
