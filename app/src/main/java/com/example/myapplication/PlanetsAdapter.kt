package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.planet_item.view.*

class PlanetsAdapter(val planetList: PlanetList): RecyclerView.Adapter<CustomViewholder>(){
    override fun getItemCount(): Int {
        return planetList.planets.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewholder {
        val layoutInflater =LayoutInflater.from(parent.context)
        val cellForRow= layoutInflater.inflate(R.layout.planet_item, parent, false)
        return CustomViewholder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewholder, position: Int) {
        val planet = planetList.planets.get(position)
        holder.view.textView_planetName.text = planet.name
        holder.view.textView_population.text = planet.population
        holder.view.textView_orbitalPeriod.text = planet.orbitalPeriod.toString()
        holder.view.textView_rotationPeriod.text = planet.rotationPeriod.toString()
    }
}

class CustomViewholder(val view: View): RecyclerView.ViewHolder(view)