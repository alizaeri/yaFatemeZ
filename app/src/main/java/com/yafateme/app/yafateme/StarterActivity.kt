package com.yafateme.app.yafateme

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.annotation.RequiresApi
import com.yafateme.app.yafateme.MainActivity.Companion.lang
import com.yafateme.app.yafateme.Splash.Companion.itemsList
import com.yafateme.app.yafateme.Splash.Companion.listLang
import com.yafateme.app.yafateme.Splash.Companion.listLanguage
import androidx.multidex.MultiDex

import androidx.multidex.MultiDexApplication
import com.yafateme.app.yafateme.Splash.Companion.madahList
import com.yafateme.app.yafateme.Splash.Companion.madhList
//import com.yafateme.app.yafateme.database.Companion.Name
//import com.yafateme.app.yafateme.database.Companion.path
import java.util.ArrayList

class StarterActivity : AppCompatActivity() {
    lateinit var spin_lang: Spinner
    lateinit var sp: SharedPreferences
    var rbEnStr: String? = null
    var rbFaStr: String? = null
    var rbArStr: String? = null
    var rbUrStr: String? = null
    var save: TextView? = null
    var save2: TextView? = null
    var tex5: TextView? = null
    var saveLight: ImageButton? = null
    var saveLight2: ImageButton? = null
   // var db: database? = null
   // var lang = 1
    var f : Int = 0
    private var adapter_lang: SpinAdapter? = null
    var langApp: ArrayList<Spin> = ArrayList<Spin>()
    lateinit var mydb: SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starter)


        val bundle = intent.extras
        //----------------define-all-view-----------------
        save = findViewById<View>(R.id.save) as TextView
        save2 = findViewById<View>(R.id.save2) as TextView
        tex5 = findViewById<View>(R.id.TextView05) as TextView
        saveLight = findViewById<View>(R.id.save_light) as ImageButton
        saveLight2 = findViewById<View>(R.id.save_light2) as ImageButton
        spin_lang = findViewById(R.id.spin_lang)
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade)
        saveLight!!.startAnimation(animation)
        saveLight2!!.startAnimation(animation)
        val fontArabic = Typeface.createFromAsset(assets, "fonts/arabic.ttf")
        val fontmyredM = Typeface.createFromAsset(assets, "fonts/myriadl.ttf")
        //----------------get-db-text-from-database------------------

        showData("language", "lang0", langApp)
        adapter_lang = SpinAdapter(
            this@StarterActivity,
            R.layout.spinner,
            langApp
        )
        spin_lang.setAdapter(adapter_lang)
        spin_lang.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {

            @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            )  {
                val lin = view.findViewById<View>(R.id.l_line) as LinearLayout
                lin.visibility = View.GONE
                val text = view.findViewById<TextView>(R.id.text_spin)
                text.setTextColor(Color.parseColor("#3d3e43"))

                //text.setText("farsi")
                val spinner: Spin? = adapter_lang!!.getItem(position)
                if (spinner != null) {
                    lang = spinner?.id-1
                    Log.d("!!!", "Lang: set meghdare lang : $lang va spinner.id = ${spinner?.id} getitemposition : ${spinner} ")

                }

                view.textAlignment = View.TEXT_ALIGNMENT_CENTER
                view.setBackgroundResource(R.drawable.set_bgradioselect)
                Lang(lang)

                    when(lang){
                        0-> {text.setText(listLanguage[0].lang0)
                        save!!.setTypeface(fontArabic)
                                save2!!.setTypeface(fontArabic)
                                tex5!!.setTypeface(fontArabic)}
                        1-> {text.setText(listLanguage[1].lang1)
                            save!!.setTypeface(fontArabic)
                            save2!!.setTypeface(fontArabic)
                            tex5!!.setTypeface(fontArabic)}
                        2-> {text.setText(listLanguage[2].lang2)
                            save!!.setTypeface(fontmyredM)
                            save2!!.setTypeface(fontmyredM)
                            tex5!!.setTypeface(fontmyredM)}

                        3-> {text.setText(listLanguage[3].lang3)
                        save!!.setTypeface(fontArabic)
                                save2!!.setTypeface(fontArabic)
                                tex5!!.setTypeface(fontArabic)}
                    }

                showData("language", "lang0", langApp)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        //*******************************
        saveLight!!.setOnClickListener {
            SaveTuts()
            startActivity(Intent(this@StarterActivity, GuidesActivity::class.java))
            finish()
        }
        saveLight2!!.setOnClickListener {
            SaveTuts()
            startActivity(Intent(this@StarterActivity, MainActivity::class.java))
            finish()
        }
    }

    fun SaveTuts() {
        sp = applicationContext.getSharedPreferences("setting", 0)
        val edit = sp.edit()
        edit.putInt("lang", lang)
        MainActivity.lang = lang
        edit.putString("chekTuts", "false")
        Splash.chekTuts = "false"
        edit.apply()
    }

    fun Lang( l: Int) {

        for (item in listLang){
            if(item.id == 246){
                when (l){
                    0 ->  tex5!!.text = item.translateFa
                    1 ->  tex5!!.text = item.translateAr
                    2 ->  tex5!!.text = item.translateEn
                    3 ->  tex5!!.text = item.translateUr
                }

            } else if (item.id == 266) {
                when (l) {
                    0 -> save!!.text = item.translateFa
                    1 -> save!!.text = item.translateAr
                    2 -> save!!.text = item.translateEn
                    3 -> save!!.text = item.translateUr

                }
            } else if (item.id == 267) {
                when (l) {
                    0 ->  save2!!.text = item.translateFa
                   1 ->  save2!!.text = item.translateAr
                    2 ->  save2!!.text = item.translateEn
                    3 ->  save2!!.text = item.translateUr
                }

                }
            /*
            if (l != 3) {
                val fontArabic = Typeface.createFromAsset(assets, "fonts/arabic.ttf")
                save!!.setTypeface(fontArabic)
                save2!!.setTypeface(fontArabic)
                tex5!!.setTypeface(fontArabic)
            } else {
                val fontmyredM = Typeface.createFromAsset(assets, "fonts/myriadl.ttf")
                save!!.setTypeface(fontmyredM)
                save2!!.setTypeface(fontmyredM)
                tex5!!.setTypeface(fontmyredM)
            }

             */
        }
    }

    @SuppressLint("Range")
    private fun showData(tableName: String, nameField: String, value: ArrayList<Spin>) {
        value.clear()
        for (item in listLanguage)
        {

            val arrey = Spin()
            arrey.id = item.id
            when (lang){
                0-> arrey.name = item.lang0
                1-> arrey.name = item.lang1
                2-> arrey.name = item.lang2
                3-> arrey.name = item.lang3
            }

            value.add(arrey)

        }

    }
}