package com.lujun61.userrole.dao;


import com.lujun61.userrole.entity.SysRole;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface SysRoleMapper {

   List<SysRole> selectRoleByUser(Integer userId);
}
