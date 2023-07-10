package com.example.testapp

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalMaterialNavigationApi
@ExperimentalFoundationApi
@Composable
fun InnerNavigation(
    bottomPadding: PaddingValues,
    stateHolder: StateHolder,
    innerNavController: NavHostController,
    rootNavController: NavHostController,
    onDrawerClick: () -> Unit
) {

    NavHost(
        navController = innerNavController,
        startDestination = "route3",
        enterTransition = { fadeIn(tween(300)) },
        exitTransition = { fadeOut(tween(300)) }
    ) {

        composable("route3") {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottomPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                text = "route3",
                                style = MaterialTheme.typography.titleLarge,
                                textAlign = TextAlign.Center
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    onDrawerClick()
                                },
                            ) {
                                Icon(imageVector = Rounded.Menu, contentDescription = "")
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            stateHolder.currentInfoForShow.value = "Info 1"
                            rootNavController.navigate("sheetRoute1") {
                                launchSingleTop = true
                            }
                        },
                        contentPadding = PaddingValues(32.dp)
                    ) {
                        Text(text = "Navigate to sheetRoute1", color = Color.White)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            stateHolder.currentInfoForShow.value = "Info 2"
                            rootNavController.navigate("sheetRoute1") {
                                launchSingleTop = true
                            }
                        },
                        contentPadding = PaddingValues(32.dp)
                    ) {
                        Text(text = "Navigate to sheetRoute1", color = Color.White)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    FlowRow {
                        (0..500).forEach {
                            Icon(Rounded.Image, contentDescription = "")
                        }
                    }
                }
            }
        }

        composable("route4") {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottomPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "route4", style = MaterialTheme.typography.titleLarge)
            }
        }

        composable("route5") {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottomPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "route5", style = MaterialTheme.typography.titleLarge)
            }
        }

        composable("route6") {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottomPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "route6", style = MaterialTheme.typography.titleLarge)
            }
        }


    }
}