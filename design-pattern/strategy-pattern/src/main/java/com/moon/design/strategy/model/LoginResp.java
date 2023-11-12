package com.moon.design.strategy.model;

import lombok.Data;

/**
 * 响应参数
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-09-30 12:03
 * @description
 */
@Data
public class LoginResp {

    private Integer userId;
    private String userName;
    private String roleCode;
    private String token; //jwt令牌
    private boolean success;

}
