package com.moon.drools.entity;

import java.util.StringJoiner;

/**
 * 规则映射实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-10-01 22:57
 * @description
 */
public class Calculation {

    private double wage; // 税前工资
    private double wagemore; // 应纳税所得额
    private double cess; // 税率
    private double preminus; // 速算扣除数
    private double wageminus; // 扣税额
    private double actualwage; // 税后工资

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public double getWagemore() {
        return wagemore;
    }

    public void setWagemore(double wagemore) {
        this.wagemore = wagemore;
    }

    public double getCess() {
        return cess;
    }

    public void setCess(double cess) {
        this.cess = cess;
    }

    public double getPreminus() {
        return preminus;
    }

    public void setPreminus(double preminus) {
        this.preminus = preminus;
    }

    public double getWageminus() {
        return wageminus;
    }

    public void setWageminus(double wageminus) {
        this.wageminus = wageminus;
    }

    public double getActualwage() {
        return actualwage;
    }

    public void setActualwage(double actualwage) {
        this.actualwage = actualwage;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Calculation.class.getSimpleName() + "[", "]")
                .add("wage=" + wage)
                .add("wagemore=" + wagemore)
                .add("cess=" + cess)
                .add("preminus=" + preminus)
                .add("wageminus=" + wageminus)
                .add("actualwage=" + actualwage)
                .toString();
    }
}
