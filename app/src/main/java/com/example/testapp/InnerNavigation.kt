package com.example.testapp

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi

@OptIn(ExperimentalAnimationApi::class)
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
    ) {

        composable("route3") {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottomPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                Text(text = "route3", style = MaterialTheme.typography.titleLarge)
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