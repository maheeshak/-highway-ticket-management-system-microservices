package lk.ijse.ticket_payment_service.service;

import java.util.List;

public interface PaymentService<T, ID> {
    void save(T t);

    void update(T t);

    void delete(ID id);

    T search(ID id);

    List<T> getAll();

    boolean isExist(ID id);
}
