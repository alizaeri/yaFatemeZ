package com.yafateme.app.yafateme

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.downloader.*
import com.yafateme.app.yafateme.AdapterDuaPage.Companion.fontArabic
import com.yafateme.app.yafateme.AdapterDuaPage.Companion.fontmyredM
import com.yafateme.app.yafateme.MainActivity.Companion.DataSong
import com.yafateme.app.yafateme.MainActivity.Companion.lang
import com.yafateme.app.yafateme.Splash.Companion.listLang
import com.yafateme.app.yafateme.Splash.Companion.listLanguage
//import com.yafateme.app.yafateme.database.Companion.Name
//import com.yafateme.app.yafateme.database.Companion.path
import kotlinx.coroutines.*
import java.io.*
import java.lang.Exception
import java.util.ArrayList


class Setting : Activity() {
    private lateinit var id: Array<String>
    private lateinit var id2: Array<String>
    var rl_set: RelativeLayout? = null
    lateinit var spin_tr1: Spinner
   lateinit var spin_tr2: Spinner
    lateinit var spin_lang: Spinner
    lateinit var spin_maddah: Spinner

    lateinit var sp: SharedPreferences
    //var lang = 0
    var a = 0
    //var DataSong = 0
    private var adapter_lang: SpinAdapter? = null
    private var adapter_tr1: SpinAdapter? = null
    private var adapter_tr2: SpinAdapter? = null
    private var adapter_maddah: SpinAdapter? = null
    private val textView = arrayOfNulls<TextView>(15)
    private val radioButtons = arrayOfNulls<RadioButton>(2)
    private var checkScroll: CheckBox? = null
    private var checkBgMp: CheckBox? = null
    private var chekSeek: CheckBox? = null
    private var chekReview: CheckBox? = null
    private var chekAdvance: CheckBox? = null
    private var chekAds: CheckBox? = null
    private var saveLight: ImageButton? = null
    private val str = arrayOfNulls<String>(15)
    private var DataTr1: String? = null
    private var DataTr2: String? = null
    private var MoveM: String? = null
    private var enteqalFaraz: String? = null
    private var yesBtn: String? = null
    private var toasDialog: String? = null
    private var noBtn: String? = null
    private var toas: String? = null
    lateinit var DirS: String 
        //"/sdcard/Android/data/" + G.context!!.applicationContext.packageName.toString() + "/"
    var FileUrl: String? = null
    lateinit var file_name: String
    var file_size = 0.0
    lateinit var mProgressDialog: ProgressBar
    lateinit var pbarTitle : TextView
    lateinit var pbarCaption : TextView
    lateinit var perprogressBar : TextView
    lateinit var pb_ralativeGone : RelativeLayout
    var languagechoice : Int = 0
    var dirPath: String? = null
    var downloadIdOne = 0
    var tempString : Int = 0





    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        mProgressDialog = findViewById(R.id.sProgressBar)
        pbarTitle = findViewById(R.id.pbTitle)
        pbarCaption = findViewById(R.id.pbCaption)
        perprogressBar = findViewById(R.id.percentProgressBar)
        pb_ralativeGone = findViewById(R.id.progress_ralativeGone)
        //init()
        val config = PRDownloaderConfig.newBuilder()
            .setDatabaseEnabled(true)
            .build()
        PRDownloader.initialize(applicationContext, config)
        DirS = Utils.getRootDirPath(applicationContext)+"/"
        Log.d("dirPath", "onCreate: $DirS ")

        fontmyredM = Typeface.createFromAsset(assets, "fonts/myriadl.ttf")
        fontmyredL = Typeface.createFromAsset(assets, "fonts/myriadm.ttf")
        fontArabic = Typeface.createFromAsset(assets, "fonts/arabicb.otf")
        var themp: Int
        var themp2: Int
        //- --------------     -0-          -1-         -2-          -3-          -4-        -5-         -6-          -7-         -8-           -9-           -10-     -11-     -12-         -13-       -14-
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
            "tv_adv"
        )

        //- --------------   -0-      -1-
        id2 = arrayOf("rb_all", "rb_none")
        for (i in id2.indices) {
            themp2 = resources.getIdentifier(id2[i], "id", packageName)
            radioButtons[i] = findViewById<View>(themp2) as RadioButton
        }
        for (i in id.indices) {
            themp = resources.getIdentifier(id[i], "id", packageName)
            textView[i] = findViewById<View>(themp) as TextView
        }
        spin_lang = findViewById(R.id.spinner_lang)
        spin_maddah = findViewById(R.id.spinner_maddah)
        spin_tr1 = findViewById(R.id.spinner_tr1)
        spin_tr2 = findViewById(R.id.spinner_tr2)

        //*******************************
//
        val ml = lang
        showData("lang" , langApp) // baraye lang 0 mifrestim lang$ml
        adapter_lang = SpinAdapter(
            this@Setting,
            R.layout.spinner,
            langApp
        )
        spin_lang.setAdapter(adapter_lang)
        spin_lang.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val lin = view.findViewById<View>(R.id.l_line) as LinearLayout
                lin.visibility = View.GONE
                val text = view.findViewById<TextView>(R.id.text_spin)
                text.setTextColor(Color.parseColor("#3d3e43"))
                val spinner = adapter_lang!!.getItem(position)
                lang = spinner!!.id - 1
                view.textAlignment = View.TEXT_ALIGNMENT_CENTER
                //                view.setBackgroundColor(Color.parseColor("#019595"));
                view.setBackgroundResource(R.drawable.set_bgradioselect)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        //*******************************
        showData("trl1" ,tr1)
        adapter_tr1 = SpinAdapter(
            this@Setting,
            R.layout.spinner,
            tr1
        )
        spin_tr1.setAdapter(adapter_tr1)
        spin_tr1.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val lin = view.findViewById<View>(R.id.l_line) as LinearLayout
                lin.visibility = View.GONE
                val text = view.findViewById<TextView>(R.id.text_spin)
                text.setTextColor(Color.parseColor("#3d3e43"))
                var id_tr1: Int
                view.setBackgroundResource(R.drawable.set_bgradioselect)
                val spinner = adapter_tr1!!.getItem(position)
                DataTr1 = spinner!!.trl
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                parent.setBackgroundResource(R.drawable.set_bgradio)
            }
        })
        //*******************************
        showData("trl2" ,tr2) //"trl2_$ml"
        adapter_tr2 = SpinAdapter(
            this@Setting,
            R.layout.spinner,
            tr2
        )
        spin_tr2.setAdapter(adapter_tr2)
        spin_tr2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val lin = view.findViewById<View>(R.id.l_line) as LinearLayout
                lin.visibility = View.GONE
                val text = view.findViewById<TextView>(R.id.text_spin)
                text.setTextColor(Color.parseColor("#3d3e43"))
                val spinner = adapter_tr2!!.getItem(position)
                view.setBackgroundResource(R.drawable.set_bgradioselect)
                DataTr2 = spinner!!.trl
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                parent.setBackgroundResource(R.drawable.set_bgradio)
            }
        })
        //*******************************
        showData( "name" ,maddah) //"name$ml"
        adapter_maddah = SpinAdapter(
            this@Setting,
            R.layout.spinner,
            maddah
        )
        spin_maddah.setAdapter(adapter_maddah)
        spin_maddah.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val lin = view.findViewById<View>(R.id.l_line) as LinearLayout
                val text = view.findViewById<TextView>(R.id.text_spin)
                text.setTextColor(Color.parseColor("#3d3e43"))
                lin.visibility = View.GONE
                val spinner = adapter_maddah!!.getItem(position)
                view.setBackgroundResource(R.drawable.set_bgradioselect)
                val r = spinner!!.id
                tempString=spinner!!.id

                if (r == 1) {
                    DataSong = spinner.id
                } else {
                    FileUrl = spinner.url


                    if (myFile!!.exists()) {
                        DataSong = spinner.id
                    } else {

                        if (isInternetAvailable) {
//                            Toast.makeText(Setting.this, spinner.name, Toast.LENGTH_SHORT).show();
                           // DialogDownload(spinner.id)
                               onCreateDialog()
                            DataSong = spinner.id
                            view.setBackgroundResource(R.drawable.download_maddah)
                            text.setTextColor(Color.WHITE)
                        } else {
                            Toast.makeText(this@Setting, str[14], Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
//                LinearLayout liSpin=parent.findViewById(R.id.l_spin);
//                TextView text=parent.findViewById(R.id.text_spin);
//
//                if (!myFile.exists()) {
//                    liSpin.setBackgroundResource(R.drawable.download_maddah);
//                    text.setTextColor(Color.WHITE);
//
//                } else {
//                    liSpin.setBackgroundColor(Color.parseColor("#2e2c34"));
//                }
            }
        })

        //*******************************
        set()
        setLanguage()
        laod()
        ifLoad()
       // ExistingFile()
        Internet()
        //*******************************
        radioButtons[1]!!.setOnClickListener {
            Rep = true
            radioButtons[0]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[1]!!.setTextColor(Color.WHITE)
        }
        radioButtons[0]!!.setOnClickListener {
            Rep = false
            radioButtons[1]!!.setTextColor(Color.rgb(128, 130, 142))
            radioButtons[0]!!.setTextColor(Color.WHITE)
        }
        //*******************************
        checkScroll!!.setOnClickListener {
            Scroll = checkScroll!!.isChecked
        }
        chekReview!!.setOnClickListener {
            Review = chekReview!!.isChecked
        }
        //*******************************
        textView[13]!!.setOnClickListener { res() }

        //*******************************
        chekAds!!.setOnClickListener {
            Ads = chekAds!!.isChecked
        }
        //*******************************
        chekAdvance!!.setOnClickListener {
            Pro = chekAdvance!!.isChecked
        }
        chekSeek!!.setOnClickListener {
            AutoSeek = chekSeek!!.isChecked
        }
        checkBgMp!!.setOnClickListener {
            BgMp = checkBgMp!!.isChecked
        }
        //*******************************

        //*******************************
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade)
        saveLight!!.setBackgroundResource(R.drawable.set_savelight)
        saveLight!!.startAnimation(animation)
        saveLight!!.setOnClickListener {
            sp = applicationContext.getSharedPreferences("setting", 0)
            val edit = sp.edit()
            edit.putString("DataTr1", DataTr1)
            MainActivity.DataTr1 = DataTr1
            edit.putString("DataTr2", DataTr2)
            MainActivity.DataTr2 = DataTr2
            edit.putBoolean("Scroll", Scroll)
            MainActivity.Scroll = Scroll
            edit.putBoolean("BgMp", BgMp)
            MainActivity.BgMp = BgMp
            edit.putBoolean("Pro", Pro)
            MainActivity.Pro = Pro
            edit.putBoolean("Ads", Ads)
            MainActivity.Ads = Ads
            edit.putInt("DataSong", DataSong)
            MainActivity.DataSong = DataSong
            edit.putInt("lang", lang)
            MainActivity.lang = lang
            edit.putBoolean("Rep", Rep)
            MainActivity.Rep = Rep
            edit.putBoolean("AutoSeek", AutoSeek)
            MainActivity.AutoSeek = AutoSeek
            edit.putBoolean("Review", Review)
            MainActivity.Review = Review
            edit.apply()
            Toast.makeText(applicationContext, toas, Toast.LENGTH_SHORT).show()

            startActivity(Intent(this@Setting, MainActivity::class.java))
            finish()
        }
    }

    /*-----------------------------------------------------------*/
    @SuppressLint("Range")
    private fun showData(attributed :String , value: ArrayList<Spin>) {

        value.clear()
        if(attributed=="name"){
            for (item in listLanguage) {
                if (item.name0 != null) {
                    val arrey = Spin()
                    arrey.id = item.id
                    arrey.url = item.url
                    arrey.trl = item.trl

                    when (lang) {
                        0 ->
                            arrey.name = item.name0
                    1 ->
                        arrey.name = item.name1
                    2 ->
                        arrey.name = item.name2
                    3 ->
                        arrey.name = item.name3
                }
                value.add(arrey)
            }
            }
        } else {
            for (item in listLanguage)
            {
                val arrey = Spin()
                arrey.id = item.id
                arrey.url = item.url
                arrey.trl = item.trl

                when (lang) {
                    0 -> if (attributed == "lang") {
                        arrey.name = item.lang0

                    } else if (attributed == "trl1") {
                        arrey.name = item.trl1_0

                    }
                    else if (attributed == "trl2") {
                        arrey.name = item.trl2_0

                    }
                    1 -> if (attributed == "lang") {
                        arrey.name = item.lang1
                    } else if (attributed == "trl1") {
                        arrey.name = item.trl1_1
                    }
                    else if (attributed == "trl2") {
                        arrey.name = item.trl2_1

                    }
                    2 -> if (attributed == "lang") {
                        arrey.name = item.lang2
                    } else if (attributed == "trl1") {
                        arrey.name = item.trl1_2
                    }
                    else if (attributed == "trl2") {
                        arrey.name = item.trl2_2

                    }
                    3 -> if (attributed == "lang") {
                        arrey.name = item.lang3
                    } else if (attributed == "trl1") {
                        arrey.name = item.trl1_3
                    }
                    else if (attributed == "trl2") {
                        arrey.name = item.trl2_3

                    }
                }

                value.add(arrey)




            }

        }


    }
    fun dialogDownloader (){
        pb_ralativeGone.isVisible = true
        pbarTitle.text = " Download file "
        pbarCaption.text = "you need to download file "
        perprogressBar.text = "00%"
        when (lang){
            0 -> {
                pbarTitle.text = listLang[250].translateFa.toString()
                pbarCaption.text = listLang[252].translateFa
                perprogressBar.text = ""



            }
            1 -> {
                pbarTitle.text = listLang[250].translateAr.toString()
                pbarCaption.text = listLang[252].translateAr
                perprogressBar.text = ""


            }
            2 ->  {
                pbarTitle.text = listLang[250].translateEn.toString()
                pbarCaption.text = listLang[252].translateEn
                perprogressBar.text = ""


            }
            3 ->  {
                pbarTitle.text = listLang[250].translateUr.toString()
                pbarCaption.text = listLang[252].translateUr
                perprogressBar.text = ""


            }
        }

        var  URL = listLanguage[tempString-1].url
        var name = listLanguage[tempString-1].name2
        // Log.d("!!!!", "dialogDownloader: $name , $URL")












        if (Status.RUNNING == PRDownloader.getStatus(downloadIdOne)) {
            PRDownloader.pause(downloadIdOne)

        }
        // buttonOne.isEnabled = false
        mProgressDialog.isIndeterminate = true
        mProgressDialog.indeterminateDrawable.setColorFilter(
            Color.BLUE, PorterDuff.Mode.SRC_IN
        )
        if (Status.PAUSED == PRDownloader.getStatus(downloadIdOne)) {
            PRDownloader.resume(downloadIdOne)

        }
        downloadIdOne = PRDownloader.download(URL, "/storage/emulated/0/Android/data/com.yafateme.app.yafateme/files" , name)
            .build()
            .setOnStartOrResumeListener {
                mProgressDialog.isIndeterminate = false
                // buttonOne.isEnabled = true
                // buttonOne.setText(com.downloader.R.string.pause)
                //buttonCancelOne.isEnabled = true
            }
            //.setOnPauseListener { buttonOne.setText(com.downloader.R.string.resume) }
            .setOnCancelListener {
                //  buttonOne.setText(com.downloader.R.string.start)
                // buttonCancelOne.isEnabled = false
                mProgressDialog.progress = 0
                perprogressBar.text = ""
                downloadIdOne = 0
                mProgressDialog.isIndeterminate = false
            }
            .setOnProgressListener { progress ->
                totalbytesString = progress.totalBytes.toString()
                val progressPercent = progress.currentBytes * 100 / progress.totalBytes
                mProgressDialog.progress = progressPercent.toInt()
                perprogressBar.setText(
                    Utils.getProgressDisplayLine(
                        progress.currentBytes,
                        progress.totalBytes
                    )
                )
                mProgressDialog.isIndeterminate = false
            }
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    Toast.makeText(applicationContext,"download is completed", Toast.LENGTH_LONG)
                    pb_ralativeGone.isVisible = false
                    // buttonOne.isEnabled = false
                    // buttonCancelOne.isEnabled = false
                    // buttonOne.setText(com.downloader.R.string.completed)
                }

                override fun onError(error: Error) {
                    // buttonOne.setText(com.downloader.R.string.start)
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.some_error_occurred) + " " + "1",
                        Toast.LENGTH_SHORT
                    ).show()
                    perprogressBar.text = ""
                    mProgressDialog.progress = 0
                    downloadIdOne = 0
                    //buttonCancelOne.isEnabled = false
                    // progressBarOne.isIndeterminate = false
                    //buttonOne.isEnabled = true
                }
            })

        //buttonCancelOne.setOnClickListener { PRDownloader.cancel(downloadIdOne) }






    }
    fun onCreateDialog() {
        val alert = AlertDialog.Builder(this@Setting)
        // pbarTitle.text = " Dowload file "
        // pbarCaption.text = "you need to download file "
        // perprogressBar.text = "00%"
        var pbartitleString = ""
        var pbarCaptionString = ""
        var perprogressBarString = ""
        var buttonOkString = ""
        var buttonNoString = ""
        when (lang){
            0 -> {
                pbartitleString = listLang[248].translateFa.toString()
                pbarCaptionString = listLang[249].translateFa.toString() + listLanguage[tempString-1].name0
                perprogressBarString = ""
                buttonOkString = listLang[224].translateFa.toString()
                buttonNoString = listLang[225].translateFa.toString()


            }
            1 -> {
                pbartitleString = listLang[248].translateAr.toString()
                pbarCaptionString = listLang[249].translateAr.toString()  + listLanguage[tempString-1].name0
                perprogressBarString = ""
                buttonOkString = listLang[224].translateAr.toString()
                buttonNoString = listLang[225].translateAr.toString()

            }
            2 ->  {
                pbartitleString = listLang[248].translateEn.toString()
                pbarCaptionString = listLang[249].translateEn.toString() + listLanguage[tempString-1].name2
                perprogressBarString = ""
                buttonOkString = listLang[224].translateEn.toString()
                buttonNoString = listLang[225].translateEn.toString()

            }
            3 ->  {
                pbartitleString = listLang[248].translateUr.toString()
                pbarCaptionString = listLang[249].translateUr.toString() + listLanguage[tempString-1].name0
                perprogressBarString = ""
                buttonOkString = listLang[224].translateUr.toString()
                buttonNoString = listLang[225].translateUr.toString()

            }
        }

        alert.setTitle(pbartitleString)
        alert.setMessage(
            """
                ${pbarCaptionString}
                
                
                """.trimIndent()
        )
        alert.setPositiveButton(
            buttonOkString
        ) { dialog, which ->
            dialogDownloader()



/*
            Toast.makeText(
                applicationContext,
                """
                    ${buttonNoString}

                    """.trimIndent(),
                Toast.LENGTH_SHORT
            ).show()

 */

            dialog.dismiss()
        }
        alert.setNegativeButton(
            buttonNoString
        ) { dialog, which -> dialog.dismiss()

        }
        alert.show()


    }

    /*-----------------------------------------------------------*/
    fun set() {
        checkScroll = findViewById<View>(R.id.check_scroll) as CheckBox
        checkBgMp = findViewById<View>(R.id.check_bgmp) as CheckBox
        chekSeek = findViewById<View>(R.id.check_seektest) as CheckBox
        chekReview = findViewById<View>(R.id.check_review) as CheckBox
        chekAdvance = findViewById<View>(R.id.check_advanc_mod) as CheckBox
        chekAds = findViewById<View>(R.id.check_adv) as CheckBox
        saveLight = findViewById<View>(R.id.save_light) as ImageButton
    }

    /*-----------------------------------------------------------*/
    fun setLanguage() {
       // db!!.open()
        for (i in 0..14) {
            when(lang){
                0 ->  str[i]= listLang[i+ 248].translateFa
                1 ->  str[i] = listLang[i+ 248].translateAr
                2 ->  str[i] = listLang[i+ 248].translateEn
                3 ->  str[i] = listLang[i+ 248].translateUr
            }

        }
        MoveM = str[5]
        enteqalFaraz = str[6]
        toasDialog = str[7]
        when(lang){
            0 -> {
                noBtn = listLang[225].translateFa
                yesBtn = listLang[224].translateFa
                toas = listLang[14].translateFa
            }
            1 -> {
                noBtn = listLang[225].translateAr
                yesBtn = listLang[224].translateAr
                toas = listLang[14].translateAr
            }
            2 -> {
                noBtn = listLang[225].translateEn
                yesBtn = listLang[224].translateEn
                toas = listLang[14].translateEn
            }
            3 -> {
                noBtn = listLang[225].translateUr
                yesBtn = listLang[224].translateUr
                toas = listLang[14].translateUr
            }
        }


        textView[13]!!.typeface = fontArabic
        for (i in id2.indices) {
            radioButtons[i]!!.typeface = fontArabic
            when(lang){
                0 ->  radioButtons[i]!!.text= listLang[i+ 56].translateFa
                1 ->  radioButtons[i]!!.text = listLang[i+ 56].translateAr
                2 ->  radioButtons[i]!!.text = listLang[i+ 56].translateEn
                3 ->  radioButtons[i]!!.text = listLang[i+ 56].translateUr
            }
        }
        for (i in id.indices) {
            textView[i]!!.typeface = fontArabic
            when(lang){
                0 ->  textView[i]!!.text= listLang[i+ 79].translateFa
                1 ->  textView[i]!!.text = listLang[i+ 79].translateAr
                2 ->  textView[i]!!.text = listLang[i+ 79].translateEn
                3 ->  textView[i]!!.text = listLang[i+ 79].translateUr
            }
        }
        if (MainActivity.lang === 2) {
            for (i in id2.indices) {
                radioButtons[i]!!.typeface = fontmyredL
            }
            for (i in id.indices) {
                textView[i]!!.typeface = fontmyredM
            }
            textView[13]!!.typeface = fontmyredL
        }
       // db!!.close()
    }

    /*-----------------------------------------------------------*/
    private fun laod() {
        sp = applicationContext.getSharedPreferences("setting", 0)
        val bool3 = sp.getBoolean("Scroll", true)
        Scroll = bool3

        val bool7 = sp.getBoolean("Rep", true)
        Rep = bool7

        val bool6 = sp.getBoolean("BgMp", true)
        BgMp = bool6

        val bool4 = sp.getBoolean("AutoSeek", true)
        AutoSeek = bool4

        val bool5 = sp.getBoolean("Review", true)
        Review = bool5

        val bool2 = sp.getBoolean("Pro", true)
        Pro = bool2

        val bool1 = sp.getBoolean("Ads", true)
        Ads = bool1

        val tr11 = sp.getString("DataTr1", "none")
        DataTr1 = tr11
        var tr1_name: String? = ""
        for (i in tr1.indices) {
            tr1_name = tr1[i].trl
            if (tr11 == tr1_name) {
                DataTr1 = tr1_name
            }
        }
        val tr22 = sp.getString("DataTr2", "none")
        DataTr2 = tr22
        var tr2_name: String? = ""
        for (i in tr2.indices) {
            tr2_name = tr2[i].trl
            if (tr22 == tr2_name) {
                DataTr2 = tr2_name
            }
        }
        val l = sp.getInt("lang", 2)
        lang = l
        for (i in langApp.indices) {
            if (l == langApp[i].id) {
                lang = langApp[i].id
            }
        }
        val ds = sp.getInt("DataSong", 1)
        DataSong = ds
        if (ds == 1) {
           // DataSong = 1
           // var  maddahUrl = ""
            //maddahUrl = listLanguage[ds - 1 ].name2.toString()
           // Log.d("testURL", "laod: $maddahUrl va meghdare $DirS")

        } else {
            for (i in 1 until maddah.size) {
                if (ds == maddah[i].id) {
                   var  maddahUrl = ""

                          maddahUrl = listLanguage[ds - 1 ].name2.toString()
                    Log.d("testURL", "laod: $maddahUrl")

                    myFile = File(DirS + maddahUrl + ".mp3")
                    Log.d("testURL", "laod: test myfile $myFile")

                    if (!myFile!!.exists()) {
                        DataSong = 1
                        MainActivity.DataSong = 1
                    } else {
                        DataSong = maddah[i].id
                    }
                }
            }
        }
    }
    /*-----------------------------------------------------------*/
    fun ifLoad() {
        var spin: Int
        for (i in langApp.indices) {
            if (MainActivity.lang === langApp[i].id) {
                spin = adapter_lang!!.getItem(i)!!.id
                spin_lang!!.setSelection(spin)
            }
        }
        var tr1_name: String? = ""
        for (i in tr1.indices) {
            tr1_name = tr1[i].trl
            if (MainActivity.DataTr1.equals(tr1_name)) {
                spin = adapter_tr1!!.getItem(i)!!.id
                spin_tr1!!.setSelection(spin - 1)
                DataTr1 = tr1_name
            }
        }
        var tr2_name: String? = ""
        for (i in tr2.indices) {
            tr2_name = tr2[i].trl
            if (MainActivity.DataTr2.equals(tr2_name)) {
                spin = adapter_tr2!!.getItem(i)!!.id
                spin_tr2!!.setSelection(spin - 1)
            }
        }
        for (i in maddah.indices) {
            if (MainActivity.DataSong === maddah[i].id) {
                spin = adapter_maddah!!.getItem(i)!!.id
                spin_maddah!!.setSelection(spin - 1)
            }
        }
        if (MainActivity.Scroll) {
            checkScroll!!.isChecked = true
            checkScroll!!.setTextColor(Color.WHITE)
        } else {
            checkScroll!!.isChecked = false
        }
        if (MainActivity.BgMp) {
            checkBgMp!!.isChecked = true
            checkBgMp!!.setTextColor(Color.WHITE)
        } else {
            checkBgMp!!.isChecked = false
        }
        if (MainActivity.AutoSeek) {
            chekSeek!!.isChecked = true
            chekSeek!!.setTextColor(Color.WHITE)
        } else {
            chekSeek!!.isChecked = false
        }
        if (MainActivity.Rep) {
            radioButtons[1]!!.isChecked = true
            radioButtons[1]!!.setTextColor(Color.WHITE)
        } else {
            radioButtons[0]!!.isChecked = true
            radioButtons[0]!!.setTextColor(Color.WHITE)
        }
        if (MainActivity.Review) {
            chekReview!!.isChecked = true
            chekReview!!.setTextColor(Color.WHITE)
        } else {
            chekReview!!.isChecked = false
        }
        if (MainActivity.Pro) {
            chekAdvance!!.isChecked = true
            chekAdvance!!.setTextColor(Color.WHITE)
        } else {
            chekAdvance!!.isChecked = false
        }
        if (MainActivity.Ads) {
            chekAds!!.isChecked = true
            chekAds!!.setTextColor(Color.WHITE)
        } else {
            chekAds!!.isChecked = false
        }
    }

    /*-----------------------------------------------------------*/


    /*-----------------------------------------------------------*/
    fun Internet() {
        if (!isInternetAvailable) {
            Toast.makeText(this, str[14], Toast.LENGTH_LONG).show()
        }
    }

    /*-----------------------------------------------------------*/
    val isInternetAvailable: Boolean
        get() {
            val connectivity = G.context
                ?.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivity == null) {
                return false
            } else {
                val info = connectivity.allNetworkInfo
                if (info != null) {
                    for (i in info.indices) {
                        if (info[i].state == NetworkInfo.State.CONNECTED) {
                            return true
                        }
                    }
                }
            }
            return false
        }

    /*-----------------------------------------------------------*/


    /*-----------------------------------------------------------*/
    fun res() {
        val alert = AlertDialog.Builder(this@Setting)
        alert.setTitle(MoveM)
        alert.setMessage(enteqalFaraz)
        alert.setPositiveButton(
            yesBtn
        ) { dialog, which ->
            Toast.makeText(applicationContext, toasDialog, Toast.LENGTH_SHORT).show()
            sp = applicationContext.getSharedPreferences("setting", 0)
            val edit = sp.edit()
            edit.putString("DataTr1", "none")
            edit.putString("DataTr2", "none")
            edit.putBoolean("AutoSeek", true)
            edit.putBoolean("BgMp", true)
            edit.putBoolean("Pro", true)
            edit.putBoolean("Ads", true)
            edit.putBoolean("Scroll", true)
            edit.putInt("lang", 2)
            edit.putBoolean("Rep", true)
            edit.putInt("DataSong", 1)
            edit.putBoolean("Review", true)
            edit.putString("fontAr", "suls")
            edit.putString("fontfa", "arabic")
            edit.putString("fonten", "gothic")
            edit.putString("fontur", "palatino")
            edit.putInt("ArabicSize", 18)
            edit.putInt("FaSize", 18)
            edit.putInt("EnSize", 18)
            edit.putInt("UrSize", 18)
            edit.putInt("ColorAr", 1)
            edit.putInt("ColorFa", 2)
            edit.putInt("ColorEn", 3)
            edit.putInt("ColorUr", 4)
            edit.putInt("space", 0)
            edit.putString("mood", "day")
            edit.putString("ShadowMod", "true")
            edit.putString("chekTuts", "false")
            edit.apply()
            Toast.makeText(applicationContext, toas, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@Setting, StarterActivity::class.java))
            finish()
            dialog.dismiss()
        }
        alert.setNegativeButton(
            noBtn
        ) { dialog, which -> dialog.dismiss() }
        alert.show()
    }

    /*-----------------------------------------------------------*/
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(Intent(this@Setting, MainActivity::class.java))
            showInterstitial()
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    /*-----------------------------------------------------------*/
    private fun showInterstitial() {}

    /*-----------------------------------------------------------*/
    fun finish(view: View?) {
        showInterstitial()
        finish()
    }

    /*-----------------------------------------------------------*/

    @OptIn(InternalCoroutinesApi::class)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSION -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, str[12], Toast.LENGTH_LONG).show()
                    val dir = File(DirS)
                    try {
                        dir.mkdir()
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(this@Setting, str[13], Toast.LENGTH_LONG).show()
                    }
                    //this is the file url to download
                    //MyViewModel().execute(FileUrl)
                    //DownloadTask().execute(FileUrl) فراخانی تابع باید اصلاح شود
                } else {
                    Toast.makeText(this, str[11], Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    /*-----------------------------------------------------------*/















    companion object {
        var langApp = ArrayList<Spin>()
      //  lateinit var db: database
        lateinit var mydb: SQLiteDatabase

        var tr1 = ArrayList<Spin>()
        var tr2 = ArrayList<Spin>()
        var maddah = ArrayList<Spin>()
        var Ads = false
        var Pro = false
        var Scroll = false
        var AutoSeek = false
        var Review = false
        var BgMp = false
        var Rep = false
        var fontmyredL: Typeface? = null
        var fontarabicB: Typeface? = null
        private const val MY_PERMISSION = 1
        var myFile: File? = null
        var totalbytesString = ""
    }
}
