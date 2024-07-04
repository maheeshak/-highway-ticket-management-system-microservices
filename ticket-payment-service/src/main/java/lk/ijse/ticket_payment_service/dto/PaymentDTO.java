package lk.ijse.ticket_payment_service.dto;



import lk.ijse.ticket_payment_service.util.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDTO implements SuperDTO{
    private String payment_code;
    private String ticket_code;
    private Date payment_date;
    private Time payment_time;
    private double price;
    private PaymentType payment_type;

}
