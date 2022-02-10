package com.example.test2.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    private Long id;
    private String username;
    private String password;
    private Long group_id;

}
