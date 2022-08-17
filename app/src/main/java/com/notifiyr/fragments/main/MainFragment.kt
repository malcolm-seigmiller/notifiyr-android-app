package com.notifiyr.fragments.main

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.notifiyr.BuildConfig
import com.notifiyr.R
import com.notifiyr.Settings
import com.notifiyr.api.SimpleApi
import com.notifiyr.models.cccode
import com.notifiyr.viewmodel.ViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class MainFragment : Fragment() {

    private lateinit var mUserViewModel: ViewModel
    private val myAdapter by lazy { MainAdapter() }

    val gson = GsonBuilder()
            .setLenient()
            .create()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        mUserViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        view.see_cccodes.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_listFragment2)
        }
        view.enter_btn.setOnClickListener { 
            insertDataToDatabase()
        }
        if (!Settings.recViewAlreadyUpdated) {
            dbread()
            Settings.recViewAlreadyUpdated = true
        } else {
            dbread()
        }
        return view
    }

    private fun dbread() {
        mUserViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        mUserViewModel.readSomeData.observe(viewLifecycleOwner, Observer { user ->
            if (user != null) {
                val typedArray = user.split(",").toTypedArray()
                TestGet(typedArray)
            } else {
                println("NULL Observer")
            }
        })
    }
    val BASE_URL = "https://www.notifiyr.com/"

    private fun TestGet(abc: Array<String>) {

        val builder = OkHttpClient().newBuilder()
        builder.readTimeout(120, TimeUnit.SECONDS)
        builder.connectTimeout(5, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }
        //you're going to want to remove above at launch
        val client = builder.build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
                .create(SimpleApi::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = retrofit.postit(abc)
                try {
                    Log.i("RESPONSE", "RAW: " + response)
                    for (Mlist in response.Mlist) {
                        Log.d("MainActivity", "Result + $Mlist")
                        response.Mlist.let { myAdapter.setData(it) }
                    }

                    withContext(Dispatchers.Main) {
                        setUpRecyclerView()
                    }
                } catch (e: Exception) {
                    println("you messed up the connection some how")
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "something went wrong!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        main_recyclerview.adapter = myAdapter
        main_recyclerview.layoutManager = LinearLayoutManager(this.context)

    }

    private fun insertDataToDatabase() {
        val cccode = editcccode.text.toString()

        if (inputCheck(cccode)) {
            val cccode = cccode(0, cccode)

            mUserViewModel.addUser(cccode)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_mainFragment_to_listFragment2)

        } else {
            Toast.makeText(requireContext(), "Please enter a Cccode.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(cccode: String): Boolean {
        return !(TextUtils.isEmpty(cccode))
    }
}