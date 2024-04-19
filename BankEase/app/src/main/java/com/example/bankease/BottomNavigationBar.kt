package com.example.bankease

import com.example.bankease.data.BottomNavigation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Wallet
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable


val items = listOf(
    BottomNavigation(
        title = "Home",
        icon = Icons.Rounded.Home
    ),

    BottomNavigation(
        title = "Wallet",
        icon = Icons.Rounded.Wallet
    ),

    BottomNavigation(
        title = "Notifications",
        icon = Icons.Rounded.Notifications
    ),

    BottomNavigation(
        title = "Accounts",
        icon = Icons.Rounded.AccountCircle
    )
)

@Composable
fun BottomNavigationBar(){
    NavigationBar {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {

            items.forEachIndexed { index, item ->
                NavigationBarItem {
                    selected = index == 0,
                    onClick = {},
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }

        }
    }
}























