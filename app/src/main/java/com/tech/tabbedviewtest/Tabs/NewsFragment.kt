package com.tech.tabbedviewtest.Tabs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.tech.tabbedviewtest.MyCutomAdapter
import com.tech.tabbedviewtest.NewsDetails
import com.tech.tabbedviewtest.R
import com.tech.tabbedviewtest.Repo
import com.tech.umr.Network.ApiEndpoint
import com.tech.umr.Network.RetroInstance


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var recycler: RecyclerView

    private lateinit var arrayList: ArrayList<NewsDetails>

    private val retroInstance by lazy {
        RetroInstance().create(ApiEndpoint::class.java)
    }
    private val repository by lazy {
        Repo(retroInstance)
    }

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            NewsViewModelFactory(repository)
        )[NewsViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        recycler = view.findViewById(R.id.recyclerView)


        recycler.layoutManager = LinearLayoutManager(requireActivity())
        arrayList = ArrayList<NewsDetails>()
        val cutomAdapter = MyCutomAdapter(requireActivity(), arrayList)
        recycler.adapter = cutomAdapter

        val progress: ProgressBar = view.findViewById(R.id.progress_circular)
        progress.visibility = View.VISIBLE
        viewModel.getNewsData("1168145cd01f267063f46bc13531f961")


        viewModel.newsData.observe(viewLifecycleOwner) {
            Toast.makeText(activity, "Succes !", Toast.LENGTH_LONG).show()
            arrayList = it.data as ArrayList<NewsDetails>
            Log.e("success", Gson().toJson(arrayList))
            cutomAdapter.setModel(arrayList)
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                progress.visibility = View.VISIBLE

            } else {
                progress.visibility = View.GONE

            }

        }


        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

