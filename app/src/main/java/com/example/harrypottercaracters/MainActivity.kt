package com.example.harrypottercaracters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypottercaracters.adapters.RVAdapter
import com.example.harrypottercaracters.models.ItemsViewModel

class MainActivity : AppCompatActivity() {
	private lateinit var viewModel: HarryPotterViewModel
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		viewModel = ViewModelProvider(this).get(HarryPotterViewModel::class.java)
		val txt = findViewById<TextView>(R.id.textbox)
		viewModel.getStaff()
		txt.text = viewModel.test
		//getAllCharacters()

		createRV()
	}

	fun createRV(){
		val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

		// this creates a vertical layout Manager
		recyclerview.layoutManager = LinearLayoutManager(this)

		// ArrayList of class ItemsViewModel
		val data = ArrayList<ItemsViewModel>()

		// This loop will create 20 Views containing
		// the image with the count of view
		for (i in 1..20) {
			data.add(ItemsViewModel(R.drawable.ic_launcher_background, "Item " + i))
		}

		// This will pass the ArrayList to our Adapter
		val adapter = RVAdapter(data)

		// Setting the Adapter with the recyclerview
		recyclerview.adapter = adapter
	}

}