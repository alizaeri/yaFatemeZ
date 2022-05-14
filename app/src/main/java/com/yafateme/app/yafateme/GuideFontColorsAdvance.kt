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
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AlertDialog
import com.yafateme.app.yafateme.GuideSettings.Companion.font
import com.yafateme.app.yafateme.Splash.Companion.listDoa
import com.yafateme.app.yafateme.Splash.Companion.listLang

class GuideFontColorsAdvance : Activity() {
    lateinit var id: Array<String>
    lateinit var id2: Array<String>
    lateinit var id3: Array<String>
    lateinit var id4: Array<String>
    private var seekSizeAr: SeekBar? = null
    private var seekSizeFa: SeekBar? = null
    private var seekSizeEn: SeekBar? = null
    private var seekSizeUr: SeekBar? = null
    private var sbspace: SeekBar? = null
    private var testAr: String? = null
    lateinit var sp: SharedPreferences
   // private var db: database? = null
    var relSelColor: RelativeLayout? = null
    var rlColorAr: RelativeLayout? = null
    var rlColorFa: RelativeLayout? = null
    var rlColorEn: RelativeLayout? = null
    var rlColorUr: RelativeLayout? = null
    private val textView = arrayOfNulls<TextView>(24)
    private val radioButton = arrayOfNulls<RadioButton>(14)
    private val imageButton = arrayOfNulls<ImageButton>(15)
    private var checkShadow: CheckBox? = null
    private val Str = arrayOfNulls<String>(16)
    var btnBack: ImageButton? = null
    var btnNext: ImageButton? = null
    var linearLayout = arrayOfNulls<LinearLayout>(14)
    var lang = 0
    var scrollV: ScrollView? = null
    var conterGuide = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_guide_font_colors_advance)
        sp = applicationContext.getSharedPreferences("setting", 0)
        lang = sp.getInt("lang", 2)
        val m = sp.getString("fontAr", "suls")
        font = Typeface.createFromAsset(assets, "fonts/$m.ttf")
        btnBack = findViewById<View>(R.id.btn_back) as ImageButton
        btnNext = findViewById<View>(R.id.btn_next) as ImageButton
        scrollV = findViewById<View>(R.id.scrollView1) as ScrollView
        checkShadow = findViewById<View>(R.id.check_shadow) as CheckBox
        var themp: Int
        var themp2: Int
        var themp3: Int
        var themp4: Int
        //- --------------   -0-       -1-         -2-         -3-      -4-        -5-         -6-          -7-          -8-       -9-        -10-           -11-          -12-           13-
        id = arrayOf(
            "rb_suls",
            "rb_shin",
            "rb_arabic_ar",
            "rbday",
            "rbnight",
            "rb_myriad",
            "rb_gothic",
            "rb_arabic_en",
            "rb_homa",
            "rb_casa",
            "rb_arabic_fa",
            "rb_harir",
            "rb_palatino",
            "rb_arabic_ur"
        )

        //- --------------  -0-        -1-            -2-             -3-           -4-        -text2--5-   -text3--6-       -7-
        id2 = arrayOf(
            "test",
            "textgroup_ar",
            "textgroup_fa",
            "textgroup_en",
            "textgroup_ur",
            "TextView03",
            "TextView02",
            "text_group1",  //   -8-           -9-          -10-            -11-           -12-            -13-           -14-            -15-
            "text_group2",
            "text_group3",
            "text_group4",
            "text_group5",
            "text_seek_ar",
            "text_seek_fa",
            "text_seek_en",
            "text_seek_ur",  //   -16-          -17-             -18-          -19-       -20-          -21-		     	-22-		-23-
            "tv_color_ar",
            "tv_color_fa",
            "tv_color_en",
            "tv_color_ur",
            "save",
            "text_color_picker",
            "tv_shadow",
            "tv_note2"
        )

        //- --------------     -0-            -1-             -2-            -3-             -4-              -5-          -6-          -7-           -8-            -9-           -10-           -11-          -12-         -13-          -14-
        id3 = arrayOf(
            "save_light",
            "btn_color_ar",
            "btn_color_fa",
            "btn_color_en",
            "btn_color_ur",
            "btn_color1",
            "btn_color2",
            "btn_color3",
            "btn_color4",
            "btn_color5",
            "btn_color6",
            "btn_color7",
            "btn_color8",
            "btn_color9",
            "btn_color10"
        )
        //- -------------- 	  	  -0-       	     -1-  	           -2- 		           -3-               -4-	      	      -5- 		     	    -6- 	            -7-  	           -8- 		        -9-     	        -10-   	        -11-   	         -12-					-13-			-14-
        id4 = arrayOf(
            "li_guide_test1",
            "li_guide_all_ar",
            "li_guide_font_ar",
            "li_guide_size_ar",
            "li_guide_color_ar",
            "li_guide_all_fa",
            "li_guide_all_en",
            "li_guide_all_ur",
            "li_guide_spacing",
            "li_guide_bg",
            "li_guide_shadow",
            "li_guide_save_color",
            "li_guide_color",
            "li_gravity"
        )
        for (i in id.indices) {
            themp = resources.getIdentifier(id[i], "id", packageName)
            radioButton[i] = findViewById<View>(themp) as RadioButton
        }
        for (i in id2.indices) {
            themp2 = resources.getIdentifier(id2[i], "id", packageName)
            textView[i] = findViewById<View>(themp2) as TextView
        }
        for (i in id3.indices) {
            themp3 = resources.getIdentifier(id3[i], "id", packageName)
            imageButton[i] = findViewById<View>(themp3) as ImageButton
        }
        for (i in id4.indices) {
            themp4 = resources.getIdentifier(id4[i], "id", packageName)
            linearLayout[i] = findViewById<View>(themp4) as LinearLayout
        }
        set()
        ifLoad()
        setLanguage()
        lang()
        linearLayout[0]!!.setBackgroundResource(R.drawable.border)
        textView[23]!!.text = Str[0]
        btnBack!!.isEnabled = false
        btnBack!!.setBackgroundResource(R.drawable.back_not)
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade)
        val myAnimShake = AnimationUtils.loadAnimation(this@GuideFontColorsAdvance, R.anim.shake2)
        myAnimShake.interpolator = MyBounceInterpolator(0.2, 30.0)
        val myAnimShake2 = AnimationUtils.loadAnimation(this@GuideFontColorsAdvance, R.anim.shake2)
        myAnimShake2.interpolator = MyBounceInterpolator(0.2, 30.0)
        val myAnimShake3 = AnimationUtils.loadAnimation(this@GuideFontColorsAdvance, R.anim.shake2)
        myAnimShake3.interpolator = MyBounceInterpolator(0.2, 30.0)
        imageButton[0]!!.setBackgroundResource(R.drawable.set_savelight)
        imageButton[0]!!.startAnimation(animation)
        checkShadow!!.setOnClickListener {
            conterGuide = 10
            noBorder()
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.BOTTOM
            scrollV!!.smoothScrollTo(0, linearLayout[8]!!.top)
            linearLayout[10]!!.setBackgroundResource(R.drawable.border)
            textView[23]!!.text = Str[10]
        }
        seekSizeAr!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, value: Int, fromUser: Boolean) {
                conterGuide = 3
                noBorder()
                textView[23]!!.startAnimation(myAnimShake3)
                linearLayout[13]!!.gravity = Gravity.BOTTOM
                scrollV!!.smoothScrollTo(0, linearLayout[0]!!.top)
                linearLayout[3]!!.setBackgroundResource(R.drawable.border)
                textView[23]!!.text = Str[3]
            }
        })
        seekSizeFa!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, value: Int, fromUser: Boolean) {
                conterGuide = 5
                noBorder()
                textView[23]!!.startAnimation(myAnimShake3)
                linearLayout[13]!!.gravity = Gravity.BOTTOM
                scrollV!!.smoothScrollTo(0, linearLayout[5]!!.top)
                linearLayout[5]!!.setBackgroundResource(R.drawable.border)
                textView[23]!!.text = Str[5]
            }
        })
        seekSizeEn!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, value: Int, fromUser: Boolean) {
                conterGuide = 6
                noBorder()
                textView[23]!!.startAnimation(myAnimShake3)
                linearLayout[13]!!.gravity = Gravity.BOTTOM
                scrollV!!.smoothScrollTo(0, linearLayout[6]!!.top)
                linearLayout[6]!!.setBackgroundResource(R.drawable.border)
                textView[23]!!.text = Str[6]
            }
        })
        seekSizeUr!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, value: Int, fromUser: Boolean) {
                conterGuide = 7
                noBorder()
                textView[23]!!.startAnimation(myAnimShake3)
                linearLayout[13]!!.gravity = Gravity.BOTTOM
                scrollV!!.smoothScrollTo(0, linearLayout[7]!!.top)
                linearLayout[7]!!.setBackgroundResource(R.drawable.border)
                textView[23]!!.text = Str[7]
            }
        })
        sbspace!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, value: Int, fromUser: Boolean) {
                conterGuide = 8
                noBorder()
                textView[23]!!.startAnimation(myAnimShake3)
                linearLayout[13]!!.gravity = Gravity.BOTTOM
                scrollV!!.smoothScrollTo(0, linearLayout[8]!!.top)
                linearLayout[8]!!.setBackgroundResource(R.drawable.border)
                textView[23]!!.text = Str[8]
            }
        })
        radioButton[3]!!.setOnClickListener {
            conterGuide = 9
            noBorder()
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.BOTTOM
            scrollV!!.smoothScrollTo(0, linearLayout[8]!!.top)
            linearLayout[9]!!.setBackgroundResource(R.drawable.border)
            textView[23]!!.text = Str[9]
        }
        radioButton[4]!!.setOnClickListener {
            conterGuide = 9
            noBorder()
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.BOTTOM
            scrollV!!.smoothScrollTo(0, linearLayout[8]!!.top)
            linearLayout[9]!!.setBackgroundResource(R.drawable.border)
            textView[23]!!.text = Str[9]
        }


        //----------------------------Arabic------------------------------
        radioButton[0]!!.setOnClickListener {
            conterGuide = 2
            noBorder()
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.BOTTOM
            textView[23]!!.text = Str[2]
            scrollV!!.smoothScrollTo(0, linearLayout[0]!!.top)
            linearLayout[2]!!.setBackgroundResource(R.drawable.border)
        }
        radioButton[1]!!.setOnClickListener {
            conterGuide = 2
            noBorder()
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.BOTTOM
            textView[23]!!.text = Str[2]
            scrollV!!.smoothScrollTo(0, linearLayout[0]!!.top)
            linearLayout[2]!!.setBackgroundResource(R.drawable.border)
        }
        radioButton[2]!!.setOnClickListener {
            conterGuide = 2
            noBorder()
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.BOTTOM
            textView[23]!!.text = Str[2]
            scrollV!!.smoothScrollTo(0, linearLayout[0]!!.top)
            linearLayout[2]!!.setBackgroundResource(R.drawable.border)
        }
        //-------------------------------Farsi---------------------------------
        radioButton[8]!!.setOnClickListener {
            conterGuide = 5
            noBorder()
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.BOTTOM
            scrollV!!.smoothScrollTo(0, linearLayout[5]!!.top)
            linearLayout[5]!!.setBackgroundResource(R.drawable.border)
            textView[23]!!.text = Str[5]
        }
        radioButton[9]!!.setOnClickListener {
            conterGuide = 5
            noBorder()
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.BOTTOM
            scrollV!!.smoothScrollTo(0, linearLayout[5]!!.top)
            linearLayout[5]!!.setBackgroundResource(R.drawable.border)
            textView[23]!!.text = Str[5]
        }
        radioButton[10]!!.setOnClickListener {
            conterGuide = 5
            noBorder()
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.BOTTOM
            scrollV!!.smoothScrollTo(0, linearLayout[5]!!.top)
            linearLayout[5]!!.setBackgroundResource(R.drawable.border)
            textView[23]!!.text = Str[5]
        }
        //-------------------------------Englisi----------------------------------
        radioButton[6]!!.setOnClickListener {
            conterGuide = 6
            noBorder()
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.BOTTOM
            scrollV!!.smoothScrollTo(0, linearLayout[6]!!.top)
            linearLayout[6]!!.setBackgroundResource(R.drawable.border)
            textView[23]!!.text = Str[6]
        }
        radioButton[5]!!.setOnClickListener {
            conterGuide = 6
            noBorder()
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.BOTTOM
            scrollV!!.smoothScrollTo(0, linearLayout[6]!!.top)
            linearLayout[6]!!.setBackgroundResource(R.drawable.border)
            textView[23]!!.text = Str[6]
        }
        radioButton[7]!!.setOnClickListener {
            conterGuide = 6
            noBorder()
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.BOTTOM
            scrollV!!.smoothScrollTo(0, linearLayout[6]!!.top)
            linearLayout[6]!!.setBackgroundResource(R.drawable.border)
            textView[23]!!.text = Str[6]
        } //---------------------------------------------------------------------------
        radioButton[11]!!.setOnClickListener {
            conterGuide = 7
            noBorder()
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.BOTTOM
            scrollV!!.smoothScrollTo(0, linearLayout[7]!!.top)
            linearLayout[7]!!.setBackgroundResource(R.drawable.border)
            textView[23]!!.text = Str[7]
        }
        radioButton[12]!!.setOnClickListener {
            conterGuide = 7
            noBorder()
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.BOTTOM
            scrollV!!.smoothScrollTo(0, linearLayout[7]!!.top)
            linearLayout[7]!!.setBackgroundResource(R.drawable.border)
            textView[23]!!.text = Str[7]
        }
        radioButton[13]!!.setOnClickListener {
            conterGuide = 7
            noBorder()
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.BOTTOM
            scrollV!!.smoothScrollTo(0, linearLayout[7]!!.top)
            linearLayout[7]!!.setBackgroundResource(R.drawable.border)
            textView[23]!!.text = Str[7]
        }
        //-----------------------------------------------------------------------------------
        imageButton[1]!!.setOnClickListener {
            noBorder()
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.BOTTOM
            conterGuide = 4
            linearLayout[4]!!.setBackgroundResource(R.drawable.border)
            textView[23]!!.text = Str[4]
            scrollV!!.smoothScrollTo(0, linearLayout[0]!!.top)
        }
        imageButton[2]!!.setOnClickListener {
            noBorder()
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.BOTTOM
            scrollV!!.smoothScrollTo(0, linearLayout[5]!!.top)
            linearLayout[5]!!.setBackgroundResource(R.drawable.border)
            textView[23]!!.text = Str[5]
            conterGuide = 5
        }
        imageButton[3]!!.setOnClickListener {
            noBorder()
            linearLayout[6]!!.setBackgroundResource(R.drawable.border)
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.BOTTOM
            textView[23]!!.text = Str[6]
            conterGuide = 6
            scrollV!!.smoothScrollTo(0, linearLayout[6]!!.top)
        }
        imageButton[4]!!.setOnClickListener {
            noBorder()
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.BOTTOM
            conterGuide = 7
            linearLayout[7]!!.setBackgroundResource(R.drawable.border)
            textView[23]!!.text = Str[7]
            scrollV!!.smoothScrollTo(0, linearLayout[7]!!.top)
        }
        imageButton[0]!!.setOnClickListener {
            noBorder()
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.CENTER
            scrollV!!.smoothScrollTo(0, linearLayout[8]!!.top)
            textView[23]!!.text = Str[11]
            linearLayout[11]!!.setBackgroundResource(R.drawable.border)
            btnNext!!.isEnabled = false
            btnNext!!.setBackgroundResource(R.drawable.next_not)
            conterGuide = 10
        }
        //-----------------------------------------------------------------------------------
        btnNext!!.setOnClickListener {
            noBorder()
            conterGuide = conterGuide + 1
            btnNext!!.startAnimation(myAnimShake2)
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.BOTTOM
            when (conterGuide) {
                1 -> {
                    btnBack!!.isEnabled = true
                    scrollV!!.smoothScrollTo(0, linearLayout[1]!!.top)
                    btnBack!!.setBackgroundResource(R.drawable.back)
                    textView[23]!!.text = Str[1]
                    linearLayout[1]!!.setBackgroundResource(R.drawable.border)
                }
                2 -> {
                    textView[23]!!.text = Str[2]
                    scrollV!!.smoothScrollTo(0, linearLayout[0]!!.top)
                    linearLayout[2]!!.setBackgroundResource(R.drawable.border)
                }
                3 -> {
                    textView[23]!!.text = Str[3]
                    scrollV!!.smoothScrollTo(0, linearLayout[0]!!.top)
                    linearLayout[3]!!.setBackgroundResource(R.drawable.border)
                }
                4 -> {
                    linearLayout[4]!!.setBackgroundResource(R.drawable.border)
                    textView[23]!!.text = Str[4]
                    scrollV!!.smoothScrollTo(0, linearLayout[0]!!.top)
                }
                5 -> {
                    scrollV!!.smoothScrollTo(0, linearLayout[5]!!.top)
                    linearLayout[5]!!.setBackgroundResource(R.drawable.border)
                    textView[23]!!.text = Str[5]
                }
                6 -> {
                    linearLayout[6]!!.setBackgroundResource(R.drawable.border)
                    textView[23]!!.text = Str[6]
                    scrollV!!.smoothScrollTo(0, linearLayout[6]!!.top)
                }
                7 -> {
                    linearLayout[7]!!.setBackgroundResource(R.drawable.border)
                    textView[23]!!.text = Str[7]
                    scrollV!!.smoothScrollTo(0, linearLayout[7]!!.top)
                }
                8 -> {
                    linearLayout[8]!!.setBackgroundResource(R.drawable.border)
                    textView[23]!!.text = Str[8]
                    scrollV!!.smoothScrollTo(0, linearLayout[8]!!.top)
                }
                9 -> {
                    linearLayout[9]!!.setBackgroundResource(R.drawable.border)
                    textView[23]!!.text = Str[9]
                    scrollV!!.smoothScrollTo(0, linearLayout[8]!!.top)
                }
                10 -> {
                    scrollV!!.smoothScrollTo(0, linearLayout[8]!!.top)
                    textView[23]!!.text = Str[10]
                    linearLayout[10]!!.setBackgroundResource(R.drawable.border)
                }
                11 -> {
                    linearLayout[13]!!.gravity = Gravity.CENTER
                    scrollV!!.smoothScrollTo(0, linearLayout[8]!!.top)
                    textView[23]!!.text = Str[11]
                    linearLayout[11]!!.setBackgroundResource(R.drawable.border)
                    btnNext!!.isEnabled = false
                    btnNext!!.setBackgroundResource(R.drawable.next_not)
                }
            }
        }
        btnBack!!.setOnClickListener {
            noBorder()
            conterGuide = conterGuide - 1
            btnBack!!.startAnimation(myAnimShake)
            textView[23]!!.startAnimation(myAnimShake3)
            linearLayout[13]!!.gravity = Gravity.BOTTOM
            when (conterGuide) {
                0 -> {
                    btnBack!!.isEnabled = false
                    btnBack!!.setBackgroundResource(R.drawable.back_not)
                    textView[23]!!.text = Str[0]
                    linearLayout[13]!!.gravity = Gravity.CENTER
                    linearLayout[0]!!.setBackgroundResource(R.drawable.border)
                }
                1 -> {
                    textView[23]!!.text = Str[1]
                    scrollV!!.smoothScrollTo(0, linearLayout[1]!!.top)
                    linearLayout[1]!!.setBackgroundResource(R.drawable.border)
                }
                2 -> {
                    textView[23]!!.text = Str[2]
                    scrollV!!.smoothScrollTo(0, linearLayout[1]!!.top)
                    linearLayout[2]!!.setBackgroundResource(R.drawable.border)
                }
                3 -> {
                    textView[23]!!.text = Str[3]
                    scrollV!!.smoothScrollTo(0, linearLayout[1]!!.top)
                    linearLayout[3]!!.setBackgroundResource(R.drawable.border)
                }
                4 -> {
                    linearLayout[4]!!.setBackgroundResource(R.drawable.border)
                    textView[23]!!.text = Str[4]
                    scrollV!!.smoothScrollTo(0, linearLayout[0]!!.top)
                }
                5 -> {
                    scrollV!!.smoothScrollTo(0, linearLayout[5]!!.top)
                    linearLayout[5]!!.setBackgroundResource(R.drawable.border)
                    textView[23]!!.text = Str[5]
                }
                6 -> {
                    linearLayout[6]!!.setBackgroundResource(R.drawable.border)
                    textView[23]!!.text = Str[6]
                    scrollV!!.smoothScrollTo(0, linearLayout[6]!!.top)
                }
                7 -> {
                    linearLayout[7]!!.setBackgroundResource(R.drawable.border)
                    textView[23]!!.text = Str[7]
                    scrollV!!.smoothScrollTo(0, linearLayout[7]!!.top)
                }
                8 -> {
                    linearLayout[8]!!.setBackgroundResource(R.drawable.border)
                    textView[23]!!.text = Str[8]
                    scrollV!!.smoothScrollTo(0, linearLayout[8]!!.top)
                }
                9 -> {
                    linearLayout[9]!!.setBackgroundResource(R.drawable.border)
                    textView[23]!!.text = Str[9]
                    scrollV!!.smoothScrollTo(0, linearLayout[8]!!.top)
                }
                10 -> {
                    scrollV!!.smoothScrollTo(0, linearLayout[8]!!.top)
                    textView[23]!!.text = Str[10]
                    linearLayout[10]!!.setBackgroundResource(R.drawable.border)
                    btnNext!!.isEnabled = true
                    btnNext!!.setBackgroundResource(R.drawable.next)
                }
            }
        }
    }

    fun noBorder() {
        btnEnable()
        for (i in 0 until id4.size - 1) {
            linearLayout[i]!!.setBackgroundResource(R.drawable.no_border)
        }
    }

    fun set() {
        seekSizeAr = findViewById<View>(R.id.seek_size_ar) as SeekBar
        seekSizeFa = findViewById<View>(R.id.seek_size_fa) as SeekBar
        seekSizeEn = findViewById<View>(R.id.seek_size_en) as SeekBar
        seekSizeUr = findViewById<View>(R.id.seek_size_ur) as SeekBar
        sbspace = findViewById<View>(R.id.sbspace) as SeekBar
        relSelColor = findViewById<View>(R.id.rel_sel_color) as RelativeLayout
        rlColorAr = findViewById<View>(R.id.rl_color_ar) as RelativeLayout
        rlColorFa = findViewById<View>(R.id.rl_color_fa) as RelativeLayout
        rlColorEn = findViewById<View>(R.id.rl_color_en) as RelativeLayout
        rlColorUr = findViewById<View>(R.id.rl_color_ur) as RelativeLayout
        relSelColor!!.visibility = View.GONE
    }

    fun setLanguage() {
        val fontmyredM = Typeface.createFromAsset(assets, "fonts/myriadl.ttf")
        val fontmyredL = Typeface.createFromAsset(assets, "fonts/myriadm.ttf")
        val fontArabic = Typeface.createFromAsset(assets, "fonts/arabic.ttf")
        val fontArabicB = Typeface.createFromAsset(assets, "fonts/arabicb.otf")

        for (i in id.indices) {
            radioButton[i]!!.typeface = fontArabic
        }
        for (i in id2.indices) {
            textView[i]!!.typeface = fontArabic
        }
        for (i in 7..11) {
            textView[i]!!.typeface = fontArabicB
        }
        testAr = listDoa[2].text
        if (lang == 2) {
            for (i in id.indices) {
                radioButton[i]!!.typeface = fontmyredL
            }
            for (i in id2.indices) {
                textView[i]!!.typeface = fontmyredM
            }
            for (i in 7..11) {
                textView[i]!!.typeface = fontmyredL
            }
        }
        for (item in id.indices) {
            when(lang){
                0 ->  radioButton[item]!!.text = listLang[item].translateFa
                1 ->  radioButton[item]!!.text = listLang[item].translateAr
                2 ->  radioButton[item]!!.text = listLang[item].translateEn
                3 ->  radioButton[item]!!.text = listLang[item].translateUr
            }

        }
        for (item in id2.indices) {
            when(lang){
                0 ->  textView[item]!!.text= listLang[item + 19].translateFa
                1 ->  textView[item]!!.text = listLang[item + 19].translateAr
                2 ->  textView[item]!!.text = listLang[item + 19].translateEn
                3 ->  textView[item]!!.text = listLang[item + 19].translateUr
            }
        }
        textView[0]!!.text = testAr
        textView[0]!!.typeface = font
        textView[0]!!.textSize = 20f
    }

    fun ifLoad() {
        checkShadow!!.isChecked = true
        checkShadow!!.setTextColor(Color.WHITE)
        textView[0]!!.setTextColor(Color.WHITE)
        radioButton[3]!!.isChecked = true
        radioButton[3]!!.setTextColor(Color.WHITE)
        radioButton[0]!!.isChecked = true
        radioButton[0]!!.setTextColor(Color.WHITE)
        radioButton[8]!!.isChecked = true
        radioButton[8]!!.setTextColor(Color.WHITE)
        radioButton[5]!!.isChecked = true
        radioButton[5]!!.setTextColor(Color.WHITE)
        radioButton[11]!!.isChecked = true
        radioButton[1]!!.setTextColor(Color.WHITE)
        rlColorAr!!.setBackgroundColor(Color.rgb(116, 244, 255))
        rlColorFa!!.setBackgroundColor(Color.rgb(255, 255, 128))
        rlColorEn!!.setBackgroundColor(Color.rgb(230, 230, 230))
        rlColorUr!!.setBackgroundColor(Color.rgb(0, 66, 0))
    }

    fun lang() {
        val fontArabic = Typeface.createFromAsset(assets, "fonts/arabic.ttf")
        val fontmyredL = Typeface.createFromAsset(assets, "fonts/myriadm.ttf")
        val fontmyredM = Typeface.createFromAsset(assets, "fonts/myriadl.ttf")
        textView[23]!!.typeface = fontArabic

        if (lang == 2) {
            textView[23]!!.typeface = fontmyredL
            textView[23]!!.textSize = 20f
        }
        for (item in 0..15) {
            when(lang){
                0 ->  Str[item]= listLang[item + 180].translateFa
                1 ->  Str[item] = listLang[item + 180].translateAr
                2 ->  Str[item] = listLang[item + 180].translateEn
                3 ->  Str[item] = listLang[item + 180].translateUr
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
            val alert = AlertDialog.Builder(this@GuideFontColorsAdvance)
            alert.setTitle(Str[12])
            alert.setMessage(Str[13])
            alert.setPositiveButton(
                Str[14]
            ) { dialog, which ->
                val MainIntent = Intent(this@GuideFontColorsAdvance, GuidesActivity::class.java)
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

    }
}