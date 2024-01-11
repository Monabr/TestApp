package com.example.testapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessAlarm
import androidx.compose.material.icons.rounded.Apartment
import androidx.compose.material.icons.rounded.Cottage
import androidx.compose.material.icons.rounded.DirectionsBike
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.testapp.BottomNavigationItem.Route3
import com.example.testapp.BottomNavigationItem.Route4
import com.example.testapp.BottomNavigationItem.Route5
import com.example.testapp.BottomNavigationItem.Route6

@Composable
fun BottomNavigationBar(
    innerNavController: NavController,
) {
    val context = LocalContext.current

    val items = remember {
        mutableStateOf(
            listOf(
                Route3,
                Route4,
                Route5,
                Route6,
            ),
        )
    }

    NavigationBar {
        val navBackStackEntry by innerNavController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.value.forEach { item ->
            val isSelected = currentRoute == item.route

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        maxLines = 1,
                        overflow = TextOverflow.Visible,
                        softWrap = false,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = if (isSelected) {
                            FontWeight.SemiBold
                        } else {
                            null
                        },
                    )
                },
                alwaysShowLabel = true,
                selected = isSelected,
                onClick = {
                    innerNavController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        innerNavController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
            )
        }
    }
}

sealed class BottomNavigationItem(
    var route: String,
    var icon: ImageVector,
    var title: String,
) {
    object Route3 : BottomNavigationItem("route3", Icons.Rounded.AccessAlarm, "Screen1")
    object Route4 : BottomNavigationItem("route4", Icons.Rounded.Apartment, "Screen2")
    object Route5 : BottomNavigationItem("route5", Icons.Rounded.DirectionsBike, "Screen3")
    object Route6 : BottomNavigationItem("route6", Icons.Rounded.Cottage, "Screen4")
}
