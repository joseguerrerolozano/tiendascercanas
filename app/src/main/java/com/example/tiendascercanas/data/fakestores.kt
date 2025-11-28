package com.example.tiendascercanas.data

// Tiendas simuladas distribuidas en 6 clusters:
// 1. Cluster Base
// 2. Cluster Tlalpan Centro
// 3. Cluster Coyoacán
// 4. Cluster Iztapalapa Oriente

object FakeStores {

    val stores = listOf(
        // --- 1. CLUSTER BASE (Cerca de 19.2870, -99.1168) ---
        Store(name = "Mini Súper La Esquina", address = "Calle Primavera 12, Col. Local", latitude = 19.287537, longitude = -99.116386),
        Store(name = "Oxxo Camino Real", address = "Av. Camino Real 150", latitude = 19.286537, longitude = -99.117386),
        Store(name = "Farmacia Salud Total", address = "Av. Salud 45", latitude = 19.288037, longitude = -99.117886),
        Store(name = "Abarrotes Doña Lupita", address = "Calle Mercado 33", latitude = 19.286837, longitude = -99.115885),

        // --- 2. CLUSTER TLALPAN CENTRO (Cerca de 19.2950, -99.1650) ---
        Store(name = "Tienda La Villa", address = "Calle Guadalupe 1, Tlalpan", latitude = 19.2955, longitude = -99.1645),
        Store(name = "Abarrotes El Retiro", address = "Av. Juárez 2, Tlalpan", latitude = 19.2945, longitude = -99.1655),
        Store(name = "Farmacia Santa Úrsula", address = "Calzada de Tlalpan 3, Villa Olímpica", latitude = 19.2960, longitude = -99.1660),
        Store(name = "Mercado Tlalpan Express", address = "Calle Hacienda 4, Tlalpan", latitude = 19.2940, longitude = -99.1640),

        // --- 3. CLUSTER COYOACÁN (Cerca de 19.3450, -99.1650) ---
        Store(name = "Super Coyoacán", address = "Av. Pacífico 101, Coyoacán", latitude = 19.3455, longitude = -99.1640),
        Store(name = "Mini Súper Universidad", address = "Av. Universidad 202", latitude = 19.3440, longitude = -99.1660),
        Store(name = "Oxxo Aztecas", address = "Av. Aztecas 303, Ciudad Jardín", latitude = 19.3460, longitude = -99.1655),
        Store(name = "Abarrotes El Río", address = "Río Churubusco 404", latitude = 19.3445, longitude = -99.1645),

        // --- 4. CLUSTER IZTAPALAPA ORIENTE (Cerca de 19.3750, -99.0800) ---
        Store(name = "Bodega Iztapalapa", address = "Eje 5 Sur 505, Iztapalapa", latitude = 19.3755, longitude = -99.0805),
        Store(name = "Farmacia San Rafael", address = "Av. Ermita 606, San Rafael", latitude = 19.3745, longitude = -99.0795),
        Store(name = "Tienda Abastos", address = "Central de Abastos 707", latitude = 19.3760, longitude = -99.0810),
        Store(name = "Oxxo Santa Cruz", address = "Av. Tláhuac 808, Santa Cruz", latitude = 19.3740, longitude = -99.0790),

        // --- 5. CLUSTER SAN ANDRÉS (Tlalpan - Punto Clave 1) ---
        // Coordenadas base: ~19.2810, -99.1350
        Store(name = "Mini Súper San Andrés 1", address = "San Andrés 3, Cruz Del Farol", latitude = 19.2815, longitude = -99.1345),
        Store(name = "Farmacia Tlalpan Sur", address = "Calle Farol 5, San Andrés", latitude = 19.2805, longitude = -99.1355),
        Store(name = "Oxxo Periférico Sur", address = "Periférico Sur 707", latitude = 19.2820, longitude = -99.1360),
        Store(name = "Abarrotes El Punto", address = "Av. Acueducto 808", latitude = 19.2800, longitude = -99.1340),

        // --- 6. CLUSTER ERMITA (Iztapalapa - Punto Clave 2) ---
        // Coordenadas base: ~19.3620, -99.1200
        Store(name = "Tienda Ermita 1", address = "Ermita Iztapalapa 557", latitude = 19.3625, longitude = -99.1195),
        Store(name = "Super Atlalilco", address = "Av. Atlalilco 606", latitude = 19.3615, longitude = -99.1205),
        Store(name = "Farmacia Metro", address = "Metro Atlalilco 707", latitude = 19.3630, longitude = -99.1210),
        Store(name = "Abarrotes Cerca", address = "Calle Iztapalapa 808", latitude = 19.3610, longitude = -99.1190)
    )
}