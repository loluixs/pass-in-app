package nwl.passinapi.repositories;

import nwl.passinapi.domain.checkin.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinRepository extends JpaRepository<CheckIn, Integer> {
}
