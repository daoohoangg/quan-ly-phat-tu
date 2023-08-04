package com.alibou.security.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "dondangkys")
public class DonDangKys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dondangkyid;
    private LocalDate ngayguidon;
    private LocalDate ngayxuly;
    private Integer nguoixuly;
    private Integer trangthaidon;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daotrangid")
    public DaoTrangs daoTrangs;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phattuid")
    public User user;
}
