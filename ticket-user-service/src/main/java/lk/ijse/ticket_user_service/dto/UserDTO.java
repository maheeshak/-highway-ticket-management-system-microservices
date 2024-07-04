package lk.ijse.ticket_user_service.dto;


import lk.ijse.ticket_user_service.util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO implements SuperDTO{
    private String user_code;
    private String password;
    private String name;
    private String email;
    private String contact;
    private String address;
    private String nic;
    private LocalDate registrationDate;
    private Status status = Status.AVAILABLE;
}
