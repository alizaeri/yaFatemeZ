package com.yafateme.app.yafateme

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.android.material.navigation.NavigationView

class GuideMainActivity : AppCompatActivity() {
    var finish: ImageButton? = null
    var menu: ImageButton? = null
    var btnPage: ImageButton? = null
    var btnBack: ImageButton? = null
    var btnNext: ImageButton? = null
    var rlMalakot: RelativeLayout? = null
    var intCont = 0
    var conterGuide = 0
    var copyt: TextView? = null
    var tvNote1: TextView? = null
    var l: ListView? = null
    lateinit var navigationView: NavigationView
    private val linearLayout = arrayOfNulls<LinearLayout>(6)
    private val Str = arrayOfNulls<String>(18)
    lateinit var id: Array<String>
    lateinit var sp: SharedPreferences
    var lang = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_main)
        var themp: Int
        //- --------------    -0-               -1-          -2-          -3-            -4-           -5-
        id = arrayOf(
            "li_guid_finish",
            "li_guid_menu",
            "li_note1",
            "li_guide_maing",
            "li_gravity",
            "li_guid_list"
        )
        for (i in id.indices) {
            themp = resources.getIdentifier(id[i], "id", packageName)
            linearLayout[i] = findViewById<View>(themp) as LinearLayout
        }

//        copyt  = (TextView) findViewById(R.id.textcopy);
        navigationView = findViewById(R.id.nav_view)
        tvNote1 = findViewById<View>(R.id.tv_note1) as TextView
        btnPage = findViewById<View>(R.id.btn_light) as ImageButton
        rlMalakot = findViewById<View>(R.id.rl_btn) as RelativeLayout
        finish = findViewById<View>(R.id.back_btn) as ImageButton
        menu = findViewById<View>(R.id.menu_btn) as ImageButton
        btnBack = findViewById<View>(R.id.btn_back) as ImageButton
        btnNext = findViewById<View>(R.id.btn_next) as ImageButton
        //        l= (ListView) findViewById(R.id.listView);
        langLoad()
        linearLayout[3]!!.setBackgroundResource(R.drawable.border)
        tvNote1!!.text = Str[0]
        btnBack!!.isEnabled = false
        btnBack!!.setBackgroundResource(R.drawable.back_not)
        val myAnimShake = AnimationUtils.loadAnimation(this@GuideMainActivity, R.anim.shake2)
        myAnimShake.interpolator = MyBounceInterpolator(0.2, 30.0)
        val myAnimShake2 = AnimationUtils.loadAnimation(this@GuideMainActivity, R.anim.shake2)
        myAnimShake2.interpolator = MyBounceInterpolator(0.2, 30.0)
        val myAnimShake3 = AnimationUtils.loadAnimation(this@GuideMainActivity, R.anim.shake2)
        myAnimShake3.interpolator = MyBounceInterpolator(0.2, 30.0)

//
//

        menu!!.setOnClickListener {
            tvNote1!!.startAnimation(myAnimShake3)
            btnEnable()
            if (intCont == 0) {
                menu!!.setBackgroundResource(R.drawable.back_menu)
                intCont = 1
                fadeout()
                conterGuide = 3
                linearLayout[4]!!.gravity = Gravity.CENTER
                noBorder()
                tvNote1!!.text = Str[3]
                linearLayout[5]!!.setBackgroundResource(R.drawable.border)
            } else if (intCont == 1) {
                if (conterGuide > 2) {
                    noBorderMenu()
                }
                linearLayout[1]!!.setBackgroundResource(R.drawable.border)
                tvNote1!!.text = Str[2]
                conterGuide = 2
                linearLayout[4]!!.gravity = Gravity.TOP
                menu!!.setBackgroundResource(R.drawable.menu_btn)
                intCont = 0
                fadein()
            }
        }
        finish!!.setOnClickListener {
            noBorder()
            if (conterGuide > 1) {
                noBorderMenu()
                menu!!.setBackgroundResource(R.drawable.menu_btn)
                intCont = 0
                fadein()
            }
            linearLayout[0]!!.setBackgroundResource(R.drawable.border)
            tvNote1!!.text = Str[1]
            conterGuide = 1
            linearLayout[4]!!.gravity = Gravity.TOP
            tvNote1!!.startAnimation(myAnimShake3)
            btnEnable()
        }
        btnPage!!.setOnClickListener {
            noBorder()
            if (conterGuide > 2) {
                noBorderMenu()
            }
            linearLayout[3]!!.setBackgroundResource(R.drawable.border)
            tvNote1!!.text = Str[0]
            conterGuide = 0
            btnBack!!.isEnabled = false
            btnBack!!.setBackgroundResource(R.drawable.back_not)
            linearLayout[4]!!.gravity = Gravity.TOP
            tvNote1!!.startAnimation(myAnimShake3)
        }
        navigationView.setOnClickListener(View.OnClickListener {
            if (conterGuide > 2) {
                noBorderMenu()
            }
            linearLayout[2]!!.setBackgroundResource(R.drawable.border)
            tvNote1!!.text = Str[2]
            tvNote1!!.startAnimation(myAnimShake3)
            btnNext!!.isEnabled = true
            btnNext!!.setBackgroundResource(R.drawable.next)
            conterGuide = 2
            linearLayout[5]!!.gravity = Gravity.TOP
            menu!!.setBackgroundResource(R.drawable.menu_btn)
            intCont = 0
            fadein()
        })
        btnNext!!.setOnClickListener {
            conterGuide = conterGuide + 1
            btnNext!!.startAnimation(myAnimShake2)
            tvNote1!!.startAnimation(myAnimShake3)
            noBorder()
            if (conterGuide > 3) {
                noBorderMenu()
            }
            when (conterGuide) {
                1 -> {
                    btnBack!!.isEnabled = true
                    btnBack!!.setBackgroundResource(R.drawable.back)
                    tvNote1!!.text = Str[1]
                    linearLayout[1]!!.setBackgroundResource(R.drawable.border)
                }
                2 -> {
                    tvNote1!!.text = Str[2]
                    linearLayout[2]!!.setBackgroundResource(R.drawable.border)
                }
                3 -> {
                    linearLayout[5]!!.gravity = Gravity.CENTER
                    menu!!.setBackgroundResource(R.drawable.back_menu)
                    intCont = 1
                    fadeout()
                    tvNote1!!.text = Str[3]
                    linearLayout[6]!!.setBackgroundResource(R.drawable.border)
                }
                4 -> {
                    tvNote1!!.text = Str[4]
                    l!!.getChildAt(0).setBackgroundResource(R.drawable.border)
                }
                5 -> {
                    tvNote1!!.text = Str[5]
                    l!!.getChildAt(1).setBackgroundResource(R.drawable.border)
                }
                6 -> {
                    tvNote1!!.text = Str[6]
                    l!!.getChildAt(2).setBackgroundResource(R.drawable.border)
                }
                7 -> {
                    linearLayout[5]!!.gravity = Gravity.BOTTOM
                    tvNote1!!.text = Str[7]
                    l!!.getChildAt(3).setBackgroundResource(R.drawable.border)
                }
                8 -> {
                    tvNote1!!.text = Str[8]
                    l!!.getChildAt(4).setBackgroundResource(R.drawable.border)
                }
                9 -> {
                    linearLayout[5]!!.gravity = Gravity.TOP
                    tvNote1!!.text = Str[10]
                    l!!.getChildAt(5).setBackgroundResource(R.drawable.border)
                }
                10 -> {
                    tvNote1!!.text = Str[11]
                    l!!.getChildAt(6).setBackgroundResource(R.drawable.border)
                }
                11 -> {
                    btnNext!!.isEnabled = false
                    btnNext!!.setBackgroundResource(R.drawable.next_not)
                    tvNote1!!.text = Str[12]
                    l!!.getChildAt(7).setBackgroundResource(R.drawable.border)
                }
            }
        }
        btnBack!!.setOnClickListener {
            conterGuide = conterGuide - 1
            btnBack!!.startAnimation(myAnimShake)
            tvNote1!!.startAnimation(myAnimShake3)
            noBorder()
            btnEnable()
            if (conterGuide > 2) {
                noBorderMenu()
            }
            when (conterGuide) {
                0 -> {
                    btnBack!!.isEnabled = false
                    btnBack!!.setBackgroundResource(R.drawable.back_not)
                    tvNote1!!.text = Str[0]
                    linearLayout[4]!!.setBackgroundResource(R.drawable.border)
                }
                1 -> {
                    tvNote1!!.text = Str[1]
                    linearLayout[1]!!.setBackgroundResource(R.drawable.border)
                }
                2 -> {
                    linearLayout[5]!!.gravity = Gravity.TOP
                    tvNote1!!.text = Str[2]
                    intCont = 0
                    linearLayout[2]!!.setBackgroundResource(R.drawable.border)
                    menu!!.setBackgroundResource(R.drawable.menu_btn)
                    fadein()
                }
                3 -> {
                    tvNote1!!.text = Str[3]
                    linearLayout[6]!!.setBackgroundResource(R.drawable.border)
                }
                4 -> {
                    tvNote1!!.text = Str[4]
                    l!!.getChildAt(0).setBackgroundResource(R.drawable.border)
                }
                5 -> {
                    tvNote1!!.text = Str[5]
                    l!!.getChildAt(1).setBackgroundResource(R.drawable.border)
                }
                6 -> {
                    tvNote1!!.text = Str[6]
                    l!!.getChildAt(2).setBackgroundResource(R.drawable.border)
                }
                7 -> {
                    linearLayout[5]!!.gravity = Gravity.CENTER
                    tvNote1!!.text = Str[7]
                    l!!.getChildAt(3).setBackgroundResource(R.drawable.border)
                }
                8 -> {
                    tvNote1!!.text = Str[8]
                    l!!.getChildAt(4).setBackgroundResource(R.drawable.border)
                }
                9 -> {
                    linearLayout[5]!!.gravity = Gravity.BOTTOM
                    tvNote1!!.text = Str[10]
                    l!!.getChildAt(5).setBackgroundResource(R.drawable.border)
                }
                10 -> {
                    tvNote1!!.text = Str[11]
                    l!!.getChildAt(6).setBackgroundResource(R.drawable.border)
                }
                11 -> {
                    btnNext!!.isEnabled = true
                    btnNext!!.setBackgroundResource(R.drawable.next)
                    tvNote1!!.text = Str[12]
                    l!!.getChildAt(7).setBackgroundResource(R.drawable.border)
                }
            }
        }
    }

    fun fadein() {
        linearLayout[0]!!.visibility = View.GONE
        val animationTarget = linearLayout[0]
        val animation = AnimationUtils.loadAnimation(this@GuideMainActivity, R.anim.menu_fade_out)
        animationTarget!!.startAnimation(animation)
        object : CountDownTimer(450, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                linearLayout[0]!!.clearAnimation()
            }
        }.start()
    }

    fun fadeout() {
        linearLayout[0]!!.visibility = View.VISIBLE
        val animationTarget = linearLayout[0]
        val animation = AnimationUtils.loadAnimation(this@GuideMainActivity, R.anim.menu_fade_in)
        animationTarget!!.startAnimation(animation)
        object : CountDownTimer(450, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                linearLayout[0]!!.clearAnimation()
            }
        }.start()
    }

    //---------------Clair all border from Liner layout background
    fun noBorder() {

        linearLayout[1]!!.setBackgroundResource(R.drawable.no_border)
        linearLayout[2]!!.setBackgroundResource(R.drawable.no_border)
        linearLayout[4]!!.setBackgroundResource(R.drawable.no_border)
        linearLayout[5]!!.setBackgroundResource(R.drawable.no_border)


    }

    fun noBorderMenu() {
        for (i in 0..7) {
            l!!.getChildAt(i).setBackgroundResource(R.drawable.no_border)
        }
    }

    fun langLoad() {
        sp = applicationContext.getSharedPreferences("setting", 0)
        lang = sp.getInt("lang", 2)
        val fontArabic = Typeface.createFromAsset(assets, "fonts/arabic.ttf")
        val fontmyredL = Typeface.createFromAsset(assets, "fonts/myriadm.ttf")
        val fontmyredM = Typeface.createFromAsset(assets, "fonts/myriadl.ttf")
        tvNote1!!.setTypeface(fontArabic)

        var a = 0
        if (lang == 2) {
            a = 2
            tvNote1!!.setTypeface(fontmyredL)
            tvNote1!!.textSize = 20f
        }
        for (i in 0..17) {
            when(lang){
                0 ->  Str[i]= Splash.listLang[i + 100].translateFa
                1 ->  Str[i] = Splash.listLang[i + 100].translateAr
                2 ->  Str[i] = Splash.listLang[i + 100].translateEn
                3 ->  Str[i] = Splash.listLang[i + 100].translateUr
            }

        }
    }

    fun btnEnable() {
        btnBack!!.isEnabled = true
        btnBack!!.setBackgroundResource(R.drawable.back)
        btnNext!!.isEnabled = true
        btnNext!!.setBackgroundResource(R.drawable.next)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val alert = AlertDialog.Builder(this@GuideMainActivity)
            alert.setTitle(Str[14])
            alert.setMessage(Str[15])
            alert.setPositiveButton(
                Str[16]
            ) { dialog, which ->
                val MainIntent = Intent(this@GuideMainActivity, GuidesActivity::class.java)
                startActivity(MainIntent)
                dialog.dismiss()
                finish()
            }
            alert.setNegativeButton(
                Str[17]
            ) { dialog, which -> dialog.dismiss() }
            alert.show()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
