package com.can.besinlerkitabi.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.can.besinlerkitabi.model.Besin
import com.can.besinlerkitabi.servis.BesinAPIServis
import com.can.besinlerkitabi.servis.BesinDatabase
import com.can.besinlerkitabi.util.OzelSharedPreference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class BesinListesiViewModel(application: Application) : BaseViewModel(application) {

    val besinler = MutableLiveData<List<Besin>>()
    val besinHataMesaji = MutableLiveData<Boolean>()
    val besinYukleniyor = MutableLiveData<Boolean>()

    //zamanını nano time a çevirme
    private var guncellemeZamani = 10 * 60 * 1000 * 1000 * 1000L


    private val besinApiServis = BesinAPIServis()
    private val disposable = CompositeDisposable()

    private val ozelSharedPreference = OzelSharedPreference(getApplication())


    fun refreshData() {
/*
//--------------------BİZİM OLUŞTURDUĞUMUZ VERİLER--------------------
        val muz = Besin("muz", "100", "10", "5", "1", "www.test.com")
        val cilek = Besin("cilek", "102", "122", "1", "9", "www.test.com")
        val elma = Besin("elma", "133", "102", "2", "3", "www.test.com")

        val besinListesi = arrayListOf<Besin>(muz,cilek,elma)

        besinler.value = besinListesi
        besinHataMesaji.value = false
        besinYukleniyor.value = false

 */
        val kaydedilmeZamani = ozelSharedPreference.zamaniAl()
        if(kaydedilmeZamani != null && kaydedilmeZamani != 0L && System.nanoTime() - kaydedilmeZamani < guncellemeZamani){
            //Sqlite dan al
            verileriSQLitetanAl()
        }
        else{

            verileriInternettenAl()
        }

    }


    fun refreshFromInternet(){
        verileriInternettenAl()
    }


    private fun verileriSQLitetanAl(){

        besinYukleniyor.value = true

        launch {

            val besinListesi = BesinDatabase(getApplication()).besinDAO().getAllBesin()
            besinleriGoster(besinListesi)
            Toast.makeText(getApplication(),"Besinleri Roomdan Aldık",Toast.LENGTH_LONG).show()
        }

    }



    private fun verileriInternettenAl(){

        besinYukleniyor.value = true

        //IO,Default,UI threads

        disposable.add(
            besinApiServis.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object  : DisposableSingleObserver<List<Besin>>(){

                    override fun onSuccess(t: List<Besin>) {
                        //başarılı olursa...
                        sqliteSakla(t)
                        Toast.makeText(getApplication(),"Besinleri İnternetten Aldık",Toast.LENGTH_LONG).show()

                    }

                    override fun onError(e: Throwable) {

                        besinHataMesaji.value = true
                        besinYukleniyor.value = false
                        e.printStackTrace()
                    }

                })
        )

    }


    private fun besinleriGoster(besinlerListesi : List<Besin> ){

        besinler.value = besinlerListesi
        besinHataMesaji.value = false
        besinYukleniyor.value = false
    }


    private fun sqliteSakla(besinListesi : List<Besin>){

        launch {

            val dao = BesinDatabase(getApplication()).besinDAO()
            dao.deleteAllBesin()
            val uuidListesi = dao.insertAll(*besinListesi.toTypedArray())

            var  i = 0
            while (i < besinListesi.size){

                besinListesi[i].uuid = uuidListesi[i].toInt()
                i = i+1
            }

            besinleriGoster(besinListesi)
        }

        ozelSharedPreference.zamaniKaydet(System.nanoTime())

    }


}