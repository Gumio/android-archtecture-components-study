package com.gumio_inf.aacsampleapp.ui.activity

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.gumio_inf.aacsampleapp.R

class DialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)


        val builder = AlertDialog.Builder(this)
        builder.setTitle("確認")
        builder.setMessage("ＸＸＸしてもよろしいですか？")
        builder.setPositiveButton("はい", null)
        builder.setNegativeButton("いいえ", null)
        builder.setNeutralButton("キャンセル", null)
        builder.create().show()
    }
}
