package lk.ijse.ticket_vehicle_service.repo;

import lk.ijse.ticket_vehicle_service.entity.VehicleEntity;
import lk.ijse.ticket_vehicle_service.util.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends JpaRepository<VehicleEntity, String>{

    @Query(value = "SELECT * FROM vehicle WHERE vehicle_code = ?1 AND status = 'AVAILABLE'", nativeQuery = true)
    VehicleEntity findByIdAndStatus(String vehicleCode, Status status);
}
