package lk.ijse.ticket_user_service.entity;


import jakarta.persistence.*;
import lk.ijse.ticket_user_service.util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private String user_code;
    private String password;
    private String name;
    private String email;
    private String contact;
    private String address;
    private String nic;
    private LocalDate registrationDate;
    @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE;


}
