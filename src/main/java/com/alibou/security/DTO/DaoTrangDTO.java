package com.alibou.security.DTO;

import com.alibou.security.models.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DaoTrangDTO {
    public String noidung;
    public String noitochuc;
//    public LocalDate thoigiantochuc;
    public User trutri;
}
