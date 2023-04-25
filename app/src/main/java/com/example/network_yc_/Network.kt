package com.example.network_yc_
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import java.io.File

data class Network(var nom_reseau: String, var networkImage: Int, var objet: Array<String>, var number_piece: String)

fun readNetworksFromFile(filename: String): List<Network> {
    val json = File(filename).readText()
    return Json.decodeFromString<List<Network>>(json)
}

fun writeNetworksToFile(filename: String, networks: List<Network>) {
    val json = Json.encodeToString(networks)
    File(filename).writeText(json)
}

fun addNetwork(network: Network, filename: String) {
    val networks = readNetworksFromFile(filename)
    val updatedNetworks = networks + network
    writeNetworksToFile(filename, updatedNetworks)
}