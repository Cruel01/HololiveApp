package com.example.hololiveapp.ui.theme.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.hololiveapp.R
import com.example.hololiveapp.ui.theme.HololiveAppTheme

@Composable
fun MerchCart(
    merchId: Long,
    name: String,
    photoUrl: String,
    totalPrice: Int,
    count: Int,
    countChanged: (id: Long, count: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(92.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = name,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = stringResource(
                    R.string.Rp,
                    totalPrice
                ),
                color = Color.Black,
                style = MaterialTheme.typography.subtitle2,
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
        ) {
            MerchCount(
                merchId = merchId,
                merchCount = count,
                Increased = { countChanged(merchId, count + 1) },
                Decreased = { countChanged(merchId, count - 1) },
                modifier = Modifier.padding(8.dp)
            )
        }


    }
}

@Composable
@Preview(showBackground = true)
fun merchCartPreview() {
    HololiveAppTheme() {
        MerchCart(
            4, "Shirakami Fubuki", "", 60000, 0,
            countChanged = { id, count -> },
        )
    }
}