package com.can.besinlerkitabi.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.can.besinlerkitabi.model.Besin
import com.can.besinlerkitabi.servis.BesinDatabase
import kotlinx.coroutines.launch

class BesinDetayiViewModel(application: Application) : BaseViewModel(application){

    val besinLiveData = MutableLiveData<Besin>()

    fun roomVerisiniAl(uuid : Int){
        /*
        val muz = Besin("muz", "100", "10", "5", "1", "www.test.com")
        besinLiveData.value = muz

         */

        launch {

            val dao = BesinDatabase(getApplication()).besinDAO()
            val besin = dao.getBesin(uuid)
            besinLiveData.value = besin

        }

    }




}