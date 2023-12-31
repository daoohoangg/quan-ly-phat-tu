package com.alibou.security.repo;

import java.util.Optional;

import com.alibou.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

  Optional<User> findByOtp(String otp);
}
