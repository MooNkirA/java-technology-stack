package com.moon.common.model;

import java.math.BigInteger;
import java.util.List;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2024-01-10 08:22
 * @description
 */
public class Flow {

    private BigInteger flowId;
    private String flowName;
    private String flowDesc;
    private BigInteger parentFlowId;

    private List<Group> groupList;
}
