package com.yafateme.app.yafateme

import android.app.Activity
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

class FontColorsAdvance : Activity() {
    private lateinit var id: Array<String>
    private lateinit var id2: Array<String>
    private lateinit var id3: Array<String>
    private var seekSizeAr: SeekBar? = null
    private var seekSizeFa: SeekBar? = null
    private var seekSizeEn: SeekBar? = null
    private var seekSizeUr: SeekBar? = null
    private var sbspace: SeekBar? = null
    private var font: Typeface? = null
    private var font2: Typeface? = null
    private var font3: Typeface? = null
    private var font4: Typeface? = null
    private var ArabicFont: String? = null
    private var FaFont: String? = null
    private var EnFont: String? = null
    private var UrFont: String? = null
    private var ShadowMod: String? = null
    private var mood: String? = null
    private var testFa: String? = null
    private var testEn: String? = null
    private var testAr: String? = null
    private var testUr: String? = null
    private var chekClick = "non"
    private lateinit var sp: SharedPreferences
    private var toas: String? = null
   // private var db: database? = null
    var relSelColor: RelativeLayout? = null
    var rlColorAr: RelativeLayout? = null
    var rlColorFa: RelativeLayout? = null
    var rlColorEn: RelativeLayout? = null
    var rlColorUr: RelativeLayout? = null
    private val textView = arrayOfNulls<TextView>(23)
    private val radioButton = arrayOfNulls<RadioButton>(14)
    private val imageButton = arrayOfNulls<ImageButton>(15)
    var ColorEn = 0
    var ColorFa = 0
    var ColorUr = 0
    var ColorAr = 0
    private var checkShadow: CheckBox? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_font_colors_advance)
        var themp: Int
        var themp2: Int
        var themp3: Int
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
            "text_seek_ur",  //   -16-          -17-          -18-          -19-       -20-          -21-			-22-
            "tv_color_ar",
            "tv_color_fa",
            "tv_color_en",
            "tv_color_ur",
            "save",
            "text_color_picker",
            "tv_shadow"
        )

        //- --------------     -0-          -1-             -2-            -3-            -4-           -5-          -6-          -7-           -8-         -9-         -10-           -11-          -12-         -13-          -14-
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
        checkShadow = findViewById<View>(R.id.check_shadow) as CheckBox
        set()
        laod()
        setLanguage()
        ifLoad()
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade)
        imageButton[0]!!.setBackgroundResource(R.drawable.set_savelight)
        imageButton[0]!!.startAnimation(animation)
        checkShadow!!.setOnClickListener {
            if (checkShadow!!.isChecked) {
                textView[0]!!.setShadowLayer(2f, 2f, 1f, Color.BLACK)
                ShadowMod = "true"
            } else {
                ShadowMod = "false"
                textView[0]!!.setShadowLayer(0f, 0f, 0f, Color.BLACK)
            }
        }
        seekSizeAr!!.max = 30
        seekSizeAr!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                textView[0]!!.text = testAr
                textView[0]!!.setTypeface(font)
                APIcolorAr()
            }

            override fun onProgressChanged(seekBar: SeekBar, value: Int, fromUser: Boolean) {
                textView[0]!!.textSize = value.toFloat()
            }
        })
        seekSizeFa!!.max = 30
        seekSizeFa!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                textView[0]!!.text = testFa
                textView[0]!!.setTypeface(font2)
                APIcolorFa()
            }

            override fun onProgressChanged(seekBar: SeekBar, value: Int, fromUser: Boolean) {
                textView[0]!!.textSize = value.toFloat()
            }
        })
        seekSizeEn!!.max = 30
        seekSizeEn!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                textView[0]!!.text = testEn
                textView[0]!!.setTypeface(font3)
                APIcolorEn()
            }

            override fun onProgressChanged(seekBar: SeekBar, value: Int, fromUser: Boolean) {
                textView[0]!!.textSize = value.toFloat()
            }
        })
        seekSizeUr!!.max = 30
        seekSizeUr!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                textView[0]!!.text = testUr
                textView[0]!!.setTypeface(font4)
                APIcolorUr()
            }

            override fun onProgressChanged(seekBar: SeekBar, value: Int, fromUser: Boolean) {
                textView[0]!!.textSize = value.toFloat()
            }
        })
        sbspace!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, value: Int, fromUser: Boolean) {
                textView[0]!!.setLineSpacing(value.toFloat(), 1f)
            }
        })
        radioButton[3]!!.setOnClickListener {
            textView[0]!!.setBackgroundResource(R.drawable.set_bgtest)
            mood = "day"
            radioButton[4]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButton[3]!!.setTextColor(Color.WHITE)
        }
        radioButton[4]!!.setOnClickListener {
            textView[0]!!.setBackgroundResource(R.drawable.set_bgtest2)
            mood = "night"
            radioButton[3]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButton[4]!!.setTextColor(Color.WHITE)
        }


        //----------------------------Arabic------------------------------
        radioButton[0]!!.setOnClickListener {
            textView[0]!!.text = testAr
            font = Typeface.createFromAsset(assets, "fonts/suls.ttf")
            textView[0]!!.textSize = MainActivity.ArabicSize.toFloat()
            textView[0]!!.setTypeface(font)
            APIcolorAr()
            ArabicFont = "suls"
            radioButton[1]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButton[2]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButton[0]!!.setTextColor(Color.WHITE)
        }
        radioButton[1]!!.setOnClickListener {
            textView[0]!!.text = testAr
            font = Typeface.createFromAsset(assets, "fonts/shin.ttf")
            textView[0]!!.textSize = MainActivity.ArabicSize.toFloat()
            textView[0]!!.setTypeface(font)
            APIcolorAr()
            ArabicFont = "shin"
            radioButton[1]!!.setTextColor(Color.WHITE)
            radioButton[2]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButton[0]!!.setTextColor(Color.rgb(128, 130, 142))
        }
        radioButton[2]!!.setOnClickListener {
            textView[0]!!.text = testAr
            font = Typeface.createFromAsset(assets, "fonts/arabic.ttf")
            textView[0]!!.textSize = MainActivity.ArabicSize.toFloat()
            textView[0]!!.setTypeface(font)
            APIcolorAr()
            ArabicFont = "arabic"
            radioButton[1]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButton[2]!!.setTextColor(Color.WHITE)
            radioButton[0]!!.setTextColor(Color.rgb(128, 130, 142))
        }
        //-------------------------------Farsi---------------------------------
        radioButton[8]!!.setOnClickListener {
            textView[0]!!.text = testFa
            font2 = Typeface.createFromAsset(assets, "fonts/homa.ttf")
            textView[0]!!.textSize = MainActivity.FaSize.toFloat()
            textView[0]!!.setTypeface(font2)
            APIcolorFa()
            FaFont = "homa"
            radioButton[9]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButton[10]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButton[8]!!.setTextColor(Color.WHITE)
        }
        radioButton[9]!!.setOnClickListener {
            textView[0]!!.text = testFa
            font2 = Typeface.createFromAsset(assets, "fonts/casablanca.ttf")
            textView[0]!!.textSize = MainActivity.FaSize.toFloat()
            textView[0]!!.setTypeface(font2)
            APIcolorFa()
            FaFont = "casablanca"
            radioButton[9]!!.setTextColor(Color.WHITE)
            radioButton[8]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButton[10]!!.setTextColor(Color.rgb(128, 130, 142))
        }
        radioButton[10]!!.setOnClickListener {
            textView[0]!!.text = testFa
            font2 = Typeface.createFromAsset(assets, "fonts/arabic.ttf")
            textView[0]!!.textSize = MainActivity.FaSize.toFloat()
            textView[0]!!.setTypeface(font2)
            APIcolorFa()
            FaFont = "arabic"
            radioButton[8]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButton[10]!!.setTextColor(Color.WHITE)
            radioButton[9]!!.setTextColor(Color.rgb(128, 130, 142))
        }
        //-------------------------------Englisi----------------------------------
        radioButton[6]!!.setOnClickListener {
            textView[0]!!.text = testEn
            font3 = Typeface.createFromAsset(assets, "fonts/gothic.ttf")
            textView[0]!!.textSize = MainActivity.EnSize.toFloat()
            textView[0]!!.setTypeface(font3)
            APIcolorEn()
            EnFont = "gothic"
            radioButton[7]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButton[5]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButton[6]!!.setTextColor(Color.WHITE)
        }
        radioButton[5]!!.setOnClickListener {
            textView[0]!!.text = testEn
            font3 = Typeface.createFromAsset(assets, "fonts/myriadl.ttf")
            textView[0]!!.textSize = MainActivity.EnSize.toFloat()
            textView[0]!!.setTypeface(font3)
            APIcolorEn()
            EnFont = "myriadl"
            radioButton[5]!!.setTextColor(Color.WHITE)
            radioButton[7]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButton[6]!!.setTextColor(Color.rgb(128, 130, 142))
        }
        radioButton[7]!!.setOnClickListener {
            textView[0]!!.text = testEn
            font3 = Typeface.createFromAsset(assets, "fonts/arabic.ttf")
            textView[0]!!.textSize = MainActivity.EnSize.toFloat()
            textView[0]!!.setTypeface(font3)
            APIcolorEn()
            EnFont = "arabic"
            radioButton[5]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButton[7]!!.setTextColor(Color.WHITE)
            radioButton[6]!!.setTextColor(Color.rgb(128, 130, 142))
        } //---------------------------------------------------------------------------
        radioButton[11]!!.setOnClickListener {
            textView[0]!!.text = testUr
            font4 = Typeface.createFromAsset(assets, "fonts/harir.ttf")
            textView[0]!!.textSize = MainActivity.UrSize.toFloat()
            textView[0]!!.setTypeface(font4)
            APIcolorUr()
            UrFont = "harir"
            radioButton[12]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButton[13]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButton[11]!!.setTextColor(Color.WHITE)
        }
        radioButton[12]!!.setOnClickListener {
            textView[0]!!.text = testUr
            font4 = Typeface.createFromAsset(assets, "fonts/palatino.ttf")
            textView[0]!!.textSize = MainActivity.UrSize.toFloat()
            textView[0]!!.setTypeface(font4)
            APIcolorUr()
            UrFont = "palatino"
            radioButton[12]!!.setTextColor(Color.WHITE)
            radioButton[11]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButton[13]!!.setTextColor(Color.rgb(128, 130, 142))
        }
        radioButton[13]!!.setOnClickListener {
            textView[0]!!.text = testUr
            font4 = Typeface.createFromAsset(assets, "fonts/arabic.ttf")
            textView[0]!!.textSize = MainActivity.UrSize.toFloat()
            textView[0]!!.setTypeface(font4)
            APIcolorUr()
            UrFont = "arabic"
            radioButton[11]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButton[13]!!.setTextColor(Color.WHITE)
            radioButton[12]!!.setTextColor(Color.rgb(128, 130, 142))
        }
        //-----------------------------------------------------------------------------------
        relSelColor!!.setOnClickListener { relSelColor!!.visibility = View.GONE }
        imageButton[1]!!.setOnClickListener {
            relSelColor!!.visibility = View.VISIBLE
            chekClick = "ar"
        }
        imageButton[2]!!.setOnClickListener {
            relSelColor!!.visibility = View.VISIBLE
            chekClick = "fa"
        }
        imageButton[3]!!.setOnClickListener {
            relSelColor!!.visibility = View.VISIBLE
            chekClick = "en"
        }
        imageButton[4]!!.setOnClickListener {
            relSelColor!!.visibility = View.VISIBLE
            chekClick = "ur"
        }

        //----------------------------------------------------------------------------------
        imageButton[5]!!.setOnClickListener {
            relSelColor!!.visibility = View.GONE
            textView[0]!!.setTextColor(Color.rgb(250, 246, 219))
            when (chekClick) {
                "ar" -> {
                    ColorAr = 1
                    rlColorAr!!.setBackgroundColor(Color.rgb(250, 246, 219))
                    textView[0]!!.text = testAr
                    textView[0]!!.setTypeface(font)
                }
                "fa" -> {
                    ColorFa = 1
                    rlColorFa!!.setBackgroundColor(Color.rgb(250, 246, 219))
                    textView[0]!!.text = testFa
                    textView[0]!!.setTypeface(font2)
                }
                "en" -> {
                    ColorEn = 1
                    rlColorEn!!.setBackgroundColor(Color.rgb(250, 246, 219))
                    textView[0]!!.text = testEn
                    textView[0]!!.setTypeface(font3)
                }
                "ur" -> {
                    ColorUr = 1
                    rlColorUr!!.setBackgroundColor(Color.rgb(250, 246, 219))
                    textView[0]!!.text = testUr
                    textView[0]!!.setTypeface(font4)
                }
            }
        }
        imageButton[6]!!.setOnClickListener {
            relSelColor!!.visibility = View.GONE
            textView[0]!!.setTextColor(Color.rgb(242, 211, 164))
            when (chekClick) {
                "ar" -> {
                    ColorAr = 2
                    rlColorAr!!.setBackgroundColor(Color.rgb(242, 211, 164))
                    textView[0]!!.text = testAr
                    textView[0]!!.setTypeface(font)
                }
                "fa" -> {
                    ColorFa = 2
                    rlColorFa!!.setBackgroundColor(Color.rgb(242, 211, 164))
                    textView[0]!!.text = testFa
                    textView[0]!!.setTypeface(font2)
                }
                "en" -> {
                    ColorEn = 2
                    rlColorEn!!.setBackgroundColor(Color.rgb(242, 211, 164))
                    textView[0]!!.text = testEn
                    textView[0]!!.setTypeface(font3)
                }
                "ur" -> {
                    ColorUr = 2
                    rlColorUr!!.setBackgroundColor(Color.rgb(242, 211, 164))
                    textView[0]!!.text = testUr
                    textView[0]!!.setTypeface(font4)
                }
            }
        }
        imageButton[7]!!.setOnClickListener {
            relSelColor!!.visibility = View.GONE
            textView[0]!!.setTextColor(Color.rgb(239, 178, 197))
            when (chekClick) {
                "ar" -> {
                    ColorAr = 3
                    rlColorAr!!.setBackgroundColor(Color.rgb(239, 178, 197))
                    textView[0]!!.text = testAr
                    textView[0]!!.setTypeface(font)
                }
                "fa" -> {
                    ColorFa = 3
                    rlColorFa!!.setBackgroundColor(Color.rgb(239, 178, 197))
                    textView[0]!!.text = testFa
                    textView[0]!!.setTypeface(font2)
                }
                "en" -> {
                    ColorEn = 3
                    rlColorEn!!.setBackgroundColor(Color.rgb(239, 178, 197))
                    textView[0]!!.text = testEn
                    textView[0]!!.setTypeface(font3)
                }
                "ur" -> {
                    ColorUr = 3
                    rlColorUr!!.setBackgroundColor(Color.rgb(239, 178, 197))
                    textView[0]!!.text = testUr
                    textView[0]!!.setTypeface(font4)
                }
            }
        }
        imageButton[8]!!.setOnClickListener {
            relSelColor!!.visibility = View.GONE
            textView[0]!!.setTextColor(Color.rgb(185, 194, 223))
            when (chekClick) {
                "ar" -> {
                    ColorAr = 4
                    rlColorAr!!.setBackgroundColor(Color.rgb(185, 194, 223))
                    textView[0]!!.text = testAr
                    textView[0]!!.setTypeface(font)
                }
                "fa" -> {
                    ColorFa = 4
                    rlColorFa!!.setBackgroundColor(Color.rgb(185, 194, 223))
                    textView[0]!!.text = testFa
                    textView[0]!!.setTypeface(font2)
                }
                "en" -> {
                    ColorEn = 4
                    rlColorEn!!.setBackgroundColor(Color.rgb(185, 194, 223))
                    textView[0]!!.text = testEn
                    textView[0]!!.setTypeface(font3)
                }
                "ur" -> {
                    ColorUr = 4
                    rlColorUr!!.setBackgroundColor(Color.rgb(185, 194, 223))
                    textView[0]!!.text = testUr
                    textView[0]!!.setTypeface(font4)
                }
            }
        }
        imageButton[9]!!.setOnClickListener {
            relSelColor!!.visibility = View.GONE
            textView[0]!!.setTextColor(Color.rgb(199, 232, 232))
            when (chekClick) {
                "ar" -> {
                    ColorAr = 5
                    rlColorAr!!.setBackgroundColor(Color.rgb(199, 232, 232))
                    textView[0]!!.text = testAr
                    textView[0]!!.setTypeface(font)
                }
                "fa" -> {
                    ColorFa = 5
                    rlColorFa!!.setBackgroundColor(Color.rgb(219, 232, 232))
                    textView[0]!!.text = testFa
                    textView[0]!!.setTypeface(font2)
                }
                "en" -> {
                    ColorEn = 5
                    rlColorEn!!.setBackgroundColor(Color.rgb(199, 232, 232))
                    textView[0]!!.text = testEn
                    textView[0]!!.setTypeface(font3)
                }
                "ur" -> {
                    ColorUr = 5
                    rlColorUr!!.setBackgroundColor(Color.rgb(199, 232, 232))
                    textView[0]!!.text = testUr
                    textView[0]!!.setTypeface(font4)
                }
            }
        }
        imageButton[10]!!.setOnClickListener {
            relSelColor!!.visibility = View.GONE
            textView[0]!!.setTextColor(Color.rgb(206, 230, 189))
            when (chekClick) {
                "ar" -> {
                    ColorAr = 6
                    rlColorAr!!.setBackgroundColor(Color.rgb(206, 230, 189))
                    textView[0]!!.text = testAr
                    textView[0]!!.setTypeface(font)
                }
                "fa" -> {
                    ColorFa = 6
                    rlColorFa!!.setBackgroundColor(Color.rgb(206, 230, 189))
                    textView[0]!!.text = testFa
                    textView[0]!!.setTypeface(font2)
                }
                "en" -> {
                    ColorEn = 6
                    rlColorEn!!.setBackgroundColor(Color.rgb(206, 230, 189))
                    textView[0]!!.text = testEn
                    textView[0]!!.setTypeface(font3)
                }
                "ur" -> {
                    ColorUr = 6
                    rlColorUr!!.setBackgroundColor(Color.rgb(206, 230, 189))
                    textView[0]!!.text = testUr
                    textView[0]!!.setTypeface(font4)
                }
            }
        }
        imageButton[11]!!.setOnClickListener {
            relSelColor!!.visibility = View.GONE
            textView[0]!!.setTextColor(Color.WHITE)
            when (chekClick) {
                "ar" -> {
                    ColorAr = 7
                    rlColorAr!!.setBackgroundColor(Color.WHITE)
                    textView[0]!!.text = testAr
                    textView[0]!!.setTypeface(font)
                }
                "fa" -> {
                    ColorFa = 7
                    rlColorFa!!.setBackgroundColor(Color.WHITE)
                    textView[0]!!.text = testFa
                    textView[0]!!.setTypeface(font2)
                }
                "en" -> {
                    ColorEn = 7
                    rlColorEn!!.setBackgroundColor(Color.WHITE)
                    textView[0]!!.text = testEn
                    textView[0]!!.setTypeface(font3)
                }
                "ur" -> {
                    ColorUr = 7
                    rlColorUr!!.setBackgroundColor(Color.WHITE)
                    textView[0]!!.text = testUr
                    textView[0]!!.setTypeface(font4)
                }
            }
        }
        imageButton[12]!!.setOnClickListener {
            relSelColor!!.visibility = View.GONE
            textView[0]!!.setTextColor(Color.rgb(0, 20, 50))
            when (chekClick) {
                "ar" -> {
                    ColorAr = 8
                    rlColorAr!!.setBackgroundColor(Color.rgb(0, 20, 50))
                    textView[0]!!.text = testAr
                    textView[0]!!.setTypeface(font)
                }
                "fa" -> {
                    ColorFa = 8
                    rlColorFa!!.setBackgroundColor(Color.rgb(0, 20, 50))
                    textView[0]!!.text = testFa
                    textView[0]!!.setTypeface(font2)
                }
                "en" -> {
                    ColorEn = 8
                    rlColorEn!!.setBackgroundColor(Color.rgb(0, 20, 50))
                    textView[0]!!.text = testEn
                    textView[0]!!.setTypeface(font3)
                }
                "ur" -> {
                    ColorUr = 8
                    rlColorUr!!.setBackgroundColor(Color.rgb(0, 20, 50))
                    textView[0]!!.text = testUr
                    textView[0]!!.setTypeface(font4)
                }
            }
        }
        imageButton[13]!!.setOnClickListener {
            relSelColor!!.visibility = View.GONE
            textView[0]!!.setTextColor(Color.rgb(20, 60, 50))
            when (chekClick) {
                "ar" -> {
                    ColorAr = 9
                    rlColorAr!!.setBackgroundColor(Color.rgb(20, 60, 50))
                    textView[0]!!.text = testAr
                    textView[0]!!.setTypeface(font)
                }
                "fa" -> {
                    ColorFa = 9
                    rlColorFa!!.setBackgroundColor(Color.rgb(20, 60, 50))
                    textView[0]!!.text = testFa
                    textView[0]!!.setTypeface(font2)
                }
                "en" -> {
                    ColorEn = 9
                    rlColorEn!!.setBackgroundColor(Color.rgb(20, 60, 50))
                    textView[0]!!.text = testEn
                    textView[0]!!.setTypeface(font3)
                }
                "ur" -> {
                    ColorUr = 9
                    rlColorUr!!.setBackgroundColor(Color.rgb(20, 60, 50))
                    textView[0]!!.text = testUr
                    textView[0]!!.setTypeface(font4)
                }
            }
        }
        imageButton[14]!!.setOnClickListener {
            relSelColor!!.visibility = View.GONE
            textView[0]!!.setTextColor(Color.rgb(50, 35, 10))
            when (chekClick) {
                "ar" -> {
                    ColorAr = 10
                    rlColorAr!!.setBackgroundColor(Color.rgb(50, 35, 10))
                    textView[0]!!.text = testAr
                    textView[0]!!.setTypeface(font)
                }
                "fa" -> {
                    ColorFa = 10
                    rlColorFa!!.setBackgroundColor(Color.rgb(50, 35, 10))
                    textView[0]!!.text = testFa
                    textView[0]!!.setTypeface(font2)
                }
                "en" -> {
                    ColorEn = 10
                    rlColorEn!!.setBackgroundColor(Color.rgb(50, 35, 10))
                    textView[0]!!.text = testEn
                    textView[0]!!.setTypeface(font3)
                }
                "ur" -> {
                    ColorUr = 10
                    rlColorUr!!.setBackgroundColor(Color.rgb(50, 35, 10))
                    textView[0]!!.text = testUr
                    textView[0]!!.setTypeface(font4)
                }
            }
        }


//-----------------------------------------------------------------------------------
        imageButton[0]!!.setOnClickListener {
            sp = applicationContext.getSharedPreferences("setting", 0)
            val edit = sp.edit()
            edit.putString("fontAr", ArabicFont)
            MainActivity.ArabicFont = Typeface.createFromAsset(
                assets,
                "fonts/$ArabicFont.ttf"
            )
            edit.putString("fontfa", FaFont)
            MainActivity.FaFont = Typeface.createFromAsset(assets, "fonts/$FaFont.ttf")
            edit.putString("fonten", EnFont)
            MainActivity.EnFont = Typeface.createFromAsset(assets, "fonts/$EnFont.ttf")
            edit.putString("fontur", UrFont)
            MainActivity.UrFont = Typeface.createFromAsset(assets, "fonts/$UrFont.ttf")
            edit.putInt("ArabicSize", seekSizeAr!!.progress)
            MainActivity.ArabicSize = seekSizeAr!!.progress
            edit.putInt("FaSize", seekSizeFa!!.progress)
            MainActivity.FaSize = seekSizeFa!!.progress
            edit.putInt("EnSize", seekSizeEn!!.progress)
            MainActivity.EnSize = seekSizeEn!!.progress
            edit.putInt("UrSize", seekSizeUr!!.progress)
            MainActivity.UrSize = seekSizeUr!!.progress
            edit.putString("ShadowMod", ShadowMod)
            MainActivity.ShadowMod = ShadowMod
            edit.putInt("ColorAr", ColorAr)
            MainActivity.ColorAr = ColorAr
            edit.putInt("ColorFa", ColorFa)
            MainActivity.ColorFa = ColorFa
            edit.putInt("ColorEn", ColorEn)
            MainActivity.ColorEn = ColorEn
            edit.putInt("ColorUr", ColorUr)
            MainActivity.ColorUr = ColorUr
            edit.putInt("space", sbspace!!.progress)
            MainActivity.space = sbspace!!.progress
            edit.putString("mood", mood)
            MainActivity.mood = mood
            edit.apply()
            Toast.makeText(applicationContext, toas, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@FontColorsAdvance, MainActivity::class.java))
            finish()
        }
    }

    private fun laod() {
        sp = applicationContext.getSharedPreferences("setting", 0)
        val g = sp.getString("fontAr", "suls")
        ArabicFont = g
        font = Typeface.createFromAsset(assets, "fonts/$g.ttf")
        textView[0]!!.setTypeface(font)
        val g2 = sp.getString("fontfa", "arabic")
        FaFont = g2
        font2 = Typeface.createFromAsset(assets, "fonts/$g2.ttf")
        textView[0]!!.setTypeface(font2)
        val g3 = sp.getString("fonten", "gothic")
        EnFont = g3
        font3 = Typeface.createFromAsset(assets, "fonts/$g3.ttf")
        textView[0]!!.setTypeface(font3)
        val g4 = sp.getString("fontur", "palatino")
        UrFont = g4
        font4 = Typeface.createFromAsset(assets, "fonts/$g4.ttf")
        textView[0]!!.setTypeface(font4)
        seekSizeAr!!.progress = sp.getInt("ArabicSize", 18)
        seekSizeFa!!.progress = sp.getInt("FaSize", 18)
        seekSizeEn!!.progress = sp.getInt("EnSize", 18)
        seekSizeUr!!.progress = sp.getInt("UrSize", 18)
        sbspace!!.progress = sp.getInt("space", 0)
        ColorEn = sp.getInt("ColorEn", 3)
        ColorFa = sp.getInt("ColorFa", 1)
        ColorAr = sp.getInt("ColorAr", 5)
        ColorUr = sp.getInt("ColorUr", 2)
        mood = sp.getString("mood", "day")
        val z2 = sp.getString("ShadowMod", "true")
        ShadowMod = z2
        if (z2 == "true") {
            ShadowMod = "true"
            textView[0]!!.setShadowLayer(2f, 2f, 1f, Color.BLACK)
        } else if (z2 == "false") {
            ShadowMod = "false"
            textView[0]!!.setShadowLayer(0f, 0f, 0f, Color.BLACK)
        }
        APIcolorAr()
        when (MainActivity.ColorAr) {
            1 -> rlColorAr!!.setBackgroundColor(Color.rgb(250, 246, 219))
            2 -> rlColorAr!!.setBackgroundColor(Color.rgb(242, 211, 164))
            3 -> rlColorAr!!.setBackgroundColor(Color.rgb(239, 178, 197))
            4 -> rlColorAr!!.setBackgroundColor(Color.rgb(185, 194, 223))
            5 -> rlColorAr!!.setBackgroundColor(Color.rgb(199, 232, 232))
            6 -> rlColorAr!!.setBackgroundColor(Color.rgb(206, 230, 189))
            7 -> rlColorAr!!.setBackgroundColor(Color.WHITE)
            8 -> rlColorAr!!.setBackgroundColor(Color.rgb(0, 20, 50))
            9 -> rlColorAr!!.setBackgroundColor(Color.rgb(20, 60, 50))
            10 -> rlColorAr!!.setBackgroundColor(Color.rgb(50, 35, 10))
        }
        when (MainActivity.ColorFa) {
            1 -> rlColorFa!!.setBackgroundColor(Color.rgb(250, 246, 219))
            2 -> rlColorFa!!.setBackgroundColor(Color.rgb(242, 211, 164))
            3 -> rlColorFa!!.setBackgroundColor(Color.rgb(239, 178, 197))
            4 -> rlColorFa!!.setBackgroundColor(Color.rgb(185, 194, 223))
            5 -> rlColorFa!!.setBackgroundColor(Color.rgb(199, 232, 232))
            6 -> rlColorFa!!.setBackgroundColor(Color.rgb(206, 230, 189))
            7 -> rlColorFa!!.setBackgroundColor(Color.WHITE)
            8 -> rlColorFa!!.setBackgroundColor(Color.rgb(0, 20, 50))
            9 -> rlColorFa!!.setBackgroundColor(Color.rgb(20, 60, 50))
            10 -> rlColorFa!!.setBackgroundColor(Color.rgb(50, 35, 10))
        }
        when (MainActivity.ColorEn) {
            1 -> rlColorEn!!.setBackgroundColor(Color.rgb(250, 246, 219))
            2 -> rlColorEn!!.setBackgroundColor(Color.rgb(242, 211, 164))
            3 -> rlColorEn!!.setBackgroundColor(Color.rgb(239, 178, 197))
            4 -> rlColorEn!!.setBackgroundColor(Color.rgb(185, 194, 223))
            5 -> rlColorEn!!.setBackgroundColor(Color.rgb(199, 232, 232))
            6 -> rlColorEn!!.setBackgroundColor(Color.rgb(206, 230, 189))
            7 -> rlColorEn!!.setBackgroundColor(Color.WHITE)
            8 -> rlColorEn!!.setBackgroundColor(Color.rgb(0, 20, 50))
            9 -> rlColorEn!!.setBackgroundColor(Color.rgb(20, 60, 50))
            10 -> rlColorEn!!.setBackgroundColor(Color.rgb(50, 35, 10))
        }
        when (MainActivity.ColorUr) {
            1 -> rlColorUr!!.setBackgroundColor(Color.rgb(250, 246, 219))
            2 -> rlColorUr!!.setBackgroundColor(Color.rgb(242, 211, 164))
            3 -> rlColorUr!!.setBackgroundColor(Color.rgb(239, 178, 197))
            4 -> rlColorUr!!.setBackgroundColor(Color.rgb(185, 194, 223))
            5 -> rlColorUr!!.setBackgroundColor(Color.rgb(199, 232, 232))
            6 -> rlColorUr!!.setBackgroundColor(Color.rgb(206, 230, 189))
            7 -> rlColorUr!!.setBackgroundColor(Color.WHITE)
            8 -> rlColorUr!!.setBackgroundColor(Color.rgb(0, 20, 50))
            9 -> rlColorUr!!.setBackgroundColor(Color.rgb(20, 60, 50))
            10 -> rlColorUr!!.setBackgroundColor(Color.rgb(50, 35, 10))
        }
        val f = sp.getInt("ArabicSize", 18)
        textView[0]!!.textSize = f.toFloat()
        seekSizeAr!!.progress = f
        val f2 = sp.getInt("FaSize", 18)
        textView[0]!!.textSize = f2.toFloat()
        seekSizeFa!!.progress = f2
        val f3 = sp.getInt("EnSize", 18)
        textView[0]!!.textSize = f3.toFloat()
        seekSizeEn!!.progress = f3
        val f4 = sp.getInt("UrSize", 18)
        textView[0]!!.textSize = f4.toFloat()
        seekSizeUr!!.progress = f4
        val p = sp.getInt("space", 2)
        textView[0]!!.setLineSpacing(p.toFloat(), 1f)
        sbspace!!.progress = p
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
        testFa = listDoa[2].translateFa
        testEn = listDoa[2].translateEn
        testUr = listDoa[2].translateUrdu

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
                0 ->  textView[item]!!.text = listLang[item+19].translateFa
                1 ->  textView[item]!!.text = listLang[item+19].translateAr
                2 ->  textView[item]!!.text = listLang[item+19].translateEn
                3 ->  textView[item]!!.text = listLang[item+19].translateUr
            }

        }
        for (i in 7..11) {
            textView[i]!!.textSize = 30f
        }

        when(lang){
            0 ->  toas = listLang[29].translateFa
            1 ->  toas = listLang[29].translateAr
            2 ->  toas = listLang[29].translateEn
            3 ->  toas = listLang[29].translateUr
        }

        if (MainActivity.lang === 2) {
            for (i in id.indices) {
                radioButton[i]!!.typeface = fontmyredL
            }
            for (i in id2.indices) {
                textView[i]!!.typeface = fontmyredM
            }
            textView[7]!!.typeface = fontmyredL
            textView[8]!!.typeface = fontmyredL
            textView[9]!!.typeface = fontmyredL
            textView[10]!!.typeface = fontmyredL
            textView[11]!!.typeface = fontmyredL
            textView[7]!!.textSize = 25f
            textView[8]!!.textSize = 25f
            textView[9]!!.textSize = 25f
            textView[10]!!.textSize = 25f
            textView[11]!!.textSize = 25f
        }
        textView[0]!!.text = testAr
        textView[0]!!.typeface = font
        textView[0]!!.textSize = MainActivity.ArabicSize.toFloat()
    }

    fun ifLoad() {
        if (MainActivity.ShadowMod.equals("true")) {
            checkShadow!!.isChecked = true
            checkShadow!!.setTextColor(Color.WHITE)
        } else if (MainActivity.ShadowMod.equals("false")) {
            checkShadow!!.isChecked = false
        }
        if (MainActivity.mood.equals("day")) {
            textView[0]!!.setBackgroundResource(R.drawable.set_bgtest)
            radioButton[3]!!.isChecked = true
            radioButton[3]!!.setTextColor(Color.WHITE)
        } else if (MainActivity.mood.equals("night")) {
            textView[0]!!.setBackgroundResource(R.drawable.set_bgtest2)
            radioButton[4]!!.isChecked = true
            radioButton[4]!!.setTextColor(Color.WHITE)
        }
        if (ArabicFont == "suls") {
            radioButton[0]!!.isChecked = true
            radioButton[0]!!.setTextColor(Color.WHITE)
        } else if (ArabicFont == "shin") {
            radioButton[1]!!.isChecked = true
            radioButton[1]!!.setTextColor(Color.WHITE)
        } else if (ArabicFont == "arabic") {
            radioButton[2]!!.isChecked = true
            radioButton[2]!!.setTextColor(Color.WHITE)
        }
        if (FaFont == "homa") {
            radioButton[8]!!.isChecked = true
            radioButton[8]!!.setTextColor(Color.WHITE)
        } else if (FaFont == "casablanca") {
            radioButton[9]!!.isChecked = true
            radioButton[9]!!.setTextColor(Color.WHITE)
        } else if (FaFont == "arabic") {
            radioButton[10]!!.isChecked = true
            radioButton[10]!!.setTextColor(Color.WHITE)
        }
        if (EnFont == "myriadl") {
            radioButton[5]!!.isChecked = true
            radioButton[5]!!.setTextColor(Color.WHITE)
        } else if (EnFont == "gothic") {
            radioButton[6]!!.isChecked = true
            radioButton[6]!!.setTextColor(Color.WHITE)
        } else if (EnFont == "arabic") {
            radioButton[7]!!.isChecked = true
            radioButton[7]!!.setTextColor(Color.WHITE)
        }
        if (UrFont == "harir") {
            radioButton[11]!!.isChecked = true
            radioButton[1]!!.setTextColor(Color.WHITE)
        } else if (UrFont == "palatino") {
            radioButton[12]!!.isChecked = true
            radioButton[12]!!.setTextColor(Color.WHITE)
        } else if (UrFont == "arabic") {
            radioButton[13]!!.isChecked = true
            radioButton[13]!!.setTextColor(Color.WHITE)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(Intent(this@FontColorsAdvance, MainActivity::class.java))
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

    fun APIcolorAr() {
        when (ColorAr) {
            1 -> textView[0]!!.setTextColor(Color.rgb(250, 246, 219))
            2 -> textView[0]!!.setTextColor(Color.rgb(242, 211, 164))
            3 -> textView[0]!!.setTextColor(Color.rgb(239, 178, 197))
            4 -> textView[0]!!.setTextColor(Color.rgb(185, 194, 223))
            5 -> textView[0]!!.setTextColor(Color.rgb(199, 232, 232))
            6 -> textView[0]!!.setTextColor(Color.rgb(206, 230, 189))
            7 -> textView[0]!!.setTextColor(Color.WHITE)
            8 -> textView[0]!!.setTextColor(Color.rgb(0, 20, 50))
            9 -> textView[0]!!.setTextColor(Color.rgb(20, 60, 50))
            10 -> textView[0]!!.setTextColor(Color.rgb(50, 35, 10))
        }
    }

    fun APIcolorFa() {
        when (ColorFa) {
            1 -> textView[0]!!.setTextColor(Color.rgb(250, 246, 219))
            2 -> textView[0]!!.setTextColor(Color.rgb(242, 211, 164))
            3 -> textView[0]!!.setTextColor(Color.rgb(239, 178, 197))
            4 -> textView[0]!!.setTextColor(Color.rgb(185, 194, 223))
            5 -> textView[0]!!.setTextColor(Color.rgb(199, 232, 232))
            6 -> textView[0]!!.setTextColor(Color.rgb(206, 230, 189))
            7 -> textView[0]!!.setTextColor(Color.WHITE)
            8 -> textView[0]!!.setTextColor(Color.rgb(0, 20, 50))
            9 -> textView[0]!!.setTextColor(Color.rgb(20, 60, 50))
            10 -> textView[0]!!.setTextColor(Color.rgb(50, 35, 10))
        }
    }

    fun APIcolorEn() {
        when (ColorEn) {
            1 -> textView[0]!!.setTextColor(Color.rgb(250, 246, 219))
            2 -> textView[0]!!.setTextColor(Color.rgb(242, 211, 164))
            3 -> textView[0]!!.setTextColor(Color.rgb(239, 178, 197))
            4 -> textView[0]!!.setTextColor(Color.rgb(185, 194, 223))
            5 -> textView[0]!!.setTextColor(Color.rgb(199, 232, 232))
            6 -> textView[0]!!.setTextColor(Color.rgb(206, 230, 189))
            7 -> textView[0]!!.setTextColor(Color.WHITE)
            8 -> textView[0]!!.setTextColor(Color.rgb(0, 20, 50))
            9 -> textView[0]!!.setTextColor(Color.rgb(20, 60, 50))
            10 -> textView[0]!!.setTextColor(Color.rgb(50, 35, 10))
        }
    }

    fun APIcolorUr() {
        when (ColorUr) {
            1 -> textView[0]!!.setTextColor(Color.rgb(250, 246, 219))
            2 -> textView[0]!!.setTextColor(Color.rgb(242, 211, 164))
            3 -> textView[0]!!.setTextColor(Color.rgb(239, 178, 197))
            4 -> textView[0]!!.setTextColor(Color.rgb(185, 194, 223))
            5 -> textView[0]!!.setTextColor(Color.rgb(199, 232, 232))
            6 -> textView[0]!!.setTextColor(Color.rgb(206, 230, 189))
            7 -> textView[0]!!.setTextColor(Color.WHITE)
            8 -> textView[0]!!.setTextColor(Color.rgb(0, 20, 50))
            9 -> textView[0]!!.setTextColor(Color.rgb(20, 60, 50))
            10 -> textView[0]!!.setTextColor(Color.rgb(50, 35, 10))
        }
    }

    private fun showInterstitial() {}
}