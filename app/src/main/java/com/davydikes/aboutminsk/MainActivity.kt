package com.davydikes.aboutminsk

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.davydikes.aboutminsk.databinding.ActivityMainBinding
import com.davydikes.aboutminsk.support.SupportActivityInset
import com.davydikes.aboutminsk.support.setWindowTransparency
import kotlinx.coroutines.*

class MainActivity : SupportActivityInset<ActivityMainBinding>(){
    override lateinit var viewBinding: ActivityMainBinding

    private val navHostFragment by lazy { (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setWindowTransparency(this)
    }

    override fun getActiveFragment(): Fragment? {
        return navHostFragment.childFragmentManager.fragments[0]
    }
}

/*
{"id_locale": 124,
"id": 1,
"name": "Minsk",
"lang": 4,
"logo": "https://krokapp.by/media/cities/c5fa77a4-eb04-40c8-9659-11ea334236b1.png",
"last_edit_time": 1508924729,
"visible": true,
"city_is_regional": true,
"region": "Minsk region"}
 */