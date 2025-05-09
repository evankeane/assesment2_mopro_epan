package com.evankeane.assessment2_mopro.ui.theme.screen


import android.content.res.Configuration
import android.widget.Toast
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.evankeane.assessment2_mopro.util.ViewModelFactory

const val KEY_ID_KENDARAAN = "idKendaraan"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormKendaraan(navController: NavHostController,id:Long?=null) {
    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val viewModel: KendaraanViewModel = viewModel(factory = factory)
    var merk by remember { mutableStateOf("") }
    var warna by remember { mutableStateOf("") }
    var tahun by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        if (id==null) return@LaunchedEffect
        val data = viewModel.getKendaraan(id) ?: return@LaunchedEffect
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
                    if (id==null)
                        Text(text = stringResource(id = R.string.tambah_kendaraan))

                    else
                        Text(text = stringResource(R.string.edit_kendaraan))

                },
                actions = {
                    IconButton(onClick = {
                        if (merk == "" || warna == "" || tahun == "" ){
                            Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                            return@IconButton
                        }
                        if (id == null){
                            viewModel.insert(merk, warna, tahun)
                        }else{
                            viewModel.update(id, merk, warna, tahun)
                        }
                        navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    if(id != null){
                        DeleteAction {
                            viewModel.delete(id)
                            navController.popBackStack()
                        }
                    }


                }
            )
        }
    ) { padding ->
        FormKendaraan1(
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
fun FormKendaraan1(
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

@Composable
fun DeleteAction(delete: () -> Unit) {
    IconButton(onClick = { delete() }) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = stringResource(R.string.hapus),
            tint = MaterialTheme.colorScheme.error
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