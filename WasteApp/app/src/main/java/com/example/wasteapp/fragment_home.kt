package com.example.wasteapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import org.json.JSONObject
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class fragment_home : Fragment() {

    private var general_data: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        general_data = arguments?.getString("dato")
        Log.d("Recibido",general_data.toString())
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val information = view.findViewById<ImageButton>(R.id.info_plastic)
        information.setOnClickListener{
            val window = info()
            window.show((activity as AppCompatActivity).supportFragmentManager,"window")
        }

        if(general_data != null){

            val txtpuntos = view.findViewById<TextView>(R.id.points)
            val imag_points = view.findViewById<ImageView>(R.id.lvl)

            var objecto = JSONObject(general_data)
            val puntos = objecto.getInt("Puntuacion")
            val botes = objecto.getJSONArray("Botes")

            txtpuntos.text = "Points: " + puntos

            if (puntos > 9 && puntos <19){
                imag_points.setImageResource(R.drawable.lv_1)
            } else if (puntos > 19 && puntos < 29){
                imag_points.setImageResource(R.drawable.lv_2)
            } else if (puntos > 29 && puntos < 39){
                imag_points.setImageResource(R.drawable.lv_3)
            } else if (puntos > 39 && puntos < 49){
                imag_points.setImageResource(R.drawable.lv_4)
            } else if (puntos > 49 && puntos < 59){
                imag_points.setImageResource(R.drawable.lv_5)
            } else if (puntos > 59 && puntos < 69){
                imag_points.setImageResource(R.drawable.lv_6)
            } else if (puntos > 69 && puntos < 89){
                imag_points.setImageResource(R.drawable.lv_7)
            } else {
                imag_points.setImageResource(R.drawable.lv_8)
            }


            var porce: TextView? = null
            var cantidad: TextView? = null

            for (i in 0 until botes.length()){
                val objetounico = botes.getJSONObject(i)
                if (objetounico.getString("Tipo") == "plastic"){
                    porce=view.findViewById(R.id.item_plastic)
                    cantidad=view.findViewById(R.id.por_plastic)

                    porce.text = objetounico.getString("PorcentajeActual") + "%"
                    cantidad.text = objetounico.getString("CantidadTotal")

                } else if (objetounico.getString("Tipo") == "metal"){

                    porce=view.findViewById(R.id.por_metal)
                    cantidad=view.findViewById(R.id.item_metal)

                    porce.text = objetounico.getString("PorcentajeActual") + "%"
                    cantidad.text = objetounico.getString("CantidadTotal")

                } else if (objetounico.getString("Tipo") == "paper"){

                    porce=view.findViewById(R.id.por_paper)
                    cantidad=view.findViewById(R.id.item_paper)

                    porce.text = objetounico.getString("PorcentajeActual") + "%"
                    cantidad.text = objetounico.getString("CantidadTotal")

                } else if (objetounico.getString("Tipo") == "glass"){

                    porce=view.findViewById(R.id.por_glass)
                    cantidad=view.findViewById(R.id.item_glass)

                    porce.text = objetounico.getString("PorcentajeActual") + "%"
                    cantidad.text = objetounico.getString("CantidadTotal")

                } else if (objetounico.getString("Tipo") == "cloth"){

                    porce=view.findViewById(R.id.por_cloth)
                    cantidad=view.findViewById(R.id.item_cloth)

                    porce.text = objetounico.getString("PorcentajeActual") + "%"
                    cantidad.text = objetounico.getString("CantidadTotal")

                } else if (objetounico.getString("Tipo") == "wood"){

                    porce=view.findViewById(R.id.por_wood)
                    cantidad=view.findViewById(R.id.item_wood)

                    porce.text = objetounico.getString("PorcentajeActual") + "%"
                    cantidad.text = objetounico.getString("CantidadTotal")

                } else if (objetounico.getString("Tipo") == "inorganic"){

                    porce=view.findViewById(R.id.por_inorganic)
                    cantidad=view.findViewById(R.id.item_inorganic)

                    porce.text = objetounico.getString("PorcentajeActual") + "%"
                    cantidad.text = objetounico.getString("CantidadTotal")

                } else if (objetounico.getString("Tipo") == "organic"){

                    porce=view.findViewById(R.id.por_organic)
                    cantidad=view.findViewById(R.id.item_organic)

                    porce.text = objetounico.getString("PorcentajeActual") + "%"
                    cantidad.text = objetounico.getString("CantidadTotal")

                }
            }

        }

    }

}