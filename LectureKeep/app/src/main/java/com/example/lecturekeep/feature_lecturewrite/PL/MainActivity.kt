package com.example.lecturekeep.feature_lecturewrite.PL

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lecturekeep.feature_lecturewrite.PL.edit.components.EditLectureScreen
import com.example.lecturekeep.feature_lecturewrite.PL.lectures.components.LecturesScreen
import com.example.lecturekeep.feature_lecturewrite.PL.util.Screen
import com.example.lecturekeep.ui.theme.LectureKeepTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            LectureKeepTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController,
                    startDestination = Screen.LecturesScreen.route) {
                        composable(route = Screen.LecturesScreen.route) {
                            LecturesScreen(navController = navController)
                        }
                        composable(route = Screen.EditLectureScreen.route + "?lectureId={lectureId}&lectureSubject={lectureSubject}",
                        arguments = listOf(
                            navArgument(name = "lectureId") {
                                type = NavType.IntType
                                defaultValue = -1
                            },
                            navArgument(name = "lectureSubject") {
                                type = NavType.IntType
                                defaultValue = -1
                            },
                        )) {
                            val subject = it.arguments?.getInt("lectureSubject") ?: -1
                            EditLectureScreen(navController = navController, lectureSubject = subject)
                        }
                    }
                }
            }
        }
    }
}