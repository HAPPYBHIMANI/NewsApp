package com.app.newsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.newsapp.databinding.AdapterNewsBinding
import com.app.newsapp.response.Articles
import com.bumptech.glide.Glide
import java.util.*

class NewsAdapter(
    val context: Context,
    val articlesList: ArrayList<Articles>,
    val isLoadingAdded: Boolean? = false,
    var isBookmarked: String? = null,
    var author: String? = null
) :

    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    var onItemClick: ((Int, String, String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val recyclerRowBinding: AdapterNewsBinding =
            AdapterNewsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(recyclerRowBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(articlesList[position])
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    inner class ViewHolder(var recyclerRowBinding: AdapterNewsBinding) :
        RecyclerView.ViewHolder(recyclerRowBinding.root) {
        fun bindView(articles: Articles) {
            recyclerRowBinding.tvTitle.text = articles.title
            recyclerRowBinding.tvAuthor.text = articles.author

            Glide.with(context)
                .load(articles.urlToImage)
                .into(recyclerRowBinding.imageview)

            author = articles.author

            recyclerRowBinding.checkboxBookmarked.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    isBookmarked = "true"
//                    onItemClick?.invoke(adapterPosition, isBookma
                    //                    rked.toString())
                } else {
                    isBookmarked = "false"
//                    onItemClick?.invoke(adapterPosition, isBookmarked.toString())
                }
            }

        }

        init {
            recyclerRowBinding.root.setOnClickListener {
                if (author != null) {
                    onItemClick?.invoke(adapterPosition, isBookmarked!!, author!!)
                }
            }


        }
    }
}