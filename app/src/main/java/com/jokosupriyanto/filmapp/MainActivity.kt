package com.jokosupriyanto.filmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import com.airbnb.epoxy.*
import com.jokosupriyanto.filmapp.data.model.Result
import com.jokosupriyanto.filmapp.ui.main.ViewModel
import kotlin.properties.Delegates.observable

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }
    lateinit var rvTask: EpoxyRecyclerView
    private val viewModel by lazy {ViewModel()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTask =  findViewById(R.id.rv_result)
        setClickListener()
        observeViewModel()
    }

    private fun setClickListener() {
        findViewById<SearchView>(R.id.search_movie).setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i(TAG, "onQueryTextSubmit: Submit")
                if (query.toString().trim().isNotEmpty()) viewModel.fetchDetails(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun observeViewModel() {

        val controller = object: EpoxyController() {

            var shows by observable(emptyList<Result?>()) { _, _, _ ->
                requestModelBuild()
            }

            override fun buildModels() {

//                rvTask.withModels {
//                    // First title
//                    title {
//                        id("title-id")
//                        title("Need Assistant")
//                    }

                TitleBindingModel_()
                    .id("title")
                    .addTo(this)

                CarouselModel_()
                    .id("PROMOTION_LIST")
                    .models(shows.map { res ->
                        CardBindingModel_()
                            .id(res?.show?.id)
                            .result(res)
                            .clickListener { v ->
                                Toast.makeText(this@MainActivity, res?.show?.name, Toast.LENGTH_SHORT).show()
                            }
                    })
                    .addTo(this)
            }

        }

        rvTask.setController(controller)
        viewModel.getDetail().observe(this, {
            controller.apply {
                shows = it?.toList()!!
            }
//            rvTask.withModels {
//
//                val models = it?.map { res ->
//                    CardBindingModel_()
//                        .id("title-id")
//                        .result(res)
//                        .clickListener { v ->
//                            Toast.makeText(this@MainActivity, res?.show?.name, Toast.LENGTH_SHORT).show()
//                        }
//                }
//                carousel {
//                    id("recent")
//                    numViewsToShowOnScreen(5F)
//                    padding(Carousel.Padding.dp(0, 4, 0, 16, 8))
//                    if (models != null) {
//                        models(models)
//                    }
//                }
//
//
//
////                carousel {
////                    id("carousel")
////                    numViewsToShowOnScreen(5F)
////                    models(CardBindingModel_ {
////                        it?.forEach {
////                            card {
////                                id("title-id")
////                                result(res)
////                                clickListener { v ->
////                                    Toast.makeText(this@MainActivity, res?.show?.name, Toast.LENGTH_SHORT).show()
////                                }
////                            }
////                        }
////                    }
////                }
//
////                // Tasks
////                it?.forEach { res ->
////                    // item_task.xml
////                    card {
////                        id("title-id")
////                        result(res)
////                        clickListener { v ->
////                            Toast.makeText(this@MainActivity, res?.show?.name, Toast.LENGTH_SHORT).show()
////                        }
////                    }
////                }
//            }
//            it?.forEach {
//                Log.i(TAG, "observeViewModel: $it")
//            }
        })


    }
}