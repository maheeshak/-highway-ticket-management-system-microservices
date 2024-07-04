package lk.ijse.ticket_ticket_service.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.ticket_ticket_service.dto.TicketDTO;
import lk.ijse.ticket_ticket_service.entity.TicketEntity;
import lk.ijse.ticket_ticket_service.repo.TicketRepo;
import lk.ijse.ticket_ticket_service.service.TicketService;
import lk.ijse.ticket_ticket_service.util.Status;
import lk.ijse.ticket_ticket_service.util.converter.Converter;
import lk.ijse.ticket_ticket_service.util.location.CostCalculator;
import lk.ijse.ticket_ticket_service.util.map.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketServiceIMPL implements TicketService<TicketDTO, String> {
    private final TicketRepo ticketRepo;
    private final Mapper map;
    private final Converter converter;


    @Override
    public void save(TicketDTO ticketDTO) {
        if (ticketRepo.existsById(ticketDTO.getTicket_code())) {
            throw new RuntimeException("Ticket Already Exist..!");
        }

        ticketRepo.save(map.toTicketEntity(ticketDTO));


    }

    @Override
    public void update(TicketDTO ticketDTO) {
        Optional<TicketEntity> tempTicketEntity = ticketRepo.findById(ticketDTO.getTicket_code());
        if (tempTicketEntity.isPresent()) {
            if (tempTicketEntity.get().getStatus().equals(Status.NOT_AVAILABLE)) {
                throw new RuntimeException("This Ticket is Not Available..!");
            }
            TicketEntity ticketEntity = tempTicketEntity.get();
            double price = CostCalculator.calculateCost(ticketDTO.getStartLocation(), ticketDTO.getEndLocation());
            double distance = CostCalculator.getDistance(ticketDTO.getStartLocation(), ticketDTO.getEndLocation());
            ticketDTO.setPrice(price);
            ticketDTO.setKilometer(distance);
            converter.updateTicketEntity(ticketDTO, ticketEntity);


        }else {

            throw new RuntimeException("No Ticket for Update..!");
        }
    }

    @Override
    public void delete(String s) {
        Optional<TicketEntity> ticketEntity = ticketRepo.findById(s);
        if (ticketEntity.isPresent()) {
            ticketEntity.get().setStatus(Status.NOT_AVAILABLE);
        }
        throw new RuntimeException("No Ticket for Delete..!");

    }

    @Override
    public TicketDTO search(String vehicle_code) {
        TicketEntity ticketEntity = ticketRepo.findByIdAndStatus(vehicle_code, Status.AVAILABLE);
        if (ticketEntity == null) {
            throw new RuntimeException("No Ticket Found..!");

        }
        return map.mapToTicketDTO(ticketEntity);
    }

    @Override
    public boolean isExist(String vehicle_code) {
        return ticketRepo.existsById(vehicle_code);
    }

    @Override
    public List<TicketDTO> getAll() {
        List<TicketEntity> ticketEntities = ticketRepo.findAll();
        if (!(ticketEntities.isEmpty())) {
            return map.mapToTicketDTOS(ticketEntities);
        }
        throw new RuntimeException("No Tickets Found..!");
    }

    @Override
    public void updateStatus(String ticketCode) {
        TicketEntity ticketEntity = ticketRepo.findById(ticketCode).get();
        if (ticketEntity == null) {
            throw new RuntimeException("No Ticket Found..!");
        }
        ticketEntity.setStatus(Status.NOT_AVAILABLE);
        ticketEntity.setPayementStatus(Status.PAYED);
    }
}
