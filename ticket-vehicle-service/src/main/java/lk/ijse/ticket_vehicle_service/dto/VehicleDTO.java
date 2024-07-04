package lk.ijse.ticket_vehicle_service.dto;


import lk.ijse.ticket_vehicle_service.util.FuelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDTO implements SuperDTO{
    private String vehicle_code;
    private String user_code;
    private String number_plate;
    private String model;
    private FuelType fuel_type;
    private String year;
    private String color;

}
