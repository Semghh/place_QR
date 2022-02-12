package com.example.test2.POJO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Authority {

    private Long id;
    private String name;
    private String url;
    private String icon;
    private Authority[] children;
    private Integer type;

}
