package com.alibou.security.models;

import com.alibou.security.token.Token;
import com.fasterxml.jackson.databind.util.LookupCache;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "daotrangs")
public class DaoTrangs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer daotrangid;
    private boolean daketthuc=false;
    private String noidung;
    private String noitochuc;
    private Integer sothanhvienthamgia;
    private LocalDate thoigiantochuc;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nguoiTruTri")
    public User users;
    @OneToMany(mappedBy = "daoTrangs")
    private List<DonDangKys> donDangKys;
    @OneToMany(mappedBy = "daoTrangs")
    public List<PhatTuDaoTrangs> phatTuDaoTrangs;
}
