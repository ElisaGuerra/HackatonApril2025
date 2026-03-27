package com.example.wasteapp

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.DialogFragment

class info : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tache = view.findViewById<Button>(R.id.x_info)
        tache.setOnClickListener{
            dismiss()
        }

        anim()
    }

    private fun anim (){
        val infoima = view?.findViewById<ImageView>(R.id.info_ima)
        Handler().postDelayed({
            infoima?.setImageResource(R.drawable.plas_02)
        },1000)
        Handler().postDelayed({
            infoima?.setImageResource(R.drawable.plas_03)
        },2000)
        Handler().postDelayed({
            infoima?.setImageResource(R.drawable.plas_04)
        },3000)
    }
}