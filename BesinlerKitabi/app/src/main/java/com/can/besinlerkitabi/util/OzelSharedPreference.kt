package com.can.besinlerkitabi.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import java.util.prefs.PreferenceChangeEvent

class OzelSharedPreference  {


    companion object{

        private val ZAMAN = "zaman"

        private var sharedPreference : SharedPreferences? = null

        @Volatile private var instance : OzelSharedPreference? = null

        private val lock = Any()

        operator fun invoke(context : Context) : OzelSharedPreference = instance ?: synchronized(lock) {

            instance ?: ozelSharedPreferencesYap(context).also {

                instance = it
            }

        }


        private fun ozelSharedPreferencesYap(context: Context) : OzelSharedPreference{

            sharedPreference = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return OzelSharedPreference()
        }
    }


    fun zamaniKaydet(zaman : Long) {

        sharedPreference?.edit(commit = true){

            putLong(ZAMAN,zaman)
        }
    }


    fun zamaniAl() = sharedPreference?.getLong(ZAMAN,0)


}