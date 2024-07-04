package lk.ijse.ticket_ticket_service.entity;

import jakarta.persistence.*;
import lk.ijse.ticket_ticket_service.util.Status;
import lk.ijse.ticket_ticket_service.util.location.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ticket")
public class TicketEntity {
    @Id
    private String ticket_code;
    private String vehicle_code;
    private LocalDate issueDate;
    private Time issueTime;
    private Time endTime;
    private Location startLocation;
    private Location endLocation;
    private Double kilometer;
    @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE;
    private Double price;
    @Enumerated(EnumType.STRING)
    private Status payementStatus = Status.NOT_PAYED;
}
