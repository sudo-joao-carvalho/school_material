package pt.isec.amov.japc.p1_firebase_novo

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import pt.isec.amov.japc.p1_firebase_novo.util.FAuthUtil
import pt.isec.amov.japc.p1_firebase_novo.util.FStorageUtil

data class User(val name: String, val email: String, val picture: String?)

fun FirebaseUser.toUser() : User {
    val username = this.displayName ?: ""
    val strEmail = this.email ?: ""
    val photoUrl = this.photoUrl?.toString()

    return User(username, strEmail, photoUrl)
}

class FirebaseViewModel : ViewModel(){

    private val _error = mutableStateOf<String?>(null)
    val error : MutableState<String?>
        get() = _error

    private val _user = mutableStateOf(FAuthUtil.current?.toUser())
    val user : MutableState<User?>
        get() = _user

    fun createUserWithEmail(email: String, password: String){ // password no firebase tem que ter no minimo 6 caracteres
        if(email.isBlank() || password.isBlank())
            return

        viewModelScope.launch {//como estas funcoes podem demorar algum tempo a executar faz se assim
            FAuthUtil.createUserWithEmail(email, password){exception ->
                if(exception == null)
                    _user.value = FAuthUtil.current?.toUser()
                _error.value = exception?.message
            }
        }
    }

    fun signInWithEmail(email: String, password: String) {
        if(email.isBlank() || password.isBlank())
            return

        viewModelScope.launch {//como estas funcoes podem demorar algum tempo a executar faz se assim
            FAuthUtil.signInWithEmail(email, password){exception ->
                if(exception == null)
                    _user.value = FAuthUtil.current?.toUser()
                _error.value = exception?.message
            }
        }
    }

    fun signOut(){
        FAuthUtil.signOut()
        _error.value = null
        _user.value = null
    }

    fun addDataToFirestore(){
        viewModelScope.launch {//como estas funcoes podem demorar algum tempo a executar faz se assim
            FStorageUtil.addDataToFirestore(){exception ->
                _error.value = exception?.message
            }
        }
    }
}