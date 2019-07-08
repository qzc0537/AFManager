package com.qzc.afmanager.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.qzc.afmanager.communicate.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val tag = "MainActivity"
    private val function1 = "function1"
    private val function2 = "function2"
    private val function3 = "function3"
    private val function4 = "function4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AFManager.getInstance().addFunction(object : FunctionNoParamNoResult(function1) {
            override fun function() {
                Log.i(tag, "Receive function1")
            }
        }).addFunction(object : FunctionWithParam<Int>(function2) {
            override fun function(param: Int?) {
                Log.i(tag, "Receive function2, param->$param")
            }
        }).addFunction(object : FunctionWithResult<String>(function3) {
            override fun function(): String {
                Log.i(tag, "Receive function3, return 666")
                return "666"
            }
        }).addFunction(object : FunctionWithParamWithResult<Int, String>(function4) {
            override fun function(param: Int?): String {
                Log.i(tag, "Receive function4, param->$param return 888")
                return "888"
            }
        })

        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> AFManager.getInstance().invokeFunction(function1)
            R.id.btn2 -> AFManager.getInstance().invokeFunction(function2, 222)
            R.id.btn3 -> {
                val result = AFManager.getInstance().invokeFunction(function3, String::class.java)
                Log.i(tag, "function3 result->$result")
            }
            R.id.btn4 -> {
                val result = AFManager.getInstance().invokeFunction(
                    function4,
                    888,
                    String::class.java
                )
                Log.i(tag, "function3 result->$result")
            }
        }
    }
}
