package com.yafateme.app.yafateme

import android.content.ClipData
import android.content.DialogInterface
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SmoothScroller
import com.yafateme.app.yafateme.MainActivity.Companion.DataSong
import com.yafateme.app.yafateme.MainActivity.Companion.DirS
import com.yafateme.app.yafateme.MainActivity.Companion.Review
import com.yafateme.app.yafateme.MainActivity.Companion.lang

import com.yafateme.app.yafateme.MainActivity.Companion.xcount
import com.yafateme.app.yafateme.Setting.Companion.maddah
import com.yafateme.app.yafateme.Splash.Companion.listDoa
import com.yafateme.app.yafateme.Splash.Companion.listLang
import com.yafateme.app.yafateme.Splash.Companion.listLanguage
import com.yafateme.app.yafateme.Splash.Companion.listNumber
import com.yafateme.app.yafateme.Splash.Companion.madahList
import com.yafateme.app.yafateme.Splash.Companion.madhList
import java.io.File
import java.util.*

open class Dua : AppCompatActivity(), MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener {
    var chanelTamalakot: String? =null
    var shareFa:kotlin.String? = null
    var shareEn:kotlin.String? = null
    var shareAr:kotlin.String? = null
    var shareUr:kotlin.String? = null

    private val menuItems: ArrayList<Item> = ArrayList<Item>()
    private var adapter: AdapterDuaPage? = null
    var AdvLi: LinearLayout? = null
    private lateinit var memory: SharedPreferences
   // //private var db: database? = null
    var xcount = MainActivity.xcount
    private val conter = arrayOfNulls<String>(xcount)
    private val strar = arrayOfNulls<String>(xcount)
    var SaveKhotbe: ImageButton? = null
    lateinit var backBtn:ImageButton
    private  var btnRepeat:ImageButton? = null
    private var isRepeat = false
    private var isShuffle = false
    var saveInt:Int = 0
    var findFirstVisibleItemPosition = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        if (MainActivity.mood.equals("day")) {
            setContentView(R.layout.activity_dua2)
        } else {
            setContentView(R.layout.activity_dua1)
        }
        recDuaPage = findViewById(R.id.rec_duapage) // ریسایکلر ویو صفحه دعا
        backBtn = findViewById<ImageButton>(R.id.fave_icon)
        adapter = AdapterDuaPage(this@Dua, menuItems)
        recDuaPage.setLayoutManager(LinearLayoutManager(this))
        recDuaPage.setAdapter(adapter)


        val myFile = File(DirS + "farahmand.mp3")
        val myFile2 = File(DirS + "samavati.mp3")


        SaveKhotbe = findViewById<View>(R.id.save_khotbe) as ImageButton
        play = findViewById<View>(R.id.btn_play) as ImageButton
        pl = findViewById<View>(R.id.btn_pl) as ImageButton
        AdvLi = findViewById<View>(R.id.linearLayoutThatDoesNotScroll) as LinearLayout
        //--------------------Disable advertising
        if (!MainActivity.Ads) {
            AdvLi!!.visibility = View.GONE
        }
        btnRepeat = findViewById<View>(R.id.btnRepeat) as ImageButton /**/
        utils = Utilities()
        songProgressBar = findViewById<View>(R.id.songProgressBar) as SeekBar
        songProgressBar!!.setOnSeekBarChangeListener(this as OnSeekBarChangeListener)
        //----------------sharperrerences
        memory = applicationContext.getSharedPreferences("setting", 0)
        PosLong = memory.getInt("RowPos", 2)
        LangChanger()
        conterMP()
        //-----------------load-data
        Toast.makeText(applicationContext, Str[20], Toast.LENGTH_LONG).show()
        pl!!.setBackgroundResource(R.drawable.famehrab)
        play!!.setBackgroundResource(R.drawable.panmehrab)
        btnRepeat!!.setBackgroundResource(R.drawable.re_offmehrab)
        val animationTarget = findViewById<View>(R.id.btn_play) as ImageView
        val animation = AnimationUtils.loadAnimation(this, R.anim.player)
        animationTarget.startAnimation(animation)
        SaveKhotbe!!.setOnClickListener {
            val myAnimShake =
                AnimationUtils.loadAnimation(this@Dua, R.anim.shake2)
            val interpolator = MyBounceInterpolator(0.2, 30.0)
            myAnimShake.interpolator = interpolator
            SaveKhotbe!!.startAnimation(myAnimShake)
            val alert = AlertDialog.Builder(this@Dua)
            alert.setTitle(Str[15])
            alert.setMessage(
                """
                ${Str[8].toString()}
                ${Str[6]}$PosLong
                
                """.trimIndent()
            )
            alert.setPositiveButton(
                Str[18]
            ) { dialog, which ->
                Toast.makeText(
                    applicationContext,
                    """
                    ${Str[17].toString()}
                    ${Str[6]}$PosLong
                    """.trimIndent(),
                    Toast.LENGTH_SHORT
                ).show()
                mp!!.seekTo(ints[PosLong])
                if (mp!!.isPlaying) {
                } else {
                    PlaySong()
                }
                dialog.dismiss()
            }
            alert.setNegativeButton(
                Str[16]
            ) { dialog, which -> dialog.dismiss() }
            alert.show()
        }
        backBtn.setOnClickListener {
            onBackPressed()
        }

        // ---------------------------Ar text Font, Size and Color
        for (item in 0..xcount-2) {

            val array = Item()
            var doaObject = listDoa[item]
            var nuObject = listNumber[item]
           // for (item in listDoa) {

                if (doaObject.id == item+1) {

                        array.text_ar = doaObject.text.toString()

                    //   for( i in listNumber){
                           if (lang == 2) {
                               array.text_num = nuObject.en
                           }else {
                               array.text_num = nuObject.fa
                       }

                }
                if (MainActivity.DataTr1.equals("trFa")) {
                    array.text_fa = doaObject.translateFa
                }else if (MainActivity.DataTr1.equals("trEn")){
                    array.text_fa = doaObject.translateEn
                }else if (MainActivity.DataTr1.equals("trUr")){
                    array.text_fa = doaObject.translateUrdu
                }
                if (MainActivity.DataTr2.equals("trFa")) {
                    array.text_en = doaObject.translateFa
                }else if (MainActivity.DataTr2.equals("trEn")){
                    array.text_en = doaObject.translateEn
                }else if (MainActivity.DataTr2.equals("trUr")){
                    array.text_en = doaObject.translateUrdu
                }

                if (item == 0) {
                    if (Review) {
                        when (lang){
                            0 ->  array.text_fa = doaObject.translateFa
                            1 ->  array.text_fa = doaObject.text
                            2 ->  array.text_fa = doaObject.translateEn
                            3 ->  array.text_fa = doaObject.translateUrdu
                        }

                    }
                }



            menuItems.add(array)
        }
    }

    fun onClickb(v: View?) {
        if (isRepeat) {
            oneRp = "false"
            mp!!.isLooping = false
            btnRepeat?.setBackgroundResource(R.drawable.re_offmehrab)
            isRepeat = false
            Toast.makeText(applicationContext, Str[11], Toast.LENGTH_SHORT).show()
        } else {
            oneRp = "true"
            isRepeat = true
            isShuffle = false
            btnRepeat?.setBackgroundResource(R.drawable.remehrab)
            mp!!.isLooping = true
            isRepeat = true
            Toast.makeText(applicationContext, Str[12], Toast.LENGTH_SHORT).show()
        }
    }

    fun onClick(v: View?) {
        // TODO Auto-generated method stub
        if (mp!!.isPlaying) {
            PauseSong()
        } else {
            PlaySong()
        }
    }


    fun PauseSong() {
        mp!!.pause()
        pl?.setBackgroundResource(R.drawable.famehrab)
        play!!.setBackgroundResource(R.drawable.panmehrab)
        val animationTarget: ImageView? = play
        val animation = AnimationUtils.loadAnimation(this, R.anim.player)
        animationTarget!!.startAnimation(animation)
    }







    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        mHandler.removeCallbacks(mUpdateTimeTask)
    }
    override fun onStopTrackingTouch(seekBar: SeekBar) {
     Dua.mHandler.removeCallbacks(Dua.mUpdateTimeTask)
        val totalDuration: Int = Dua.mp!!.getDuration()
        val currentPosition: Int =
            Dua.utils!!.progressToTimer(seekBar.progress, totalDuration)
      Dua.mp!!.seekTo(currentPosition)
       Dua.updateProgressBar()
    }

    override fun onCompletion(mp: MediaPlayer?) {
        if (isRepeat) {
        } else if (isShuffle) {
            val rand = Random()
        } else {
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mp!!.pause()
            showInterstitial()
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }



    fun LangChanger() {

        chanelTamalakot = getString(R.string.chanel_tamalakot)

        for ( item in 150..170)
        {
            var stri = listLang[item]

            when (lang){

                0 -> Str[item-150] = stri.translateFa

                1 ->Str[item-150] = stri.translateAr

                2 -> Str[item-150] = stri.translateEn

                3 -> Str[item-150] = stri.translateUr
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (!MainActivity.BgMp) {
            if (mp!!.isPlaying) {
                PauseSong()
            }
        }
    }

    fun finish(view: View?) {
        mp!!.pause()
        showInterstitial()
        super.finish()
    }

    private fun showInterstitial() {}


   fun conterMP() {
       if (DataSong === 1) {
            mp = MediaPlayer.create(
                this, resources.getIdentifier(
                    getString(R.string.rawString1), "raw",
                    packageName
                )
            )
        } else {
           mp = MediaPlayer.create(this,Uri.parse(DirS + "farahmand.mp3")                    )

/*
           for (i in 1 until maddah.size) {
                var maddahStr : String
                for (item in listLanguage){
                 //  Log.d("#####", "conterMP: ${item.name2}")
                    if (DataSong === maddah.get(i).id) {
                        maddahStr = item.name2.toString()



                        mp = MediaPlayer.create(this,Uri.parse(DirS + "farahmand.mp3")                    )


                    }

                }

            }
           */
        }
        for (i in 1 until xcount) {
            for (item in madhList){
               when(DataSong){
                   0 -> ints[i] = item.song1!!
                   1-> ints[i] = item.song2!!
                   2-> ints[i] = item.song3!!
                  // 3-> ints[i] = item.song1!!
               }
                boll[i] = 0

            }
        }


    }

    override fun onBackPressed() {
        mp!!.pause()
        showInterstitial()
        finish()

    }


    companion object {
        var boll = IntArray(xcount)


        lateinit var recDuaPage: RecyclerView

        private var songProgressBar: SeekBar? = null
        var PosLong = 0
        var alert: AlertDialog.Builder? = null
        var Str = arrayOfNulls<String>(21)
        var stringArreyDua = Spin()

        var dialog: AlertDialog? = null
        lateinit var mp: MediaPlayer
        var ints = IntArray(xcount)
        private val mHandler = Handler()
        lateinit var  pl:ImageButton
        private var play: ImageButton? =null
        var oneRp:kotlin.String? = "false"
        var numShowe = 0
        private var utils: Utilities? = null

        fun falsBoll() {
            for (i in 1 until xcount) {
                boll[i] = 0
            }

        }
        fun toastShowe() {


            Toast.makeText(G.context,"$numShowe", Toast.LENGTH_SHORT).show()
        }
        fun updateProgressBar() {
            mHandler.postDelayed(mUpdateTimeTask, 100)
        }
        private val mUpdateTimeTask: Runnable = object : Runnable {
            override fun run() {
                val totalDuration = mp!!.duration.toLong()
                val currentDuration = mp!!.currentPosition.toLong()
                val checklistint: Int
                val lm = recDuaPage!!.layoutManager as LinearLayoutManager?
                val smoothScroller: SmoothScroller = object : LinearSmoothScroller(G.context) {
                    override fun getVerticalSnapPreference(): Int {
                        return SNAP_TO_START
                    }
                }
                checklistint = lm!!.findFirstVisibleItemPosition()
                smoothScroller.targetPosition = checklistint

                if (MainActivity.Rep) {
                    if (oneRp == "true") {
                        for (i in 1 until xcount - 1) {
                            if (currentDuration >= ints[i + 1] - 500 && currentDuration < ints[i + 1]) {
                                mp!!.seekTo(ints[i])
                                if (boll[i] == 0) {
                                    falsBoll()
                                    numShowe = 2
                                    boll[i] = 1
                                    toastShowe()
                                } else if (boll[i] == 1) {
                                    numShowe = numShowe + 1
                                    toastShowe()
                                }
                            }
                        }
                    }
                }
                if (MainActivity.Scroll) {
                    if (mp!!.isPlaying) {
                        val duration = 300
                        val offset = 0
                        for (i in 1 until xcount) {
                            if (currentDuration >= ints[i] && currentDuration < ints[i + 1]) {
                                if (checklistint == i) {
                                } else {
                                    if (MainActivity.AutoSeek) {
                                        recDuaPage!!.smoothScrollToPosition(i)
                                    } else {
                                        recDuaPage!!.smoothScrollToPosition(i)
                                    }
                                }
                            }
                        }
                    }
                }
                val progress = utils?.getProgressPercentage(currentDuration, totalDuration) as Int
                songProgressBar!!.progress = progress
                mHandler.postDelayed(this, 100)
            }
        }


        fun  PlaySong() {
            songProgressBar!!.progress = 0
            songProgressBar!!.max = 100
            pl?.setBackgroundResource(R.drawable.fa0mehrab)
            play!!.setBackgroundResource(R.drawable.pan0mehrab)
            val animationTargetp: ImageView? = play
            val animationp = AnimationUtils.loadAnimation(G.context, R.anim.player0)
            animationTargetp!!.startAnimation(animationp)
            Toast.makeText(G.context, Companion.Str[13], Toast.LENGTH_SHORT).show()
            Companion.mp!!.setOnCompletionListener(object : OnCompletionListener {
                override fun onCompletion(mp: MediaPlayer) {
                    performOnEnd()
                }

                private fun performOnEnd() {
                    pl?.setBackgroundResource(R.drawable.famehrab)
                    play!!.setBackgroundResource(R.drawable.panmehrab)
                    val animationTarget: ImageView? = play
                    val animation = AnimationUtils.loadAnimation(G.context, R.anim.player)
                    animationTarget!!.startAnimation(animation)
                }
            })
            updateProgressBar()
            Companion.mp!!.start()
        }


    }


}

