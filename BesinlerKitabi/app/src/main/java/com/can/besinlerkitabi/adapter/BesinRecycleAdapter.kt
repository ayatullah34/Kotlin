package com.can.besinlerkitabi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.can.besinlerkitabi.R
import com.can.besinlerkitabi.databinding.BesinRecycleRowBinding
import com.can.besinlerkitabi.model.Besin
import com.can.besinlerkitabi.util.gorselIndir
import com.can.besinlerkitabi.util.placeholderYap
import com.can.besinlerkitabi.view.BesinListesiFragmentDirections
import kotlinx.android.synthetic.main.besin_recycle_row.view.*

import kotlin.collections.ArrayList

//class BesinRecycleAdapter(val besinListesi : ArrayList<Besin>) : RecyclerView.Adapter<BesinRecycleAdapter.BesinViewHolder>() {

//databinding
class BesinRecycleAdapter(val besinListesi : ArrayList<Besin>) : RecyclerView.Adapter<BesinRecycleAdapter.BesinViewHolder>(),BesinClickListener {

    /*
    class BesinViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

     */
                                    //databinding
    class BesinViewHolder(var view : BesinRecycleRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BesinViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        //val view = inflater.inflate(R.layout.besin_recycle_row,parent,false)
        val view = DataBindingUtil.inflate<BesinRecycleRowBinding>(inflater,R.layout.besin_recycle_row,parent,false)

        return BesinViewHolder(view)

    }

    override fun onBindViewHolder(holder: BesinViewHolder, position: Int) {
        /*
        holder.itemView.isim.text = besinListesi[position].besinIsim
        holder.itemView.kalori.text = besinListesi[position].besinKalori

        //Glide Görsel
        holder.itemView.imageView.gorselIndir(besinListesi.get(position).besinGorsel, placeholderYap(holder.itemView.context))

        holder.itemView.setOnClickListener {
            val action = BesinListesiFragmentDirections.actionBesinListesiFragmentToBesinDetayiFragment(besinListesi.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }
         */

        //databinding
        holder.view.besin = besinListesi[position]
        holder.view.listener = this


    }

    override fun getItemCount(): Int {

        return besinListesi.size
    }


    fun besinListesiniGuncelle( yeniBesinListesi : List<Besin>){

        besinListesi.clear()
        besinListesi.addAll(yeniBesinListesi)
        notifyDataSetChanged()

    }

    //databinding
    override fun besinTiklandi(view: View) {

        val uuid = view.besin_uuid.text.toString().toIntOrNull()
        uuid?.let {

            val action = BesinListesiFragmentDirections.actionBesinListesiFragmentToBesinDetayiFragment(it)
            Navigation.findNavController(view).navigate(action)

        }
    }

}


