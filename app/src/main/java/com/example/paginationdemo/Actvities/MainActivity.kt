package com.example.paginationdemo.Actvities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paginationdemo.Actvities.Services.Models.PostResp
import com.example.paginationdemo.Actvities.Services.RetrofitService
import com.example.paginationdemo.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var page: Int = 0
    private var isLoading: Boolean = false
    private var isLastpage: Boolean = false
    private lateinit var adapter: ItemAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ItemAdapter(ArrayList())

        val layoutManager = LinearLayoutManager(this)

        binding.rvItem.setLayoutManager(layoutManager)
        binding.rvItem.adapter = adapter

        binding.rvItem.addOnScrollListener(object : PaginationWrapper(layoutManager) {

            override fun loadMoreItems() {
                isLoading = true
                _callPostApi()
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun isLastPage(): Boolean {
                return isLastpage
            }

        })

        _callPostApi()

    }

    fun _callPostApi() {

        page++
        val client = RetrofitService.getInstance()
        client.getPosts(page, 20).enqueue(object : Callback<PostResp?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<PostResp?>, response: Response<PostResp?>
            ) {
                isLoading = false
                if (response.isSuccessful) {
                    if (!response.body().toString().isEmpty()) {

                        if (adapter.getMyList().size != 0) {
                            val isLastIndex =
                                adapter.getMyList()[adapter.getMyList().size - 1]?.lastElement
                            if (isLastIndex == true) {
                                adapter.getMyList().removeAt(adapter.getMyList().size - 1)
                                adapter.notifyDataSetChanged()
                            }
                        }

                        val list = response.body()
                        if (list?.size!! < 20) {
                            isLastpage = true
                        }
                        else {
                            if (!list.isNullOrEmpty()) {
                                val record = PostResp.PostRespItem()
                                record.lastElement = true
                                list.add(record)
                                adapter.add(list)
                            }
                        }
                    }

                }
            }

            override fun onFailure(call: Call<PostResp?>, t: Throwable) {
                isLoading = false
                Log.e("Error", t.message.toString())
            }
        })

    }
}