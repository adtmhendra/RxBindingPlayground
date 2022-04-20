package com.example.rxbindingplayground.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxbindingplayground.model.SearchedGame
import com.example.rxbindingplayground.network.SearchGameApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val _listSearchGame = MutableLiveData<List<SearchedGame>>()
    val listSearchGame: LiveData<List<SearchedGame>> = _listSearchGame

    fun getSearchedGameData(query: String) {
        CompositeDisposable().add(
            SearchGameApi.retrofitService.getSearchedGameData(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ searchedGameResponse ->
                    _listSearchGame.value = searchedGameResponse.results
                    Log.d("MainViewModel", searchedGameResponse.results.toString())
                }, { error ->
                    Log.e("MainViewModel", "Error : ${error.message.toString()}")
                })
        )
    }
}