package com.example.tiendascercanas.util

import android.location.Location
import com.example.tiendascercanas.data.FakeStores
import com.example.tiendascercanas.data.Store

fun distanceInMeters(
    lat1: Double,
    lon1: Double,
    lat2: Double,
    lon2: Double
): Float {
    val results = FloatArray(1)
    Location.distanceBetween(lat1, lon1, lat2, lon2, results)
    return results[0]
}

fun getNearbyStores(
    userLat: Double,
    userLon: Double,
    radiusMeters: Float = 3000f // 3 km
): List<Pair<Store, Float>> {
    return FakeStores.stores
        .map { store ->
            val d = distanceInMeters(
                userLat, userLon,
                store.latitude, store.longitude
            )
            store to d
        }
        .filter { (_, distance) -> distance <= radiusMeters }
        .sortedBy { it.second }
}
