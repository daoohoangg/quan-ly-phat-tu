package com.alibou.security.models;

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
@Entity(name = "chuas")
public class Chuas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer chuaid;
    private LocalDate capnhat;
    private String diachi;
    private LocalDate ngaythanhlap;
    private String tenchua;
    private Integer trutri;
    @OneToMany(mappedBy = "chuas")
    private List<User> userList;
}
