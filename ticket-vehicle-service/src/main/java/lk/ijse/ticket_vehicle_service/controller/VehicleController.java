package lk.ijse.ticket_vehicle_service.controller;

import lk.ijse.ticket_vehicle_service.dto.VehicleDTO;
import lk.ijse.ticket_vehicle_service.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;
    private final RestTemplate restTemplate;

    @GetMapping("/health")
    public String health() {
        return "Vehicle Service is Running";
    }

    @PostMapping()
    public ResponseEntity<String> saveVehicle(@RequestBody VehicleDTO vehicleDTO) {
        try {
            String userId = vehicleDTO.getUser_code();
            String userServiceUrl = "http://ticket-user-service/api/v1/user/exist/" + userId;

            Boolean isExist = restTemplate.getForObject(userServiceUrl, Boolean.class);

            if (!isExist) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Not Found");
            }
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Service Not Available!\nexception :" + exception.getMessage()
            +'\n'+"Hint: Check User Service is Running or Not!");
        }


        try {
            vehicleService.save(vehicleDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Vehicle Saved");
        } catch (Exception exception) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle Save Failed!\nexception :" + exception.getMessage());
        }


    }

    @PutMapping
    public ResponseEntity<String> updateVehicle(@RequestBody VehicleDTO vehicleDTO) {

        String userId = vehicleDTO.getUser_code();
        String userServiceUrl = "http://ticket-user-service/api/v1/user/exist/" + userId;

        Boolean isExist = restTemplate.getForObject(userServiceUrl, Boolean.class);

        if (!isExist) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Not Found");
        }
        try {
            vehicleService.update(vehicleDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Vehicle Updated");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle Update Failed!\nexception: " + exception.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteVehicle(@RequestParam String vehicle_code) {
        try {
            vehicleService.delete(vehicle_code);
            return ResponseEntity.status(HttpStatus.OK).body("Vehicle Deleted");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle Delete Failed!\nexception: " + exception.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<VehicleDTO> searchVehicle(@RequestParam String vehicle_code) {
        try {
            VehicleDTO vehicleDTO = (VehicleDTO) vehicleService.search(vehicle_code);
            return ResponseEntity.status(HttpStatus.OK).body(vehicleDTO);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        try {
            List<VehicleDTO> vehicleServiceAll = vehicleService.getAll();
            return ResponseEntity.status(HttpStatus.OK).body(vehicleServiceAll);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/exist/{vehicle_code}")
    public ResponseEntity<Boolean> isExist(@PathVariable String vehicle_code) {
        try {
            boolean exist = vehicleService.isExist(vehicle_code);
            return ResponseEntity.status(HttpStatus.OK).body(exist);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }


}
