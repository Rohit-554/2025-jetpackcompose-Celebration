package io.jadu.diwali2024

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.jadu.diwali2024.ui.theme.Diwali2024Theme

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.jadu.diwali2024.introScreens.IntroPager
import io.jadu.diwali2024.introScreens.JellyfishAnimation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()
            Diwali2024Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NewYear2025(navHostController, innerPadding)
                }
            }
        }
    }
}



@Composable
fun NewYear2025(
    navHostController: NavHostController,
    innerPadding: PaddingValues
) {
    var startDestination = "intro"

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        startDestination = "JellyFish"
    }
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable("JellyFish") {
            JellyfishAnimation(navHostController)
        }

        composable("intro") {
            IntroPager(navHostController)
        }
        composable("Fireworks") {
            FireworkEffectView()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiwaliRocketApp() {
    var name by remember { mutableStateOf("") }
    var isNameEntered by remember { mutableStateOf(false) }
    var launch by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Show TextField if the name hasn't been entered yet
        if (!isNameEntered) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Enter your name") },
                modifier = Modifier.padding(16.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Button(
                onClick = { isNameEntered = true },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Let's Celebrate Diwali")
            }
        }

        // Show the "launch" animation once the user enters the name and clicks the button
        if (isNameEntered && !launch) {
            Button(
                onClick = { launch = true },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Let's Celebrate Diwali")
            }
        }

        // Start the rocket animation after clicking the button
        if (launch) {
            RocketAnimation(name) { launch = false }
        }
    }
}

@Composable
fun RocketAnimation(name: String, onEnd: () -> Unit) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            delay(4000L) // Delay for the animation duration
            onEnd() // End the launch after the animation
        }
    }

    // Individual letter animations
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        name.forEachIndexed { index, char ->
            RocketLetterAnimation(char = char, delay = index * 300L, letterCount = name.length, index = index)
        }
    }
}

@Composable
fun RocketLetterAnimation(char: Char, delay: Long, letterCount: Int, index: Int) {
    val animationDuration = 2000
    val radius = 150f // Radius of the circle where letters will settle
    val angle = 2 * PI / letterCount * index // Angle for each letter

    // Animation to move letter upwards
    val yOffset by animateFloatAsState(
        targetValue = -800f,
        animationSpec = tween(durationMillis = animationDuration, delayMillis = delay.toInt())
    )

    // After upward motion, position them in a circular layout at the top
    val circleXOffset by animateFloatAsState(
        targetValue = (radius * cos(angle)).toFloat(),
        animationSpec = tween(durationMillis = 800, delayMillis = (delay + animationDuration).toInt())
    )
    val circleYOffset by animateFloatAsState(
        targetValue = (radius * sin(angle)).toFloat() - 800f, // Offset to top-center
        animationSpec = tween(durationMillis = 800, delayMillis = (delay + animationDuration).toInt())
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .offset(x = circleXOffset.dp, y = yOffset.dp + circleYOffset.dp)
            .size(50.dp)
            .background(Color.Magenta, shape = CircleShape)
    ) {
        Text(
            text = char.toString(),
            fontSize = 28.sp,
        )
    }
}

