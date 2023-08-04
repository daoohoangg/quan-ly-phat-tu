package com.alibou.security.Controller;

import com.alibou.security.DTO.ChangePassDTO;
import com.alibou.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/change-password")
public class UserController {
    @Autowired
    UserService userService;
    @PutMapping("/regenerate-otp")
    public ResponseEntity<String> regenerateOtp(@RequestParam String email) {
        return new ResponseEntity<>(userService.regenerateOtp(email), HttpStatus.OK);
    }
    @PutMapping("/change-password-otp")
    public ResponseEntity<String> changePassword(@RequestParam String otp,String password) {
        return new ResponseEntity<>(userService.changePassword(otp,password), HttpStatus.OK);}
    @PutMapping("/change-password")
    public  ResponseEntity<String> changePass(@RequestBody ChangePassDTO dto){
        return new ResponseEntity<>(userService.changePass(dto), HttpStatus.OK);
    }

}
