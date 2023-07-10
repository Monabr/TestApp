package com.example.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testapp.material.ModalBottomSheetLayoutCustom
import com.example.testapp.material.bottomSheet
import com.example.testapp.material.rememberBottomSheetNavigatorCustom
import com.example.testapp.ui.theme.TestAppTheme
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(
    ExperimentalMaterialNavigationApi::class, ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class, ExperimentalFoundationApi::class,
    com.example.testapp.material.ExperimentalMaterialNavigationApi::class, ExperimentalMaterial3Api::class
)
@Composable
fun MainScreen() {
    val stateHolder = rememberStateHolder()
    val bottomSheetNavigator = rememberBottomSheetNavigatorCustom()
    val rootNavController = rememberNavController(bottomSheetNavigator)

    ModalBottomSheetLayoutCustom(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetElevation = 0.dp,
        sheetBackgroundColor = Color.Transparent
    ) {
        NavHost(
            navController = rootNavController,
            startDestination = "route2",
            enterTransition = { fadeIn(tween(300)) },
            exitTransition = { fadeOut(tween(300)) }
        ) {
            composable(
                route = "route1"
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp),
                                    text = "route1",
                                    style = MaterialTheme.typography.titleLarge,
                                    textAlign = TextAlign.Center
                                )
                            },
                            navigationIcon = {
                                IconButton(
                                    onClick = {
                                        rootNavController.popBackStack()
                                    },
                                ) {
                                    Icon(imageVector = Rounded.ArrowBack, contentDescription = "")
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "route1", style = MaterialTheme.typography.titleLarge)
                        }
                    }
                }
            }
            composable(
                route = "route2"
            ) {
                val scope = rememberCoroutineScope()
                val drawerState = rememberDrawerState(Closed)

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    gesturesEnabled = drawerState.isOpen,
                    drawerContent = {
                        ModalDrawerSheet {
                            if (drawerState.isOpen) {
                                BackHandler {
                                    scope.launch { drawerState.close() }
                                }
                            }


                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(horizontal = 16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(
                                    contentPadding = PaddingValues(32.dp),
                                    onClick = {
                                        scope.launch { drawerState.close() }
                                        rootNavController.navigate("route1") {
                                            launchSingleTop = true
                                        }
                                    }
                                ) {
                                    Text(text = "Navigate to route1")
                                }
                            }
                        }
                    }
                ) {
                    val innerNavController = rememberNavController()

                    Scaffold(
                        bottomBar = {
                            Column {
                                BottomNavigationBar(
                                    innerNavController = innerNavController,
                                )
                            }
                        }
                    ) {
                        InnerNavigation(
                            bottomPadding = it,
                            stateHolder = stateHolder,
                            innerNavController = innerNavController,
                            rootNavController = rootNavController,
                            onDrawerClick = {
                                scope.launch { drawerState.open() }
                            }
                        )
                    }
                }
            }
            bottomSheet("sheetRoute1") {
                val viewModel = hiltViewModel<ExampleViewModel>()
                stateHolder.currentInfoForShow.value?.let { info ->
                    SomeSheetScreen(
                        viewModel = viewModel,
                        info = info
                    )
                }
            }
        }
    }
}

@Composable
private fun SomeSheetScreen(viewModel: ExampleViewModel, info: String) {
    Surface(
        modifier = Modifier.clip(
            MaterialTheme.shapes.extraLarge.copy(
                bottomStart = CornerSize(0.dp),
                bottomEnd = CornerSize(0.dp)
            )
        ),
        tonalElevation = DrawerDefaults.ModalDrawerElevation
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(top = 16.dp)
        ) {
            Text(text = "Info - $info")

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { viewModel.someFunction() }) {
                Text(text = "SomeButton")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Vitae suscipit tellus mauris a diam maecenas. Dignissim suspendisse in est ante in nibh. Vitae sapien pellentesque habitant morbi. Tortor at risus viverra adipiscing.  Tempor commodo ullamcorper a lacus vestibulum sed. Libero justo laoreet sit amet cursus sit amet dictum. In hac habitasse platea dictumst quisque sagittis purus sit amet. Sit amet consectetur adipiscing elit duis. Nec ultrices dui sapien eget mi proin.")
        }
    }
}




