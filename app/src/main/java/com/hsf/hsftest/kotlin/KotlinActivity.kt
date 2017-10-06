package com.hsf.hsftest.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.hsf.hsftest.R

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        main("kkkk")
    }
    fun main(ss : String) {
        Log.e("TAG","HHHHH")
    }
}
