package lk.ijse.ticket_user_service.util.map;

import lk.ijse.ticket_user_service.dto.LoginDTO;
import lk.ijse.ticket_user_service.dto.UserDTO;
import lk.ijse.ticket_user_service.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Mapper {
    private final ModelMapper map;


    public UserDTO mapToUserDTO(UserEntity userEntity){
        return map.map(userEntity, UserDTO.class);
    }

    public UserEntity mapToUserEntity(UserDTO userDTO){
        return map.map(userDTO, UserEntity.class);
    }


    public List<UserDTO> mapToUserDTOs(List<UserEntity> userEntities) {
        return map.map(userEntities, List.class);
    }

    public LoginDTO mapToLoginDTO(UserEntity user) {
        return map.map(user, LoginDTO.class);
    }
}
