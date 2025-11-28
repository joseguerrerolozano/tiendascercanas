package com.example.tiendascercanas

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.tiendascercanas.data.Store
import com.example.tiendascercanas.ui.theme.TiendascercanasTheme
import com.example.tiendascercanas.util.getNearbyStores
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.text.DecimalFormat
import com.example.tiendascercanas.ui.PantallaCarga


class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        setContent {
            TiendascercanasTheme {

                var mostrarPantallaCarga by remember { mutableStateOf(true) }

                if (mostrarPantallaCarga) {
                        PantallaCarga { mostrarPantallaCarga = false }
                } else {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        TiendasCercanasScreen(fusedLocationClient)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TiendasCercanasScreen(
    fusedLocationClient: FusedLocationProviderClient
) {
    val context = LocalContext.current

    var userLocation by remember { mutableStateOf<Location?>(null) }
    var nearbyStores by remember { mutableStateOf<List<Pair<Store, Float>>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (granted) {
                errorMessage = null
                isLoading = true
                getLastLocation(fusedLocationClient) { location ->
                    isLoading = false
                    if (location != null) {
                        userLocation = location
                        nearbyStores = getNearbyStores(location.latitude, location.longitude)
                    } else {
                        errorMessage = "No se pudo obtener tu ubicación."
                    }
                }
            } else {
                errorMessage = "Se necesita el permiso de ubicación para mostrar las tiendas cercanas."
            }
        }

    fun loadLocationAndStores() {
        val hasPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            errorMessage = null
            isLoading = true
            getLastLocation(fusedLocationClient) { location ->
                isLoading = false
                if (location != null) {
                    userLocation = location
                    nearbyStores = getNearbyStores(location.latitude, location.longitude)
                } else {
                    errorMessage = "No se pudo obtener tu ubicación."
                }
            }
        } else {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Tiendas cercanas",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { innerPadding ->
        TiendasCercanasContent(
            nearbyStores = nearbyStores,
            isLoading = isLoading,
            errorMessage = errorMessage,
            onBuscarClick = { loadLocationAndStores() },
            userLocation = userLocation,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun TiendasCercanasContent(
    nearbyStores: List<Pair<Store, Float>>,
    isLoading: Boolean,
    errorMessage: String?,
    onBuscarClick: () -> Unit,
    userLocation: Location?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Encuentra los negocios a tu alrededor",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onBuscarClick,
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(48.dp)
            ) {
                Text("Buscar tiendas cercanas")
            }

            Spacer(modifier = Modifier.height(20.dp))

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator()
                            Spacer(Modifier.height(8.dp))
                            Text("Obteniendo tu ubicación…")
                        }
                    }
                }

                errorMessage != null -> {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = errorMessage,
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                userLocation == null -> {
                    Text(
                        text = "Pulsa el botón para detectar tu ubicación y ver qué tiendas están cerca.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                nearbyStores.isEmpty() -> {
                    Text(
                        text = "No se encontraron tiendas dentro del radio configurado.",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                }

                else -> {
                    Text(
                        text = "Encontramos ${nearbyStores.size} lugar(es) cerca de ti:",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(nearbyStores) { (store, distance) ->
                            StoreItem(store, distance)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StoreItem(
    store: Store,
    distanceMeters: Float
) {
    val df = DecimalFormat("#.##")
    val distanceKm = distanceMeters / 1000f

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Store,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = store.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = store.address,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "A ${df.format(distanceKm)} km de ti",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

private fun getLastLocation(
    fusedLocationClient: FusedLocationProviderClient,
    onLocationResult: (Location?) -> Unit
) {
    try {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                onLocationResult(location)
            }
            .addOnFailureListener {
                onLocationResult(null)
            }
    } catch (e: SecurityException) {
        onLocationResult(null)
    }
}
