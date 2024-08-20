package com.example.app101.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app101.core.common.isValidEmail
import com.example.app101.core.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {

    val email = MutableLiveData("")
    val password = MutableLiveData("")

    val isButtonEnabled: LiveData<Boolean> get() = _isButtonEnabled

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    private val _isButtonEnabled = MediatorLiveData<Boolean>().apply {
        addSource(email) { value = isFormValid() }
        addSource(password) { value = isFormValid() }
    }

    fun login() {
        viewModelScope.launch {

            if (!isFormValid()) {
                return@launch
            }

            val result = repository.loginUser(email.value, password.value)
            _loginSuccess.postValue(result.success)
        }
    }

    private fun isFormValid(): Boolean {
        val currentEmail = email.value ?: ""
        val currentPassword = password.value ?: ""
        return currentEmail.isValidEmail() && currentPassword.isNotEmpty()
    }
}
