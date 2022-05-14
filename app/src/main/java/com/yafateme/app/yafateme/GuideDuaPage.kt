package com.yafateme.app.yafateme

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AlertDialog
import com.yafateme.app.yafateme.MainActivity.Companion.xcount
import com.yafateme.app.yafateme.Splash.Companion.listDoa
import com.yafateme.app.yafateme.Splash.Companion.listLang
import com.yafateme.app.yafateme.Splash.Companion.listNumber


class GuideDuaPage : AppCompatActivity(), OnSeekBarChangeListener {
    var SaveKhotbe: ImageButton? = null
    var finishBtn: ImageButton? = null
   // private var db: database? = null

    private val strar = arrayOfNulls<String>(xcount)
    private val conter = arrayOfNulls<String>(xcount)
    private var play: ImageButton? = null
    private var pl: ImageButton? = null
    private var btnRepeat: ImageButton? = null
    private var btnBack: ImageButton? = null
    private var btnNext: ImageButton? = null
    var tvNote1: TextView? = null
    var li: ListView? = null
    private val linearLayout = arrayOfNulls<LinearLayout>(6)
    lateinit var id: Array<String>
    private val Str = arrayOfNulls<String>(11)
    var conterGuide = 0
    lateinit var sp: SharedPreferences
    var lang = 0
    var ArabicFont: Typeface? = null
    private var songProgressBar: SeekBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContentView(R.layout.activity_guide_dua_page)
        var themp: Int
        //- --------------      -0-              -1-             -2-               -3-            -4-           -5-
        id = arrayOf(
            "li_guide_play",
            "li_guide_seek",
            "li_guide_repeat",
            "li_guide_back",
            "li_guide_save",
            "li_gravity"
        )
        for (i in id.indices) {
            themp = resources.getIdentifier(id[i], "id", packageName)
            linearLayout[i] = findViewById<View>(themp) as LinearLayout
        }
        tvNote1 = findViewById<View>(R.id.tv_note2) as TextView
        btnBack = findViewById<View>(R.id.btn_back) as ImageButton
        btnNext = findViewById<View>(R.id.btn_next) as ImageButton
        SaveKhotbe = findViewById<View>(R.id.save_khotbe) as ImageButton
        finishBtn = findViewById<View>(R.id.fave_icon) as ImageButton
        play = findViewById<View>(R.id.btn_play) as ImageButton
        pl = findViewById<View>(R.id.btn_pl) as ImageButton
        btnRepeat = findViewById<View>(R.id.btnRepeat) as ImageButton /**/
        //rlcont = (RelativeLayout) findViewById(R.id.liPage1malakot);
        li = findViewById<View>(R.id.listViewDuaPage) as ListView
        pl!!.setBackgroundResource(R.drawable.famehrab)
        play!!.setBackgroundResource(R.drawable.panmehrab)
        btnRepeat!!.setBackgroundResource(R.drawable.re_offmehrab)
        sp = applicationContext.getSharedPreferences("setting", 0)
        lang = sp.getInt("lang", 2)
        val m = sp.getString("fontAr", "suls")
        ArabicFont = Typeface.createFromAsset(assets, "fonts/$m.ttf")
        songProgressBar = findViewById<View>(R.id.songProgressBar) as SeekBar
        songProgressBar!!.setOnSeekBarChangeListener(this as OnSeekBarChangeListener)
        val animationTarget = findViewById<View>(R.id.btn_play) as ImageView
        val animation = AnimationUtils.loadAnimation(this, R.anim.player)
        animationTarget.startAnimation(animation)
        langLoad()
        val myAnimShake = AnimationUtils.loadAnimation(this@GuideDuaPage, R.anim.shake2)
        myAnimShake.interpolator = MyBounceInterpolator(0.2, 30.0)
        val myAnimShake2 = AnimationUtils.loadAnimation(this@GuideDuaPage, R.anim.shake2)
        myAnimShake2.interpolator = MyBounceInterpolator(0.2, 30.0)
        val myAnimShake3 = AnimationUtils.loadAnimation(this@GuideDuaPage, R.anim.shake2)
        myAnimShake3.interpolator = MyBounceInterpolator(0.2, 30.0)
        linearLayout[0]!!.setBackgroundResource(R.drawable.border)
        tvNote1!!.text = Str[0]
        btnBack!!.isEnabled = false
        btnBack!!.setBackgroundResource(R.drawable.back_not)
      //  db = database(this@GuideDuaPage)
       // db!!.useable()
        class MyAdapter(
            context: Context?, resource: Int, textViewResourceId: Int,
            strings: Array<String?>?
        ) :
            ArrayAdapter<String?>(context!!, resource, textViewResourceId, strings!!) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val row: View = inflater.inflate(R.layout.row_quide, parent, false)
                val iv1 = row.findViewById<View>(R.id.img1) as ImageView
                val tvMain = row.findViewById<View>(R.id.text_ar) as TextView
                val tvTest = row.findViewById<View>(R.id.tv_test) as TextView
                val rlRow = row.findViewById<View>(R.id.rl_row) as RelativeLayout
                val fontArabic = Typeface.createFromAsset(assets, "fonts/arabic.ttf")
             //   db!!.open()
                if (lang == 2) {
                    conter[position] = listNumber[position].en
               //     conter[position] = db!!.namayesh(1, position, "conter")
                } else {
                    conter[position] = listNumber[position].fa

               //     conter[position] = db!!.namayesh(0, position, "conter")
                }
                strar[position] = listDoa[position].text
               // strar[position] = db!!.namayesh(position, 0, "database")
                tvTest.text = conter[position]
                tvMain.text = strar[position]
                tvMain.setTypeface(ArabicFont)
                tvMain.textSize = 20f
                tvMain.setTextColor(Color.rgb(255, 255, 255))
                tvTest.setTypeface(fontArabic)
                tvTest.textSize = 15f
                tvTest.setTextColor(Color.rgb(255, 255, 255))
                iv1.setBackgroundResource(R.drawable.sp3)
                if (position == 0) {
                    tvTest.visibility = View.GONE
                    iv1.visibility = View.GONE
                    rlRow.visibility = View.GONE
                    tvMain.visibility = View.GONE
                }
               // db!!.close()
                return row
            }
        }
        li!!.adapter = MyAdapter(
            this, android.R.layout.simple_list_item_1,
            R.id.text_ar, resources.getStringArray(R.array.list_conter)
        )
        li!!.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                noBorder()
                btnEnable()
                tvNote1!!.startAnimation(myAnimShake3)
                tvNote1!!.text = Str[3]
                li!!.setSelection(0)
                linearLayout[5]!!.gravity = Gravity.BOTTOM
                conterGuide = 4
                li!!.getChildAt(2).setBackgroundResource(R.drawable.border)
            }
        finishBtn!!.setOnClickListener {
            li!!.setSelection(0)
            li!!.getChildAt(2).setBackgroundResource(R.drawable.no_border)
            noBorder()
            btnEnable()
            tvNote1!!.startAnimation(myAnimShake3)
            linearLayout[3]!!.setBackgroundResource(R.drawable.border)
            tvNote1!!.text = Str[5]
            conterGuide = 5
            linearLayout[5]!!.gravity = Gravity.CENTER
        }
        play!!.setOnClickListener {
            li!!.setSelection(0)
            li!!.getChildAt(2).setBackgroundResource(R.drawable.no_border)
            noBorder()
            btnEnable()
            tvNote1!!.startAnimation(myAnimShake3)
            linearLayout[0]!!.setBackgroundResource(R.drawable.border)
            tvNote1!!.text = Str[0]
            conterGuide = 0
            linearLayout[5]!!.gravity = Gravity.CENTER
            btnBack!!.isEnabled = false
            btnBack!!.setBackgroundResource(R.drawable.back_not)
        }
        btnRepeat!!.setOnClickListener {
            li!!.setSelection(0)
            li!!.getChildAt(2).setBackgroundResource(R.drawable.no_border)
            noBorder()
            btnEnable()
            tvNote1!!.startAnimation(myAnimShake3)
            linearLayout[2]!!.setBackgroundResource(R.drawable.border)
            tvNote1!!.text = Str[2]
            conterGuide = 2
            linearLayout[5]!!.gravity = Gravity.CENTER
        }
        btnNext!!.setOnClickListener {
            conterGuide = conterGuide + 1
            btnNext!!.startAnimation(myAnimShake2)
            tvNote1!!.startAnimation(myAnimShake3)
            noBorder()
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
                    tvNote1!!.text = Str[3]
                    li!!.setSelection(0)
                    li!!.getChildAt(2).setBackgroundResource(R.drawable.border)
                    linearLayout[5]!!.gravity = Gravity.BOTTOM
                }
                4 -> {
                    li!!.setSelection(0)
                    li!!.getChildAt(2).setBackgroundResource(R.drawable.border)
                    tvNote1!!.text = Str[4]
                }
                5 -> {
                    tvNote1!!.text = Str[5]
                    li!!.getChildAt(2).setBackgroundResource(R.drawable.no_border)
                    linearLayout[3]!!.setBackgroundResource(R.drawable.border)
                    linearLayout[5]!!.gravity = Gravity.CENTER
                }
                6 -> {
                    tvNote1!!.text = Str[6]
                    linearLayout[4]!!.setBackgroundResource(R.drawable.border)
                    btnNext!!.isEnabled = false
                    btnNext!!.setBackgroundResource(R.drawable.next_not)
                }
            }
        }
        btnBack!!.setOnClickListener {
            conterGuide = conterGuide - 1
            btnBack!!.startAnimation(myAnimShake)
            tvNote1!!.startAnimation(myAnimShake3)
            noBorder()
            btnEnable()
            when (conterGuide) {
                0 -> {
                    btnBack!!.isEnabled = false
                    btnBack!!.setBackgroundResource(R.drawable.back_not)
                    tvNote1!!.text = Str[0]
                    linearLayout[0]!!.setBackgroundResource(R.drawable.border)
                }
                1 -> {
                    tvNote1!!.text = Str[1]
                    linearLayout[1]!!.setBackgroundResource(R.drawable.border)
                }
                2 -> {
                    tvNote1!!.text = Str[2]
                    li!!.getChildAt(2).setBackgroundResource(R.drawable.no_border)
                    linearLayout[2]!!.setBackgroundResource(R.drawable.border)
                    linearLayout[5]!!.gravity = Gravity.CENTER
                }
                3 -> {
                    li!!.setSelection(0)
                    tvNote1!!.text = Str[3]
                }
                4 -> {
                    li!!.setSelection(0)
                    li!!.getChildAt(2).setBackgroundResource(R.drawable.border)
                    tvNote1!!.text = Str[4]
                    linearLayout[5]!!.gravity = Gravity.BOTTOM
                }
                5 -> {
                    tvNote1!!.text = Str[5]
                    linearLayout[3]!!.setBackgroundResource(R.drawable.border)
                    btnNext!!.isEnabled = true
                    btnNext!!.setBackgroundResource(R.drawable.next)
                }
            }
        }
        SaveKhotbe!!.setOnClickListener {
            noBorder()
            btnEnable()
            tvNote1!!.startAnimation(myAnimShake3)
            li!!.setSelection(0)
            li!!.getChildAt(2).setBackgroundResource(R.drawable.no_border)
            tvNote1!!.text = Str[6]
            conterGuide = 6
            linearLayout[4]!!.setBackgroundResource(R.drawable.border)
            linearLayout[5]!!.gravity = Gravity.CENTER
            btnNext!!.isEnabled = false
            btnNext!!.setBackgroundResource(R.drawable.next_not)
        }
        li!!.isLongClickable = true
        li!!.onItemLongClickListener =
            OnItemLongClickListener { l, v, position, id ->
                li!!.setSelection(0)
                noBorder()
                btnEnable()
                tvNote1!!.startAnimation(myAnimShake3)
                conterGuide = 3
                tvNote1!!.text = Str[4]
                linearLayout[5]!!.gravity = Gravity.BOTTOM
                li!!.getChildAt(2).setBackgroundResource(R.drawable.border)
                false
            }
    }

    fun langLoad() {
        val fontmyredL = Typeface.createFromAsset(assets, "fonts/myriadm.ttf")
        val fontArabic = Typeface.createFromAsset(assets, "fonts/arabic.ttf")
        tvNote1!!.typeface = fontArabic


        if (lang == 2) {

            tvNote1!!.typeface = fontmyredL
            tvNote1!!.textSize = 20f
        }
        for (item in 0..10) {
            when(lang){
                0 ->  Str[item] = listLang[item + 131].translateFa
                1 ->  Str[item] = listLang[item + 131].translateAr
                2 ->  Str[item] = listLang[item + 131].translateEn
                3 ->  Str[item] = listLang[item + 131].translateUr
            }



        }

    }

    fun noBorder() {
        linearLayout[0]!!.setBackgroundResource(R.drawable.no_border)
        linearLayout[1]!!.setBackgroundResource(R.drawable.no_border)
        linearLayout[2]!!.setBackgroundResource(R.drawable.no_border)
        linearLayout[3]!!.setBackgroundResource(R.drawable.no_border)
        linearLayout[4]!!.setBackgroundResource(R.drawable.no_border)
        li!!.setBackgroundResource(R.drawable.no_border)
    }

    fun btnEnable() {
        btnBack!!.isEnabled = true
        btnBack!!.setBackgroundResource(R.drawable.back)
        btnNext!!.isEnabled = true
        btnNext!!.setBackgroundResource(R.drawable.next)
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromTouch: Boolean) {}
    override fun onStartTrackingTouch(seekBar: SeekBar) {}
    override fun onStopTrackingTouch(seekBar: SeekBar) {
        val myAnimShake3 = AnimationUtils.loadAnimation(this@GuideDuaPage, R.anim.shake2)
        myAnimShake3.interpolator = MyBounceInterpolator(0.2, 30.0)
        li!!.setSelection(0)
        li!!.getChildAt(2).setBackgroundResource(R.drawable.no_border)
        noBorder()
        btnEnable()
        tvNote1!!.startAnimation(myAnimShake3)
        linearLayout[1]!!.setBackgroundResource(R.drawable.border)
        tvNote1!!.text = Str[1]
        conterGuide = 1
        linearLayout[5]!!.gravity = Gravity.CENTER
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val alert = AlertDialog.Builder(this@GuideDuaPage)
            alert.setTitle(Str[7])
            alert.setMessage(Str[8])
            alert.setPositiveButton(
                Str[9]
            ) { dialog, which ->
                val MainIntent = Intent(this@GuideDuaPage, GuidesActivity::class.java)
                startActivity(MainIntent)
                dialog.dismiss()
                finish()
            }
            alert.setNegativeButton(
                Str[10]
            ) { dialog, which -> dialog.dismiss() }
            alert.show()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}