package com.moon.common.model;

import lombok.Data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2024-01-10 09:00
 * @description
 */
@Data
public class Node {

    private String id;
    private Integer score;
    private List<Node> parentNodes;
    private List<Node> nextNodes;

    public static void main(String[] args) {
        // 假设节点集合数据已经按上图准备好。
        List<Node> list = new ArrayList<>();
        for (Node node : list) {
            String id = node.getId();
            List<Node> parentNodes = node.getParentNodes();
            // 拼接父节点名称
            String pName = "null";
            if (parentNodes != null && !parentNodes.isEmpty()) {
                pName = parentNodes.stream().map(Node::getId)
                        .collect(Collectors.joining(","));
            }
            // 计算总得分
            Integer totalScore = countScore(0, parentNodes);
            System.out.println(id + "父节点：" + pName + "，得分：" + totalScore);
        }
    }

    private static Integer countScore(Integer score, List<Node> parentNodes) {
        if (parentNodes != null && !parentNodes.isEmpty()) {
            for (Node node : parentNodes) {
                Integer i = Optional.ofNullable(node.getScore()).orElse(Integer.valueOf("0"));
                score = Integer.sum(score, i);
                List<Node> pNodes = node.getParentNodes();
                if (pNodes != null && !pNodes.isEmpty()) {
                    countScore(score, pNodes);
                }
            }
        }
        return score;
    }
}
