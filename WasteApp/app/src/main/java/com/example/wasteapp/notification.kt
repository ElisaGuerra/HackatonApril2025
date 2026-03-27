package com.example.wasteapp

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import org.json.JSONArray
import org.json.JSONObject

class notification: DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notif, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val info = arguments?.getString("intern")

        val json = JSONObject(info)
        val list = json.getJSONArray("Notificacion")

        for(i in 0 until list.length()){
            val noti = list.getJSONObject(i)
            val icono = noti.getString("Icono")
            val titulo = noti.getString("Titulo")
            val descri = noti.getString("Contenido")

            // Crear el LinearLayout padre
            val parentLinearLayout = LinearLayout(requireContext())
            val parentLayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                135.dpToPx()
            )
            parentLinearLayout.layoutParams = parentLayoutParams
            parentLinearLayout.orientation = LinearLayout.VERTICAL

            // Crear el LinearLayout hijo para el título
            val titleLinearLayout = LinearLayout(requireContext())
            val titleLayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                65.dpToPx()
            )
            titleLinearLayout.layoutParams = titleLayoutParams
            titleLinearLayout.orientation = LinearLayout.HORIZONTAL

            // Crear el ImageView para el icono
            val iconImageView = ImageView(requireContext())
            val iconLayoutParams = LinearLayout.LayoutParams(50.dpToPx(), 50.dpToPx())
            iconLayoutParams.setMargins(15.dpToPx(), 15.dpToPx(), 0, 0)
            iconImageView.layoutParams = iconLayoutParams

            if(icono=="goal"){
                iconImageView.setBackgroundResource(R.drawable.not_goal)
            } else if (icono=="plastic"){
                iconImageView.setBackgroundResource(R.drawable.plastic)
            } else if (icono=="metal"){
                iconImageView.setBackgroundResource(R.drawable.metal)
            } else if (icono=="paper"){
                iconImageView.setBackgroundResource(R.drawable.paper)
            } else if (icono=="glass"){
                iconImageView.setBackgroundResource(R.drawable.glass)
            } else if (icono=="cloth"){
                iconImageView.setBackgroundResource(R.drawable.cloth)
            } else if (icono=="wood"){
                iconImageView.setBackgroundResource(R.drawable.wood)
            } else if (icono=="inorganic"){
                iconImageView.setBackgroundResource(R.drawable.inorganic)
            } else if (icono=="organic"){
                iconImageView.setBackgroundResource(R.drawable.organic)
            }

            titleLinearLayout.addView(iconImageView)

            // Crear el TextView para el título
            val titleTextView = TextView(requireContext())
            val titleTextViewParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            titleTextViewParams.setMargins(15.dpToPx(), 30.dpToPx(), 0, 0)
            titleTextView.layoutParams = titleTextViewParams
            titleTextView.text = titulo
            titleTextView.gravity = Gravity.CENTER
            titleTextView.setTextColor(resources.getColor(R.color.black))
            titleTextView.textSize = 6.dpToPx().toFloat()
            titleTextView.setTypeface(null, Typeface.BOLD)
            titleLinearLayout.addView(titleTextView)

            // Agregar el LinearLayout hijo del título al LinearLayout padre
            parentLinearLayout.addView(titleLinearLayout)

            // Crear el TextView para el mensaje
            val messageTextView = TextView(requireContext())
            val messageTextViewParams = LinearLayout.LayoutParams(
                250.dpToPx(),
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            messageTextViewParams.setMargins(15.dpToPx(), 0, 0, 0)
            messageTextView.layoutParams = messageTextViewParams
            messageTextView.text = descri
            messageTextView.setTextColor(resources.getColor(R.color.black))
            parentLinearLayout.addView(messageTextView)

            // Agregar el LinearLayout padre al layout principal
            val mainLayout = view.findViewById<LinearLayout>(R.id.principal)
            mainLayout.addView(parentLinearLayout)
        }

        val close = view.findViewById<Button>(R.id.close)
        close.setOnClickListener{
            dismiss()
        }
    }

    private fun Int.dpToPx(): Int {
        val density = resources.displayMetrics.density
        return (this * density).toInt()
    }
}