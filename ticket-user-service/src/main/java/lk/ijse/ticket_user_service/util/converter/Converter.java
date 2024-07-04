package lk.ijse.ticket_user_service.util.converter;

import lk.ijse.ticket_user_service.dto.UserDTO;
import lk.ijse.ticket_user_service.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public void updateUserEntity(UserDTO userDTO, UserEntity existingUser) {
        existingUser.setName(userDTO.getName());
        existingUser.setAddress(userDTO.getAddress());
        existingUser.setContact(userDTO.getContact());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setNic(userDTO.getNic());
        existingUser.setRegistrationDate(userDTO.getRegistrationDate());
    }
}
