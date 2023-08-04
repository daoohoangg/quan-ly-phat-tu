package com.alibou.security.auth;

import com.alibou.security.models.KieuThanhViens;
import com.alibou.security.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private Role role;
  private KieuThanhViens kieuThanhViens;
}
