package android.example.yeahscience.ui.spaceXRockets

import android.example.yeahscience.R
import android.example.yeahscience.data.spaceX.rockets.models.SpaceXRocket
import android.example.yeahscience.utils.takeAndSetImage
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rocket.view.*
import java.util.ArrayList


class RocketsAdapter(private val listener: RocketItemListener) : RecyclerView.Adapter<RocketsAdapter.RocketsHolder>(){

    private val items = ArrayList<SpaceXRocket>()

    fun setItems(items: List<SpaceXRocket>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
    inner class RocketsHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.item_rocket_image
        val name: TextView = view.item_rocket_name
        private lateinit var rocket : SpaceXRocket

        fun bind(item: SpaceXRocket) {
               this.rocket = item
               this.name.text = rocket.name
            rocket.images?.let { this.image.takeAndSetImage(it[1]) }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rocket, parent, false)
        return RocketsHolder(view)
    }

    override fun onBindViewHolder(holder: RocketsHolder, position: Int) {
        val item = items[position]
       holder.bind(item = item)
        holder.itemView.setOnClickListener {
            listener.onClickItemListener(id = item.id)
        }
    }

    override fun getItemCount(): Int {
       return items.size
    }



    interface RocketItemListener{
        fun onClickItemListener(id: String)
    }
}
