package com.evankeane.assessment2_mopro.ui.theme.screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

import com.evankeane.assessment2_mopro.R
import com.evankeane.assessment2_mopro.model.RecycleKendaraan

import com.evankeane.assessment2_mopro.util.ViewModelFactory



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecycleScreen(navController: NavController) {
    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val recycleKendaraan: RecycleViewModel = viewModel(factory = factory)

    // State untuk dialog
    var showDialog by remember { mutableStateOf(false) }
    var selectedId by remember { mutableStateOf<Long?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Data Sampah") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            DeletedItemGrid(
                recycleKendaraan = recycleKendaraan,
                onPermanentDeleteRequest = { id ->
                    selectedId = id
                    showDialog = true
                }
            )
        }



        if(showDialog && selectedId != null){
            DisplayAlertDialog(
                onDismissRequest = {showDialog = false}
            ){
                showDialog = false
                recycleKendaraan.permanentDelete(selectedId!!)
                navController.popBackStack()
            }
        }
    }
}

@Composable
fun DeletedItemGrid(
    recycleKendaraan: RecycleViewModel,
    onPermanentDeleteRequest: (Long) -> Unit
) {
    val deletedItems = recycleKendaraan.deletedItems.collectAsState().value

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(deletedItems) { item ->
            DeletedItemCard(
                item = item,
                onRestore = { recycleKendaraan.restoreKendaraan(item.id) },
                onPermanentDelete = { onPermanentDeleteRequest(item.id) }
            )
        }
    }
}

@Composable
fun DeletedItemCard(
    item: RecycleKendaraan,
    onRestore: () -> Unit,
    onPermanentDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = item.merk,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            Text(
                text = item.warna,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            Text(
                text = item.tahun,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onRestore) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Restore",
                        tint = Color.Green
                    )
                }
                IconButton(onClick = onPermanentDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Permanently",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}





//@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//fun RecycleScreenPreview() {
//    Assessment2_MoproTheme  {
//        RecycleScreen(rememberNavController())
//    }
//}