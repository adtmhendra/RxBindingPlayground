package com.example.rxbindingplayground

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.rxbindingplayground.databinding.ActivityMainBinding
import com.example.rxbindingplayground.viewmodel.MainViewModel
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            lifecycleOwner = this@MainActivity

            //--> Implementasi RxBinding <--//
            editText.requestFocus()
            editText.textChanges() // Perubahan teks
                .filter { it.length > 3 } // Melakukan request API ketika text inputan user sudah di atas 3
                .debounce(500, TimeUnit.MILLISECONDS) // Melakukan pencarian ketika user berhenti mengetik selama 0.5 detik
                .subscribeOn(Schedulers.io()) // Menentukan operasi observable akan dilakukan di thread I/O (background thread)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { text ->
                    viewModel.getSearchedGameData(text.toString())
                    Toast.makeText(this@MainActivity, text.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
        }

        viewModel.listSearchGame.observe(this) { listSearchedGame ->
            binding.textView.text = listSearchedGame.toString()
            Log.d("MainViewModelActivity", listSearchedGame.toString())
        }
    }
}