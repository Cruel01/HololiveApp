package com.example.hololiveapp.ui.theme.page.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hololiveapp.VMFactory
import com.example.hololiveapp.injection.Injection
import com.example.hololiveapp.ui.theme.common.UiState
import com.example.hololiveapp.R
import com.example.hololiveapp.ui.theme.component.BuyMerch
import com.example.hololiveapp.ui.theme.component.MerchCart

@Composable
fun CartPage(
    viewModel: CartVM = viewModel(
        factory = VMFactory(
            Injection.provideRepository()
        )
    ),
    onOrderButtonClicked: (String) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllAddedMerch()
            }
            is UiState.Success -> {
                CartContent(
                    uiState.data,
                    countChange = { id, count ->
                        viewModel.updateAddedMerch(id, count)
                    },
                    buttonClicked = onOrderButtonClicked
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun CartContent(
    state: CartState,
    countChange: (id: Long, count: Int) -> Unit,
    buttonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val msg = stringResource(
        R.string.thanks,
        state.merch.count(),
        state.totalPrice
    )
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopAppBar(backgroundColor = MaterialTheme.colors.surface) {
            Text(
                text = stringResource(R.string.Cart),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
        BuyMerch(
            text = stringResource(R.string.Rp, state.totalPrice),
            enabled = state.merch.isNotEmpty(),
            onClick = {buttonClicked(msg)},
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(state.merch, key = { it.member.id }) { item ->
                MerchCart(
                    merchId = item.member.id,
                    name = item.member.name,
                    photoUrl = item.member.photoUrl,
                    totalPrice = item.member.price * item.count,
                    count = item.count,
                    countChanged = countChange,
                )
                Divider()
            }
        }
    }
}