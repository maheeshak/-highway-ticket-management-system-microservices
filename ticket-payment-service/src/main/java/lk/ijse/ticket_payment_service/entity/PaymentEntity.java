package lk.ijse.ticket_payment_service.entity;

import jakarta.persistence.*;
import lk.ijse.ticket_payment_service.util.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "payment")
public class PaymentEntity {
    @Id
    private String payment_code;
    private String ticket_code;
    private Date payment_date;
    private Time payment_time;
    private double price;
    @Enumerated(EnumType.STRING)
    private PaymentType payment_type;

}
