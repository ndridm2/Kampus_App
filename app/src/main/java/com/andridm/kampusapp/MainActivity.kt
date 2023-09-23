package com.andridm.kampusapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andridm.kampusapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvKampus: RecyclerView
    private val list = ArrayList<Kampus>()
    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvKampus = binding.rvKampus
        rvKampus.setHasFixedSize(true)

        list.addAll(getListKampus())
        showRecyclerList()
    }

    private fun getListKampus(): ArrayList<Kampus> {

        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listKampus = ArrayList<Kampus>()
        for (i in dataName.indices){
            val kampus = Kampus(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listKampus.add(kampus)
        }
        return listKampus
    }

    private fun showRecyclerList() {
        if (isLinearLayoutManager){
            rvKampus.layoutManager = LinearLayoutManager(this)
        }else {
            rvKampus.layoutManager = GridLayoutManager(this, 2)
        }
        val listKampusAdapter = ListKampusAdapter(list)
        rvKampus.adapter = listKampusAdapter

        listKampusAdapter.setOnItemClickCallback(object : ListKampusAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Kampus) {
                showSelectedKampus(data)
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra("DATA", data)
                startActivity(intentToDetail)

            }
        })
    }

    private fun showSelectedKampus(kampus: Kampus){
        Toast.makeText(this, "Detail " + kampus.name, Toast.LENGTH_SHORT).show()
    }

    private fun setIcon(menuItem: MenuItem?){
        if (menuItem == null)
            return

        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this, R.drawable.ic_baseline_grid_view)
            else ContextCompat.getDrawable(this, R.drawable.ic_baseline_view_list)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar, menu)

        val layoutButton = menu?.findItem(R.id.switch_layout)
        setIcon(layoutButton)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.aboutView -> startActivity(Intent(this@MainActivity,
                AboutActivity::class.java))
            R.id.switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                showRecyclerList()
                setIcon(item)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}