package com.example.materialme

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import java.util.ArrayList

internal class SportsAdapter(
    private val mContext: Context,
    private val mSportsData: ArrayList<Sport>

) : RecyclerView.Adapter<SportsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentSport = mSportsData[position]
        holder.bindTo(currentSport)

        Glide.with(mContext).load(currentSport.imageResource).into(
            holder.mSportsImage
        )
        holder.mTitleText.text = currentSport.title
        holder.mInfoText.text = currentSport.info

        holder.mSportsImage.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_IMAGE, currentSport.imageResource)
            intent.putExtra(DetailActivity.EXTRA_TITLE, currentSport.title)
            intent.putExtra(DetailActivity.EXTRA_NEWS, currentSport.info)

            holder.itemView.context.startActivity(intent)
        }

        holder.mCardView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_IMAGE, currentSport.imageResource)
            intent.putExtra(DetailActivity.EXTRA_TITLE, currentSport.title)
            intent.putExtra(DetailActivity.EXTRA_NEWS, currentSport.info)

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mSportsData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mTitleText: TextView
        val mInfoText: TextView
        val mSportsImage: ImageView
        val mCardView: CardView

        fun bindTo(currentSport: Sport) {
            mTitleText.text = currentSport.title
            mInfoText.text = currentSport.info
        }

        init {
            mTitleText = itemView.findViewById<View>(R.id.title) as TextView
            mInfoText = itemView.findViewById<View>(R.id.subTitle) as TextView
            mSportsImage = itemView.findViewById<View>(R.id.sportsImage) as ImageView
            mCardView = itemView.findViewById<View>(R.id.card_view) as CardView
        }
    }
}