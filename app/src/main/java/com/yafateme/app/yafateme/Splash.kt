package com.yafateme.app.yafateme
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.room.Room
import com.yafateme.app.yafateme.mydb.*
import com.yafateme.app.yafateme.mydb.Number
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
class Splash : Activity(), CoroutineScope {
    private lateinit var job : Job
    private lateinit var db : AppDatabase
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private lateinit var sp: SharedPreferences

    private val linearLayout = arrayOfNulls<LinearLayout>(7)
    private val anim = arrayOfNulls<Animation>(7)
    private lateinit var id: Array<String?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.setBackgroundResource(android.R.color.transparent)
        setContentView(R.layout.activity_splash)
        job = Job()
        sp = applicationContext.getSharedPreferences("setting", 0)
        chekTuts = sp.getString("chekTuts", "true")
        //getSupportActionBar().hide();
        db =  Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db")
            .createFromAsset("database/database.db")
            .fallbackToDestructiveMigration()
            .build()
        val list = loadAllAbout()

        val madahList = LoadAllMadahsong()

        val doaList = loadAllDoa()

        val langList = loadAllLang()

        val langAppList = loadAllLangApp()

        val languageList = loadAllLanguage()
        val numberList = loadAllNumber()
        launch {
            itemsList = list.await()
            madhList = madahList.await()
            listDoa = doaList.await()
            listLang = langList.await()
            Log.d("listlangcounter", "onCreate: ${listLang.count()}")
            listLangApp = langAppList.await()
            listLanguage = languageList.await()
            listNumber = numberList.await()
        }





        var themp: Int
        //- --------------    -0-        -1-         -2-          -3-          -4-         -5-          -6-           -7-
        id = arrayOf(
            "anim_alfa1",
            "anim_alfa2",
            "anim_alfa3",
            "anim_alfa4",
            "anim_alfa5",
            "anim_alfa6",
            "anim_alfa7"
        )
        for (i in id.indices) {
            themp = resources.getIdentifier(id[i], "id", packageName)
            linearLayout[i] = findViewById<View>(themp) as LinearLayout
        }
        animLogo()
        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                if (chekTuts == "true") {
                    val TutsIntent = Intent(this@Splash, StarterActivity::class.java) //StaterActivity
                    TutsIntent.putExtra("IntPos", 0)
                    startActivity(TutsIntent)
                    finish()
                } else if (chekTuts == "false") {
                    val MainIntent = Intent(this@Splash, MainActivity::class.java)
                    startActivity(MainIntent)
                    finish()
                }
            }
        }.start()
    }
    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
    fun LoadAllMadahsong() : Deferred<List<MadahSong>> =
        async(Dispatchers.IO) {
            db.madahSongDao().getAll()
        }

    fun loadAllDoa() : Deferred<List<Doa>> =
        async(Dispatchers.IO) {
            db.doaDao().getAll()
        }
    fun loadAllLang() : Deferred<List<Lang>> =
        async(Dispatchers.IO) {
            db.langDao().getAll()
        }
    fun loadAllLanguage() : Deferred<List<Language>> =
        async(Dispatchers.IO) {
            db.languageDao().getAll()
        }
    fun loadAllNumber() : Deferred<List<Number>> =
        async(Dispatchers.IO) {
            db.numberDao().getAll()
        }
    fun loadAllAbout() : Deferred<List<About>> =
        async(Dispatchers.IO) {
            db.aboutDao().getAll()
        }
    fun loadAllLangApp() : Deferred<List<LangApp>> =
        async(Dispatchers.IO) {
            db.langAppDao().getAll()
        }
    fun animLogo() {
        object : CountDownTimer(10, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                val animat1 = AnimationUtils.loadAnimation(G.context, R.anim.scale_splash)
                linearLayout[0]!!.startAnimation(animat1)
                object : CountDownTimer(100, 1000) {
                    override fun onTick(millisUntilFinished: Long) {}
                    override fun onFinish() {
                        val animat2 = AnimationUtils.loadAnimation(G.context, R.anim.scale_splash)
                        linearLayout[1]!!.startAnimation(animat2)
                        object : CountDownTimer(100, 1000) {
                            override fun onTick(millisUntilFinished: Long) {}
                            override fun onFinish() {
                                linearLayout[0]!!.clearAnimation()
                                val animat3 =
                                    AnimationUtils.loadAnimation(G.context, R.anim.scale_splash)
                                linearLayout[2]!!.startAnimation(animat3)
                                object : CountDownTimer(100, 1000) {
                                    override fun onTick(millisUntilFinished: Long) {}
                                    override fun onFinish() {
                                        linearLayout[1]!!.clearAnimation()
                                        val animat4 = AnimationUtils.loadAnimation(
                                            G.context,
                                            R.anim.scale_splash
                                        )
                                        linearLayout[3]!!.startAnimation(animat4)
                                        object : CountDownTimer(100, 1000) {
                                            override fun onTick(millisUntilFinished: Long) {}
                                            override fun onFinish() {
                                                linearLayout[2]!!.clearAnimation()
                                                val animat5 = AnimationUtils.loadAnimation(
                                                    G.context,
                                                    R.anim.scale_splash
                                                )
                                                linearLayout[4]!!.startAnimation(animat5)
                                                object : CountDownTimer(100, 1000) {
                                                    override fun onTick(millisUntilFinished: Long) {}
                                                    override fun onFinish() {
                                                        linearLayout[3]!!.clearAnimation()
                                                        val animat6 = AnimationUtils.loadAnimation(
                                                            G.context,
                                                            R.anim.scale_splash
                                                        )
                                                        linearLayout[5]!!.startAnimation(animat6)
                                                        object : CountDownTimer(100, 1000) {
                                                            override fun onTick(millisUntilFinished: Long) {}
                                                            override fun onFinish() {
                                                                linearLayout[4]!!.clearAnimation()
                                                                val animat7 =
                                                                    AnimationUtils.loadAnimation(
                                                                        G.context,
                                                                        R.anim.scale_splash
                                                                    )
                                                                linearLayout[6]!!.startAnimation(
                                                                    animat7
                                                                )
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
                                                                                        100,
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
    companion object {
        var chekTuts: String? = null
        lateinit var itemsList : List<About>
        lateinit var madhList : List<MadahSong>
        lateinit var listDoa : List<Doa>
        lateinit var listLang : List<Lang>
        lateinit var listLangApp : List<LangApp>
        lateinit var listLanguage : List<Language>
        lateinit var listNumber : List<Number>
        lateinit var madahList : List<MadahSong>
    }
}