package com.example.harrypottercaracters

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypottercaracters.adapters.RVAdapter
import com.example.harrypottercaracters.models.all.All
import com.example.harrypottercaracters.models.house.House
import com.example.harrypottercaracters.models.other.RVViewModel
import com.example.harrypottercaracters.models.staff.Staffs
import com.example.harrypottercaracters.models.students.Students


class MainActivity : AppCompatActivity() {
	private lateinit var viewModel: HarryPotterViewModel
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		viewModel = ViewModelProvider(this).get(HarryPotterViewModel::class.java)
		getAll()
		val allBtn = findViewById<Button>(R.id.allBtn)
		val staffBtn = findViewById<Button>(R.id.staffBtn)
		val studentBtn = findViewById<Button>(R.id.studentBtn)
		val houseBtn = findViewById<Button>(R.id.houseBtn)

		allBtn.setOnClickListener { getAll()}
		staffBtn.setOnClickListener {getStaff()}
		studentBtn.setOnClickListener {getStudents()}
		houseBtn.setOnClickListener {


			housePicker()
		}


	}

	private  fun getStaff(){
		if (checkForInternet(this) == true) {
			viewAllStaffOffline()
		}
		val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
		recyclerview.layoutManager = LinearLayoutManager(this)
		val data = ArrayList<RVViewModel>()

	viewModel.getStaffLiveObserver().observe(this, Observer<Staffs> {
		if (it != null) {
			run {
				for (i in 0 until it.size) {
				data.add(
					RVViewModel(
						it[i].actor,
						it[i].name,
						it[i].house,
						it[i].image
				)
			)
				}
		val adapter = RVAdapter(data)
		recyclerview.adapter = adapter
		}}
	else{

		println("NO WIFI")
	}

})
		viewModel.makeAPiStaffCall()

	}










	private  fun getStudents(){
		if (checkForInternet(this) == true) {
			viewStudentUsersOffline()
		}
		val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
		recyclerview.layoutManager = LinearLayoutManager(this)
		val data = ArrayList<RVViewModel>()
		viewModel.getStudentLiveObserver().observe(this, Observer<Students> {
			if (it != null) {
				run {
					for (i in 0 until it.size) {
						data.add(
							RVViewModel(
								it[i].actor,
								it[i].name,
								it[i].house,
								it[i].image
							)
						)}
					val adapter = RVAdapter(data)
					recyclerview.adapter = adapter
				}}
			else{

				println("NO WIFI")
			}

		})
		viewModel.makeAPiStudentCall()

	}



	private  fun getAll(){
		if (checkForInternet(this) == true) {
			viewAllUsersOffline()
		}
		val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
		recyclerview.layoutManager = LinearLayoutManager(this)
		val data = ArrayList<RVViewModel>()
		viewModel.getAllLiveObserver().observe(this, Observer<All> {

			if (it != null) {
				run {
					for (i in 0 until it.size) {
						data.add(
							RVViewModel(
								it[i].actor,
								it[i].name,
								it[i].house,
								it[i].image

							)
						)}
					val adapter = RVAdapter(data)
					recyclerview.adapter = adapter
				}}

			else {

				println("NO WIFI")
			}

		})
		viewModel.makeAPiAllCall()

	}

	private  fun getHouse(houseType: String) {

		if (checkForInternet(this) == true) {

			if(houseType == "gryffindor"){
				viewAllGrifOffline()
			}
				else if( houseType == "Slytherin"){
				viewAllSlyOffline()
			}
			else if( houseType == "Hufflepuff"){
				viewHuffUsersOffline()
			}
			else if( houseType == "Ravenclaw"){
				viewRavUsersOffline()
			}



		}
		val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
		recyclerview.layoutManager = LinearLayoutManager(this)
		val data = ArrayList<RVViewModel>()
		viewModel.getHouseLiveObserver().observe(this, Observer<House> {
			if (it != null) {
				run {
					for (i in 0 until it.size) {
						data.add(
							RVViewModel(
								it[i].actor,
								it[i].name,
								it[i].house,
								it[i].image

							)
						)
					}
					val adapter = RVAdapter(data)
					recyclerview.adapter = adapter
				}
			}


			else {

			}

		})
		viewModel.makeAPiHouseCall(houseType)

	}


	fun housePicker(){
		val builder = AlertDialog.Builder(this)
		builder.setTitle("Title")
		builder.setItems(
			arrayOf<CharSequence>("Gryffindor", "Slytherin","Hufflepuff", "Ravenclaw")
		) { dialog, which -> // The 'which' argument contains the index position
			// of the selected item
			when (which) {
				0 -> getHouse("gryffindor")
				1 -> getHouse("Slytherin")
				2 -> getHouse("Hufflepuff")
				3 -> getHouse("Ravenclaw")
			}
		}
		builder.create().show()

}


	fun viewAllUsersOffline(){
		val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
		recyclerview.layoutManager = LinearLayoutManager(this)
		val data = ArrayList<RVViewModel>()
		viewModel.readAllData.observe(this, {
			if (it != null) {
				run {
					for (i in 0 until it.size) {
						data.add(
							RVViewModel(
								it[i].actor,
								it[i].name,
								it[i].house,
								it[i].image

							)
						)
					}
					val adapter = RVAdapter(data)
					recyclerview.adapter = adapter
				}
			}
			else {

			}

		})
	}



	fun viewStudentUsersOffline(){
		val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
		recyclerview.layoutManager = LinearLayoutManager(this)
		val data = ArrayList<RVViewModel>()
		viewModel.readStudentData.observe(this, {
			if (it != null) {
				run {
					for (i in 0 until it.size) {
						data.add(
							RVViewModel(
								it[i].actor,
								it[i].name,
								it[i].house,
								it[i].image

							)
						)
					}
					val adapter = RVAdapter(data)
					recyclerview.adapter = adapter
				}
			}
			else {

			}

		})
	}

	fun viewAllStaffOffline(){
		val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
		recyclerview.layoutManager = LinearLayoutManager(this)
		val data = ArrayList<RVViewModel>()
		viewModel.readAllStaffData.observe(this, {
			if (it != null) {
				run {
					for (i in 0 until it.size) {
						data.add(
							RVViewModel(
								it[i].actor,
								it[i].name,
								it[i].house,
								it[i].image

							)
						)
					}
					val adapter = RVAdapter(data)
					recyclerview.adapter = adapter
				}
			}
			else{

			}

		})
	}
			fun viewAllGrifOffline(){
				val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
				recyclerview.layoutManager = LinearLayoutManager(this)
				val data = ArrayList<RVViewModel>()
				viewModel.readGrifData.observe(this, {
					if (it != null) {
						run {
							for (i in 0 until it.size) {
								data.add(
									RVViewModel(
										it[i].actor,
										it[i].name,
										it[i].house,
										it[i].image

									)
								)
							}
							val adapter = RVAdapter(data)
							recyclerview.adapter = adapter
						}
					}
					else {

					}

				})
			}

	fun viewAllSlyOffline(){
		val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
		recyclerview.layoutManager = LinearLayoutManager(this)
		val data = ArrayList<RVViewModel>()
		viewModel.readSlyData.observe(this, {
			if (it != null) {
				run {
					for (i in 0 until it.size) {
						data.add(
							RVViewModel(
								it[i].actor,
								it[i].name,
								it[i].house,
								it[i].image

							)
						)
					}
					val adapter = RVAdapter(data)
					recyclerview.adapter = adapter
				}
			}
			else {

			}

		})
	}

	fun viewRavUsersOffline(){
		val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
		recyclerview.layoutManager = LinearLayoutManager(this)
		val data = ArrayList<RVViewModel>()
		viewModel.readRavData.observe(this, {
			if (it != null) {
				run {
					for (i in 0 until it.size) {
						data.add(
							RVViewModel(
								it[i].actor,
								it[i].name,
								it[i].house,
								it[i].image

							)
						)
					}
					val adapter = RVAdapter(data)
					recyclerview.adapter = adapter
				}
			}
			else {

			}

		})
	}

	fun viewHuffUsersOffline(){
		val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
		recyclerview.layoutManager = LinearLayoutManager(this)
		val data = ArrayList<RVViewModel>()
		viewModel.readHufData.observe(this, {
			if (it != null) {
				run {
					for (i in 0 until it.size) {
						data.add(
							RVViewModel(
								it[i].actor,
								it[i].name,
								it[i].house,
								it[i].image

							)
						)
					}
					val adapter = RVAdapter(data)
					recyclerview.adapter = adapter
				}
			}
			else {

			}

		})
	}
	private fun checkForInternet(context: Context): Boolean {

		// register activity with the connectivity manager service
		val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

		// if the android version is equal to M
		// or greater we need to use the
		// NetworkCapabilities to check what type of
		// network has the internet connection
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

			// Returns a Network object corresponding to
			// the currently active default data network.
			val network = connectivityManager.activeNetwork ?: return false

			// Representation of the capabilities of an active network.
			val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

			return when {
				// Indicates this network uses a Wi-Fi transport,
				// or WiFi has network connectivity
				activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

				// Indicates this network uses a Cellular transport. or
				// Cellular has network connectivity
				activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

				// else return false
				else -> false
			}
		} else {
			// if the android version is below M
			@Suppress("DEPRECATION") val networkInfo =
				connectivityManager.activeNetworkInfo ?: return false
			@Suppress("DEPRECATION")
			return networkInfo.isConnected
		}
	}
}










