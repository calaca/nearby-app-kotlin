package com.example.nearbyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.nearbyapp.data.model.Market
import com.example.nearbyapp.ui.screen.home.HomeScreen
import com.example.nearbyapp.ui.screen.home.HomeViewModel
import com.example.nearbyapp.ui.screen.market_details.MarketDetailsScreen
import com.example.nearbyapp.ui.screen.splash.SplashScreen
import com.example.nearbyapp.ui.screen.welcome.WelcomeScreen
import com.example.nearbyapp.ui.route.Home
import com.example.nearbyapp.ui.route.QRCodeScanner
import com.example.nearbyapp.ui.route.Splash
import com.example.nearbyapp.ui.route.Welcome
import com.example.nearbyapp.ui.screen.market_details.MarketDetailsUiEvent
import com.example.nearbyapp.ui.screen.market_details.MarketDetailsViewModel
import com.example.nearbyapp.ui.screen.qrcode_scanner.QRCodeScannerScreen
import com.example.nearbyapp.ui.theme.NearbyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NearbyAppTheme {
                val navController = rememberNavController()

                val homeViewModel by viewModels<HomeViewModel>()
                val homeUiState = homeViewModel.uiState.collectAsStateWithLifecycle().value

                val marketDetailsViewModel by viewModels<MarketDetailsViewModel>()
                val marketDetailsUiState =
                    marketDetailsViewModel.uiState.collectAsStateWithLifecycle().value

                NavHost(
                    navController = navController,
                    startDestination = Splash
                ) {
                    composable<Splash> {
                        SplashScreen(
                            onNavigateToWelcome = {
                                navController.navigate(Welcome)
                            }
                        )
                    }
                    composable<Welcome> {
                        WelcomeScreen(
                            onNavigateToHome = {
                                navController.navigate(Home)
                            }
                        )
                    }
                    composable<Home> {
                        HomeScreen(
                            onNavigateToMarketDetails = { selectedMarket ->
                                navController.navigate(selectedMarket)
                            },
                            uiState = homeUiState,
                            onEvent = homeViewModel::onEvent,
                            selectedMarketId = it.arguments?.getString("marketId")
                        )
                    }
                    composable("${Home::class.simpleName}/{marketId}") { backStackEntry ->
                        HomeScreen(
                            onNavigateToMarketDetails = { selectedMarket ->
                                navController.navigate(selectedMarket)
                            },
                            uiState = homeUiState,
                            onEvent = homeViewModel::onEvent,
                            selectedMarketId = backStackEntry.arguments?.getString("marketId")
                        )
                    }
                    composable<Market> {
                        val selectedMarket = it.toRoute<Market>()
                        MarketDetailsScreen(
                            market = selectedMarket,
                            onNavigateBack = {
                                navController.popBackStack()
                            },
                            uiState = marketDetailsUiState,
                            onEvent = marketDetailsViewModel::onEvent,
                            onNavigateToMarker = {
                                navController.navigate("${Home::class.simpleName}/${selectedMarket.id}")
                            },
                            onNavigateToQRCodeScanner = {
                                navController.navigate(QRCodeScanner)
                            }
                        )
                    }
                    composable<QRCodeScanner> {
                        QRCodeScannerScreen(
                            onCompletedScan = { scannedCode ->
                                if (scannedCode.isNotEmpty()) {
                                    marketDetailsViewModel.onEvent(
                                        MarketDetailsUiEvent.OnFetchCoupon(
                                            scannedCode
                                        )
                                    )
                                }
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
