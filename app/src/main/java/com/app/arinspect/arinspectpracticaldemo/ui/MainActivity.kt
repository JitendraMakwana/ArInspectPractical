package com.app.arinspect.arinspectpracticaldemo.ui

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.app.arinspect.arinspectpracticaldemo.dataModel.JsonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.app.arinspect.arinspectpracticaldemo.R
import com.app.arinspect.arinspectpracticaldemo.adapter.RecyclerViewAdapter
import com.app.arinspect.arinspectpracticaldemo.apiService.ApiClient
import com.app.arinspect.arinspectpracticaldemo.apiService.ApiInterface
import com.app.arinspect.arinspectpracticaldemo.dataModel.Rows


/**
 * @author Jitendra Makwana
 * @created on 15-Sept-19.
 */
class MainActivity : AppCompatActivity() {

    @BindView(R.id.recyclerview)
    lateinit var recyclerview: RecyclerView
    @BindView(R.id.progressBar)
    lateinit var progressBar: ProgressBar
    @BindView(R.id.txtNoRecords)
    lateinit var txtNoRecords: TextView
    @BindView(R.id.swipeRefreshLayout)
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    @BindView(R.id.toolbar_actionbar)
    lateinit var toolbar: Toolbar
    lateinit var apiInterface: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        init()

        swipeRefreshLayout.setOnRefreshListener {
            getServerData(true)
        }
    }

    private fun init() {

        recyclerview.layoutManager = LinearLayoutManager(this)

//      use for add vertical decider between row
        recyclerview.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        progressBar.visibility = View.VISIBLE
        getServerData(false)


    }

    /*
    * get serverdata use for getting data from server
    * use retrofit for getting data from server
    * get method is use
    *
    *
    * @parameter isSwipe boolean
    * use : check for calling swipe refresh layout or direct calling
    * if isSwipe true means stopping isRefresh after success or fail response
    * if isSwipe false means showing progressbar hide on response
    * */
    private fun getServerData(isSwipe: Boolean) {
        if (checkInternetAvailable(this@MainActivity)) {

            apiInterface = ApiClient().getApiClient()!!.create(ApiInterface::class.java)
            apiInterface.getJsonRowData().enqueue(object : Callback<JsonResponse> {
                override fun onResponse(call: Call<JsonResponse>?, response: Response<JsonResponse>?) {

                    if (isSwipe)
                        swipeRefreshLayout.isRefreshing = false
                    else
                        progressBar.visibility = View.GONE

                    recyclerview.visibility = View.VISIBLE
                    if (response?.body() != null) {
                        if (!response.body()!!.title.isNullOrEmpty()) {
                            if (!response.body()!!.title.isNullOrEmpty())
                                toolbar.title = response.body()!!.title!!
                            else
                                toolbar.title = "ArInspect Demo"
                        }

                        txtNoRecords.visibility = View.GONE
                        if (response.body()?.rows!!.isNotEmpty()) {
                            recyclerview.adapter = RecyclerViewAdapter(response.body()!!.rows as ArrayList<Rows>?)
                        } else {
                            txtNoRecords.text = getString(R.string.no_records_found)
                            txtNoRecords.visibility = View.VISIBLE
                        }
                    } else {
                        txtNoRecords.text = getString(R.string.no_records_found)
                        txtNoRecords.visibility = View.VISIBLE
                        recyclerview.visibility = View.GONE
                    }
                }

                override fun onFailure(call: Call<JsonResponse>?, t: Throwable?) {
                    if (progressBar.visibility == View.VISIBLE)
                        progressBar.visibility = View.GONE
                    if (swipeRefreshLayout.isRefreshing)
                        swipeRefreshLayout.isRefreshing = false
                    txtNoRecords.visibility = View.VISIBLE
                    recyclerview.visibility = View.GONE
                }
            })

        } else {
            if (progressBar.visibility == View.VISIBLE)
                progressBar.visibility = View.GONE
            if (swipeRefreshLayout.isRefreshing)
                swipeRefreshLayout.isRefreshing = false
            txtNoRecords.text = getString(R.string.no_internet_msg)
            txtNoRecords.visibility = View.VISIBLE
            recyclerview.visibility = View.GONE
            Toast.makeText(this@MainActivity, "Please check Internet connection", Toast.LENGTH_LONG).show()
        }
    }

    /*
    * check for internet.
    * return true if internet available
    * return false if not availavble
    * */
    private fun checkInternetAvailable(activity: AppCompatActivity): Boolean {
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
