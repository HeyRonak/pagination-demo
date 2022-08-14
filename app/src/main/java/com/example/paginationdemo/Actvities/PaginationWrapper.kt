package com.example.paginationdemo.Actvities

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener


abstract class PaginationWrapper(var layoutManager: LinearLayoutManager) : OnScrollListener() {

    @Override
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount: Int = layoutManager.childCount
        val pastVisibleItem: Int = layoutManager.findFirstCompletelyVisibleItemPosition()
        val total = layoutManager.itemCount

        if(dy <=0  || isLoading()){
            return
        }

        if (!isLoading() && !isLastPage()) {
            if (visibleItemCount + pastVisibleItem >= total) {
                loadMoreItems()
//                Log.e("loadmore",  "called")
            }
        }


    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
    }

    abstract fun loadMoreItems()

    abstract fun isLoading(): Boolean
    abstract fun isLastPage(): Boolean


}