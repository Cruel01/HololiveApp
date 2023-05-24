package com.example.hololiveapp.ui.theme.page.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hololiveapp.R

@Preview
@Composable
fun AboutPage(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopAppBar(backgroundColor = Color(0xFFE8AA42)) {
            Text(
                text = stringResource(R.string.Profile),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Text(stringResource(R.string.Profile))
        }
        Image(
            painter = painterResource(R.drawable.foto1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .height(480.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
        )
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text (
                text = stringResource(R.string.name),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            Text (
                text = stringResource(R.string.email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(),
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }

    }
}