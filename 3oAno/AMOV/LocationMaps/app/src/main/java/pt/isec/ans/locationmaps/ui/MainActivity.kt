package pt.isec.ans.locationmaps.ui

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import pt.isec.ans.locationmaps.LocationMapsApp
import pt.isec.ans.locationmaps.ui.screens.MainScreenOSM
import pt.isec.ans.locationmaps.ui.theme.LocationMapsTheme
import pt.isec.ans.locationmaps.ui.viewmodels.LocationViewModel
import pt.isec.ans.locationmaps.ui.viewmodels.LocationViewModelFactory

class MainActivity : ComponentActivity() {

    val app by lazy { application as LocationMapsApp }

    private val viewModel : LocationViewModel by viewModels{
        LocationViewModelFactory(app.locationHandler)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LocationMapsTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    MainScreenOSM(viewModel = viewModel)
                }
            }
        }
        verifyPermissions()
    }

    override fun onResume() {
        super.onResume()
        viewModel.startLocationUpdates()
    }

    /*override fun onPause() {
        super.onPause()
        viewModel.stopLocationUpdates()
    }*/

    private fun verifyPermissions() : Boolean{
        viewModel.coarseLocationPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        viewModel.fineLocationPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            viewModel.backgroundLocationPermission = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        } else
            viewModel.backgroundLocationPermission = viewModel.coarseLocationPermission || viewModel.fineLocationPermission

        if (!viewModel.coarseLocationPermission && !viewModel.fineLocationPermission) {
            basicPermissionsAuthorization.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
            return false
        } else
            verifyBackgroundPermission()
        return true
    }

    private val basicPermissionsAuthorization = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        viewModel.coarseLocationPermission = results[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
        viewModel.fineLocationPermission = results[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        viewModel.startLocationUpdates()
        verifyBackgroundPermission()
    }

    private fun verifyBackgroundPermission() {
        if (!(viewModel.coarseLocationPermission || viewModel.fineLocationPermission))
            return

        if (!viewModel.backgroundLocationPermission && Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.ACCESS_BACKGROUND_LOCATION
                )
            ) {
                val dlg = AlertDialog.Builder(this)
                    .setTitle("Background Location")
                    .setMessage(
                        "This application needs your permission to use location while in the background.\n" +
                                "Please choose the correct option in the following screen" +
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
                                    " (\"${packageManager.backgroundPermissionOptionLabel}\")."
                                else
                                    "."
                    )
                    .setPositiveButton("Ok") { _, _ ->
                        backgroundPermissionAuthorization.launch(
                            Manifest.permission.ACCESS_BACKGROUND_LOCATION
                        )
                    }
                    .create()
                dlg.show()
            }
        }
    }

    private val backgroundPermissionAuthorization = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { result ->
        viewModel.backgroundLocationPermission = result
        Toast.makeText(this,"Background location enabled: $result", Toast.LENGTH_LONG).show()
    }

}

@Composable
fun MainScreen(modifier: Modifier = Modifier, viewModel: LocationViewModel) {

    val location = viewModel.currentLocation.observeAsState()

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Latitude: ${location.value?.latitude ?:"--"}") // vai buscar o valor da latitude e se for null aparece os traços
        Spacer(Modifier.height(16.dp))
        Text(text = "Longitude: ${location.value?.longitude ?:"--"}")
    }
}

