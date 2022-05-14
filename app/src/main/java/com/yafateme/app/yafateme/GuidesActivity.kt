package com.yafateme.app.yafateme

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.yafateme.app.yafateme.MainActivity.Companion.xcount

class GuidesActivity : AppCompatActivity() {
    var tvTitle: TextView? = null
    var tvTitle2: TextView? = null
    var tvGuide1: TextView? = null
    var tvGuide2: TextView? = null
    var tvGuide3: TextView? = null
    var tvGuide4: TextView? = null
    var btnVideo: ImageButton? = null
    var btnGuid1: ImageButton? = null
    var btnGuid2: ImageButton? = null
    var btnGuid3: ImageButton? = null
    var btnGuid4: ImageButton? = null
    lateinit var sp: SharedPreferences
    var lang = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guides)
        xcount = Integer.valueOf(getString(R.string.xCountString))
        tvTitle = findViewById<View>(R.id.tv_title) as TextView
        tvTitle2 = findViewById<View>(R.id.tv_title2) as TextView
        tvGuide1 = findViewById<View>(R.id.tv_guide1) as TextView
        tvGuide2 = findViewById<View>(R.id.tv_guide2) as TextView
        tvGuide3 = findViewById<View>(R.id.tv_guide3) as TextView
        tvGuide4 = findViewById<View>(R.id.tv_guide4) as TextView
        btnVideo = findViewById<View>(R.id.VidTuts) as ImageButton
        btnGuid1 = findViewById<View>(R.id.btn_guide1) as ImageButton
        btnGuid2 = findViewById<View>(R.id.btn_guide2) as ImageButton
        btnGuid3 = findViewById<View>(R.id.btn_guide3) as ImageButton
        btnGuid4 = findViewById<View>(R.id.btn_guide4) as ImageButton
        lang()
        btnGuid1!!.setOnClickListener {
            startActivity(Intent(this@GuidesActivity, GuideMainActivity::class.java))
            finish()
        }
        btnGuid2!!.setOnClickListener {
            startActivity(Intent(this@GuidesActivity, GuideFontColorsAdvance::class.java))
            finish()
        }
        btnGuid3!!.setOnClickListener {
            startActivity(Intent(this@GuidesActivity, GuideDuaPage::class.java))
            finish()
        }
        btnGuid4!!.setOnClickListener {
            startActivity(Intent(this@GuidesActivity, GuideSettings::class.java))
            finish()
        }
    }

    fun lang() {
        sp = applicationContext.getSharedPreferences("setting", 0)
        lang = sp.getInt("lang", 2)
        val fontmyredL = Typeface.createFromAsset(assets, "fonts/myriadl.ttf")
        val fontmyredM = Typeface.createFromAsset(assets, "fonts/myriadm.ttf")
        val fontArabic = Typeface.createFromAsset(assets, "fonts/arabic.ttf")
        val fontArabicB = Typeface.createFromAsset(assets, "fonts/arabicb.otf")
        tvTitle!!.setTypeface(fontArabicB)
        tvTitle2!!.setTypeface(fontArabicB)
        tvGuide4!!.setTypeface(fontArabic)
        tvGuide3!!.setTypeface(fontArabic)
        tvGuide1!!.setTypeface(fontArabic)
        tvGuide2!!.setTypeface(fontArabic)
        if (lang == 0) {
            tvTitle!!.setText(R.string.guide_title1_fa)
            tvTitle2!!.setText(R.string.guide_title2_fa)
            tvGuide3!!.setText(R.string.guide_play_fa)
            tvGuide4!!.setText(R.string.guide_setting_fa)
            tvGuide1!!.setText(R.string.guide_main_fa)
            tvGuide2!!.setText(R.string.guide_color_fa)
        } else if (lang == 2) {
            tvTitle!!.setTypeface(fontmyredL)
            tvTitle2!!.setTypeface(fontmyredL)
            tvGuide4!!.setTypeface(fontmyredM)
            tvGuide3!!.setTypeface(fontmyredM)
            tvGuide1!!.setTypeface(fontmyredM)
            tvGuide2!!.setTypeface(fontmyredM)
            tvTitle!!.setText(R.string.guide_title1_en)
            tvTitle2!!.setText(R.string.guide_title2_en)
            tvGuide3!!.setText(R.string.guide_play_en)
            tvGuide4!!.setText(R.string.guide_setting_en)
            tvGuide1!!.setText(R.string.guide_main_en)
            tvGuide2!!.setText(R.string.guide_color_en)
        } else if (lang == 1) {
            tvTitle!!.setText(R.string.guide_title1_ar)
            tvTitle2!!.setText(R.string.guide_title2_ar)
            tvGuide3!!.setText(R.string.guide_play_ar)
            tvGuide4!!.setText(R.string.guide_setting_ar)
            tvGuide1!!.setText(R.string.guide_main_ar)
            tvGuide2!!.setText(R.string.guide_color_ar)
        } else if (lang == 3) {
            tvTitle!!.setText(R.string.guide_title1_ur)
            tvTitle2!!.setText(R.string.guide_title2_ur)
            tvGuide3!!.setText(R.string.guide_play_ur)
            tvGuide4!!.setText(R.string.guide_setting_ur)
            tvGuide1!!.setText(R.string.guide_main_ur)
            tvGuide2!!.setText(R.string.guide_color_ur)
        }
        Log.d("TAG", "lang: $lang")
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val MainIntent = Intent(this@GuidesActivity, MainActivity::class.java)
            startActivity(MainIntent)
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    companion object {
        /*
        class GuidesActivity : AppCompatActivity() {
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_guides)

            }
        }

         */
    }


}
