package pt.isec.amov.japc.p1_firebase_novo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pt.isec.amov.japc.p1_firebase_novo.ui.theme.P1_firebase_novoTheme

class MainActivity : ComponentActivity() {

    val viewModel : FirebaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            P1_firebase_novoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "LoginScreen"){
                        composable("LoginScreen"){
                            LoginScreen(viewModel = viewModel){
                                navController.navigate("MainScreen")
                            }
                        }
                        composable("MainScreen"){
                            MainScreen(viewModel = viewModel){
                                navController.navigateUp()
                                //aqui nao se faz como o de cima .navigate("LoginScreen") porque se depois fizessemos back depois de ele fazer logOut ia voltar ao ecra anterior
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: FirebaseViewModel, modifier: Modifier = Modifier,
               onSignOut: () -> Unit){
    val error by remember { viewModel.error }
    val user by remember { viewModel.user }

    LaunchedEffect(key1 = user){
        if(user == null){
            onSignOut()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if(error != null) {
            Text("Error: $error", Modifier.background(Color(255, 0, 0)))
            Spacer(Modifier.height(16.dp))
        }

        Row {
            Text(text = "Email: ")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = user?.email ?: "")
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { viewModel.signOut() }) {
            Text(text = "Sign Out")
        }

        Spacer(Modifier.height(16.dp))
        Button(onClick = { viewModel.addDataToFirestore() }) {
            Text(text = "Add Firestore")
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: FirebaseViewModel, modifier: Modifier = Modifier,
                onSuccess : () -> Unit){ // no trabalho podemos apssar o navController para depois mudar com o botao se nao estiver registado ir para o login e vice-versa, ou fazer com CallBack
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val error by remember { viewModel.error }
    val user by remember { viewModel.user }

    LaunchedEffect(key1 = user){
        if(user != null && error == null){
            onSuccess()
        }
    }
    
    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(error != null) {
            Text("Error: $error", Modifier.background(Color(255, 0, 0)))
            Spacer(Modifier.height(16.dp))
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email.value,
            onValueChange = { email.value = it },
            label = {Text("Email")}
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password.value,
            onValueChange = { password.value = it },
            label = {Text("Password")}
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.createUserWithEmail(email.value, password.value) }) {
            Text(text = "Create User")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.signInWithEmail(email.value, password.value) }) {
            Text(text = "Login User")
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    P1_firebase_novoTheme {
        Greeting("Android")
    }
}