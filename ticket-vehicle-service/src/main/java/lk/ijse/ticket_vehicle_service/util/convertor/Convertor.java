package lk.ijse.ticket_vehicle_service.util.convertor;

import lk.ijse.ticket_vehicle_service.dto.VehicleDTO;
import lk.ijse.ticket_vehicle_service.entity.VehicleEntity;
import org.springframework.stereotype.Component;

@Component
public class Convertor {

    public void updateVehicleEntity(VehicleDTO vehicleDTO, VehicleEntity vehicleEntity) {

        vehicleEntity.setColor(vehicleDTO.getColor());
        vehicleEntity.setFuel_type(vehicleDTO.getFuel_type());
        vehicleEntity.setModel(vehicleDTO.getModel());
        vehicleEntity.setNumber_plate(vehicleDTO.getNumber_plate());
        vehicleEntity.setUser_code(vehicleDTO.getUser_code());
        vehicleEntity.setYear(vehicleDTO.getYear());

    }
}
