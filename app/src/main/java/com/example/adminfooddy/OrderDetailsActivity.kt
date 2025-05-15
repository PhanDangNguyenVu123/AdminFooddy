package com.example.adminfooddy

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminfooddy.adapter.OrderDetailsAdapter
import com.example.adminfooddy.databinding.ActivityOrderDetailsBinding
import com.example.adminfooddy.model.OrderDetails

class OrderDetailsActivity : AppCompatActivity() {
    private val binding: ActivityOrderDetailsBinding by lazy {
        ActivityOrderDetailsBinding.inflate(layoutInflater)
    }
    private  var userName: String? = null
    private  var address: String? = null
    private  var phoneNumber: String? = null
    private  var totalPrice: String? = null
    private  var foodName : ArrayList<String> = arrayListOf()
    private  var foodImages : ArrayList<String> = arrayListOf()
    private  var foodQuantity : ArrayList<Int> = arrayListOf()
    private  var foodPrices : ArrayList<String> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }
        getDataFromIntent()
    }

    private fun getDataFromIntent() {
        val receivedOrderDetails = intent.getSerializableExtra("UserOrderDetails") as OrderDetails
        receivedOrderDetails?.let { orderDetails ->

                userName = receivedOrderDetails.userName
                foodName = receivedOrderDetails.foodNames as ArrayList<String>
                foodImages = receivedOrderDetails.foodImages as ArrayList<String>
                foodQuantity = receivedOrderDetails.foodQuantities as ArrayList<Int>
                address = receivedOrderDetails.address
                phoneNumber = receivedOrderDetails.phoneNumber
                foodPrices = receivedOrderDetails.foodPrices as ArrayList<String>
                totalPrice = receivedOrderDetails.totalPrice

                setUserDetail()
                setAdapter()

        }

    }


    private fun setUserDetail() {
        binding.name.text = userName
        binding.address.text = address
        binding.phone.text = phoneNumber
        binding.totalPay.text = totalPrice

    }
    private fun setAdapter() {
        binding.orderDetailRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = OrderDetailsAdapter(this,foodName,foodImages,foodQuantity,foodPrices)
        binding.orderDetailRecyclerView.adapter = adapter
    }
}
