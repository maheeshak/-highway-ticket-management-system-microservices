package lk.ijse.ticket_payment_service.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.ticket_payment_service.dto.PaymentDTO;
import lk.ijse.ticket_payment_service.entity.PaymentEntity;
import lk.ijse.ticket_payment_service.repo.PaymentRepo;
import lk.ijse.ticket_payment_service.service.PaymentService;
import lk.ijse.ticket_payment_service.util.convertor.Convertor;
import lk.ijse.ticket_payment_service.util.map.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceIMPL implements PaymentService<PaymentDTO, String> {

    private final Mapper map;
    private final PaymentRepo paymentRepo;
    private final Convertor converter;

    @Override
    public void save(PaymentDTO paymentDTO) {
        paymentRepo.save(map.toPaymentEntity(paymentDTO));
    }

    @Override
    public void update(PaymentDTO paymentDTO) {
        Optional<PaymentEntity> tmpPaymentEntity = paymentRepo.findById(paymentDTO.getPayment_code());
        if (tmpPaymentEntity.isPresent()) {
            PaymentEntity paymentEntity = tmpPaymentEntity.get();
            converter.updatePaymentEntity(paymentDTO, paymentEntity);

        }
    }



    @Override
    public void delete(String payment_code) {
        Optional<PaymentEntity> userEntity = paymentRepo.findById(payment_code);
        if (userEntity.isPresent()) {
           paymentRepo.deleteById(payment_code);
       }
    }

    @Override
    public PaymentDTO search(String vehicle_code) {
        Optional<PaymentEntity> userEntity = paymentRepo.findById(vehicle_code);
        if (userEntity.isPresent()) {
            return map.mapToPaymentDTO(userEntity.get());
        }
        throw new RuntimeException("No Payment for Vehicle Code: " + vehicle_code);

    }

    @Override
    public List<PaymentDTO> getAll() {
        List<PaymentEntity> all = paymentRepo.findAll();
        if (all == null) {
            return null;
        }
        return map.mapToPaymentDTOS(all);
    }


    @Override
    public boolean isExist(String vehicle_code) {
        return paymentRepo.existsById(vehicle_code);
    }

}
