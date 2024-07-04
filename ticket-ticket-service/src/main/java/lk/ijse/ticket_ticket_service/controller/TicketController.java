package lk.ijse.ticket_ticket_service.controller;


import lk.ijse.ticket_ticket_service.dto.TicketDTO;
import lk.ijse.ticket_ticket_service.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final RestTemplate restTemplate;

    @GetMapping("/health")
    public String health() {
        return "Ticket Service is Running";
    }

    @PostMapping()
    public ResponseEntity<String> saveTicket(@RequestBody TicketDTO ticketDTO) {

        String vehicle_code = ticketDTO.getVehicle_code();
        String vehicleServiceUrl = "http://ticket-vehicle-service/api/v1/vehicle/exist/" + vehicle_code;
        Boolean isExist = restTemplate.getForObject(vehicleServiceUrl, Boolean.class);

        if (!isExist) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle Not Found");
        }
        try {
            ticketService.save(ticketDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Ticket Saved");
        } catch (Exception exception) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticket Save Failed!\nexception :" + exception.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> updateTicket(@RequestBody TicketDTO ticketDTO) {

        String vehicle_code = ticketDTO.getVehicle_code();
        String vehicleServiceUrl = "http://ticket-vehicle-service/api/v1/vehicle/exist/" + vehicle_code;
        Boolean isExist = restTemplate.getForObject(vehicleServiceUrl, Boolean.class);


        if (!isExist) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle Not Found");
        }
        try {
            ticketService.update(ticketDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Ticket Updated");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticket Update Failed!\nexception :" + exception.getMessage());
        }
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteTicket(@RequestParam String ticket_code) {
        try {
            ticketService.delete(ticket_code);
            return ResponseEntity.status(HttpStatus.OK).body("Ticket Deleted");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticket Delete Failed");
        }
    }

    @GetMapping()
    public ResponseEntity<TicketDTO> searchTicket(@RequestParam String ticket_code) {
        try {
            TicketDTO ticketDTO = (TicketDTO) ticketService.search(ticket_code);
            return ResponseEntity.status(HttpStatus.OK).body(ticketDTO);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        try {
            List<TicketDTO> all = ticketService.getAll();
            return ResponseEntity.status(HttpStatus.OK).body(all);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/exist/{id}")
    public ResponseEntity<Boolean> isExist(@PathVariable String id) {
        try {
            boolean exist = ticketService.isExist(id);
            return ResponseEntity.status(HttpStatus.OK).body(exist);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }

    @GetMapping("/updateStatus/{ticket_code}")
    public ResponseEntity<String> updateStatus(@PathVariable String ticket_code) {
        try {
            ticketService.updateStatus(ticket_code);
            return ResponseEntity.status(HttpStatus.OK).body("Ticket Status Updated");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticket Status Update Failed");
        }
    }
}
