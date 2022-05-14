package com.yafateme.app.yafateme

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog


class GuideSettings : Activity() {
    lateinit var id: Array<String>
    lateinit var id2: Array<String>
    lateinit var id3: Array<String>
    private var checkScroll: CheckBox? = null
    private var checkBgMp: CheckBox? = null
    private var chekSeek: CheckBox? = null
    private var chekReview: CheckBox? = null
    private var chekAdvance: CheckBox? = null
    lateinit var sp: SharedPreferences
    private var toas: String? = null
    private val textView = arrayOfNulls<TextView>(15)
    private val radioButtons = arrayOfNulls<RadioButton>(17)
    private var saveLight: ImageButton? = null
    private val Str = arrayOfNulls<String>(16)
    var btnBack: ImageButton? = null
    var btnNext: ImageButton? = null
    var linearLayout = arrayOfNulls<LinearLayout>(13)
    var lang = 0
    var conterGuide = 0
    var scrollV: ScrollView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_guide_settings)
        btnBack = findViewById<View>(R.id.btn_back) as ImageButton
        btnNext = findViewById<View>(R.id.btn_next) as ImageButton
        scrollV = findViewById<View>(R.id.scrollView1) as ScrollView
        var themp: Int
        var themp2: Int
        var themp3: Int
        //- --------------     -0-          -1-         -2-          -3-          -4-        -5-         -6-          -7-         -8-           -9-           -10-     -11-     -12-         -13-
        id = arrayOf(
            "TextView04",
            "TextView07",
            "TextView05",
            "TextView06",
            "tv_scroll",
            "tv_bgmp",
            "tv_otherseet",
            "tv_rep",
            "tv_seektest",
            "tv_advanc_mod",
            "tv_review",
            "save",
            "tv_reset",
            "btn_reset",
            "tv_note2"
        )

        //- --------------     -0-        -1-        -2-      -3-      -4-      -5-      -6-       -7-     -8-
        id2 = arrayOf(
            "rb_song1",
            "rb_song2",
            "rb_song3",
            "rbset3",
            "rbset1",
            "rbset2",
            "rbset4",
            "rb_all",
            "rb_none",  // -9-     -10-     -11-       -12-       -13-        -14-     -15-       -16-
            "rbTrFa",
            "rbTrEn",
            "rbTrUr",
            "rbTrNone",
            "rbTr2Fa",
            "rbTr2En",
            "rbTr2Ur",
            "rbTr2None"
        )
        //				-0-			 	-1-				-2-				-3-				-4-				-5-					-6-				-7-					-8-
        id3 = arrayOf(
            "li_guide_tr1",
            "li_guide_tr2",
            "li_guide_lang",
            "li_guide_medah",
            "li_guide_rep",
            "li_guide_scroll",
            "li_guide_bgpl",
            "li_guide_review",
            "li_guide_scroll_anim",  // 			-9-     		-10- 		    -11- 		      -12-
            "li_guide_color_adv",
            "li_guide_res",
            "li_guide_save_set",
            "li_gravity"
        )
        for (i in id.indices) {
            themp = resources.getIdentifier(id[i], "id", packageName)
            textView[i] = findViewById<View>(themp) as TextView
        }
        for (i in id2.indices) {
            themp2 = resources.getIdentifier(id2[i], "id", packageName)
            radioButtons[i] = findViewById<View>(themp2) as RadioButton
        }
        for (i in id3.indices) {
            themp3 = resources.getIdentifier(id3[i], "id", packageName)
            linearLayout[i] = findViewById<View>(themp3) as LinearLayout
        }
        lang()
        linearLayout[0]!!.setBackgroundResource(R.drawable.border)
        btnBack!!.isEnabled = false
        btnBack!!.setBackgroundResource(R.drawable.back_not)
        val myAnimShake = AnimationUtils.loadAnimation(this@GuideSettings, R.anim.shake2)
        myAnimShake.interpolator = MyBounceInterpolator(0.2, 30.0)
        val myAnimShake2 = AnimationUtils.loadAnimation(this@GuideSettings, R.anim.shake2)
        myAnimShake2.interpolator = MyBounceInterpolator(0.2, 30.0)
        val myAnimShake3 = AnimationUtils.loadAnimation(this@GuideSettings, R.anim.shake2)
        myAnimShake3.interpolator = MyBounceInterpolator(0.2, 30.0)
        set()
        setLanguage()
        ifLoad()
        textView[14]!!.text = Str[0]
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade)
        saveLight!!.setBackgroundResource(R.drawable.set_savelight)
        saveLight!!.startAnimation(animation)
        checkScroll!!.setOnClickListener {
            conterGuide = 5
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.BOTTOM
            scrollV!!.smoothScrollTo(0, linearLayout[5]!!.top)
            linearLayout[5]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[5]
        }
        chekReview!!.setOnClickListener {
            conterGuide = 7
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.BOTTOM
            scrollV!!.smoothScrollTo(0, linearLayout[7]!!.top)
            linearLayout[7]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[7]
        }
        //---------------------------------
        textView[13]!!.setOnClickListener {
            conterGuide = 10
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.CENTER
            scrollV!!.smoothScrollTo(0, linearLayout[10]!!.top)
            linearLayout[10]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[10]
        }
        //------------------------------advanc mod
        chekAdvance!!.setOnClickListener {
            conterGuide = 9
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.BOTTOM
            scrollV!!.smoothScrollTo(0, linearLayout[9]!!.top)
            linearLayout[9]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[9]
        }
        chekSeek!!.setOnClickListener {
            conterGuide = 8
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.BOTTOM
            scrollV!!.smoothScrollTo(0, linearLayout[8]!!.top)
            linearLayout[8]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[8]
        }
        checkBgMp!!.setOnClickListener {
            conterGuide = 6
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.BOTTOM
            scrollV!!.smoothScrollTo(0, linearLayout[6]!!.top)
            linearLayout[6]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[6]
        }
        radioButtons[8]!!.setOnClickListener {
            radioButtons[7]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[8]!!.setTextColor(Color.WHITE)
            conterGuide = 4
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.CENTER
            scrollV!!.smoothScrollTo(0, linearLayout[4]!!.top)
            linearLayout[4]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[4]
        }
        radioButtons[7]!!.setOnClickListener {
            radioButtons[8]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[7]!!.setTextColor(Color.WHITE)
            conterGuide = 4
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.CENTER
            scrollV!!.smoothScrollTo(0, linearLayout[4]!!.top)
            linearLayout[4]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[4]
        }
        radioButtons[5]!!.setOnClickListener {
            radioButtons[4]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[3]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[6]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[5]!!.setTextColor(Color.WHITE)
            conterGuide = 2
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.CENTER
            scrollV!!.smoothScrollTo(0, linearLayout[2]!!.top)
            linearLayout[2]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[2]
        }
        radioButtons[4]!!.setOnClickListener {
            radioButtons[5]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[3]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[6]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[4]!!.setTextColor(Color.WHITE)
            conterGuide = 2
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.CENTER
            scrollV!!.smoothScrollTo(0, linearLayout[2]!!.top)
            linearLayout[2]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[2]
        }
        radioButtons[3]!!.setOnClickListener {
            radioButtons[5]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[3]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[6]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[3]!!.setTextColor(Color.WHITE)
            conterGuide = 2
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.CENTER
            scrollV!!.smoothScrollTo(0, linearLayout[2]!!.top)
            linearLayout[2]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[2]
        }
        radioButtons[6]!!.setOnClickListener {
            radioButtons[5]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[3]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[6]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[6]!!.setTextColor(Color.WHITE)
            conterGuide = 2
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.CENTER
            scrollV!!.smoothScrollTo(0, linearLayout[2]!!.top)
            linearLayout[2]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[2]
        }
        radioButtons[0]!!.setOnClickListener {
            radioButtons[2]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[1]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[0]!!.setTextColor(Color.WHITE)
            conterGuide = 3
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.CENTER
            scrollV!!.smoothScrollTo(0, linearLayout[3]!!.top)
            linearLayout[3]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[3]
        }
        radioButtons[1]!!.setOnClickListener {
            radioButtons[2]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[0]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[1]!!.setTextColor(Color.WHITE)
            conterGuide = 3
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.CENTER
            scrollV!!.smoothScrollTo(0, linearLayout[3]!!.top)
            linearLayout[3]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[3]
        }
        radioButtons[2]!!.setOnClickListener {
            radioButtons[0]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[1]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[2]!!.setTextColor(Color.WHITE)
            conterGuide = 3
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.CENTER
            scrollV!!.smoothScrollTo(0, linearLayout[3]!!.top)
            linearLayout[3]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[3]
        }
        radioButtons[9]!!.setOnClickListener {
            rbBtn1()
            radioButtons[9]!!.setTextColor(Color.WHITE)
            conterGuide = 0
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.CENTER
            scrollV!!.smoothScrollTo(0, linearLayout[0]!!.top)
            linearLayout[0]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[0]
            btnBack!!.isEnabled = false
            btnBack!!.setBackgroundResource(R.drawable.back_not)
        }
        radioButtons[10]!!.setOnClickListener {
            rbBtn1()
            radioButtons[10]!!.setTextColor(Color.WHITE)
            conterGuide = 0
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.CENTER
            scrollV!!.smoothScrollTo(0, linearLayout[0]!!.top)
            linearLayout[0]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[0]
            btnBack!!.isEnabled = false
            btnBack!!.setBackgroundResource(R.drawable.back_not)
        }
        radioButtons[11]!!.setOnClickListener {
            rbBtn1()
            radioButtons[11]!!.setTextColor(Color.WHITE)
            conterGuide = 0
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.CENTER
            scrollV!!.smoothScrollTo(0, linearLayout[0]!!.top)
            linearLayout[0]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[0]
            btnBack!!.isEnabled = false
            btnBack!!.setBackgroundResource(R.drawable.back_not)
        }
        radioButtons[12]!!.setOnClickListener {
            rbBtn1()
            radioButtons[12]!!.setTextColor(Color.WHITE)
            conterGuide = 0
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.CENTER
            scrollV!!.smoothScrollTo(0, linearLayout[0]!!.top)
            linearLayout[0]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[0]
            btnBack!!.isEnabled = false
            btnBack!!.setBackgroundResource(R.drawable.back_not)
        }
        radioButtons[13]!!.setOnClickListener {
            rbBtn2()
            radioButtons[13]!!.setTextColor(Color.WHITE)
            conterGuide = 1
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.CENTER
            scrollV!!.smoothScrollTo(0, linearLayout[1]!!.top)
            linearLayout[1]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[1]
        }
        radioButtons[14]!!.setOnClickListener {
            rbBtn2()
            radioButtons[14]!!.setTextColor(Color.WHITE)
            conterGuide = 1
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.CENTER
            scrollV!!.smoothScrollTo(0, linearLayout[1]!!.top)
            linearLayout[1]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[1]
        }
        radioButtons[15]!!.setOnClickListener {
            rbBtn2()
            radioButtons[15]!!.setTextColor(Color.WHITE)
            conterGuide = 1
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.CENTER
            scrollV!!.smoothScrollTo(0, linearLayout[1]!!.top)
            linearLayout[1]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[1]
        }
        radioButtons[16]!!.setOnClickListener {
            rbBtn2()
            radioButtons[16]!!.setTextColor(Color.WHITE)
            conterGuide = 1
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.CENTER
            scrollV!!.smoothScrollTo(0, linearLayout[1]!!.top)
            linearLayout[1]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[1]
        }
        saveLight!!.setOnClickListener {
            conterGuide = 11
            noBorder()
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.CENTER
            scrollV!!.smoothScrollTo(0, linearLayout[11]!!.top)
            linearLayout[11]!!.setBackgroundResource(R.drawable.border)
            textView[14]!!.text = Str[11]
            btnNext!!.isEnabled = false
            btnNext!!.setBackgroundResource(R.drawable.next_not)
            btnNext!!.isEnabled = false
        }
        btnNext!!.setOnClickListener {
            noBorder()
            conterGuide = conterGuide + 1
            btnNext!!.startAnimation(myAnimShake2)
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.CENTER
            when (conterGuide) {
                1 -> {
                    btnBack!!.isEnabled = true
                    scrollV!!.smoothScrollTo(0, linearLayout[1]!!.top)
                    btnBack!!.setBackgroundResource(R.drawable.back)
                    textView[14]!!.text = Str[1]
                    linearLayout[1]!!.setBackgroundResource(R.drawable.border)
                }
                2 -> {
                    textView[14]!!.text = Str[2]
                    scrollV!!.smoothScrollTo(0, linearLayout[2]!!.top)
                    linearLayout[2]!!.setBackgroundResource(R.drawable.border)
                }
                3 -> {
                    textView[14]!!.text = Str[3]
                    scrollV!!.smoothScrollTo(0, linearLayout[3]!!.top)
                    linearLayout[3]!!.setBackgroundResource(R.drawable.border)
                }
                4 -> {
                    linearLayout[4]!!.setBackgroundResource(R.drawable.border)
                    textView[14]!!.text = Str[4]
                    scrollV!!.smoothScrollTo(0, linearLayout[4]!!.top)
                }
                5 -> {
                    scrollV!!.smoothScrollTo(0, linearLayout[5]!!.top)
                    linearLayout[5]!!.setBackgroundResource(R.drawable.border)
                    textView[14]!!.text = Str[5]
                    linearLayout[12]!!.gravity = Gravity.BOTTOM
                }
                6 -> {
                    linearLayout[6]!!.setBackgroundResource(R.drawable.border)
                    textView[14]!!.text = Str[6]
                    scrollV!!.smoothScrollTo(0, linearLayout[6]!!.top)
                    linearLayout[12]!!.gravity = Gravity.BOTTOM
                }
                7 -> {
                    linearLayout[7]!!.setBackgroundResource(R.drawable.border)
                    textView[14]!!.text = Str[7]
                    scrollV!!.smoothScrollTo(0, linearLayout[7]!!.top)
                    linearLayout[12]!!.gravity = Gravity.BOTTOM
                }
                8 -> {
                    linearLayout[8]!!.setBackgroundResource(R.drawable.border)
                    textView[14]!!.text = Str[8]
                    scrollV!!.smoothScrollTo(0, linearLayout[8]!!.top)
                    linearLayout[12]!!.gravity = Gravity.BOTTOM
                }
                9 -> {
                    linearLayout[9]!!.setBackgroundResource(R.drawable.border)
                    textView[14]!!.text = Str[9]
                    scrollV!!.smoothScrollTo(0, linearLayout[9]!!.top)
                    linearLayout[12]!!.gravity = Gravity.BOTTOM
                }
                10 -> {
                    scrollV!!.smoothScrollTo(0, linearLayout[10]!!.top)
                    textView[14]!!.text = Str[10]
                    linearLayout[10]!!.setBackgroundResource(R.drawable.border)
                }
                11 -> {
                    scrollV!!.smoothScrollTo(0, linearLayout[11]!!.top)
                    textView[14]!!.text = Str[11]
                    linearLayout[11]!!.setBackgroundResource(R.drawable.border)
                    btnNext!!.setBackgroundResource(R.drawable.next_not)
                    btnNext!!.isEnabled = false
                }
            }
        }
        btnBack!!.setOnClickListener {
            noBorder()
            conterGuide = conterGuide - 1
            btnBack!!.startAnimation(myAnimShake)
            textView[14]!!.startAnimation(myAnimShake3)
            linearLayout[12]!!.gravity = Gravity.CENTER
            when (conterGuide) {
                0 -> {
                    btnBack!!.isEnabled = false
                    btnBack!!.setBackgroundResource(R.drawable.back_not)
                    textView[14]!!.text = Str[0]
                    linearLayout[0]!!.setBackgroundResource(R.drawable.border)
                    scrollV!!.smoothScrollTo(0, linearLayout[0]!!.top)
                }
                1 -> {
                    textView[14]!!.text = Str[1]
                    scrollV!!.smoothScrollTo(0, linearLayout[1]!!.top)
                    linearLayout[1]!!.setBackgroundResource(R.drawable.border)
                }
                2 -> {
                    textView[14]!!.text = Str[2]
                    scrollV!!.smoothScrollTo(0, linearLayout[2]!!.top)
                    linearLayout[2]!!.setBackgroundResource(R.drawable.border)
                }
                3 -> {
                    textView[14]!!.text = Str[3]
                    scrollV!!.smoothScrollTo(0, linearLayout[3]!!.top)
                    linearLayout[3]!!.setBackgroundResource(R.drawable.border)
                }
                4 -> {
                    linearLayout[4]!!.setBackgroundResource(R.drawable.border)
                    textView[14]!!.text = Str[4]
                    scrollV!!.smoothScrollTo(0, linearLayout[4]!!.top)
                }
                5 -> {
                    scrollV!!.smoothScrollTo(0, linearLayout[5]!!.top)
                    linearLayout[5]!!.setBackgroundResource(R.drawable.border)
                    textView[14]!!.text = Str[5]
                    linearLayout[12]!!.gravity = Gravity.BOTTOM
                }
                6 -> {
                    linearLayout[6]!!.setBackgroundResource(R.drawable.border)
                    textView[14]!!.text = Str[6]
                    scrollV!!.smoothScrollTo(0, linearLayout[6]!!.top)
                    linearLayout[12]!!.gravity = Gravity.BOTTOM
                }
                7 -> {
                    linearLayout[7]!!.setBackgroundResource(R.drawable.border)
                    textView[14]!!.text = Str[7]
                    scrollV!!.smoothScrollTo(0, linearLayout[7]!!.top)
                    linearLayout[12]!!.gravity = Gravity.BOTTOM
                }
                8 -> {
                    linearLayout[8]!!.setBackgroundResource(R.drawable.border)
                    textView[14]!!.text = Str[8]
                    scrollV!!.smoothScrollTo(0, linearLayout[8]!!.top)
                    linearLayout[12]!!.gravity = Gravity.BOTTOM
                }
                9 -> {
                    linearLayout[9]!!.setBackgroundResource(R.drawable.border)
                    textView[14]!!.text = Str[9]
                    scrollV!!.smoothScrollTo(0, linearLayout[9]!!.top)
                    linearLayout[12]!!.gravity = Gravity.BOTTOM
                }
                10 -> {
                    scrollV!!.smoothScrollTo(0, linearLayout[10]!!.top)
                    textView[14]!!.text = Str[10]
                    linearLayout[10]!!.setBackgroundResource(R.drawable.border)
                    btnNext!!.isEnabled = true
                    btnNext!!.setBackgroundResource(R.drawable.next)
                }
            }
        }
    }

    fun setLanguage() {
        val fontmyredM = Typeface.createFromAsset(assets, "fonts/myriadl.ttf")
        val fontmyredL = Typeface.createFromAsset(assets, "fonts/myriadm.ttf")
        val fontArabic = Typeface.createFromAsset(assets, "fonts/arabic.ttf")

        if (lang == 2) {
            for (i in id2.indices) {
                textView[13]!!.typeface = fontmyredL
                textView[14]!!.typeface = fontmyredL
            }
        }
        for (i in id2.indices) {
            radioButtons[i]!!.typeface = fontArabic
            when(lang){
                0 ->  radioButtons[i]!!.text= Splash.listLang[i + 49].translateFa
                1 ->  radioButtons[i]!!.text = Splash.listLang[i + 49].translateAr
                2 ->  radioButtons[i]!!.text = Splash.listLang[i + 49].translateEn
                3 ->  radioButtons[i]!!.text = Splash.listLang[i + 49].translateUr
            }
        }
        for (i in id.indices) {

            textView[i]!!.typeface = fontArabic

            when(lang){
                0 ->  textView[i]!!.text= Splash.listLang[i + 79].translateFa
                1 ->  textView[i]!!.text = Splash.listLang[i + 79].translateAr
                2 ->  textView[i]!!.text = Splash.listLang[i + 79].translateEn
                3 ->  textView[i]!!.text = Splash.listLang[i + 79].translateUr
            }
        }
        when(lang){
            0 ->  toas= Splash.listLang[14].translateFa
            1 ->  toas = Splash.listLang[14].translateAr
            2 ->  toas = Splash.listLang[14].translateEn
            3 ->  toas = Splash.listLang[14].translateUr
        }

    }

    fun ifLoad() {
        radioButtons[4]!!.isChecked = true
        radioButtons[4]!!.setTextColor(Color.WHITE)
        radioButtons[10]!!.isChecked = true
        radioButtons[10]!!.setTextColor(Color.WHITE)
        radioButtons[15]!!.isChecked = true
        radioButtons[15]!!.setTextColor(Color.WHITE)
        radioButtons[1]!!.isChecked = true
        radioButtons[1]!!.setTextColor(Color.WHITE)
        checkScroll!!.isChecked = true
        checkScroll!!.setTextColor(Color.WHITE)
        checkBgMp!!.isChecked = true
        checkBgMp!!.setTextColor(Color.WHITE)
        chekSeek!!.isChecked = true
        chekSeek!!.setTextColor(Color.WHITE)
        radioButtons[7]!!.isChecked = true
        radioButtons[7]!!.setTextColor(Color.WHITE)
        chekReview!!.isChecked = false
        chekAdvance!!.isChecked = true
        chekAdvance!!.setTextColor(Color.WHITE)
    }

    fun rbBtn1() {
        radioButtons[12]!!.setTextColor(Color.rgb(128, 130, 142))
        radioButtons[11]!!.setTextColor(Color.rgb(128, 130, 142))
        radioButtons[10]!!.setTextColor(Color.rgb(128, 130, 142))
        radioButtons[9]!!.setTextColor(Color.rgb(128, 130, 142))
    }

    fun rbBtn2() {
        radioButtons[16]!!.setTextColor(Color.rgb(128, 130, 142))
        radioButtons[15]!!.setTextColor(Color.rgb(128, 130, 142))
        radioButtons[14]!!.setTextColor(Color.rgb(128, 130, 142))
        radioButtons[13]!!.setTextColor(Color.rgb(128, 130, 142))
    }

    fun set() {
        checkScroll = findViewById<View>(R.id.check_scroll) as CheckBox
        checkBgMp = findViewById<View>(R.id.check_bgmp) as CheckBox
        chekSeek = findViewById<View>(R.id.check_seektest) as CheckBox
        chekReview = findViewById<View>(R.id.check_review) as CheckBox
        chekAdvance = findViewById<View>(R.id.check_advanc_mod) as CheckBox
        saveLight = findViewById<View>(R.id.save_light) as ImageButton
    }

    //----------------------------------------------
    fun lang() {
        sp = applicationContext.getSharedPreferences("setting", 0)
        lang = sp.getInt("lang", 2)
        val m = sp.getString("fontAr", "suls")
        font = Typeface.createFromAsset(assets, "fonts/$m.ttf")

        if (lang == 2) {
            textView[14]!!.textSize = 20f
        }
        for (i in 0..15) {
            when(lang){
                0 ->  Str[i]= Splash.listLang[i+ 210].translateFa
                1 ->  Str[i] = Splash.listLang[i+ 210].translateAr
                2 ->  Str[i] = Splash.listLang[i+ 210].translateEn
                3 ->  Str[i] = Splash.listLang[i+ 210].translateUr
            }
        }
    }

    fun btnEnable() {
        btnBack!!.isEnabled = true
        btnBack!!.setBackgroundResource(R.drawable.back)
        btnNext!!.isEnabled = true
        btnNext!!.setBackgroundResource(R.drawable.next)
    }

    fun noBorder() {
        btnEnable()
        for (i in 0 until id3.size - 1) {
            linearLayout[i]!!.setBackgroundResource(R.drawable.no_border)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val alert = AlertDialog.Builder(this@GuideSettings)
            alert.setTitle(Str[12])
            alert.setMessage(Str[13])
            alert.setPositiveButton(
                Str[14]
            ) { dialog, which ->
                val MainIntent = Intent(this@GuideSettings, GuidesActivity::class.java)
                startActivity(MainIntent)
                dialog.dismiss()
                finish()
            }
            alert.setNegativeButton(
                Str[15]
            ) { dialog, which -> dialog.dismiss() }
            alert.show()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    companion object {
        var font: Typeface? = null
    }
}