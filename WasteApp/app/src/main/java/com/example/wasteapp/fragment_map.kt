package com.example.wasteapp

import android.R
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class fragment_map : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.example.wasteapp.R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val direct = view.findViewById<TextView>(com.example.wasteapp.R.id.directions)  // Cambia R.id.textView por el ID de tu TextView

        val linkText = "<a href='https://www.google.com/maps/place/TEPOSA+-+TERMINADOS+PLASTICOS+DE+OCCIDENTE/@20.6218496,-103.4544634,12z/data=!4m10!1m3!11m2!2s5lTQE6MuR9ScOP-h1x2LUg!3e3!3m5!1s0x8428ad3bef1b19ad:0xa89d18cdf3c2d4a8!8m2!3d20.5855923!4d-103.3884827!16s%2Fg%2F11f03v_hvd?entry=ttu'>Directions</a>"
        direct.text = Html.fromHtml(linkText, Html.FROM_HTML_MODE_LEGACY)
        direct.movementMethod = LinkMovementMethod.getInstance()
    }

}