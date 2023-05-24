package com.example.hololiveapp.ui.theme.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hololiveapp.ui.theme.HololiveAppTheme

@Composable
fun MerchCount(
    merchId: Long,
    merchCount: Int,
    Increased: (Long) -> Unit,
    Decreased: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .size(width = 124.dp, height = 48.dp)
            .padding(4.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(size = 8.dp),
            border = BorderStroke(1.dp, MaterialTheme.colors.primary),
            color = Color.Transparent,
            contentColor = MaterialTheme.colors.primary,
            modifier = Modifier.size(30.dp)
        ) {
            Text(
                text = "➖",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        Decreased(merchId)
                    }
            )
        }
        Text(
            text = merchCount.toString(),
            modifier = Modifier
                .weight(1f),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Surface(
            shape = RoundedCornerShape(size = 8.dp),
            border = BorderStroke(1.dp, MaterialTheme.colors.primary),
            color = Color.Transparent,
            contentColor = MaterialTheme.colors.primary,
            modifier = Modifier.size(30.dp)
        ) {
            Text(
                text = "➕",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        Increased(merchId)
                    }
            )
        }
    }
}

@Preview
@Composable
fun merchCountPreview() {
    HololiveAppTheme {
        MerchCount(
            merchId = 1,
            merchCount = 0,
            Increased = { },
            Decreased = { }
        )
    }
}