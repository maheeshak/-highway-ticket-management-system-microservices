package lk.ijse.ticket_ticket_service.util.converter;


import lk.ijse.ticket_ticket_service.dto.TicketDTO;
import lk.ijse.ticket_ticket_service.entity.TicketEntity;
import lk.ijse.ticket_ticket_service.util.Status;
import org.springframework.stereotype.Component;

@Component
public class Converter {


    public void updateTicketEntity(TicketDTO ticketDTO, TicketEntity ticketEntity) {


            ticketEntity.setEndTime(ticketDTO.getEndTime());
            ticketEntity.setEndLocation(ticketDTO.getEndLocation());
            ticketEntity.setPrice(ticketDTO.getPrice());
            ticketEntity.setKilometer(ticketDTO.getKilometer());
            ticketEntity.setStatus(Status.NOT_AVAILABLE);

    }
}
