package lk.ijse.ticket_user_service.service;

import lk.ijse.ticket_user_service.dto.LoginDTO;
import lk.ijse.ticket_user_service.dto.UserDTO;

import java.util.List;

public interface UserService<T,ID> {
    void save(T dto);
    void update(T dto);
    void delete(ID id);
    T search(ID id);
    List<T> getAll();

    boolean isExist(String id);

    LoginDTO login(LoginDTO dto);
}
