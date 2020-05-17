package com.byjuassignmentbysaddam.adapter

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import com.byjuassignmentbysaddam.interfaces.NewsListener
import com.byjuassignmentbysaddam.networkConnection.Response.ArticlesResponse
import com.byjuassignmentbysaddam.networkConnection.Response.NewsDetailModel
import com.byjuassignmentbysaddam.networkConnection.Response.NewsListData
import com.byjuassignmsaddam.R
import com.byjuassignmsaddam.databinding.ItemNewsBinding
import com.byjuassignmsaddam.utility.CommonUtils
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


/**
 * Created by Anand Gaurav on 10/10/18.
 */
class NewsListAdapter(val context: Context?, private var newsDataList: List<NewsDetailModel>, val listener: NewsListener) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsDataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var NwDataList: NewsDetailModel = newsDataList.get(position)

        if (NwDataList.urlToImage != null && !NwDataList.urlToImage.toString().isEmpty()) {
            Picasso.with(context).load(NwDataList.urlToImage).placeholder(R.drawable.ic_avatar).fit().into(holder!!.binding!!.clBackground, object :
                Callback {
                override fun onSuccess() {

                }

                override fun onError() {

                }
            })
        } else {
            holder!!.binding!!.clBackground.setBackgroundColor(R.drawable.ic_avatar)
        }

        holder!!.binding!!.tvTitle.setText(NwDataList.title)
        holder!!.binding!!.tvNewsName.setText(NwDataList.name)
        holder!!.binding!!.tvData.setText(CommonUtils().getDateFormet(NwDataList.publishedAt!!))
        holder!!.binding!!.clBackground.setOnClickListener {

            listener.OnClickListener(NwDataList)


        }


    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemNewsBinding?

        init {
            binding = DataBindingUtil.bind<ItemNewsBinding>(itemView)
        }
    }
}