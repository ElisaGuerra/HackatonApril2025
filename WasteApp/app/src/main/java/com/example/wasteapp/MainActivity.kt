package com.example.wasteapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.wasteapp.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    private var general_data: String? = null
    private lateinit var fragmento: fragment_home
    private lateinit var binding: ActivityMainBinding

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Base)
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Peticion("Elisa",1)

        val notification = findViewById<ImageButton>(R.id.notif)

        notification.setOnClickListener{
            val window = notification()
            val bundle = Bundle()
            bundle.putString("intern",general_data.toString())
            window.arguments = bundle
            window.show(supportFragmentManager,"window")
        }

        Repite()

        binding.bottomNavigationView.setOnItemSelectedListener {

            when (it.itemId){
                R.id.navigation_home->{
                    if(general_data != null){
                        fragmento = fragment_home().apply {
                            arguments = Bundle().apply {
                                putString("dato", general_data)
                            }
                        }
                        Replace(fragmento)
                    }else{
                        Replace(fragment_home())
                    }
                }
                R.id.navigation_map->Replace(fragment_map())
                R.id.navigation_delivery->Replace(fragment_delivery())
                R.id.navigation_goals->Replace(fragment_goals())
            else->{}
            }
            true
        }
        supportActionBar?.setDisplayShowTitleEnabled(false)

    }

    private fun Replace(fragmento: Fragment){
        val fragmentomanager = supportFragmentManager
        val fragmentotransaction = fragmentomanager.beginTransaction()
        fragmentotransaction.replace(R.id.frameLayout,fragmento)
        fragmentotransaction.commit()
    }

    private fun Peticion(us: String, num: Int){
        val json = "{\"user\":\"$us\"}"
        Log.d("send",json)
        val url = "http://192.168.88.57:5000/data"
        val queue = Volley.newRequestQueue(this)

        // Request a string response from the provided URL.
        val stringRequest = JsonObjectRequest(Request.Method.GET, url, JSONObject(json),
            Response.Listener { response -> Log.d("exito",response.toString())
                general_data = response.toString()

                if(general_data!= null && num == 1){
                    Log.d("Datos",general_data.toString())
                    ExtractData(general_data.toString())
                    fragmento = fragment_home().apply {
                        arguments = Bundle().apply {
                            putString("dato", general_data)
                        }
                    }
                    Replace(fragmento)
                }else if(general_data!= null && num == 2){

                }else{
                    Replace(fragment_home())
                }

            },
            Response.ErrorListener { error -> Log.d("error","Hubo un error:${error.message}")})

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    private fun ExtractData(dat: String){
        val ima_noti = findViewById<ImageButton>(R.id.notif)
        val ima_lvl = findViewById<TextView>(R.id.level)
        val ima_din = findViewById<TextView>(R.id.coins)

        var objecto = JSONObject(dat)
        val nivel = objecto.getInt("nivel")
        val dinero = objecto.getInt("Dinero")
        val noti = objecto.getJSONArray("Notificacion")

        if (noti != null){
            ima_noti.setImageResource(R.drawable.notf)
        }

        ima_lvl.text = "Lvl " + nivel

        if (dinero<1000 && dinero>99){
            ima_din.text = "0" + dinero
        } else if(dinero<100 && dinero>9){
            ima_din.text = "00" + dinero
        } else {
            ima_din.text = "000" + dinero
        }

    }

    private fun Repite(){
        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable {
            override fun run() {

                Peticion("Elisa",2)
                if(general_data!=null){
                    Log.d("Actualizacion",general_data.toString())
                    ExtractData(general_data.toString())
                }
                handler.postDelayed(this, 1000)
            }
        }
        startRepeatingTask()
    }

    private fun startRepeatingTask() {
        runnable.run()
    }

    private fun stopRepeatingTask() {
        handler.removeCallbacks(runnable)
    }override fun onDestroy() {
        super.onDestroy()
        stopRepeatingTask()
    }

}