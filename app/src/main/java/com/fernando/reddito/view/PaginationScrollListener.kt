package com.fernando.reddito.view

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Pagination class to add more items to the list when reach the last item.
 */
abstract class PaginationScrollListener(
    private var layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    /**
     * Set scrolling threshold here (for now i'm assuming 10 item in one page)
     */

    companion object {
        const val PAGE_START = 1
    }

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val childCount = layoutManager.childCount
        val itemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if ((childCount + firstVisibleItemPosition) >= itemCount
                && firstVisibleItemPosition >= 0) {
                loadMoreItems()
            }
        }
    }

    abstract fun loadMoreItems()
}