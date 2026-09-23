package tech.aiedge.auralink.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.math.max
import kotlin.random.Random

/**
 * Production swap: implement with BluetoothLeScanner + ScanCallback.
 * This simulator preserves the same Flow contract so the UI never changes.
 */
fun interface EdgeRepository {
    fun watch(): Flow<List<EdgeNode>>
}

class SimulatedMesh : EdgeRepository {
    private val seeds = listOf(
        "gate-01" to "South Gate",
        "pump-07" to "Borehole",
        "roof-pv" to "Array West",
        "cold-01" to "Cold store",
        "mesh-hx" to "Helix node",
    )

    override fun watch(): Flow<List<EdgeNode>> = flow {
        val rssi = seeds.map { -40 - Random.nextInt(35) }.toMutableList()
        val batt = seeds.map { 55 + Random.nextInt(40) }.toMutableList()
        while (true) {
            seeds.indices.forEach { i ->
                rssi[i] = (rssi[i] + Random.nextInt(-4, 5)).coerceIn(-95, -28)
                batt[i] = max(1, batt[i] - if (Random.nextFloat() < 0.08f) 1 else 0)
            }
            emit(
                seeds.mapIndexed { i, (id, name) ->
                    EdgeNode(
                        id = id,
                        name = name,
                        rssi = rssi[i],
                        batteryPct = batt[i],
                        online = rssi[i] > -88,
                    )
                },
            )
            delay(700)
        }
    }
}
