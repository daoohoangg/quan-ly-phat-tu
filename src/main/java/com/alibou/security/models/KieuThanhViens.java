package com.alibou.security.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "kieuthanhviens")
public class KieuThanhViens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer kieuthanhvienid;
    private String code;
    private String tenkieu;
    @OneToMany(mappedBy = "kieuThanhViens")
    private List<User> userList;
}
