package com.example.tiendascercanas.data

// Coordenadas base: 19.287037, -99.116886 (tu ubicación)

object FakeStores {

    val stores = listOf(
        Store(
            name = "Mini Súper La Esquina",
            address = "Calle Primavera 12, Col. Local",
            latitude = 19.287537,   // + ~50 m al norte
            longitude = -99.116386  // + ~50 m al este
        ),
        Store(
            name = "Oxxo Camino Real",
            address = "Av. Camino Real 150",
            latitude = 19.286537,   // - ~50 m al sur
            longitude = -99.117386  // - ~50 m al oeste
        ),
        Store(
            name = "Farmacia Salud Total",
            address = "Av. Salud 45",
            latitude = 19.288037,   // + ~110 m al norte
            longitude = -99.117886  // - ~90 m al oeste
        ),
        Store(
            name = "Abarrotes Doña Lupita",
            address = "Calle Mercado 33",
            latitude = 19.286837,   // - ~20 m al sur
            longitude = -99.115885  // + ~100 m al este
        )
    )
}

