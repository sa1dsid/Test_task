package com.example.test_task

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task.retrofit.CharacterViewModel
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    //private lateinit var adapter: ItemAdapter
    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val splashScreenController = SplashScreenController(splashScreen)
        splashScreenController.holdSplashScreen(lifecycleScope, 2500L)


        viewModel.characterList.observe(this, Observer { characters ->
            recyclerView.adapter = ItemAdapter(characters)
        })

        viewModel.errorMessage.observe(this, Observer { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


}
