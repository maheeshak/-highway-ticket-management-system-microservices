package lk.ijse.ticket_vehicle_service.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.ticket_vehicle_service.dto.VehicleDTO;
import lk.ijse.ticket_vehicle_service.entity.VehicleEntity;
import lk.ijse.ticket_vehicle_service.repo.VehicleRepo;
import lk.ijse.ticket_vehicle_service.service.VehicleService;
import lk.ijse.ticket_vehicle_service.util.Status;
import lk.ijse.ticket_vehicle_service.util.convertor.Convertor;
import lk.ijse.ticket_vehicle_service.util.map.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceIMPL implements VehicleService<VehicleDTO, String> {

    private final Mapper map;
    private final VehicleRepo vehicleRepo;
    private final Convertor converter;

    @Override
    public void save(VehicleDTO vehicleDTO) {
        vehicleRepo.save(map.toVehicleEntity(vehicleDTO));
    }

    @Override
    public void update(VehicleDTO vehicleDTO) {
        Optional<VehicleEntity> tmpVehicleEntity = vehicleRepo.findById(vehicleDTO.getVehicle_code());
        if (tmpVehicleEntity.isPresent()) {
            VehicleEntity vehicleEntity = tmpVehicleEntity.get();
            converter.updateVehicleEntity(vehicleDTO, vehicleEntity);

        }
    }

    @Override
    public void delete(String vehicle_code) {
        Optional<VehicleEntity> userEntity = vehicleRepo.findById(vehicle_code);
        if (userEntity.isPresent()) {
            userEntity.get().setStatus(Status.NOT_AVAILABLE);
       }



    }

    @Override
    public VehicleDTO search(String vehicle_code) {
          VehicleEntity vehicleEntity = vehicleRepo.findByIdAndStatus(vehicle_code, Status.AVAILABLE);
        if (vehicleEntity == null) {
            return null;
        }
        return map.mapToVehicleDTO(vehicleEntity);
    }

    @Override
    public List<VehicleDTO> getAll() {
        List<VehicleEntity> vehicleEntities = vehicleRepo.findAll();
        if (!(vehicleEntities.isEmpty())) {
            return map.mapToVehicleDTOS(vehicleEntities);
        }
        throw new RuntimeException("No Users Found..!");
    }

    @Override
    public boolean isExist(String vehicle_code) {
        return vehicleRepo.existsById(vehicle_code);
    }
}
