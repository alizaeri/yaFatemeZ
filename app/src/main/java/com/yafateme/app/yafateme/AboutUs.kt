package com.yafateme.app.yafateme

import android.app.Activity
import android.graphics.Typeface
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.yafateme.app.yafateme.Splash.Companion.itemsList


class AboutUs : Activity() {
    var manageTv: TextView? = null
    var managerEnTi: TextView? = null
    var managerFaTi: TextView? = null
    var tvGUIt: TextView? = null
    var tvGUIEn1: TextView? = null
    var tvGUIEn2: TextView? = null
    var tvGUIFa1: TextView? = null
    var tvGUIFa2: TextView? = null
    var tvDevelTi: TextView? = null
    var tvDevelEn1: TextView? = null
    var tvDevelEn2: TextView? = null
    var tvDevelFa1: TextView? = null
    var tvDevelFa2: TextView? = null
    var tvCont: TextView? = null
    var tvContEn1: TextView? = null
    var tvContEn2: TextView? = null
    var tvContEn3: TextView? = null
    var tvContEn4: TextView? = null
    var tvContFa1: TextView? = null
    var tvContFa2: TextView? = null
    var tvContFa3: TextView? = null
    var tvContFa4: TextView? = null
    var tvPane: TextView? = null
    var tvPaneEn: TextView? = null
    var tvPaneFa: TextView? = null
    var tvEmail: TextView? = null
    var tvInfo: TextView? = null
    var TvUrl: TextView? = null
    var TVPan1: TextView? = null
    var TVPan2: TextView? = null
    var tvDevelFa3: TextView? = null
    var tvDevelEn3: TextView? = null
    var tvCopyen: TextView? = null
    var tvCopyfa: TextView? = null
    private lateinit var id: Array<String>
    private val textView = arrayOfNulls<TextView>(32)
   // private var db: database? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_about_us)
        val fontmyredL = Typeface.createFromAsset(assets, "fonts/myriadl.ttf")
        val fontmyredM = Typeface.createFromAsset(assets, "fonts/myriadm.ttf")
        val fontArabicb = Typeface.createFromAsset(assets, "fonts/arabicb.otf")
        var themp: Int
        var themp2: Int
        //- --------------   -0-     -1-        -2-        -3-          -4-       -5-       -6-        -7-         -8-          -9-       -10-      -11-
        id = arrayOf(
            "copyen",
            "copyfa",
            "rvmanager",
            "tvconten1",
            "tvcontra1",
            "rvgui",
            "tvgui_en1",
            "tvgui_fa1",
            "tvgui_en2",
            "tvgui_fa2",
            "rvdevl",
            "tvdevl_en1" //-   -12-        -13-        -14-         -15-          -16-         -17-      -18-         -19-         -20-          -21-        -22-         -23-
            ,
            "tvdevl_en2",
            "tvdevl_fa1",
            "tvdevl_fa2",
            "tvdev3_fa1",
            "tvdev3_en1",
            "rv_cont",
            "tvcont_en1",
            "tvcont_en2",
            "tvcont_en3",
            "tvcont_en4",
            "tvcont_fa1",
            "tvcont_fa2",  //--  -24-        -25-        -26-       -27-        -28-       -29-       -30-      -31-        -32-        -33-
            "tvcont_fa3",
            "tvcont_fa4",
            "tv_pane",
            "tv_paneen",
            "tv_panefa",
            "tv_email",
            "tv_info",
            "tv_url"
        )
        for (item in id.indices) {
            themp2 = resources.getIdentifier(id[item], "id", packageName)
            textView[item] = findViewById<View>(themp2) as TextView
            textView[item]!!.setTypeface(fontmyredM)
            textView[item]!!.text = itemsList[item].dbText
        }
        textView[2]!!.setTypeface(fontmyredL)
        textView[4]!!.setTypeface(fontArabicb)
        textView[5]!!.setTypeface(fontmyredL)
        textView[7]!!.setTypeface(fontArabicb)
        textView[9]!!.setTypeface(fontArabicb)
        textView[10]!!.setTypeface(fontmyredL)
        textView[13]!!.setTypeface(fontArabicb)
        textView[15]!!.setTypeface(fontArabicb)
        textView[14]!!.setTypeface(fontArabicb)
        textView[17]!!.setTypeface(fontmyredL)
        textView[0]!!.setTypeface(fontmyredL)
        textView[22]!!.setTypeface(fontArabicb)
        textView[23]!!.setTypeface(fontArabicb)
        textView[24]!!.setTypeface(fontArabicb)
        textView[25]!!.setTypeface(fontArabicb)
        textView[26]!!.setTypeface(fontmyredL)
        textView[28]!!.setTypeface(fontArabicb)
        textView[28]!!.setTypeface(fontArabicb)
        textView[30]!!.setTypeface(fontmyredL)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
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

    private fun showInterstitial() {}
}
