package lk.ijse.ticket_user_service.repo;


import lk.ijse.ticket_user_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, String> {

    @Query(value = "SELECT * FROM users WHERE user_code = ?1 AND status = 'AVAILABLE'", nativeQuery = true)
    UserEntity findByIdAndStatus(String userCode);
}
