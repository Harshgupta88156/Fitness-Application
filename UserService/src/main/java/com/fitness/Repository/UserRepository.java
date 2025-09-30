package com.fitness.Repository;

import com.fitness.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository  extends JpaRepository<User, String> {

    Boolean existsByEmail(String email);
    Boolean existsBykeyCloakId(String email);

    User findByEmail(@NotBlank(message = "Email is required") @Email(message = "Entered email is not correct") String email);
}


