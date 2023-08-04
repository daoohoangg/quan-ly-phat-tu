package com.alibou.security.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "phattudaotrangs")
@NoArgsConstructor
@AllArgsConstructor
public class PhatTuDaoTrangs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer phattudaotrangid;
    private boolean daThamGia;
    private String lyDoKhongThamGia;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daotrangid")
    public DaoTrangs daoTrangs;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phattuid")
    public User user;
}
