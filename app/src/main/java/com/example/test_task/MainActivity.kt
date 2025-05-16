package com.example.test_task

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task.retrofit.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main) // ✅ Сначала устанавливаем layout

        recyclerView = findViewById(R.id.recyclerView) // ✅ Теперь можно искать View
        recyclerView.layoutManager = LinearLayoutManager(this)

        val splashScreenController = SplashScreenController(splashScreen)
        splashScreenController.holdSplashScreen(lifecycleScope, 2500L)

        loadItems() // ✅ Загружаем реальные данные

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loadItems() {
        val call = Common.retrofitService.getMovieList()

        call.enqueue(object : Callback<MutableList<Item>> {
            override fun onResponse(call: Call<MutableList<Item>>, response: Response<MutableList<Item>>) {
                if (response.isSuccessful) {
                    val itemList = response.body() ?: mutableListOf()
                    adapter = ItemAdapter(itemList)
                    recyclerView.adapter = adapter
                } else {
                    Toast.makeText(this@MainActivity, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MutableList<Item>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Ошибка сети: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("MainActivity", "Network error", t)
            }
        })
    }
}
