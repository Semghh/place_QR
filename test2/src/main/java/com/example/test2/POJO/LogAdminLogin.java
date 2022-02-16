package com.example.test2.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogAdminLogin {

    private Long id;
    private Long admin_id;
    private String username;
    private Long login_time;

}
