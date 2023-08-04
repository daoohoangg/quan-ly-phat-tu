package com.alibou.security.service;


import com.alibou.security.DTO.*;
import com.alibou.security.mail.EmailUtil;
import com.alibou.security.mail.OtpUtil;
import com.alibou.security.models.Chuas;
import com.alibou.security.models.DaoTrangs;
import com.alibou.security.models.DonDangKys;
import com.alibou.security.models.User;
import com.alibou.security.repo.ChuasRepo;
import com.alibou.security.repo.DaoTrangsRepo;
import com.alibou.security.repo.DonDangKysRepo;
import com.alibou.security.repo.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    DaoTrangsRepo daoTrangsRepo;
    @Autowired
    ChuasRepo chuasRepo;
    @Autowired
    DonDangKysRepo donDangKysRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public String regenerateOtp(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }
        user.setOtp(otp);
        userRepository.save(user);
        return "Email sent... please verify account within 1 minute";
    }

    public String changePassword(String otp,String password) {
        User user = userRepository.findByOtp(otp)
                .orElseThrow(() -> new RuntimeException());
        if(user.getOtp().equals(otp) && !user.getPassword().equals(password)
//            && Duration.between(user.getOtpGeneratedTime(),
//            LocalDateTime.now()).getSeconds() < (1 * 60)
        ){
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            return "Change password Successfully!";
        }
        else if(!user.getOtp().equals(otp)
//            || Duration.between(user.getOtpGeneratedTime(),
//            LocalDateTime.now()).getSeconds() > (1 * 60)
        ){
            return "OTP is invaild";
        }
        else if(user.getPassword().equals(password)){
            return "Password and old password are the same !";
        }
        return null;
    }

    public String changePass(ChangePassDTO dto) {
        User user = userRepository.findByEmail(dto.email)
                .orElseThrow(()->new RuntimeException("Email is not match with account!"));
        if(passwordEncoder.matches(dto.oldpass, user.getPassword())){
            String endcode=passwordEncoder.encode(dto.newpass);
            user.setPassword(endcode);
            userRepository.save(user);
            return  "Change password successfully!";
        } else {
            throw new IllegalArgumentException("Incorrect password");
        }
        }

    public String disableAcc(Integer id) {
        User user= userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("khong tim thay phat tu can sua!"));
        user.setActive(false);
        userRepository.save(user);
        return "Xoa phat tu thannh cong!";
    }

    public String enableAcc(Integer id) {
        User user= userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("khong tim thay phat tu can sua!"));
        user.setActive(true);
        userRepository.save(user);
        return "OK";
    }

    public String create(DonDangKysDTO dto) {
        DonDangKys donDangKys = new DonDangKys();
        if(daoTrangsRepo.findById(dto.getPhatTu().getPhattuid()).isPresent()&&
                userRepository.existsById(dto.getPhatTu().getPhattuid())){
        donDangKys.setNgayguidon(LocalDate.now());
        donDangKys.setUser(dto.getPhatTu());
        donDangKys.setDaoTrangs(dto.getDaoTrangs());
        donDangKys.setTrangthaidon(0);
        donDangKysRepo.save(donDangKys);
        return "them thanh cong";}
        return "them that bai";
    }
    public List<PhatTuDTO> paging(Integer page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<User> users = userRepository.findAll(pageable);
        List<PhatTuDTO> phatTuDTOList=new ArrayList<>();
        for (User y:users.getContent()
             ) {
            PhatTuDTO phatTuDTO = new PhatTuDTO();
            phatTuDTO.setHo(y.getHo());
            phatTuDTO.setEmail(y.getEmail());
            phatTuDTO.setGioiTinh(y.getGioiTinh());
            phatTuDTO.setActive(y.isActive());
            phatTuDTO.setDaHoanTuc(y.isDaHoanTuc());
            phatTuDTO.setSoDienThoai(y.getSoDienThoai());
            phatTuDTO.setNgaySinh(y.getNgaySinh());
            phatTuDTOList.add(phatTuDTO);
        }
        return phatTuDTOList;
    }
    public List<ChuaDTO> pagingchua(Integer page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<Chuas> chuas = chuasRepo.findAll(pageable);
        List<ChuaDTO> chuaDTOList=new ArrayList<>();
        for (Chuas y:chuas.getContent()
        ) {
            ChuaDTO chuaDTO = new ChuaDTO();
            chuaDTO.setDiachi(y.getDiachi());
            chuaDTO.setTrutri(y.getTrutri());
            chuaDTO.setTenchua(y.getTenchua());
            chuaDTOList.add(chuaDTO);
        }
        return chuaDTOList;
    }

    public List<DaoTrangDTO> pagingdaotrang(Integer page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<DaoTrangs> daoTrangs = daoTrangsRepo.findAll(pageable);
        List<DaoTrangDTO> daoTrangDTOList=new ArrayList<>();
        for (DaoTrangs y:daoTrangs.getContent()
        ) {
            DaoTrangDTO daoTrangDTO = new DaoTrangDTO();
            daoTrangDTO.setTrutri(y.getUsers());
            daoTrangDTO.setNoitochuc(y.getNoitochuc());
            daoTrangDTO.setNoidung(y.getNoidung());
            daoTrangDTOList.add(daoTrangDTO);
        }
        return daoTrangDTOList;
    }
}


