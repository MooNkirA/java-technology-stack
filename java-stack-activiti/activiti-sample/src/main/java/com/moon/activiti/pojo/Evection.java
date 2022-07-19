package com.moon.activiti.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 出差申请 pojo
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-07-14 11:25
 * @description
 */
@Data
public class Evection implements Serializable {

    private static final long serialVersionUID = -5791160141505345527L;
    /**
     * 主键Id
     */
    private Long id;

    /**
     * 出差单的名字
     */
    private String evectionName;

    /**
     * 出差天数
     */
    private Double num;

    /**
     * 开始时间
     */
    private Date beginDate;

    /**
     * 出差结束时间
     */
    private Date endDate;

    /**
     * 目的地
     */
    private String destination;

    /**
     * 出差原因
     */
    private String reson;

}
