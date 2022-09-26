package com.lujun61.userrole.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRole {
    private Integer id;
    private String name;
    private String memo;
}
