package com.CapStone.Backend.Repository;

import com.CapStone.Backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>  {

    Optional<User> findByUserEmail(String email);
    Optional<User> findByUserId(Long id);

}
