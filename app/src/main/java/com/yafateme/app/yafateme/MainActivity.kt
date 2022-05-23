package com.yafateme.app.yafateme
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.AssetManager
import android.database.sqlite.SQLiteDatabase
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Settings.Global.getString
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
//import co.ronash.pushe.Pushe
import com.google.android.material.navigation.NavigationView
import com.yafateme.app.yafateme.*
import com.yafateme.app.yafateme.AdapterDuaPage.Companion.fontArabic
import com.yafateme.app.yafateme.AdapterDuaPage.Companion.fontmyredM
import com.yafateme.app.yafateme.Setting.Companion.fontarabicB
import com.yafateme.app.yafateme.Setting.Companion.fontmyredL
import com.yafateme.app.yafateme.Splash.Companion.listLang
import java.io.File
import java.util.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var navigationView: NavigationView
    var toolbar: Toolbar? = null
    lateinit var drawer: DrawerLayout
    lateinit var  sp: SharedPreferences
    var finish: ImageButton? = null
    var menu: ImageButton? = null
    var btnPage: ImageButton? = null
    lateinit var back_nav: ImageView
    var rlMalakot: RelativeLayout? = null
    var rlExit: RelativeLayout? = null
    var intCont = 0
    var copyt: TextView? = null
    var tvExit: TextView? = null
    var tvStar: TextView? = null
    var tvHemayat: TextView? = null
    var tvCaption: TextView? = null
    var tvTitle: TextView? = null
    var tvExitf: TextView? = null
    var shareApp: String? = null
    var duaStr: String? = null

    private val linearLayout = arrayOfNulls<LinearLayout>(8)
    private lateinit var id: Array<String>
    var exitTest = false
    var liBtnExit: LinearLayout? = null
    var liBtnStar: LinearLayout? = null
    var liBtnHemayat: LinearLayout? = null
    var llmain: LinearLayout? = null

    //var DirS = "/sdcard/Android/data/" + G.context?.getApplicationContext()?.getPackageName()
     //   .toString() + "/"
    var mydb: SQLiteDatabase? = null
    var menuDr = ArrayList<Menu>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DirS = Utils.getRootDirPath(applicationContext)+"/"
        Log.d("#DirS", "onCreate: ${DirS}")



        //Pushe.initialize(this, true)
        //        AdCommon.init(this, getString(R.string.ad_sdk_key),false,true);
        var themp: Int
        //- --------------    -0-           -1-           -2-          -3-            -4-           -5-           -6-
        id = arrayOf(
            "anim_alfa7",
            "anim_alfa3",
            "anim_alfa4",
            "anim_alfa6",
            "anim_alfa5",
            "anim_alfa2",
            "anim_alfa1"
        )
        for (i in id.indices) {
            themp = resources.getIdentifier(id[i], "id", packageName)
            linearLayout[i] = findViewById<View>(themp) as LinearLayout
        }


         myFile = File(DirS + getString(R.string.rawString2) + ".mp3")
         myFile2 = File(DirS + getString(R.string.rawString3) + ".mp3")
        val am = applicationContext.assets
        fontArabic =
            Typeface.createFromAsset(am, String.format(Locale.US, "fonts/%s", "arabic.ttf"))
        fontmyredL =
            Typeface.createFromAsset(am, String.format(Locale.US, "fonts/%s", "myriadm.ttf"))
        fontmyredM =
            Typeface.createFromAsset(am, String.format(Locale.US, "fonts/%s", "myriadl.ttf"))
        fontarabicB =
            Typeface.createFromAsset(am, String.format(Locale.US, "fonts/%s", "arabicb.otf"))
        xcount = Integer.valueOf(getString(R.string.xCountString))
        sp = applicationContext.getSharedPreferences("setting", 0)
        val m = sp.getString("fontAr", "suls")
        ArabicFont = Typeface.createFromAsset(
            am, String.format(
                Locale.US, "fonts/%s",
                "$m.ttf"
            )
        )
        val c1 = sp.getString("fontfa", "arabic")
        FaFont = Typeface.createFromAsset(
            am, String.format(
                Locale.US, "fonts/%s",
                "$c1.ttf"
            )
        )
        val c2 = sp.getString("fontur", "palatino")
        UrFont = Typeface.createFromAsset(
            am, String.format(
                Locale.US, "fonts/%s",
                "$c2.ttf"
            )
        )
        val en = sp.getString("fonten", "gothic")
        EnFont = Typeface.createFromAsset(
            am, String.format(
                Locale.US, "fonts/%s",
                "$en.ttf"
            )
        )
        ArabicSize = sp.getInt("ArabicSize", 18)
        FaSize = sp.getInt("FaSize", 18)
        EnSize = sp.getInt("EnSize", 18)
        UrSize = sp.getInt("UrSize", 18)
        ColorEn = sp.getInt("ColorEn", 3)
        ColorFa = sp.getInt("ColorFa", 1)
        ColorAr = sp.getInt("ColorAr", 5)
        ColorUr = sp.getInt("ColorUr", 2)
        space = sp.getInt("space", 0)
        mood = sp.getString("mood", "day")
        DataTr1 = sp.getString("DataTr1", "none")
        DataTr2 = sp.getString("DataTr2", "none")
        lang = sp.getInt("lang", 0)
        ShadowMod = sp.getString("ShadowMod", "true")
        DataSong = sp.getInt("DataSong", 1)
        Rep = sp.getBoolean("Rep", true)
        BgMp = sp.getBoolean("BgMp", true)
        Review = sp.getBoolean("Review", true)
        AutoSeek = sp.getBoolean("AutoSeek", true)
        Scroll = sp.getBoolean("Scroll", true)
        Pro = sp.getBoolean("Pro", true)
        Ads = sp.getBoolean("Ads", true)
        val edit = sp.edit()

        if (DataSong == 2) {

            if (!myFile.exists()) {
                Log.d(" not exists", "onCreate zaeri ")
                DataSong = 1
                currentMaddah = "zaeri.mp3"
            }else{
                Log.d(" exists", "onCreate: farahmand  ")
                DataSong= 2
                currentMaddah = "farahmand.mp3"

            }
        } else if (DataSong == 3) {
            if (!myFile2.exists()) {
                Log.d("not exists", "onCreate zaeri2  ")
                DataSong = 1
                currentMaddah = "zaeri.mp3"
            } else {
                Log.d("exists", "onCreate samavati ")
                DataSong = 3
                currentMaddah = "samavati.mp3"
            }
        }
        edit.putInt("DataSong", DataSong)
        edit.apply()
        toolbar = findViewById(R.id.toolbar)
        drawer = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        val headerview = navigationView.getHeaderView(0)
        tvTitle = findViewById<View>(R.id.tv_title) as TextView
        tvCaption = findViewById<View>(R.id.tv_caption) as TextView
        tvHemayat = findViewById<View>(R.id.tv_hemayat) as TextView
        tvStar = findViewById<View>(R.id.tv_star) as TextView
        tvExit = findViewById<View>(R.id.tv_exit) as TextView
        tvExitf = findViewById<View>(R.id.tvExit) as TextView
        btnPage = findViewById<View>(R.id.btn_light) as ImageButton
        //        menu = (ImageButton) findViewById(R.id.menu_btn);
        back_nav = headerview.findViewById(R.id.back_nav)
        rlMalakot = findViewById<View>(R.id.rl_btn) as RelativeLayout
        finish = findViewById<View>(R.id.back_btn) as ImageButton
        rlExit = findViewById<View>(R.id.rl_exit) as RelativeLayout
        //
        liBtnExit = findViewById<View>(R.id.li_btn_exit) as LinearLayout
        liBtnStar = findViewById<View>(R.id.li_btn_star) as LinearLayout
        liBtnHemayat = findViewById<View>(R.id.li_btn_hemayat) as LinearLayout
        llmain = findViewById<View>(R.id.llmain) as LinearLayout
        tvTitle!!.setTypeface(fontarabicB)
        tvCaption!!.setTypeface(fontArabic)
        tvHemayat!!.setTypeface(fontarabicB)
        tvStar!!.setTypeface(fontarabicB)
        tvExit!!.setTypeface(fontarabicB)
        rlExit!!.visibility = View.GONE
        animLogo()
        langLoad()

        if (isFirstTime) {
            rlExit!!.visibility = View.VISIBLE
            liBtnExit!!.visibility = View.GONE
            liBtnStar!!.visibility = View.GONE
            liBtnHemayat!!.visibility = View.GONE
            llmain!!.visibility = View.GONE
            tvExitf!!.visibility = View.VISIBLE
            for (item in listLang){
                if(item.id == 246) {
                    when (lang) {
                        0 -> tvTitle!!.text = item.translateFa
                        1 -> tvTitle!!.text = item.translateAr
                        2 -> tvTitle!!.text = item.translateEn
                        3 -> tvTitle!!.text = item.translateUr
                    }
                }
               else if(item.id == 247){
                    when (lang){
                        0 ->  tvCaption!!.text = item.translateFa
                        1 ->  tvCaption!!.text = item.translateAr
                        2 ->  tvCaption!!.text = item.translateEn
                        3 ->  tvCaption!!.text = item.translateUr
                    }
                } else if (item.id == 245){
                        when (lang){
                            0 ->  tvExitf!!.text = item.translateFa
                            1 ->  tvExitf!!.text = item.translateAr
                            2 ->  tvExitf!!.text = item.translateEn
                            3 ->  tvExitf!!.text = item.translateUr

                    }

                }
            }


        } else {
            tvExitf!!.visibility = View.GONE
        }
        val animationMenu = findViewById<View>(R.id.btn_light) as ImageButton
        val animation1 = AnimationUtils.loadAnimation(this, R.anim.fade)
        animationMenu.startAnimation(animation1)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        tvExitf!!.setOnClickListener {


            var captionToast3 : String = ""
            for (item in listLang) {


                if (item.id == 248) {
                    when (lang) {
                        0 -> captionToast3 = item.translateFa.toString()
                        1 -> captionToast3 = item.translateAr.toString()
                        2 -> captionToast3 = item.translateEn.toString()
                        3 -> captionToast3 = item.translateUr.toString()
                    }
                }
            }
            Toast.makeText(this@MainActivity, captionToast3, Toast.LENGTH_LONG).show()
            // بعدا بررسی شود ما فکر میکنیم که فقط یک تواست بوده است

            langLoad()
            rlExit!!.visibility = View.GONE
        }
        back_nav.setOnClickListener(View.OnClickListener { drawer.closeDrawer(GravityCompat.START) })
        liBtnExit!!.setOnClickListener { onFinish() }
        finish!!.setOnClickListener { onFinish() }
        btnPage!!.setOnClickListener {
            val pa0 = Intent(this@MainActivity, Dua::class.java)// DuaTest
            startActivity(pa0)
        }
        liBtnStar!!.setOnClickListener { Stars() }
        liBtnHemayat!!.setOnClickListener { ShareApp() }
        rlExit!!.setOnClickListener {
            rlExit!!.visibility = View.GONE
            exitTest = false
        }
        navigationView.setNavigationItemSelectedListener(this)
        val l = lang
        // کد زیر اصل جاواست که کامنت شده است
//        navigationView.getMenu().clear();
//        final Menu menu = navigationView.getMenu();
//        int j = 0;

  //      for (int i = 267; i < 275; i++) {
//            j = i - 267;
//            menu.add(i - 267, i - 267, i - 267, db.namayesh(i, l, "lang"));
//        }
        //duaStr = db.namayesh(44, l, "langapp")
      //  shareApp = db.namayesh(61, l, "langapp")
    }

    override fun onBackPressed() {
        if (drawer!!.isDrawerOpen(GravityCompat.START)) {
            drawer!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        var menu = menu

        // تابع اددمنو کمی مشکوک میزند که باید بررسی شود اگر بعدا مشکلی پیش امد
        val l = lang
        navigationView!!.menu.clear()
        menu = navigationView!!.menu

        for (i in 268..275) {
            var menuu = listLang[i-1]


                    when (lang) {
                        0 -> menu.add(i - 268, i - 268, i - 268,menuu.translateFa)
                        1 -> menu.add(i - 268, i - 268, i - 268,menuu.translateAr)
                        2 -> menu.add(i - 268, i - 268, i - 268,menuu.translateEn)
                        3 -> menu.add(i - 268, i - 268, i - 268,menuu.translateUr)

                    }
                }





        menu.findItem(0).setIcon(R.drawable.ic_menu_share)
        menu.findItem(1).setIcon(R.drawable.ic_menu_rate)
        menu.findItem(2).setIcon(R.drawable.ic_menu_app)
        menu.findItem(3).setIcon(R.drawable.ic_menu_aboutus)
        menu.findItem(4).setIcon(R.drawable.ic_menu_contactus)
        menu.findItem(5).setIcon(R.drawable.ic_menu_guide)
        menu.findItem(6).setIcon(R.drawable.ic_menu_font)
        menu.findItem(7).setIcon(R.drawable.ic_menu_setting)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        val id = menuItem.itemId
       // Log.d("aliali", "onNavigationItemSelected: $id ")
        when (id) {
            0 -> ShareApp()
            1 -> Stars()
            2 -> Stars()
            3 -> startActivity(Intent(this@MainActivity, AboutUs::class.java))
            4 -> startActivity(Intent(this@MainActivity, ContactUs::class.java))
            5 -> startActivity(Intent(this@MainActivity, GuidesActivity::class.java))
            6 -> if (Pro) {
                val AdSet = Intent(this@MainActivity, FontColorsAdvance::class.java)
                startActivity(AdSet)
            } else {
                val NoSet = Intent(this@MainActivity, FontsColorNormal::class.java)
                startActivity(NoSet)
            }
            7 -> {

                val intentFontColors = Intent(this@MainActivity, Setting::class.java)
                startActivity(intentFontColors)
                finish()
            }
        }

        drawer!!.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onFinish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    fun anim() {
        val myAnimShake = AnimationUtils.loadAnimation(this@MainActivity, R.anim.shake2)
        val interpolator = MyBounceInterpolator(0.2, 30.0)
        myAnimShake.interpolator = interpolator
        menu!!.startAnimation(myAnimShake)
    }

    fun animLogo() {
        object : CountDownTimer(10, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                val animLogo1 = linearLayout[1]
                val animat1 = AnimationUtils.loadAnimation(this@MainActivity, R.anim.scale_logo)
                animLogo1!!.startAnimation(animat1)
                object : CountDownTimer(100, 1000) {
                    override fun onTick(millisUntilFinished: Long) {}
                    override fun onFinish() {
                        val animLogo2 = linearLayout[2]
                        val animat2 =
                            AnimationUtils.loadAnimation(this@MainActivity, R.anim.scale_logo)
                        animLogo2!!.startAnimation(animat2)
                        object : CountDownTimer(100, 1000) {
                            override fun onTick(millisUntilFinished: Long) {}
                            override fun onFinish() {
                                linearLayout[0]!!.clearAnimation()
                                val animLogo3 = linearLayout[2]
                                val animat3 = AnimationUtils.loadAnimation(
                                    this@MainActivity,
                                    R.anim.scale_logo
                                )
                                animLogo3!!.startAnimation(animat3)
                                object : CountDownTimer(100, 1000) {
                                    override fun onTick(millisUntilFinished: Long) {}
                                    override fun onFinish() {
                                        linearLayout[1]!!.clearAnimation()
                                        val animLogo3 = linearLayout[3]
                                        val animat3 = AnimationUtils.loadAnimation(
                                            this@MainActivity,
                                            R.anim.scale_logo
                                        )
                                        animLogo3!!.startAnimation(animat3)
                                        object : CountDownTimer(100, 1000) {
                                            override fun onTick(millisUntilFinished: Long) {}
                                            override fun onFinish() {
                                                linearLayout[2]!!.clearAnimation()
                                                val animLogo4 = linearLayout[4]
                                                val animat4 = AnimationUtils.loadAnimation(
                                                    this@MainActivity,
                                                    R.anim.scale_logo
                                                )
                                                animLogo4!!.startAnimation(animat4)
                                                object : CountDownTimer(100, 1000) {
                                                    override fun onTick(millisUntilFinished: Long) {}
                                                    override fun onFinish() {
                                                        linearLayout[3]!!.clearAnimation()
                                                        val animLogo5 = linearLayout[5]
                                                        val animat5 = AnimationUtils.loadAnimation(
                                                            this@MainActivity,
                                                            R.anim.scale_logo
                                                        )
                                                        animLogo5!!.startAnimation(animat5)
                                                        object : CountDownTimer(100, 1000) {
                                                            override fun onTick(millisUntilFinished: Long) {}
                                                            override fun onFinish() {
                                                                linearLayout[4]!!.clearAnimation()
                                                                val animLogo6 = linearLayout[6]
                                                                val animat6 =
                                                                    AnimationUtils.loadAnimation(
                                                                        this@MainActivity,
                                                                        R.anim.scale_logo
                                                                    )
                                                                animLogo6!!.startAnimation(animat6)
                                                                object : CountDownTimer(100, 1000) {
                                                                    override fun onTick(
                                                                        millisUntilFinished: Long
                                                                    ) {
                                                                    }

                                                                    override fun onFinish() {
                                                                        linearLayout[5]!!.clearAnimation()
                                                                        object : CountDownTimer(
                                                                            100,
                                                                            1000
                                                                        ) {
                                                                            override fun onTick(
                                                                                millisUntilFinished: Long
                                                                            ) {
                                                                            }

                                                                            override fun onFinish() {
                                                                                linearLayout[6]!!
                                                                                    .clearAnimation()
                                                                                object :
                                                                                    CountDownTimer(
                                                                                        6000,
                                                                                        1000
                                                                                    ) {
                                                                                    override fun onTick(
                                                                                        millisUntilFinished: Long
                                                                                    ) {
                                                                                    }

                                                                                    override fun onFinish() {
                                                                                        animLogo()
                                                                                    }
                                                                                }.start()
                                                                            }
                                                                        }.start()
                                                                    }
                                                                }.start()
                                                            }
                                                        }.start()
                                                    }
                                                }.start()
                                            }
                                        }.start()
                                    }
                                }.start()
                            }
                        }.start()
                    }
                }.start()
            }
        }.start()
    }

    fun langLoad() {

        if (lang == 2) {
            tvTitle!!.typeface = fontmyredL
            tvCaption!!.typeface = fontmyredM
            tvHemayat!!.typeface = fontmyredL
            tvStar!!.typeface = fontmyredL
            tvExit!!.typeface = fontmyredL
        } else {
            tvTitle!!.typeface = fontarabicB
            tvCaption!!.typeface = fontArabic
            tvHemayat!!.typeface = fontarabicB
            tvStar!!.typeface = fontarabicB
            tvExit!!.typeface = fontarabicB
        }
       // for (i in 267..274) {
            for (item in listLang) {
               // Log.d("11111", "langLoad: ")


                if (item.id == 70) {
                    when (lang) {
                        0 -> tvTitle!!.text= item.translateFa
                        1 -> tvTitle!!.text= item.translateAr
                        2 -> tvTitle!!.text= item.translateEn
                        3 -> tvTitle!!.text= item.translateUr

                    }
                } else if ( item.id == 67){
                    when (lang) {
                        0 -> tvCaption!!.text= item.translateFa
                        1 -> tvCaption!!.text= item.translateAr
                        2 -> tvCaption!!.text= item.translateEn
                        3 -> tvCaption!!.text= item.translateUr

                }
                }
                else if ( item.id == 68){
                    when (lang) {
                        0 -> tvHemayat!!.text= item.translateFa
                        1 -> tvHemayat!!.text= item.translateAr
                        2 -> tvHemayat!!.text= item.translateEn
                        3 -> tvHemayat!!.text= item.translateUr

                    }
                }
                else if ( item.id == 69){
                    when (lang) {
                        0 -> tvStar!!.text= item.translateFa
                        1 -> tvStar!!.text= item.translateAr
                        2 -> tvStar!!.text= item.translateEn
                        3 -> tvStar!!.text= item.translateUr

                    }
                }
                else if ( item.id == 66){
                    when (lang) {
                        0 -> tvExit!!.text= item.translateFa
                        1 -> tvExit!!.text= item.translateAr
                        2 -> tvExit!!.text= item.translateEn
                        3 -> tvExit!!.text= item.translateUr

                    }
                }


            }

    }

    private val isFirstTime: Boolean
        private get() {
            val preferences = getPreferences(MODE_PRIVATE)
            val ranBefore = preferences.getBoolean("RanBefore", false)
            if (!ranBefore) {
                val editor = preferences.edit()
                editor.putBoolean("RanBefore", true)
                editor.commit()
            }
            return !ranBefore
        }

    fun ShareApp() {

    }

    fun Stars() {
        var intent: Intent? = null
        var chooser: Intent? = null
        intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("market://details?id=com.appeks.ziyarateashura")
        chooser = Intent.createChooser(intent, "Launch Market")
        startActivity(chooser)
    }

    fun onFinish() {
        drawer!!.isDrawerOpen(GravityCompat.START)
        drawer!!.closeDrawer(GravityCompat.START)
        langLoad()
        liBtnStar!!.visibility = View.VISIBLE
        liBtnHemayat!!.visibility = View.VISIBLE
        liBtnExit!!.visibility = View.VISIBLE
        if (!exitTest) {
            exitTest = true
            rlExit!!.visibility = View.VISIBLE
            tvExitf!!.visibility = View.GONE
        } else {
            finish()
        }
    }

    companion object {
        lateinit var currentMaddah: String
        lateinit var myFile : File
        lateinit var myFile2 : File
        lateinit var DirS: String
        var ArabicSize = 0
        var FaSize = 0
        var EnSize = 0
        var UrSize = 0
        var space = 0
        var ShadowMod: String? = null
        var mood: String? = null
        var DataTr1: String? = null
        var DataTr2: String? = null
        var ArabicFont: Typeface? = null
        var FaFont: Typeface? = null
        var UrFont: Typeface? = null
        var EnFont: Typeface? = null
        var ColorEn = 0
        var ColorFa = 0
        var ColorAr = 0
        var ColorUr = 0
        var DataSong = 0
        var xcount = 0
        var lang = 0
        var Ads = false
        var Pro = false
        var Scroll = false
        var AutoSeek = false
        var Review = false
        var BgMp = false
        var Rep = false
    }
}