package com.vastausf.wesolient.presentation.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.vastausf.wesolient.LocalWesolientDatabase
import com.vastausf.wesolient.Navigation
import com.vastausf.wesolient.model.WesolientDatabase
import com.vastausf.wesolient.presentation.design.token.WesolientTheme
import com.vastausf.wesolient.presentation.ui.screen.WorkspaceSettingsPreload
import com.vastausf.wesolient.presentation.ui.screen.MainScreen
import com.vastausf.wesolient.presentation.ui.screen.WorkspaceScreenPreload

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WithDependencies {
                WesolientTheme {
                    val colors = WesolientTheme.colors

                    Surface(
                        modifier = Modifier
                            .fillMaxSize(),
                        color = colors.background
                    ) {
                        AppNavigation()
                    }
                }
            }
        }
    }

    @Composable
    private fun AppNavigation() {
        val navController = rememberNavController()

        NavHost(navController, startDestination = Navigation.MAIN.path) {
            composable(Navigation.MAIN.path) {
                MainScreen(navController)
            }

            composable("${Navigation.WORKSPACE.path}/{id}") { backStackEntry ->
                val workspaceId = backStackEntry.arguments?.getString("id")

                if (workspaceId == null) {
                    navController.navigate(Navigation.MAIN.path)

                    return@composable
                }

                WorkspaceScreenPreload(navController, workspaceId)
            }

            composable("${Navigation.WORKSPACE_SETTINGS.path}/{id}") { backStackEntry ->
                val workspaceId = backStackEntry.arguments?.getString("id")

                if (workspaceId == null) {
                    navController.navigate(Navigation.MAIN.path)

                    return@composable
                }

                WorkspaceSettingsPreload(navController, workspaceId)
            }
        }
    }

    @Composable
    private fun WithDependencies(
        content: @Composable () -> Unit
    ) {
        val wesolientDatabase = remember {
            Room
                .databaseBuilder(
                    applicationContext,
                    WesolientDatabase::class.java,
                    "WesolientDatabase"
                )
                .build()
        }

        CompositionLocalProvider(
            LocalWesolientDatabase provides wesolientDatabase,
            content = content
        )
    }
}
