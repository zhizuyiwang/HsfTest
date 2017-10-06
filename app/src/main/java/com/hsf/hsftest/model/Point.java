package com.hsf.hsftest.model;

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/1 19:41
 * 保存点信息，或者说是向量信息。包含向量的基本运算。
 */


public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //旋转
    public Point rotate(float theta) {
        int x = this.x;
        int y = this.y;
        this.x = (int) (Math.cos(theta) * x - Math.sin(theta) * y);
        this.y = (int) (Math.sin(theta) * x + Math.cos(theta) * y);
        return this;
    }

    //乘以一个常数
    public Point mult(float f) {
        this.x *= f;
        this.y *= f;
        return this;
    }

    //复制
    public Point clone() {
        return new Point(this.x, this.y);
    }

    //该点与圆心距离
    public float length() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    //向量相减
    public Point subtract(Point p) {
        this.x -= p.x;
        this.y -= p.y;
        return this;
    }

    //向量相加
    public Point add(Point p) {
        this.x += p.x;
        this.y += p.y;
        return this;
    }

    public Point set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }
}
