package com.alibou.security.demo;

import com.alibou.security.DTO.ChuaDTO;
import com.alibou.security.DTO.DaoTrangDTO;
import com.alibou.security.DTO.DonDangKysDTO;
import com.alibou.security.DTO.PhatTuDTO;
import com.alibou.security.models.User;
import com.alibou.security.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logged-controller")
@Hidden
public class LoggedInController {

  @Autowired
  UserService userService;
  @GetMapping
  public ResponseEntity<String> sayHello() {
    return ResponseEntity.ok("okokok");
  }

  @PutMapping("/disable-account")
  public ResponseEntity<String> disableAcc(Integer id){
    return new ResponseEntity<>(userService.disableAcc(id), HttpStatus.OK);
  }
  @PutMapping("/enable-account")
  public ResponseEntity<String> enableAcc(Integer id){
    return new ResponseEntity<>(userService.enableAcc(id), HttpStatus.OK);
  }


  @PostMapping("/create-sign-form")
  public ResponseEntity<String> create(@RequestBody DonDangKysDTO dto){
    return new ResponseEntity<>(userService.create(dto),HttpStatus.OK);
  }


  @GetMapping("/page-phat-tu")
  public List<PhatTuDTO> phantrangphattu(@RequestParam Integer page){
    return userService.paging(page);
  }
  @GetMapping("/page-chua")
  public List<ChuaDTO> phantrangchua(@RequestParam Integer page){
    return userService.pagingchua(page);
  }
  @GetMapping("/page-dao-trang")
  public List<DaoTrangDTO> phantrangdaotrang(@RequestParam Integer page){
    return userService.pagingdaotrang(page);
  }
}
