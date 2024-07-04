package lk.ijse.ticket_payment_service.controller;

import lk.ijse.ticket_payment_service.dto.PaymentDTO;
import lk.ijse.ticket_payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final RestTemplate restTemplate;

    @GetMapping("/health")
    public String health() {
        return "Payment Service is Running";
    }

    @PostMapping
    public ResponseEntity<String> savePayment(@RequestBody PaymentDTO paymentDTO){
        try {
            String ticket_code = paymentDTO.getTicket_code();
            String ticketServiceUrl = "http://ticket-ticket-service/api/v1/ticket/exist/" + ticket_code;

            Boolean isExist = restTemplate.getForObject(ticketServiceUrl, Boolean.class);

            if (!isExist) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticket Not Found");
            }
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticket Service Not Available!\n" + exception.getMessage()
                    +'\n'+"Hint: Check Ticket Service is Running or Not!");
        }


        try {
            paymentService.save(paymentDTO);


            String ticket_code = paymentDTO.getTicket_code();
            String ticketServiceUrl = "http://ticket-ticket-service/api/v1/ticket/updateStatus/" + ticket_code;

            restTemplate.getForObject(ticketServiceUrl,String.class);

            return ResponseEntity.status(HttpStatus.CREATED).body("Payment Saved");


        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Payment Save Failed!\n" + exception.getMessage());
        }

    }

    @PutMapping
    public ResponseEntity<String> updatePayment(@RequestBody PaymentDTO paymentDTO){
        try {
            String ticket_code = paymentDTO.getTicket_code();
            String ticketServiceUrl = "http://ticket-ticket-service/api/v1/ticket/exist/" + ticket_code;

            Boolean isExist = restTemplate.getForObject(ticketServiceUrl, Boolean.class);

            if (!isExist) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticket Not Found");
            }
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticket Service Not Available!\n" + exception.getMessage()
                    +'\n'+"Hint: Check Ticket Service is Running or Not!");
        }

        try {
            paymentService.update(paymentDTO);
            return ResponseEntity.ok("Payment Updated");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Payment Update Failed!\n" + exception.getMessage());
        }
    }

    @DeleteMapping("/{payment_code}")
    public ResponseEntity<String> deletePayment(@PathVariable String payment_code){
        try {
            paymentService.delete(payment_code);
            return ResponseEntity.ok("Payment Deleted");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Payment Delete Failed!\n" + exception.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<PaymentDTO> searchPayment(@RequestParam String payment_code){
        try {
            PaymentDTO paymentDTO = (PaymentDTO) paymentService.search(payment_code);
            return ResponseEntity.ok(paymentDTO);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPayments(){
        try {
            return ResponseEntity.ok(paymentService.getAll());
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/exist/{payment_code}")
    public ResponseEntity<Boolean> isPaymentExist(@PathVariable String payment_code){
        return ResponseEntity.ok(paymentService.isExist(payment_code));
    }
}
