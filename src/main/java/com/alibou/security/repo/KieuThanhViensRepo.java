package com.alibou.security.repo;

import com.alibou.security.models.KieuThanhViens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KieuThanhViensRepo extends JpaRepository<KieuThanhViens,Integer> {
}
