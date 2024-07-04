package lk.ijse.ticket_vehicle_service.entity;

import jakarta.persistence.*;
import lk.ijse.ticket_vehicle_service.util.FuelType;
import lk.ijse.ticket_vehicle_service.util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "vehicle")
public class VehicleEntity {
    @Id
    private String vehicle_code;
    private String number_plate;
    private String model;
    @Enumerated(EnumType.STRING)
    private FuelType fuel_type;
    private String year;
    private String color;
    private String user_code;
    @Enumerated(EnumType.STRING)
    private Status status=Status.AVAILABLE;

}
