package com.example.test_task.retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    val characterList = MutableLiveData<List<Result>>()
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String?>()

    init {
        fetchCharacters()
    }

    fun fetchCharacters() {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val response = RetrofitClient.retrofitService.getCharacterList()
                characterList.value = (response.body()?.results ?: emptyList()) as List<Result>?
            } catch (e: Exception) {
                errorMessage.value = "Ошибка сети: ${e.message}"
            }
        }

    }
}