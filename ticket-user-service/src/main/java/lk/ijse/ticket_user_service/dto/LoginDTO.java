package lk.ijse.ticket_user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDTO implements SuperDTO{
    private String user_code;
    private String password;
}
