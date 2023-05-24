package com.example.hololiveapp.ui.theme.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import com.example.hololiveapp.ui.theme.HololiveAppTheme
import com.example.hololiveapp.R

@Composable
fun LayoutMenu(
    name: String,
    gen: String,
    photoUrl: String,
    price : Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        Color(0xFFC4DFDF),

    ) {
        Column(
            modifier = modifier
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .height(160.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = name,
                    maxLines = 2,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = gen,
                    maxLines = 2,
                    color = Color.Black,
                    style = MaterialTheme.typography.body2,
                )

            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = stringResource(R.string.Rp, price),
                    color = Color.Black,
                    style = MaterialTheme.typography.h6,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun listMemberPreview() {
    HololiveAppTheme() {
        LayoutMenu(
            "Shirakami Fubuki",
            "Holoive Japan Generation 1",
            "",
            2000,
            modifier = Modifier.padding(8.dp)
        )
    }
}