package com.atilsamancioglu.havucoyunu

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var skor = 0
    var gorselListesi = ArrayList<ImageView>()
    var handler = Handler()
    var runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Görsel Listesi
        gorselListesi.add(imageView)
        gorselListesi.add(imageView2)
        gorselListesi.add(imageView3)
        gorselListesi.add(imageView4)
        gorselListesi.add(imageView5)
        gorselListesi.add(imageView6)
        gorselListesi.add(imageView7)
        gorselListesi.add(imageView8)
        gorselListesi.add(imageView9)

        //CountDownTimer
        object : CountDownTimer(15000,1000){
            override fun onFinish() {
                zamanText.text = "Kalan Zaman: 0"

                //Handler
                handler.removeCallbacks(runnable)

                for (gorsel in gorselListesi) {
                    gorsel.visibility = View.INVISIBLE
                }

                //Alert Dialog
                var uyariMesaji = AlertDialog.Builder(this@MainActivity)
                uyariMesaji.setTitle("Oyun Bitti!")
                uyariMesaji.setMessage("Tekrar Oynamak İster misiniz?")
                uyariMesaji.setPositiveButton("Evet"){ dialogInterface: DialogInterface, i: Int ->
                    //Oyunu baştan başlat
                    val intent = intent
                    finish()
                    startActivity(intent)
                }

                uyariMesaji.setNegativeButton("Hayır",DialogInterface.OnClickListener { dialog, which ->
                    //Oyun bitiyor
                    Toast.makeText(this@MainActivity,"Oyun Bitti",Toast.LENGTH_LONG).show()
                })

                uyariMesaji.show()

            }

            override fun onTick(millisUntilFinished: Long) {
                zamanText.text = "Kalan Zaman: ${millisUntilFinished/1000}"
            }

        }.start()


        //Havuç Gizleme İşlemleri
        havucGizle()

    }

    fun havucGizle(){
        runnable = object : Runnable{
            override fun run() {
                for (gorsel in gorselListesi) {
                    gorsel.visibility = View.INVISIBLE
                }
                val random = Random()
                val rastgeleSayi = random.nextInt(9)
                gorselListesi[rastgeleSayi].visibility = View.VISIBLE

                handler.postDelayed(runnable,500)
            }

        }

        handler.post(runnable)
    }

    fun skoruArttir(view : View){
        skor = skor + 1
        skorText.text = "Skor: ${skor}"
    }
}