package com.evankeane.assessment2_mopro.ui.theme.screen


import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.evankeane.assessment2_mopro.R
import com.evankeane.assessment2_mopro.ui.theme.Assessment2_MoproTheme

const val KEY_ID_KENDARAAN = "idKendaraan"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormKendaraan(navController: NavHostController,id:Long?=null) {
    val viewModel:MainViewModel= viewModel()
    var merk by remember { mutableStateOf("") }
    var warna by remember { mutableStateOf("") }
    var tahun by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        if (id==null) return@LaunchedEffect
        val data = viewModel.getCatatan(id) ?: return@LaunchedEffect
        merk =data.merk
        warna =data.warna
        tahun = data.tahun
    }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.tambah_kendaraan))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                }
            )
        }
    ) { padding ->
        FormCatatan(
            merk = merk,
            onMerkChange = {merk = it},
            warna = warna,
            onWarnaChange = {warna = it},
            tahun = tahun,
            onTahunChange = {tahun = it},

            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun FormCatatan(
    merk : String, onMerkChange: (String) ->Unit,
    warna : String, onWarnaChange: (String) -> Unit,
    tahun : String, onTahunChange: (String) -> Unit,
    modifier: Modifier
){
    Column (
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ){
        OutlinedTextField(
            value = merk,
            onValueChange = {onMerkChange(it)},
            label = { Text(text = stringResource(R.string.merk)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = warna,
            onValueChange = {onWarnaChange(it)},
            label = { Text(text = stringResource(R.string.warna)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = tahun,
            onValueChange = {onTahunChange(it)},
            label = { Text(text = stringResource(R.string.tahun)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview(){
    Assessment2_MoproTheme  {
        FormKendaraan(rememberNavController())
    }
}