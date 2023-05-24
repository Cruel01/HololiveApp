package com.example.hololiveapp.ui.theme.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hololiveapp.R

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onQueryChange: (String) -> Unit,
) {
    var query by remember { mutableStateOf("") }

    TextField(
        value = query,
        onValueChange = { value ->
            query = value
            onQueryChange(value)
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0xFFE8AA42),
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            Text(stringResource(R.string.cari))
        },
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .heightIn(min = 48.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search"
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
    )
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    SearchBar(modifier = Modifier, onQueryChange = {})
}