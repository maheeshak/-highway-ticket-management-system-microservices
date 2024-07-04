package lk.ijse.ticket_payment_service.util.map;

import lk.ijse.ticket_payment_service.dto.PaymentDTO;
import lk.ijse.ticket_payment_service.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class Mapper {

    private final ModelMapper map;


    public PaymentEntity toPaymentEntity(PaymentDTO paymentDTO) {
        return map.map(paymentDTO, PaymentEntity.class);
    }

    public PaymentDTO mapToPaymentDTO(PaymentEntity paymentEntity) {
        return map.map(paymentEntity, PaymentDTO.class);
    }

    public List<PaymentDTO> mapToPaymentDTOS(List<PaymentEntity> all) {
        return map.map(all, List.class);
    }
}
