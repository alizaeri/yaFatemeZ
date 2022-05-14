package com.yafateme.app.yafateme

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.text.ClipboardManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.yafateme.app.yafateme.Dua.Companion.PosLong
import com.yafateme.app.yafateme.Dua.Companion.alert
import com.yafateme.app.yafateme.Dua.Companion.mp
import java.util.ArrayList

class AdapterDuaPage(var context: Context, var items: ArrayList<Item>) :
    RecyclerView.Adapter<AdapterDuaPage.ViewHolder>() {
    var PosClick = 0
    var numShowe = 0
    var saveInt = 0
    private lateinit var memory: SharedPreferences
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = items[position]
        PosClick = position
        if (model.text_en == null) {
            holder.tv_en.visibility = View.GONE
        }
        if (model.text_fa == null) {
            holder.tv_fa.visibility = View.GONE
        }
        holder.tv_ar.text = model.text_ar
        holder.tv_num.text = model.text_num

        /*---------------------------------------*/if (MainActivity.Pro) {
            holder.tv_ar.setTypeface(MainActivity.ArabicFont)
            holder.tv_ar.textSize = MainActivity.ArabicSize.toFloat()
            if (MainActivity.ShadowMod.equals("true")) {
                holder.tv_ar.setShadowLayer(2f, 2f, 1f, Color.BLACK)
                holder.tv_fa.setShadowLayer(2f, 2f, 1f, Color.BLACK)
                holder.tv_en.setShadowLayer(2f, 2f, 1f, Color.BLACK)
            }
            when (MainActivity.ColorAr) {
                1 -> holder.tv_ar.setTextColor(Color.rgb(250, 246, 219))
                2 -> holder.tv_ar.setTextColor(Color.rgb(242, 211, 164))
                3 -> holder.tv_ar.setTextColor(Color.rgb(239, 178, 197))
                4 -> holder.tv_ar.setTextColor(Color.rgb(185, 194, 223))
                5 -> holder.tv_ar.setTextColor(Color.rgb(199, 232, 232))
                6 -> holder.tv_ar.setTextColor(Color.rgb(206, 230, 189))
                7 -> holder.tv_ar.setTextColor(Color.WHITE)
                8 -> holder.tv_ar.setTextColor(Color.rgb(0, 20, 50))
                9 -> holder.tv_ar.setTextColor(Color.rgb(20, 60, 50))
                10 -> holder.tv_ar.setTextColor(Color.rgb(50, 35, 10))
            }
        }
        // ---------------------------text_fa Farsi text Font, Size and Color
        if (MainActivity.DataTr1.equals("trFa")) {
            holder.tv_fa.text = model.text_fa
            holder.tv_fa.visibility = View.VISIBLE
            holder.tv_fa.setTypeface(MainActivity.FaFont)
            if (MainActivity.Pro) {
                holder.tv_fa.textSize = MainActivity.FaSize.toFloat()
                when (MainActivity.ColorFa) {
                    1 -> holder.tv_fa.setTextColor(Color.rgb(250, 246, 219))
                    2 -> holder.tv_fa.setTextColor(Color.rgb(242, 211, 164))
                    3 -> holder.tv_fa.setTextColor(Color.rgb(239, 178, 197))
                    4 -> holder.tv_fa.setTextColor(Color.rgb(185, 194, 223))
                    5 -> holder.tv_fa.setTextColor(Color.rgb(199, 232, 232))
                    6 -> holder.tv_fa.setTextColor(Color.rgb(206, 230, 189))
                    7 -> holder.tv_fa.setTextColor(Color.WHITE)
                    8 -> holder.tv_fa.setTextColor(Color.rgb(0, 20, 50))
                    9 -> holder.tv_fa.setTextColor(Color.rgb(20, 60, 50))
                    10 -> holder.tv_fa.setTextColor(Color.rgb(50, 35, 10))
                }
            }
        } else if (MainActivity.DataTr1.equals("trEn")) {
            holder.tv_fa.text = model.text_fa
            holder.tv_fa.visibility = View.VISIBLE
            if (MainActivity.Pro) {
                holder.tv_fa.textSize = MainActivity.EnSize.toFloat()
                holder.tv_fa.setTypeface(MainActivity.EnFont)
                when (MainActivity.ColorFa) {
                    1 -> holder.tv_fa.setTextColor(Color.rgb(250, 246, 219))
                    2 -> holder.tv_fa.setTextColor(Color.rgb(242, 211, 164))
                    3 -> holder.tv_fa.setTextColor(Color.rgb(239, 178, 197))
                    4 -> holder.tv_fa.setTextColor(Color.rgb(185, 194, 223))
                    5 -> holder.tv_fa.setTextColor(Color.rgb(199, 232, 232))
                    6 -> holder.tv_fa.setTextColor(Color.rgb(206, 230, 189))
                    7 -> holder.tv_fa.setTextColor(Color.WHITE)
                    8 -> holder.tv_fa.setTextColor(Color.rgb(0, 20, 50))
                    9 -> holder.tv_fa.setTextColor(Color.rgb(20, 60, 50))
                    10 -> holder.tv_fa.setTextColor(Color.rgb(50, 35, 10))
                }
            }
        } else if (MainActivity.DataTr1.equals("trUr")) {
            holder.tv_fa.text = model.text_fa
            holder.tv_fa.visibility = View.VISIBLE
            if (MainActivity.Pro) {
                holder.tv_fa.textSize = MainActivity.UrSize.toFloat()
                holder.tv_fa.setTypeface(MainActivity.UrFont)
                when (MainActivity.ColorFa) {
                    1 -> holder.tv_fa.setTextColor(Color.rgb(250, 246, 219))
                    2 -> holder.tv_fa.setTextColor(Color.rgb(242, 211, 164))
                    3 -> holder.tv_fa.setTextColor(Color.rgb(239, 178, 197))
                    4 -> holder.tv_fa.setTextColor(Color.rgb(185, 194, 223))
                    5 -> holder.tv_fa.setTextColor(Color.rgb(199, 232, 232))
                    6 -> holder.tv_fa.setTextColor(Color.rgb(206, 230, 189))
                    7 -> holder.tv_fa.setTextColor(Color.WHITE)
                    8 -> holder.tv_fa.setTextColor(Color.rgb(0, 20, 50))
                    9 -> holder.tv_fa.setTextColor(Color.rgb(20, 60, 50))
                    10 -> holder.tv_fa.setTextColor(Color.rgb(50, 35, 10))
                }
            }
        } else if (MainActivity.DataTr1.equals("none")) {
            holder.tv_fa.visibility = View.GONE
        }

        /*-------------------------------------*/if (MainActivity.DataTr2.equals("trFa")) {
            holder.tv_en.text = model.text_en
            holder.tv_en.visibility = View.VISIBLE
            holder.tv_en.setTypeface(MainActivity.FaFont)
            if (MainActivity.Pro) {
                holder.tv_en.textSize = MainActivity.FaSize.toFloat()
                holder.tv_en.setTypeface(MainActivity.FaFont)
                when (MainActivity.ColorFa) {
                    1 -> holder.tv_en.setTextColor(Color.rgb(250, 246, 219))
                    2 -> holder.tv_en.setTextColor(Color.rgb(242, 211, 164))
                    3 -> holder.tv_en.setTextColor(Color.rgb(239, 178, 197))
                    4 -> holder.tv_en.setTextColor(Color.rgb(185, 194, 223))
                    5 -> holder.tv_en.setTextColor(Color.rgb(199, 232, 232))
                    6 -> holder.tv_en.setTextColor(Color.rgb(206, 230, 189))
                    7 -> holder.tv_en.setTextColor(Color.WHITE)
                    8 -> holder.tv_en.setTextColor(Color.rgb(0, 20, 50))
                    9 -> holder.tv_en.setTextColor(Color.rgb(20, 60, 50))
                    10 -> holder.tv_en.setTextColor(Color.rgb(50, 35, 10))
                }
            }
        } else if (MainActivity.DataTr2.equals("trEn")) {
            holder.tv_en.text = model.text_en
            holder.tv_en.visibility = View.VISIBLE
            holder.tv_en.setTypeface(MainActivity.EnFont)
            if (MainActivity.Pro) {
                holder.tv_en.setTypeface(MainActivity.EnFont)
                holder.tv_en.textSize = MainActivity.EnSize.toFloat()
                when (MainActivity.ColorFa) {
                    1 -> holder.tv_en.setTextColor(Color.rgb(250, 246, 219))
                    2 -> holder.tv_en.setTextColor(Color.rgb(242, 211, 164))
                    3 -> holder.tv_en.setTextColor(Color.rgb(239, 178, 197))
                    4 -> holder.tv_en.setTextColor(Color.rgb(185, 194, 223))
                    5 -> holder.tv_en.setTextColor(Color.rgb(199, 232, 232))
                    6 -> holder.tv_en.setTextColor(Color.rgb(206, 230, 189))
                    7 -> holder.tv_en.setTextColor(Color.WHITE)
                    8 -> holder.tv_en.setTextColor(Color.rgb(0, 20, 50))
                    9 -> holder.tv_en.setTextColor(Color.rgb(20, 60, 50))
                    10 -> holder.tv_en.setTextColor(Color.rgb(50, 35, 10))
                }
            }
        } else if (MainActivity.DataTr2.equals("trUr")) {
            holder.tv_en.text = model.text_en
            holder.tv_en.visibility = View.VISIBLE
            if (MainActivity.Pro) {
                holder.tv_en.setTypeface(MainActivity.UrFont)
                holder.tv_en.textSize = MainActivity.UrSize.toFloat()
                when (MainActivity.ColorFa) {
                    1 -> holder.tv_en.setTextColor(Color.rgb(250, 246, 219))
                    2 -> holder.tv_en.setTextColor(Color.rgb(242, 211, 164))
                    3 -> holder.tv_en.setTextColor(Color.rgb(239, 178, 197))
                    4 -> holder.tv_en.setTextColor(Color.rgb(185, 194, 223))
                    5 -> holder.tv_en.setTextColor(Color.rgb(199, 232, 232))
                    6 -> holder.tv_en.setTextColor(Color.rgb(206, 230, 189))
                    7 -> holder.tv_en.setTextColor(Color.WHITE)
                    8 -> holder.tv_en.setTextColor(Color.rgb(0, 20, 50))
                    9 -> holder.tv_en.setTextColor(Color.rgb(20, 60, 50))
                    10 -> holder.tv_en.setTextColor(Color.rgb(50, 35, 10))
                }
            }
        } else if (MainActivity.DataTr2.equals("none")) {
            holder.tv_en.visibility = View.GONE
        }

        //--------------------------------Advance mod Off
        if (!MainActivity.Pro) {
            holder.tv_ar.textSize = MainActivity.FaSize.toFloat()
            holder.tv_ar.setTypeface(MainActivity.FaFont)
            holder.tv_en.textSize = (MainActivity.FaSize - 3).toFloat()
            holder.tv_en.setTypeface(MainActivity.FaFont)
            holder.tv_fa.textSize = (MainActivity.FaSize - 3).toFloat()
            holder.tv_fa.setTypeface(MainActivity.FaFont)
            if (MainActivity.DataTr2.equals("trEn")) {
                holder.tv_en.textSize = (MainActivity.FaSize - 7).toFloat()
                holder.tv_en.setTypeface(fontmyredM)
            } else if (MainActivity.DataTr2.equals("trUr")) {
                holder.tv_en.setTypeface(fontArabic)
            }
            if (MainActivity.DataTr1.equals("trEn")) {
                holder.tv_fa.textSize = (MainActivity.FaSize - 7).toFloat()
                holder.tv_fa.setTypeface(fontmyredM)
            } else if (MainActivity.DataTr1.equals("trUr")) {
                holder.tv_fa.setTypeface(fontArabic)
            }
            if (MainActivity.mood.equals("day")) {
                holder.tv_ar.setTextColor(Color.WHITE)
                holder.tv_fa.setTextColor(Color.rgb(184, 242, 255))
                holder.tv_en.setTextColor(Color.rgb(230, 228, 255))
            } else {
                holder.tv_ar.setTextColor(Color.rgb(7, 9, 25))
                holder.tv_fa.setTextColor(Color.rgb(17, 41, 58))
                holder.tv_en.setTextColor(Color.rgb(5, 45, 62))
            }
        }
        //--------------------------------Line Spacing
        holder.tv_ar.setLineSpacing(MainActivity.space.toFloat(), 1f)
        holder.tv_fa.setLineSpacing(MainActivity.space.toFloat(), 1f)
        holder.tv_en.setLineSpacing(MainActivity.space.toFloat(), 1f)
        //--------------------------------Splitter
        holder.tv_num.setTypeface(fontArabic)
        holder.tv_num.textSize = 15f
        holder.tv_num.setTextColor(Color.WHITE)
        holder.img_sp.setBackgroundResource(R.drawable.sp3)
        //--------------------------------Review
        if (position == 0) {
            holder.tv_num.visibility = View.GONE
            holder.img_sp.visibility = View.GONE
            holder.rlRow.visibility = View.GONE
            holder.tv_ar.visibility = View.GONE
            holder.tv_en.visibility = View.GONE
            holder.tv_fa.setBackgroundResource(R.drawable.bgmalakot)
            if (MainActivity.Review) {
                holder.tv_fa.visibility = View.VISIBLE
                holder.tv_fa.text = model.text_fa
                holder.tv_fa.textSize = 17f
                if (MainActivity.lang === 2) {
                    holder.tv_fa.textSize = 12f
                }
                if (MainActivity.mood.equals("day")) {
                    holder.tv_fa.setTextColor(Color.rgb(184, 242, 255))
                } else {
                    holder.tv_fa.setTextColor(Color.rgb(17, 41, 58))
                }
                holder.tv_fa.setPadding(10, 10, 10, 10)
            } else {
                holder.tv_fa.visibility = View.GONE
            }
        } else {
            holder.tv_num.visibility = View.VISIBLE
            holder.img_sp.visibility = View.VISIBLE
            holder.rlRow.visibility = View.VISIBLE
            holder.tv_ar.visibility = View.VISIBLE
            holder.tv_fa.setBackgroundResource(0)

//            holder.tv_en.setVisibility(View.VISIBLE);
        }
        if (position == PosLong) {
            holder.main_rl.setBackgroundColor(Color.argb(30, 255, 255, 255))
        } else {
            holder.main_rl.setBackgroundColor(Color.argb(0, 255, 255, 255))
        }
        //------------------------------
        holder.itemView.setOnLongClickListener {
            alert = AlertDialog.Builder(context)
            alert!!.setTitle(Dua.Str.get(3))
            alert!!.setMessage(Dua.Str.get(7) + position)
            shareAr = model.text_ar
            shareFa = """
                
                
                ${model.text_fa}
                """.trimIndent()
            shareEn = """
                
                
                ${model.text_en}
                """.trimIndent()
            if (MainActivity.DataTr1.equals("none")) {
                shareFa = " "
            }
            if (MainActivity.DataTr2.equals("none")) {
                shareEn = " "
            }
            if (position > 1) {
                alert!!.setPositiveButton(Dua.Str.get(4),
                    DialogInterface.OnClickListener { dialog, which ->
                        val clipboard =
                            holder.itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        clipboard.text =
                            Dua.Str.get(6) + PosClick + " | " + Dua.Str.get(0) + "\n\n" + shareAr + shareFa + shareEn + shareUr + "\n\n" + chanelTamalakot
                        Toast.makeText(G.context, Dua.Str.get(14), Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    })
                alert!!.setNegativeButton(Dua.Str.get(5),
                    DialogInterface.OnClickListener { dialog, which ->
                        val sharingIntent = Intent(Intent.ACTION_SEND)
                        sharingIntent.type = "text/plain"
                        val shareBody: String =
                            Dua.Str.get(6) + PosClick + " | " + Dua.Str.get(0) + "\n\n" + shareAr + shareFa + shareEn + shareUr + "\n\n" + chanelTamalakot
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
                        holder.itemView.context.startActivity(
                            Intent.createChooser(
                                sharingIntent,
                                "Share via"
                            )
                        )
                        dialog.dismiss()
                    })
                Dua.dialog = alert!!.create()
                Dua.dialog!!.show()
            }
            false
        }
        holder.itemView.setOnClickListener {
            saveInt = position
            if (position > 1) {
                alert = AlertDialog.Builder(context)
                alert!!.setTitle(Dua.Str.get(9))
                alert!!.setMessage(
                    Dua.Str.get(10).toString() + "\n" + Dua.Str.get(6) + position
                )
                alert!!.setPositiveButton(Dua.Str.get(2),
                    DialogInterface.OnClickListener { dialog, which ->
                        PosLong = saveInt
                        memory = G.context!!.getSharedPreferences("setting", 0)
                        val edit = memory.edit()
                        edit.putInt("RowPos", PosLong)
                        edit.apply()
                        dialog.dismiss()
                        Toast.makeText(
                            G.context,
                            Dua.Str.get(19).toString() + "\n" + Dua.Str.get(6) + PosLong,
                            Toast.LENGTH_SHORT
                        ).show()
                    })
                alert!!.setNegativeButton(Dua.Str.get(1),
                    DialogInterface.OnClickListener { dialog, which ->
                        mp?.seekTo(Dua.ints.get(saveInt))
                        if (mp?.isPlaying() == true) {
                        } else {
                            Dua.PlaySong()
                        }
                        dialog.dismiss()
                    })
                Dua.dialog = alert!!.create()
                Dua.dialog!!.show()
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_ar: TextView
        var tv_fa: TextView
        var tv_en: TextView
        var tv_num: TextView
        var img_sp: ImageView
        var rlRow: RelativeLayout
        var main_rl: RelativeLayout

        init {
            tv_ar = itemView.findViewById(R.id.text_ar)
            tv_fa = itemView.findViewById(R.id.text_fa)
            tv_en = itemView.findViewById(R.id.text_en)
            tv_num = itemView.findViewById(R.id.tv_test)
            img_sp = itemView.findViewById(R.id.img1)
            rlRow = itemView.findViewById(R.id.rl_row)
            main_rl = itemView.findViewById(R.id.rel_row)
        }
    }

    companion object {
        var chanelTamalakot: String? = null
        var shareFa: String? = null
        var shareEn: String? = null
        var shareAr: String? = null
        var shareUr: String? = null
        var fontmyredM: Typeface? = null
        var fontArabic: Typeface? = null
    }
}
