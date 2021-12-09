package com.example.practiceapp.UI

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.practiceapp.Data.ImageApplication
import com.example.practiceapp.Data.ImageEntity
import com.example.practiceapp.R
import com.example.practiceapp.databinding.ActivityMainBinding

private const val PICK_IMAGE = 123
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(),ImageAdapter.onImageClickListener {
    lateinit private var binding : ActivityMainBinding
    lateinit private var model : ImageViewModel
    lateinit private var image : String
    lateinit private var contracts:ActivityResultLauncher<Intent>
    private val _adapter by lazy{
        ImageAdapter(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        image=""
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        model = ViewModelProvider(this,ImageViewModelFactory((application as ImageApplication).repository)).get(ImageViewModel::class.java)
        binding.recyclerView.apply{
            adapter = _adapter
            layoutManager = GridLayoutManager(this@MainActivity,2)
        }
        model.images.observe(this){
            _adapter.submitList(it)
        }
        binding.fab.setOnClickListener{
            addImage()
        }

        binding.clear.setOnClickListener {
            model.deleteAllImages()
        }
        contracts = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
            Log.e(TAG,"executed here")
            if(result.resultCode== Activity.RESULT_OK)
            {
                image = result.data!!.data.toString()
                model.insertThisImage(ImageEntity(image))
            }
        }



    }
    fun addImage(){
        val intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        Log.e(TAG,"wuz here")
        contracts.launch(Intent.createChooser(intent,"select picture"))
    }
    override fun getImage(image: ImageEntity) {
        model.deleteThisImage(image)
    }
}