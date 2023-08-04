package com.alibou.security.DTO;

import com.alibou.security.models.DaoTrangs;
import com.alibou.security.models.User;
import lombok.Data;

@Data
public class DonDangKysDTO {
    private DaoTrangs daoTrangs;
    private User phatTu;
}
