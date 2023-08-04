package com.alibou.security.DTO;

import lombok.Data;

import java.time.LocalDate;
@Data
public class PhatTuDTO {
    private Integer phattuid;
    private String ho;
    private String ten;
    private String email;
    private String anhChup;
    private boolean daHoanTuc;
    private String phapDanh;
    private Integer gioiTinh;
    private LocalDate ngayCapNhat;
    private LocalDate ngayHoanTuc;
    private LocalDate ngaySinh;
    private LocalDate ngayXuatGia;
    private String soDienThoai;
    private String tenDem;
    private boolean isActive=true;
}
