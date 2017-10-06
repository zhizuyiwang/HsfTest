package com.hsf.hsftest.model;


import java.util.Random;

/**
 *@作者 hsf
 *
 *@创建日期 2017/8/29 13:48
 * 随机生成器（下雨效果）
 */


public class RandomGenerator {
    private static final Random RANDOM = new Random();

    // 区间随机
    public float getRandom(float lower, float upper) {
        float min = Math.min(lower, upper);
        float max = Math.max(lower, upper);
        return getRandom(max - min) + min;
    }

    // 上界随机
    public float getRandom(float upper) {
        return RANDOM.nextFloat() * upper;
    }

    // 上界随机
    public int getRandom(int upper) {
        return RANDOM.nextInt(upper);
    }
}
