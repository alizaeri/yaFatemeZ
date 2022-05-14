package com.yafateme.app.yafateme

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import com.yafateme.app.yafateme.MainActivity.Companion.lang
import com.yafateme.app.yafateme.Splash.Companion.listDoa
import com.yafateme.app.yafateme.Splash.Companion.listLang
import com.yafateme.app.yafateme.Splash.Companion.listLangApp

class FontsColorNormal : AppCompatActivity() {
    private var rbnazanin: RadioButton? = null
    private  var rbhoma:RadioButton? = null
    private  var rbarabic:RadioButton? = null
    private  var rbday:RadioButton? = null
    private  var rbnight:RadioButton? = null
    private var test: TextView? =  null
    private  var text00:TextView? = null
    private  var tex1:TextView? = null
    private  var tex2:TextView? = null
    private  var tex3:TextView? = null
    private var sbsize: SeekBar? = null
    private  var sbspace:SeekBar? = null
    private var save: TextView? = null
    private var font: Typeface? = null
    private var sfont: String? = null
    private var mood: String? = null
    private var btnSaveStr: String? = null
    private  var rbNazaninStr:kotlin.String? = null
    private  var rbHomaStr:kotlin.String? = null
    private  var rbArabicStr:kotlin.String? = null
    private var rbLightStr: String? = null
    private  var rbDarkStr:kotlin.String? = null
    private var tvFontStr: String? =  null
    private  var tvSizeStr:kotlin.String? = null
    private  var tvFontColorStr:kotlin.String? = null
    private  var tvLinStr:kotlin.String? = null
    private  var TestText:kotlin.String? = null
    private  lateinit var sp: SharedPreferences
    private var toas: String? = null
    private var saveLight: ImageButton? = null
   // private var db: database? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_fonts_color_normal)
        set()
        laod()
        setLanguage()
        ifLoad()
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade)
        saveLight!!.setBackgroundResource(R.drawable.set_savelight)
        saveLight!!.startAnimation(animation)
        sbsize!!.max = 30
        sbsize!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, value: Int, fromUser: Boolean) {
                test!!.textSize = value.toFloat()
            }
        })
        sbspace?.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, value: Int, fromUser: Boolean) {
                test!!.setLineSpacing(value.toFloat(), 1f)
            }
        })
        rbnazanin!!.setOnClickListener {
            font = Typeface.createFromAsset(assets, "font/homa.ttf")
            test!!.setTypeface(font)
            sfont = "homa"
            rbhoma?.setTextColor(Color.rgb(128, 130, 142))
            rbarabic?.setTextColor(Color.rgb(128, 130, 142))
            rbnazanin!!.setTextColor(Color.WHITE)
        }
        rbhoma?.setOnClickListener(View.OnClickListener {
            font = Typeface.createFromAsset(assets, "font/casablanca.ttf")
            test!!.setTypeface(font)
            sfont = "casablanca"
            rbhoma!!.setTextColor(Color.WHITE)
            rbarabic?.setTextColor(Color.rgb(128, 130, 142))
            rbnazanin!!.setTextColor(Color.rgb(128, 130, 142))
        })
        rbarabic?.setOnClickListener(View.OnClickListener {
            font = Typeface.createFromAsset(assets, "font/arabic.ttf")
            test!!.setTypeface(font)
            sfont = "arabic"
            rbhoma?.setTextColor(Color.rgb(128, 130, 142))
            rbarabic!!.setTextColor(Color.WHITE)
            rbnazanin!!.setTextColor(Color.rgb(128, 130, 142))
        })
        rbday?.setOnClickListener(View.OnClickListener {
            test!!.setTextColor(Color.rgb(255, 255, 255))
            test!!.setBackgroundResource(R.drawable.set_bgtest)
            mood = "day"
            rbnight?.setTextColor(Color.rgb(128, 130, 142))
            rbday!!.setTextColor(Color.WHITE)
        })
        rbnight?.setOnClickListener(View.OnClickListener {
            test!!.setTextColor(Color.rgb(7, 9, 25))
            test!!.setBackgroundResource(R.drawable.set_bgtest2)
            mood = "night"
            rbday?.setTextColor(Color.rgb(128, 130, 142))
            rbnight!!.setTextColor(Color.WHITE)
        })
        saveLight!!.setOnClickListener {
            sp = applicationContext.getSharedPreferences("setting", 0)
            val edit = sp.edit()
            edit.putString("fontfa", sfont)
            MainActivity.FaFont = Typeface.createFromAsset(assets, "font/$sfont.ttf")
            edit.putInt("FaSize", sbsize!!.progress)
            MainActivity.FaSize = sbsize!!.progress
            sbspace?.let { it1 -> edit.putInt("space", it1.getProgress()) }
            MainActivity.space = (sbspace?.getProgress() ?: edit.putString("mood", mood)) as Int
            MainActivity.mood = mood
            edit.apply()
            Toast.makeText(applicationContext, toas, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@FontsColorNormal, MainActivity::class.java))
            finish()
        }
    }

    private fun laod() {
        sp = applicationContext.getSharedPreferences("setting", 0)
        val g = sp.getString("font", "arabic")
        sfont = g
        font = Typeface.createFromAsset(assets, "font/$g.ttf")
        test!!.setTypeface(font)
        val f = sp.getInt("ArabicSize", 18)
        test!!.textSize = f.toFloat()
        sbsize!!.progress = f
        val p = sp.getInt("space", 2)
        test!!.setLineSpacing(p.toFloat(), 1f)
        sbspace?.setProgress(p)
        val m = sp.getString("mood", "day")
        mood = m
        if (m == "day") {
            mood = "day"
        } else if (m == "night") {
            mood = "night"
        }
        val t = sp.getString("dataEn", "true")
    }

    fun set() {
        rbnazanin = findViewById<View>(R.id.rbnazanin) as RadioButton
        rbhoma = findViewById<View>(R.id.rbhoma) as RadioButton
        rbarabic = findViewById<View>(R.id.rbarabic) as RadioButton
        rbday = findViewById<View>(R.id.rbday) as RadioButton
        rbnight = findViewById<View>(R.id.rbnight) as RadioButton
        test = findViewById<View>(R.id.test) as TextView
        text00 = findViewById<View>(R.id.textgroup_ar) as TextView
        tex1 = findViewById<View>(R.id.TextView01) as TextView
        tex2 = findViewById<View>(R.id.TextView03) as TextView
        tex3 = findViewById<View>(R.id.TextView02) as TextView
        sbsize = findViewById<View>(R.id.sbsize) as SeekBar
        sbspace = findViewById<View>(R.id.sbspace) as SeekBar
        save = findViewById<View>(R.id.save) as TextView
        saveLight = findViewById<View>(R.id.save_light) as ImageButton
    }

    fun setLanguage() {
        val fontmyredM = Typeface.createFromAsset(assets, "font/myriadl.ttf")
        val fontmyredL = Typeface.createFromAsset(assets, "font/myriadm.ttf")
        val fontArabic = Typeface.createFromAsset(assets, "font/arabic.ttf")
        val fontArabicB = Typeface.createFromAsset(assets, "font/arabicb.otf")

        rbday?.setTypeface(fontArabic)
        rbnight?.setTypeface(fontArabic)
        rbnazanin!!.setTypeface(fontArabic)
        rbhoma?.setTypeface(fontArabic)
        rbarabic?.setTypeface(fontArabic)
        save!!.setTypeface(fontArabic)
        text00?.setTypeface(fontArabic)
        tex3?.setTypeface(fontArabic)
        tex1?.setTypeface(fontArabic)
        tex2?.setTypeface(fontArabic)


        TestText = listDoa[2].text
        test?.setText(TestText)
        if (MainActivity.lang === 2) {
            rbday?.setTypeface(fontmyredL)
            rbnight?.setTypeface(fontmyredL)
            rbnazanin!!.setTypeface(fontmyredL)
            rbhoma?.setTypeface(fontmyredL)
            rbarabic?.setTypeface(fontmyredL)
            save!!.setTypeface(fontmyredM)
            text00?.setTypeface(fontmyredM)
            tex3?.setTypeface(fontmyredM)
            tex1?.setTypeface(fontmyredM)
            tex2?.setTypeface(fontmyredM)
        }
        when(lang){
            0 -> {
                btnSaveStr = listLangApp[8].translateFa
                rbNazaninStr = listLangApp[11].translateFa
                 rbHomaStr = listLang[9].translateFa
                 rbArabicStr = listLang[12].translateFa
                rbLightStr = listLang[17].translateFa
                rbDarkStr = listLang[16].translateFa
                 tvFontStr = listLangApp[20].translateFa
                 tvSizeStr = listLangApp[21].translateFa
                 tvFontColorStr = listLangApp[23].translateFa
                 tvLinStr = listLangApp[22].translateFa
                 toas = listLangApp[29].translateFa
            }
            1 -> {
                btnSaveStr = listLangApp[8].translateAr
                rbNazaninStr = listLangApp[11].translateAr
                rbHomaStr = listLang[9].translateAr
                rbArabicStr = listLang[12].translateAr
                rbLightStr = listLang[17].translateAr
                rbDarkStr = listLang[16].translateAr
                tvFontStr = listLangApp[20].translateAr
                tvSizeStr = listLangApp[21].translateAr
                tvFontColorStr = listLangApp[23].translateAr
                tvLinStr = listLangApp[22].translateAr
                toas = listLangApp[29].translateAr
            }
            2 ->  {
                btnSaveStr = listLangApp[8].translateEn
                rbNazaninStr = listLangApp[11].translateEn
                rbHomaStr = listLang[9].translateEn
                rbArabicStr = listLang[12].translateEn
                rbLightStr = listLang[17].translateEn
                rbDarkStr = listLang[16].translateEn
                tvFontStr = listLangApp[20].translateEn
                tvSizeStr = listLangApp[21].translateEn
                tvFontColorStr = listLangApp[23].translateEn
                tvLinStr = listLangApp[22].translateEn
                toas = listLangApp[29].translateEn
            }
            3 ->  {
                btnSaveStr = listLangApp[8].translateUr
                rbNazaninStr = listLangApp[11].translateUr
                rbHomaStr = listLang[9].translateUr
                rbArabicStr = listLang[12].translateUr
                rbLightStr = listLang[17].translateUr
                rbDarkStr = listLang[16].translateUr
                tvFontStr = listLangApp[20].translateUr
                tvSizeStr = listLangApp[21].translateUr
                tvFontColorStr = listLangApp[23].translateUr
                tvLinStr = listLangApp[22].translateUr
                toas = listLangApp[29].translateUr
            }
        }
       // btnSaveStr = db!!.namayesh(8, MainActivity.lang, "langapp")
       // rbNazaninStr = db!!.namayesh(11, MainActivity.lang, "langapp")
       // rbHomaStr = db!!.namayesh(9, MainActivity.lang, "lang")
       // rbArabicStr = db!!.namayesh(12, MainActivity.lang, "langapp")
        //rbLightStr = db!!.namayesh(17, MainActivity.lang, "langapp")
       // rbDarkStr = db!!.namayesh(16, MainActivity.lang, "langapp")
       // tvFontStr = db!!.namayesh(20, MainActivity.lang, "langapp")
       // tvSizeStr = db!!.namayesh(21, MainActivity.lang, "langapp")
       // tvFontColorStr = db!!.namayesh(23, MainActivity.lang, "langapp")
       // tvLinStr = db!!.namayesh(22, MainActivity.lang, "langapp")
       // toas = db!!.namayesh(29, MainActivity.lang, "langapp")
        save!!.text = btnSaveStr
        rbnazanin!!.setText(rbNazaninStr)
        rbhoma?.setText(rbHomaStr)
        rbarabic?.setText(rbArabicStr)
        rbday?.setText(rbLightStr)
        rbnight?.setText(rbDarkStr)
        text00?.setText(tvFontStr)
        tex1?.setText(tvSizeStr)
        tex2?.setText(tvFontColorStr)
        tex3?.setText(tvLinStr)
    }

    fun ifLoad() {
        if (MainActivity.mood.equals("day")) {
            test!!.setTextColor(Color.rgb(255, 255, 255))
            test!!.setBackgroundResource(R.drawable.set_bgtest)
            rbday?.setChecked(true)
            rbday?.setTextColor(Color.WHITE)
        } else if (MainActivity.mood.equals("night")) {
            test!!.setTextColor(Color.rgb(7, 9, 25))
            test!!.setBackgroundResource(R.drawable.set_bgtest2)
            rbnight?.setChecked(true)
            rbnight?.setTextColor(Color.WHITE)
        }
        if (sfont == "nazanin") {
            rbnazanin!!.isChecked = true
            rbnazanin!!.setTextColor(Color.WHITE)
        } else if (sfont == "homa") {
            rbhoma?.setChecked(true)
            rbhoma?.setTextColor(Color.WHITE)
        } else if (sfont == "arabic") {
            rbarabic?.setChecked(true)
            rbarabic?.setTextColor(Color.WHITE)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(Intent(this@FontsColorNormal, MainActivity::class.java))
            showInterstitial()
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    fun finish(view: View?) {
        showInterstitial()
        finish()
    }

    private fun showInterstitial() {}
}