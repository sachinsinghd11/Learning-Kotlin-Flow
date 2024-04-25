package com.sachin_singh_dighan.learnkotlinproject.ui.retrofit.single

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sachin_singh_dighan.learnkotlinproject.R
import com.sachin_singh_dighan.learnkotlinproject.data.api.ApiHelperImpl
import com.sachin_singh_dighan.learnkotlinproject.data.api.RetrofitBuilder
import com.sachin_singh_dighan.learnkotlinproject.data.local.DatabaseBuilder
import com.sachin_singh_dighan.learnkotlinproject.data.local.DatabaseHelperImpl
import com.sachin_singh_dighan.learnkotlinproject.data.model.ApiUser
import com.sachin_singh_dighan.learnkotlinproject.ui.base.ApiUserAdapter
import com.sachin_singh_dighan.learnkotlinproject.ui.base.UiState
import com.sachin_singh_dighan.learnkotlinproject.ui.base.ViewModelFactory
import com.sachin_singh_dighan.learnkotlinproject.utils.DefaultDispatcherProvider
import kotlinx.coroutines.launch

class SingleNetworkCallActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: SingleNetworkCallViewModel
    private lateinit var adapter: ApiUserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_network_call)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        progressBar = findViewById(R.id.progressBar)
        recyclerView  = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ApiUserAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext)),
                DefaultDispatcherProvider()
            )
        )[SingleNetworkCallViewModel::class.java]
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            progressBar.visibility = View.GONE
                            renderList(it.data)
                            recyclerView.visibility = View.VISIBLE
                        }

                        is UiState.Loading -> {
                            progressBar.visibility = View.VISIBLE
                            recyclerView.visibility = View.GONE
                        }

                        is UiState.Error -> {
                            //Handle Error
                            progressBar.visibility = View.GONE
                            Toast.makeText(
                                this@SingleNetworkCallActivity,
                                it.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(users: List<ApiUser>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }
}