package com.example.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleView.layoutManager = LinearLayoutManager(this)

        fetchPlanets()
    }

    fun fetchPlanets() {
        val url = "https://swapi.dev/api/planets/?format=json"

        val Request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(Request).enqueue(object :Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = GsonBuilder().create()
                val planets = gson.fromJson(body, PlanetList::class.java)
                runOnUiThread {
                    recycleView.adapter = PlanetsAdapter(planets)
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                println("Something went wrong")
            }
        })
    }
}

class Planet(
    val name: String,
    val population: String,
    @SerializedName("rotation_period") val rotationPeriod: Int,
    @SerializedName("orbital_period") val orbitalPeriod: Int
)

class PlanetList(
    @SerializedName("results") val planets: List<Planet>
)