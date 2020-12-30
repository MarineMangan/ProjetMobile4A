package com.example.projetmobile4a.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.widget.Toolbar
import com.example.projetmobile4a.domain.entity.User
import com.example.projetmobile4a.R
import kotlinx.android.synthetic.main.activity_main_register.*
import org.koin.android.ext.android.inject

class MainActivityRegister : AppCompatActivity() {



    val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_register)


        Create_account.setOnClickListener(){
            val monIntentRetour =  Intent(this,MainActivity::class.java)
            val user = User((loginAccount_edit.text.toString().trim()),passwordAccount_edit.text.toString())
            if(user.email !="" && user.password != "") {
                mainViewModel.onClickedLoginAccount(user)
                startActivity(monIntentRetour)
            }else{
                Toast.makeText(applicationContext,"Add a user and a password",Toast.LENGTH_SHORT).show()
            }
        }
        val toolbar: Toolbar =findViewById(R.id.my_toolbar)
        setSupportActionBar(toolbar)
        val actionBar=supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}