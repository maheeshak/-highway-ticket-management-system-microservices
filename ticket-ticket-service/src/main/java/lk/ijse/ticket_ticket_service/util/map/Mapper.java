package lk.ijse.ticket_ticket_service.util.map;


import lk.ijse.ticket_ticket_service.dto.TicketDTO;
import lk.ijse.ticket_ticket_service.entity.TicketEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Mapper {
    private final ModelMapper map;


    public TicketEntity toTicketEntity(TicketDTO ticketDTO) {
        return map.map(ticketDTO, TicketEntity.class);
    }

    public TicketDTO mapToTicketDTO(TicketEntity ticketEntity) {
        return map.map(ticketEntity, TicketDTO.class);
    }

    public List<TicketDTO> mapToTicketDTOS(List<TicketEntity> ticketEntities) {
        return map.map(ticketEntities, List.class);
    }
}
