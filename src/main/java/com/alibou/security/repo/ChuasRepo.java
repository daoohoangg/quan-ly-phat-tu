package com.alibou.security.repo;

import com.alibou.security.models.Chuas;
import com.alibou.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChuasRepo extends JpaRepository<Chuas, Integer> {
}
