package com.alibou.security.auth;

import com.alibou.security.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;
  @Autowired
  UserService userService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }
  @PutMapping("/regenerate-otp")
  public ResponseEntity<String> regenerateOtp(@RequestParam String email) {
    return new ResponseEntity<>(userService.regenerateOtp(email), HttpStatus.OK);
  }
  @PutMapping("/change-password")
  public ResponseEntity<String> changePassword(@RequestParam String otp,String password) {
    return new ResponseEntity<>(userService.changePassword(otp,password), HttpStatus.OK);}

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }

}
