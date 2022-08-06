package com.example.all_api_calling

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.all_api_calling.Adpter.My_Adpter


class MainActivity : AppCompatActivity() {
    lateinit var btn: Button
    var rvview: RecyclerView? = null
    lateinit var serch_view: SearchView
    var list = arrayListOf<volleyModel>()
     lateinit var myAdpter : My_Adpter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById<Button>(R.id.btn)
        rvview = findViewById<RecyclerView>(R.id.rvview)
        serch_view = findViewById<SearchView>(R.id.serch_view)
        btn.setOnClickListener {

            volleyapiget()
//            setrv()
//            volleypost("jenish","android devloper")
//            serchfilter()

        }


    }


    fun volleyapiget() {
        var getapilink = "https://jsonplaceholder.typicode.com/posts"
        var jsonArrayRequest = JsonArrayRequest(Request.Method.GET, getapilink, null,
            { response ->
                Log.e("TAG", "volleyapiget: $response")

                var i = 0

                var list = arrayListOf<volleyModel>()

                while (i < response.length()) {
                    var userId = response.getJSONObject(i).getString("userId")
                    var id = response.getJSONObject(i).getString("id")
                    var title = response.getJSONObject(i).getString("title")
                    var body = response.getJSONObject(i).getString("body")
                    var m1 = volleyModel(userId, id, title, body)

                    list.add(m1)

                    i++

//                    Toast.makeText(this, "$title", Toast.LENGTH_SHORT).show()
                }

                setrv(list)

            }, { error ->

                Log.e("TAG", "volleyapiget: $error")
            })

        var requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(jsonArrayRequest)

    }

    fun volleypost(name: String, job: String) {

        var api = "https://reqres.in/api/users"
        var stringRequest = object : StringRequest(Request.Method.POST, api, { response ->


            Log.e("TAG", "volleypost: $response")
        },
            { error ->
                Log.e("TAG", "volleypost: $error")

            }) {
            override fun getParams(): MutableMap<String, String>? {
                var map = HashMap<String, String>()
                map.put("name", name)
                map.put("job", job)
                return map
            }

        }
        var requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)

    }

    fun setrv(list: ArrayList<volleyModel>) {
        var myAdpter = My_Adpter(this, list)
        var lm = LinearLayoutManager(this)
        rvview?.adapter = myAdpter
        rvview?.layoutManager = lm
    }

//    fun serchfilter() {
//
//        serch_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(p0: String?): Boolean {
//
//                var filter = arrayListOf<String>()
//                var fil = arrayListOf<String>()
//
//                for (filt in list) {
//                    if (filt.title.lowercase().contains(p0!!.lowercase())) {
//                        filter.add(filt.toString())
//                    }
////myAdpter.
//                }
//                return false
//            }
//        })
//
//
//    }

}