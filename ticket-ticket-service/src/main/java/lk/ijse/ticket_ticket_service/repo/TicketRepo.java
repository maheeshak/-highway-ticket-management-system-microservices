package lk.ijse.ticket_ticket_service.repo;

import lk.ijse.ticket_ticket_service.entity.TicketEntity;
import lk.ijse.ticket_ticket_service.util.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepo extends JpaRepository<TicketEntity, String>{
    @Query(value = "SELECT * FROM ticket WHERE vehicle_code = ?1 AND status = 'AVAILABLE'", nativeQuery = true)
    TicketEntity findByIdAndStatus(String vehicleCode, Status status);
}
