package lk.ijse.ticket_payment_service.repo;

import lk.ijse.ticket_payment_service.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<PaymentEntity, String>{

}
