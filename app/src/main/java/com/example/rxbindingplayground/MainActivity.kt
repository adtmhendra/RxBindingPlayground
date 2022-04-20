package com.example.rxbindingplayground

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.rxbindingplayground.databinding.ActivityMainBinding
import com.example.rxbindingplayground.viewmodel.MainViewModel
import com.jakewharton.rxbinding4.widget.textChanges

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            lifecycleOwner = this@MainActivity

            editText.apply {
                requestFocus()
                textChanges() // Perubahan teks
                    .filter { it.length > 3 } // Request API ketika inputan user di atas 3
                    .subscribe { text ->
                        viewModel.getSearchedGameData(text.toString())
                        Toast.makeText(this@MainActivity, text.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
            }
        }

        viewModel.listSearchGame.observe(this) { listSearchedGame ->
            binding.textView.text = listSearchedGame.toString()
            Log.d("MainViewModelActivity", listSearchedGame.toString())
        }
    }
}