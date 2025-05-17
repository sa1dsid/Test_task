import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task.ItemAdapter
import com.example.test_task.retrofit.ApiResponse
import com.example.test_task.retrofit.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterLoader(
    private val context: Context,
    private val retrofitService: RetrofitServices,
    private val recyclerView: RecyclerView
) {
    fun loadItems() {
        val call = retrofitService.getCharacterList()

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val characterList = response.body()?.results ?: emptyList()
                    val adapter = ItemAdapter(characterList)
                    recyclerView.adapter = adapter
                } else {
                    Toast.makeText(context, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(context, "Ошибка сети: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}