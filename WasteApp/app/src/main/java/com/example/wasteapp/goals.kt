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

class goals: DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.goals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tache = view.findViewById<Button>(R.id.x_goals)
        tache.setOnClickListener{
            dismiss()
        }

        anim()
    }

    private fun anim (){
        val goalima = view?.findViewById<ImageView>(R.id.goal01_ima)
        Handler().postDelayed({
            goalima?.setImageResource(R.drawable.goal01_earn)
        },3000)
    }


}