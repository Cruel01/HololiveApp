package com.example.hololiveapp.ui.theme.page.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hololiveapp.VMFactory
import com.example.hololiveapp.injection.Injection
import com.example.hololiveapp.model.Merch
import com.example.hololiveapp.ui.theme.common.UiState
import com.example.hololiveapp.R
import com.example.hololiveapp.ui.theme.component.LayoutMenu
import com.example.hololiveapp.ui.theme.component.SearchBar

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    viewModel: HomeVM = viewModel(
        factory = VMFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    val searchQueryState = remember {
        mutableStateOf("")
    }

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllMerch()
            }
            is UiState.Success -> {
                val filteredMerch = uiState.data.filter { merch ->
                    merch.member.name.contains(searchQueryState.value, ignoreCase = true)
                }
                HomeContent(
                    merch = filteredMerch,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    searchQuery = searchQueryState.value,
                    onQueryChange = { query ->
                        searchQueryState.value = query
                    }

                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    merch: List<Merch>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    searchQuery: String,
    onQueryChange: (String) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            onQueryChange = onQueryChange
        )

        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
        ) {
            items(merch) { data ->
                LayoutMenu(
                    name = data.member.name,
                    gen = data.member.gen,
                    photoUrl = data.member.photoUrl,
                    price = data.member.price,
                    modifier = Modifier.clickable {
                        navigateToDetail(data.member.id)
                    }
                )
            }
        }
    }
}

