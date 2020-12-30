package com.example.projetmobile4a.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.ShareActionProvider
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.Observer
import com.example.projetmobile4a.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val mainViewModel : MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       mainViewModel.loginLiveData.observe(this, Observer { it ->
            when(it){
                is LoginSuccess -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Connection")
                        .setMessage("Compte trouvé")
                        .setPositiveButton("OK") { dialog, which ->
                            dialog.dismiss()
                        }
                        .show()
                    val monIntent : Intent =  Intent(this, MainActivityApi::class.java)
                    startActivity(monIntent)
                }
                LoginError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Erreur")
                        .setMessage("Account inconnu")
                        .setPositiveButton("OK") { dialog, which ->
                            dialog.dismiss()
                        }
                        .show()
                }
            }
        })
        login_button.setOnClickListener {
            mainViewModel.onClickedLogin(login_edit.text.toString().trim(), password_edit.text.toString().trim())
        }
        create_account_button.setOnClickListener(){
            val monIntent : Intent =  Intent(this,MainActivityRegister::class.java)
            startActivity(monIntent)
        }
        val toolbar: Toolbar =findViewById(R.id.my_toolbar)
        setSupportActionBar(toolbar)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val shareItem=menu?.findItem(R.id.action_share)
        val myShareActionProvider= MenuItemCompat.getActionProvider(shareItem) as ShareActionProvider
        val myShareIntent =Intent(Intent.ACTION_SEND)
        val Test = "test"
        myShareIntent.setType("text/*")
        myShareIntent.putExtra(Intent.EXTRA_STREAM,Test)
        myShareActionProvider.setShareIntent(myShareIntent)
        myShareIntent.putExtra(Intent.EXTRA_STREAM,Test)
        myShareActionProvider.setShareIntent(myShareIntent)
        return true
    }
}