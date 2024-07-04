package lk.ijse.ticket_vehicle_service.util.map;

import lk.ijse.ticket_vehicle_service.dto.VehicleDTO;
import lk.ijse.ticket_vehicle_service.entity.VehicleEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final ModelMapper map;

    public VehicleEntity toVehicleEntity(VehicleDTO vehicleDTO) {
        return map.map(vehicleDTO, VehicleEntity.class);

    }

    public VehicleDTO mapToVehicleDTO(VehicleEntity vehicleEntity) {
        return map.map(vehicleEntity, VehicleDTO.class);
    }

    public List<VehicleDTO> mapToVehicleDTOS(List<VehicleEntity> all) {
        return map.map(all, List.class);
    }

}
