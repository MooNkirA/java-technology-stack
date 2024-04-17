package com.moon.common.model;

import java.math.BigInteger;
import java.util.List;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2024-01-10 08:22
 * @description
 */
public class Group {

    private BigInteger groupId;
    private String groupName;
    private String groupDesc;

    private List<Task> taskList;
}
