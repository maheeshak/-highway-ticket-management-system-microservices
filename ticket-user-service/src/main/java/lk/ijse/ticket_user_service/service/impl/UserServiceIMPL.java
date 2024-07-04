package lk.ijse.ticket_user_service.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.ticket_user_service.dto.LoginDTO;
import lk.ijse.ticket_user_service.dto.UserDTO;
import lk.ijse.ticket_user_service.entity.UserEntity;
import lk.ijse.ticket_user_service.repo.UserRepo;
import lk.ijse.ticket_user_service.service.UserService;
import lk.ijse.ticket_user_service.util.Status;
import lk.ijse.ticket_user_service.util.converter.Converter;
import lk.ijse.ticket_user_service.util.map.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService<UserDTO, String> {

    private final UserRepo userRepo;
    private final Mapper mapper;
    private final Converter converter;

    @Override
    public void save(UserDTO userDTO) {
        userRepo.save(mapper.mapToUserEntity(userDTO));
    }

    @Override
    public void update(UserDTO userDTO) {
        Optional<UserEntity> optionalUser = userRepo.findById(userDTO.getUser_code());

        if (optionalUser.isPresent()) {

            UserEntity existingUser = optionalUser.get();
            converter.updateUserEntity(userDTO, existingUser);

        } else {
            throw new RuntimeException("No User for Update..!");
        }
    }


    @Override
    public void delete(String user_code) {
        Optional<UserEntity> userEntity = userRepo.findById(user_code);
        if (userEntity.isPresent()) {
            userEntity.get().setStatus(Status.NOT_AVAILABLE);
        } else {
            throw new RuntimeException("No User for Delete..!");

        }

    }

    @Override
    public UserDTO search(String user_code) {
        UserEntity userEntity = userRepo.findByIdAndStatus(user_code);
        if (!(userEntity == null)) {
            return mapper.mapToUserDTO(userEntity);
        }
        throw new RuntimeException("No User for Search..!");
    }

    @Override
    public List<UserDTO> getAll() {

        List<UserEntity> userEntities = userRepo.findAll();
        if (!(userEntities.isEmpty())) {
            return mapper.mapToUserDTOs(userEntities);
        }
        throw new RuntimeException("No Users Found..!");
    }

    @Override
    public boolean isExist(String user_code) {
        return userRepo.existsById(user_code);


    }

    @Override
    public LoginDTO login(LoginDTO loginDTO) {
        Optional<UserEntity> userEntity = userRepo.findById(loginDTO.getUser_code());
        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            if (user.getPassword().equals(loginDTO.getPassword())) {
                return loginDTO;
            }
        }
        throw new RuntimeException("Invalid User Credentials..!");
    }
}
