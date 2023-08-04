package com.alibou.security.models;

import com.alibou.security.token.Token;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Integer phattuid;
  private String ho;
  private String ten;
  private String email;
  private String password;
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
  private String otp;
  private boolean isActive=true;

  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany(mappedBy = "user")
  private List<Token> tokens;
  @OneToMany(mappedBy = "users")
  private List<DaoTrangs> daoTrangs;
  @OneToMany(mappedBy = "user")
  private List<PhatTuDaoTrangs> phatTuDaoTrangs;
  @OneToMany(mappedBy = "user")
  private List<DonDangKys> donDangKys;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "chuaid")
  public Chuas chuas;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "kieuthanhvienid")
  public KieuThanhViens kieuThanhViens;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
