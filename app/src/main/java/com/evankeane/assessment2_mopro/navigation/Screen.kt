package com.evankeane.assessment2_mopro.navigation

import com.evankeane.assessment2_mopro.ui.theme.screen.KEY_ID_KENDARAAN

sealed class Screen(val route: String) {
    data object Home : Screen("mainScreen")
    data object FormTambah : Screen("formKendaraan")
    data object FormUbah : Screen("formKendaraan/{$KEY_ID_KENDARAAN}") {
            fun withId(id: Long) = "formKendaraan/$id"
    }
}