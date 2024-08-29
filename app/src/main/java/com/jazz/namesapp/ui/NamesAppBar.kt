package com.jazz.namesapp.ui

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.LocaleListCompat
import com.jazz.namesapp.R
import com.jazz.namesapp.ui.theme.AppTheme

@Composable
fun NamesAppBar(modifier: Modifier = Modifier) {
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h3
            )
        },
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(Icons.Default.MoreVert, stringResource(R.string.options))
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false },
                modifier = Modifier.background(MaterialTheme.colors.background)
            ) {
                DropdownMenuItem(
                    onClick = {
                        AppCompatDelegate.setApplicationLocales(
                            LocaleListCompat.forLanguageTags("en")
                        )
                        showMenu = false
                    }
                ) {
                    Text(stringResource(R.string.language_en))
                }
                DropdownMenuItem(
                    onClick = {
                        AppCompatDelegate.setApplicationLocales(
                            LocaleListCompat.forLanguageTags("hr")
                        )
                        showMenu = false
                    }
                ) {
                    Text(stringResource(R.string.language_hr))
                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewAppBar() {
    AppTheme {
        Surface {
            NamesAppBar()
        }
    }
}