package com.jazz.namesapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jazz.namesapp.R
import com.jazz.namesapp.data.Name
import com.jazz.namesapp.ui.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val context = LocalContext.current

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var name by remember { mutableStateOf("") }

    val namesList by viewModel.names.collectAsState(emptyList())

    Scaffold(
        topBar = {
            NamesAppBar()
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        content = { padding ->
            MainScreenContent(
                name = name,
                namesList = namesList,
                onTextFieldValueChange = { name = it },
                onClickAddName = {
                    if (name.isNotBlank()) {
                        viewModel.addName(name)
                        name = ""
                        scope.launch {
                            snackbarHostState.showSnackbar(context.getString(R.string.name_added))
                        }
                    }
                },
                onClickDelete = { index ->
                    viewModel.deleteName(namesList[index])
                },
                modifier = Modifier.padding(padding)
            )
        }
    )
}

@Composable
private fun MainScreenContent(
    name: String,
    namesList: List<Name>,
    onTextFieldValueChange: (String) -> Unit,
    onClickAddName: () -> Unit,
    onClickDelete: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.name_form_title),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Left
        )
        TextField(
            value = name,
            onValueChange = onTextFieldValueChange,
            label = { Text(stringResource(R.string.name_form_hint)) },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClickAddName,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(R.string.name_button))
        }
        if (namesList.isNotEmpty()) {
            Divider(thickness = 1.dp)
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(namesList.size) { index ->
                Card(
                    backgroundColor = MaterialTheme.colors.surface,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = namesList[index].name,
                            style = MaterialTheme.typography.body1
                        )
                        IconButton(
                            onClick = { onClickDelete(index) },
                            modifier = Modifier.size(16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = stringResource(R.string.favorite),
                                tint = MaterialTheme.colors.error
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    AppTheme(darkTheme = false) {
        Surface {
            MainScreenContent(
                name = "Jasmin",
                namesList = listOf(
                    Name(name = "Ivan"),
                    Name(name = "Luka"),
                    Name(name = "Mladen")
                ),
                onTextFieldValueChange = {},
                onClickAddName = {},
                onClickDelete = {}
            )
        }
    }
}