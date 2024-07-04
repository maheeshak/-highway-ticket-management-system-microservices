package lk.ijse.ticket_user_service.controller;


import lk.ijse.ticket_user_service.dto.LoginDTO;
import lk.ijse.ticket_user_service.dto.UserDTO;
import lk.ijse.ticket_user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/health")
    public String health() {
        return "User Service is Running";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
            LoginDTO login = userService.login(loginDTO);
            return ResponseEntity.status(HttpStatus.OK).body(login);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO) {

        try {
            userService.save(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("User Saved");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Save Failed!\nexception :" + exception.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) {
        try {

            userService.update(userDTO);
            return ResponseEntity.status(HttpStatus.OK).body("User Updated");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Update Failed!\nexception :" + exception.getMessage());
        }
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteUser(@RequestParam String user_code) {
        try {

            userService.delete(user_code);
            return ResponseEntity.status(HttpStatus.OK).body("User Deleted");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Delete Failed!\nexception :" + exception.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<UserDTO> searchUser(@RequestParam String user_code) {
        try {

            UserDTO userDTO = (UserDTO) userService.search(user_code);
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            List<UserDTO> all = userService.getAll();
            return ResponseEntity.status(HttpStatus.OK).body(all);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/exist/{id}")
    public ResponseEntity<Boolean> isExist(@PathVariable String id) {
        try {
            boolean exist = userService.isExist(id);
            return ResponseEntity.status(HttpStatus.OK).body(exist);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }
}
