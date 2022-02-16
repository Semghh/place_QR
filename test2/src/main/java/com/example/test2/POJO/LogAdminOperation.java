package com.example.test2.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogAdminOperation {

    private Long id;
    private Long admin_id;
    private String username;
    private String method_path;//方法路径
    private Integer type;//操作类型
    private Long operation_time;

}
