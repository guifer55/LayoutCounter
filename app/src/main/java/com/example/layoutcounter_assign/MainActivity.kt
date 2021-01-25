package com.example.layoutcounter_assign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import com.example.layoutcounter_assign.databinding.ActivityMainBinding
import androidx.databinding.DataBindingUtil



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val count: Count = Count("0")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.count = count
        binding.apply {
            toastBtn.setOnClickListener(){
                Toast.makeText(this@MainActivity, count!!.counter, Toast.LENGTH_SHORT).show()
            }
            countDownBtn.setOnClickListener{
                count?.counter = (count!!.counter.toInt() -1).toString()
                countShow.text = count?.counter
                invalidateAll()

            }
            countUpBtn.setOnClickListener{
                count?.counter = (count!!.counter.toInt() +1).toString()
                countShow.text = count?.counter
                invalidateAll()

            }

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count.counter", count.counter.toInt())

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.let{
            count.counter = it.getInt("count.counter").toString()
            binding.countShow.text = count.counter.toString()
        }
    }
}