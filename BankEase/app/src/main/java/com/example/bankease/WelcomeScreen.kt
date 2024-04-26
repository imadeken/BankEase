package com.example.bankease

import android.content.Context
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(navController: NavHostController, context: MainActivity) {

    val alpha = remember {
        Animatable(0f)
    }

    LaunchedEffect(
        key1 = true
    ) {
        alpha.animateTo(1f,
            animationSpec = tween())
        delay(3000)

        if (welcomeSlideIsFinished(context = context)) {
            navController.popBackStack()
            navController.navigate("Home")
        } else {
            navController.popBackStack()
            navController.navigate("Get Started")
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color.DarkGray else Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoaderAnimation(
            modifier = Modifier. size(400.dp),
            anim2 = R.raw.anim2
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "Welcome to Bank Ease",
            modifier = Modifier.alpha(alpha = Float.MAX_VALUE),
            fontSize = 32.sp,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
fun LoaderAnimation(modifier: Modifier, anim2: Int) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(anim2))

    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = modifier
    )
}

private fun welcomeSlideIsFinished(context: MainActivity):Boolean {
    val sharedPreferences = context.getSharedPreferences("Get Started", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("isFinished", false)
}