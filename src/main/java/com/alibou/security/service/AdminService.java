package com.alibou.security.service;

import com.alibou.security.DTO.ChuaDTO;
import com.alibou.security.DTO.DaoTrangDTO;
import com.alibou.security.models.*;
import com.alibou.security.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    ChuasRepo chuasRepo;
    @Autowired
    DaoTrangsRepo daoTrangsRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    KieuThanhViensRepo kieuThanhViensRepo;
    @Autowired
    DonDangKysRepo donDangKysRepo;
    private final PasswordEncoder passwordEncoder;

    public AdminService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String themchua(ChuaDTO dto) {
        Chuas chuas = new Chuas();
        Optional<Chuas> check= chuasRepo.findById(dto.trutri);
        if(check.isEmpty()){
            chuas.setTenchua(dto.tenchua);
            chuas.setCapnhat(LocalDate.now());
//            chuas.setNgaythanhlap(dto.ngaythanhlap);
            chuas.setTrutri(dto.trutri);
            chuas.setDiachi(dto.diachi);
            chuasRepo.save(chuas);
            return "them moi chua thanh cong!";
        }
        return "chua da ton tai tru tri!";
    }

    public String suachua(ChuaDTO dto) {
        Chuas chua= chuasRepo.findById(dto.trutri)
                .orElseThrow(()-> new RuntimeException("khong tim thay chua!"));
            chua.setTenchua(dto.tenchua);
            chua.setCapnhat(LocalDate.now());
            chua.setTrutri(dto.trutri);
            chua.setDiachi(dto.diachi);
            chuasRepo.save(chua);
            return "sua thong tin chua thanh cong!";
    }

    public String xoachua(Integer chuaid) {
        chuasRepo.deleteById(chuaid);
        return "xoa thanh cong!";
    }

    public String themDaoTrang(DaoTrangDTO dto) {
        DaoTrangs daoTrangs =new DaoTrangs();
        Optional<User> check= userRepository.findById(dto.trutri.getPhattuid());
        if(check.isEmpty()){
            daoTrangs.setDaketthuc(false);
            daoTrangs.setNoidung(dto.noidung);
            daoTrangs.setNoitochuc(dto.noitochuc);
//            daoTrangs.setThoigiantochuc(dto);
            daoTrangs.setUsers(dto.trutri);
            daoTrangsRepo.save(daoTrangs);
            return "them moi dao trang thanh cong!";
        }
        return "khong ton tai nguoi tru tri!";
    }


    public String themkieuthanhvien(String tenkieu) {
        KieuThanhViens newKieu= new KieuThanhViens();
        newKieu.setTenkieu(tenkieu);
        newKieu.setCode(passwordEncoder.encode(tenkieu));
        kieuThanhViensRepo.save(newKieu);
        return "them moi kieu thanh vien thanh cong!";
    }
//chua xong
    public String suadaotrang(DaoTrangDTO dto) {
        DaoTrangs daoTrangs= daoTrangsRepo.findById(dto.trutri.getPhattuid())
        .orElseThrow(()->new RuntimeException("Khonng tim thay dao trang"));
            daoTrangs.setDaketthuc(false);
            daoTrangs.setNoidung(dto.noidung);
            daoTrangs.setNoitochuc(dto.noitochuc);
//            daoTrangs.setThoigiantochuc(dto);
            daoTrangs.setUsers(dto.trutri);
            daoTrangsRepo.save(daoTrangs);
            return "sua dao trang thanh cong!";
    }

    public String xoadaotrang(Integer id) {
        daoTrangsRepo.deleteById(id);
        return "xoa thanh cong!";
    }

//    thieu nguoi xu ly
    public String xacnhandon(Integer id) {
        DonDangKys donDangKys= donDangKysRepo.findById(id)
                .orElseThrow(()->new RuntimeException("don dang ky khong co san"));
        donDangKys.setTrangthaidon(1);
        donDangKys.setNgayxuly(LocalDate.now());
//        donDangKys.setNguoixuly();
        Optional<DaoTrangs> daoTrangs= daoTrangsRepo.findById(donDangKys.getDaoTrangs().getDaotrangid());
        daoTrangs.get().setSothanhvienthamgia(daoTrangs.get().getSothanhvienthamgia()+1);
        daoTrangsRepo.save(daoTrangs.get());
        return "thanh cong!";
    }
}
