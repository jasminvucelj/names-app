package com.jazz.namesapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jazz.namesapp.data.Name
import com.jazz.namesapp.data.NameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NameRepository
) : ViewModel() {
    val names: Flow<List<Name>> = repository.names

    fun addName(name: String) {
        if (name.isNotBlank()) {
            viewModelScope.launch {
                repository.addName(name)
            }
        }
    }

    fun deleteName(name: Name) {
        viewModelScope.launch {
            repository.deleteName(name)
        }
    }
}

/*class MainViewModelFactory(private val repository: NameRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/
