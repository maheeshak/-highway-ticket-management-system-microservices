package lk.ijse.ticket_payment_service.util.convertor;

import lk.ijse.ticket_payment_service.dto.PaymentDTO;
import lk.ijse.ticket_payment_service.entity.PaymentEntity;
import org.springframework.stereotype.Component;

@Component
public class Convertor {


    public void updatePaymentEntity(PaymentDTO paymentDTO, PaymentEntity paymentEntity) {

            paymentEntity.setPayment_code(paymentDTO.getPayment_code());
            paymentEntity.setTicket_code(paymentDTO.getTicket_code());
            paymentEntity.setPayment_date(paymentDTO.getPayment_date());
            paymentEntity.setPayment_time(paymentDTO.getPayment_time());
            paymentEntity.setPrice(paymentDTO.getPrice());
            paymentEntity.setPayment_type(paymentDTO.getPayment_type());
    }
}
