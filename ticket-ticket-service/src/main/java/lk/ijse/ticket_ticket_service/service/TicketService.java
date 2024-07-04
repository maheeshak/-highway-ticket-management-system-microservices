package lk.ijse.ticket_ticket_service.service;

import java.util.List;

public interface TicketService <T,ID>{
    void save(T t);

    void update(T t);

    void delete(ID id);

    T search(ID id);

    boolean isExist(ID id);

    List<T> getAll();

    void updateStatus(ID ticketCode);
}
