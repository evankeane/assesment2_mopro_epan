package com.evankeane.assessment2_mopro.ui.theme.screen


import androidx.lifecycle.ViewModel
import com.evankeane.assessment2_mopro.model.Kendaraan

class MainViewModel : ViewModel() {

    val data = listOf(
        Kendaraan(
            1,
            "Honda Civic",
            "Hitam",
            "2020"
        ),
        Kendaraan(
            2,
            "Toyota Supra",
            "Merah",
            "2021"
        )

    )
}