package lk.ijse.ticket_ticket_service.dto;

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
public class TicketDTO implements SuperDTO{

    private String ticket_code;
    private String vehicle_code;
    private LocalDate issueDate;
    private Time issueTime;
    private Time endTime;
    private Location startLocation;
    private Location endLocation;
    private Double kilometer;
    private Status status;
    private Double price;
    private Status payementStatus;

}
