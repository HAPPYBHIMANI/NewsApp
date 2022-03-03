package com.app.newsapp.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


abstract class PaginationScrollListener(linearLayoutManager:LinearLayoutManager) : RecyclerView.OnScrollListener() {
    private var layoutManager: LinearLayoutManager? = null

    open fun PaginationScrollListener(layoutManager: LinearLayoutManager?) {
        this.layoutManager = layoutManager
    }

//    open fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
//        super.onScrolled(recyclerView!!, dx, dy)
////        val visibleItemCount = layoutManager!!.childCount
////        val totalItemCount = layoutManager!!.itemCount
////        val firstVisibleItemPosition = layoutManager!!.findFirstVisibleItemPosition()
////        if (!isLoading() && !isLastPage()) {
////            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
////                && firstVisibleItemPosition >= 0
////            ) {
////                loadMoreItems()
////            }
////        }
//    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager!!.childCount
        val totalItemCount = layoutManager!!.itemCount
        val firstVisibleItemPosition = layoutManager!!.findFirstVisibleItemPosition()
        if (!isLoading() && !isLastPage()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
            ) {
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean
}

