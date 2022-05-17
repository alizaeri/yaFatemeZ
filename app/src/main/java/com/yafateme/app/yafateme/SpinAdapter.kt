package com.yafateme.app.yafateme

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.yafateme.app.yafateme.G.Companion.context
import com.yafateme.app.yafateme.MainActivity.Companion.lang
import com.yafateme.app.yafateme.Setting.Companion.myFile
import com.yafateme.app.yafateme.Splash.Companion.listLanguage
import java.io.File
import java.util.ArrayList


class SpinAdapter(
    private val context1: Context, textViewResourceId: Int,
    spin: ArrayList<Spin>
) : ArrayAdapter<Spin?>(context1, textViewResourceId, spin as List<Spin?>) {

    // Your custom values for the spinner (User)
    var spin: ArrayList<Spin>
    var DirS = "/sdcard/Android/data/" + G.context!!.applicationContext.packageName.toString() + "/"
    //private var db: database? = null
    override fun getCount(): Int {
        return spin.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getPosition(item: Spin?): Int {
        return super.getPosition(item)
    }

    override fun getItem(position: Int): Spin? {
        return super.getItem(position)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//
        return getCustomView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
//
        return getCustomView(position, convertView, parent)
    }

    fun getCustomView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val model = spin[position]


        var maddahName = ""
        maddahName = listLanguage[position].name2.toString()




        myFile = File("$DirS + $maddahName.mp3")

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val row = inflater.inflate(R.layout.spinner, parent, false)
        val liSpin = row.findViewById<View>(R.id.l_spin) as LinearLayout
        val lin = row.findViewById<LinearLayout>(R.id.l_line)
        lin.setBackgroundColor(Color.parseColor("#3d3e43"))
        val label = row.findViewById<View>(R.id.text_spin) as TextView
        label.textAlignment = View.TEXT_ALIGNMENT_CENTER
        label.setTextColor(Color.parseColor("#ffffff"))
        label.text = spin[position].name

        /*
        when (lang){
            0-> when(position){
                0->{label.text = "fa fa"
                    Log.d("!!!!", "onItemSelected: relation lang $lang $position")}
                1->{label.text = "ar ar"
                Log.d("!!!!", "onItemSelected: relation lang $lang $position")}
                2->{label.text = "en en"
            Log.d("!!!!", "onItemSelected: relation lang $lang $position")}
                3->{label.text = "ur ur"
        Log.d("!!!!", "onItemSelected: relation lang $lang $position")}
            }
            1-> when(position){
                0->{label.text = "ar fa"
                Log.d("!!!!", "onItemSelected: relation lang $lang $position")}
                1->{label.text = "ar ar"
                Log.d("!!!!", "onItemSelected: relation lang $lang $position")}
                2->{label.text = "en en"
            Log.d("!!!!", "onItemSelected: relation lang $lang $position")}
                3->{label.text = "ur ur"
        Log.d("!!!!", "onItemSelected: relation lang $lang $position")}
            }
            2-> when(position){
                0->{label.text = "en fa"
                Log.d("!!!!", "onItemSelected: relation lang $lang $position")}
                1->{label.text = "ar ar"
                Log.d("!!!!", "onItemSelected: relation lang $lang $position")}
                2->{label.text = "en en"
            Log.d("!!!!", "onItemSelected: relation lang $lang $position")}
                3->{label.text = "ur ur"
        Log.d("!!!!", "onItemSelected: relation lang $lang $position")}
            }
            3-> when(position){

                0->{label.text = "ur fa"
                Log.d("!!!!", "onItemSelected: relation lang $lang $position")}
                1->{label.text = "ar ar"
                Log.d("!!!!", "onItemSelected: relation lang $lang $position")}
                2->{label.text = "en en"
            Log.d("!!!!", "onItemSelected: relation lang $lang $position")}
                3->{label.text = "ur ur"
        Log.d("!!!!", "onItemSelected: relation lang $lang $position")}
            }
        }
        */




        //Log.d("!!!!", "onItemSelected: relation lang $lang $position")
        //        if (!myFile.exists() && position!=0) {
//           lin.setBackgroundResource(R.drawable.download_maddah);
//        } else {
        liSpin.setBackgroundColor(Color.parseColor("#2e2c34"))
        //        }
        /*if (MainActivity.lang == 2) {
            label.setTypeface(Setting.fontmyredL)
        } else {
            label.setTypeface(Setting.fontArabic)
        }
        */

      //  Log.d("!!!", "getCustomView: ${spin[position].name} ")
        return row
    }

    init {
        this.spin = spin as ArrayList<Spin>

    }
}
