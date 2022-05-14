package com.yafateme.app.yafateme

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.yafateme.app.yafateme.Splash.Companion.itemsList

class ContactUs : AppCompatActivity(), View.OnClickListener {
    var fontmyredM: Typeface? = null
    var fArab: Typeface? = null
    var myredM: Typeface? = null
    private lateinit var id: Array<String>
    private lateinit var id2: Array<String>
    private val textView = arrayOfNulls<TextView>(20)
    private val linearLayouts = arrayOfNulls<LinearLayout>(9)
   // private var db: database? = null
    var link: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)
        fontmyredM = Typeface.createFromAsset(assets, "fonts/myriadl.ttf")
        fArab = Typeface.createFromAsset(assets, "fonts/arabicb.otf")
        myredM = Typeface.createFromAsset(assets, "fonts/myriadm.ttf")
      //  db = database(this@ContactUs)
       // db!!.useable()
      //  db!!.open()
        var themp: Int
        var themp2: Int
        id =
            arrayOf( //--0           1           2           3         4         5           6          7           8          9
                "tv_cap1",
                "tv_cap2",
                "tv_cap3",
                "tv_cap4",
                "tv_cap5",
                "tv_cap6",
                "tv_cap7",
                "tv_cap8",
                "tv_cap9",
                "tv_titrfa",  //  10          11          12        13         14         15          16        17        18          19
                "tv_link1",
                "tv_link2",
                "tv_link3",
                "tv_link4",
                "tv_link5",
                "tv_link6",
                "tv_link7",
                "tv_link8",
                "tv_link9",
                "tv_titren"
            )
        id2 = arrayOf( //--0       1       2        3      4       5       6       7      8
            "li_1", "li_2", "li_3", "li_4", "li_5", "li_6", "li_7", "li_8", "li_9"
        )
        for (item in id.indices) {
            themp = resources.getIdentifier(id[item], "id", packageName)
            textView[item] = findViewById<View>(themp) as TextView
            textView[item]!!.text = itemsList[item + 40].dbText
          //  textView[i]!!.text = db!!.namayesh(i + 40, 0, "about")
            if (item < 10) {
                textView[item]!!.setTypeface(fArab)
            } else {
                textView[item]!!.setTypeface(myredM)
            }
        }
        for (i in id2.indices) {
            themp2 = resources.getIdentifier(id2[i], "id", packageName)
            linearLayouts[i] = findViewById<View>(themp2) as LinearLayout
            linearLayouts[i]!!.setOnClickListener(this)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.li_1 -> {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.type = "text/plain"
                intent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    getString(R.string.app_name) + getString(R.string.kharabi)
                )
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.matn))
                intent.data = Uri.parse("mailto:info@appeks.com")
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            R.id.li_2 -> {
                val intent1 = Intent(Intent.ACTION_SENDTO)
                intent1.type = "text/plain"
                intent1.putExtra(
                    Intent.EXTRA_SUBJECT,
                    getString(R.string.app_name) + getString(R.string.enteghad)
                )
                intent1.putExtra(Intent.EXTRA_TEXT, getString(R.string.matn))
                intent1.data = Uri.parse("mailto:info@appeks.com")
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent1)
            }
            R.id.li_3 -> {
                link = "http://kimiya.appeks.com"
                openuri(link!!)
            }
            R.id.li_4 -> {
                link = "https://t.me/tamalakot"
                openuri(link!!)
            }
            R.id.li_5 -> {
                link = "https://sapp.ir/kimiya.saadat"
                openuri(link!!)
            }
            R.id.li_6 -> {
                link = "https://eitaa.com/kimiyasaadat"
                openuri(link!!)
            }
            R.id.li_7 -> {
                link = "https://www.instagram.com/kimiya_appeks/"
                openuri(link!!)
            }
            R.id.li_8 -> {
                link = "https://twitter.com/Appeks_group"
                openuri(link!!)
            }
            R.id.li_9 -> {
                link = "https://facebook.com/Appeks_group"
                openuri(link!!)
            }
        }
    }

    private fun openuri(link: String) {
        val url = "" + link
        val i = Intent(Intent.ACTION_VIEW)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.data = Uri.parse(url)
        application.startActivity(i)
    }
}