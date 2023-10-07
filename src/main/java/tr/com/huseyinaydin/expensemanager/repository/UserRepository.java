package tr.com.huseyinaydin.expensemanager.repository;

import tr.com.huseyinaydin.expensemanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //SELECT * FROM tbl_users WHERE email = ?
    Optional<User> findByEmail(String email);
}