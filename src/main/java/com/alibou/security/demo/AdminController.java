package com.alibou.security.demo;

import com.alibou.security.DTO.ChuaDTO;
import com.alibou.security.DTO.DaoTrangDTO;
import com.alibou.security.models.KieuThanhViens;
import com.alibou.security.service.AdminService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.naming.spi.ResolveResult;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public String get() {
        return "GET:: admin controller";
    }
    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    @Hidden
    public String post() {
        return "POST:: admin controller";
    }
    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    @Hidden
    public String put() {
        return "PUT:: admin controller";
    }
    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    @Hidden
    public String delete() {
        return "DELETE:: admin controller";
    }

    @PostMapping("/create-kieu-thanh-vien")
    public ResponseEntity<String> themkieuthanvien(@RequestBody String kieuThanhViens){
        return new ResponseEntity<>(adminService.themkieuthanhvien(kieuThanhViens),HttpStatus.OK);
    }


//    @GetMapping("/paging")
    @PostMapping("/create-chua")
    public ResponseEntity<String> ceateChua(@RequestBody ChuaDTO dto){
        return new ResponseEntity<>(adminService.themchua(dto), HttpStatus.OK);
    }
    @PutMapping("/edit-chua")
    public ResponseEntity<String> eaditChua(@RequestBody ChuaDTO dto){
        return new ResponseEntity<>(adminService.suachua(dto), HttpStatus.OK);
    }
    @DeleteMapping("/delete-chua")
    public ResponseEntity<String> deleteChua(@RequestParam Integer id){
        return new ResponseEntity<>(adminService.xoachua(id),HttpStatus.OK);
    }





    @PostMapping("/create-dao-trang")
    public ResponseEntity<String> createDaoTrang(@RequestBody DaoTrangDTO dto){
        return new ResponseEntity<>(adminService.themDaoTrang(dto),HttpStatus.OK);
    }
    @PutMapping("/edit-dao-trang")
    public ResponseEntity<String> editDaoTrang(@RequestBody DaoTrangDTO dto){
        return new ResponseEntity<>(adminService.suadaotrang(dto),HttpStatus.OK);
    }
    @DeleteMapping("/delete-dao-trang")
    public ResponseEntity<String> deleteDaoTrang(@RequestParam Integer id){
        return new ResponseEntity<>(adminService.xoadaotrang(id),HttpStatus.OK);
    }


    @PutMapping("/confirm-from")
    public ResponseEntity<String> confirm(@RequestParam Integer id){
        return new ResponseEntity<>(adminService.xacnhandon(id),HttpStatus.OK);
    }
}
