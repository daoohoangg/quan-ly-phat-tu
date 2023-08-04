package com.alibou.security.DTO;

import lombok.Data;

@Data
public class ChangePassDTO {
    public String email;
    public String oldpass;
    public String  newpass;
}
