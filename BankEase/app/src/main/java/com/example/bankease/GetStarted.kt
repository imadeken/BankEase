package com.example.bankease

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.launch
import androidx.compose.foundation.pager.PagerState as PagerState1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GetStarted(navController: NavHostController, context: MainActivity) {
    val animations = listOf(
        R.raw.anim1,
        R.raw.anim2,
        R.raw.anim3
    )
    val titles = listOf(
        "A Secured Banking Solution",
        "Ease of Use",
        "Cross Bank Payments"
    )

    val descriptions = listOf(
        "Security is a necessity, make secure transaction",
        "To serve you better, we have ensured Simplicity and ease of use",
        "Pay to your bank, pay to other banks, pay and receive money globally"
    )

    val pagerState = rememberPagerState(pageCount = { animations.size })

    Column (
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            Modifier.wrapContentSize()
        ) {
            currentPage ->
            Column(
                Modifier
                    .wrapContentSize()
                    .padding(26.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val composition by rememberLottieComposition(
                    LottieCompositionSpec.RawRes(animations[currentPage])
                )
                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier.size(400.dp)
                )
                Text(
                    text = titles[currentPage],
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = descriptions[currentPage],
                    Modifier.padding(top = 45.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }
        }
    }
    PageIndicator(
        pageCount = animations.size,
        currentPage = pagerState.currentPage,
        modifier = Modifier.padding(60.dp)
    )

    ButtonsSection (
        pagerState = pagerState,
        navController = navController
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ButtonsSection(pagerState: PagerState1, navController: NavHostController) {

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        if (pagerState.currentPage != 2) {
            Text(
                text = "Next",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clickable {
                        scope.launch {
                            val nextPage = pagerState.currentPage +1
                            pagerState.scrollToPage(nextPage)
                        }
                    },
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Back",
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .clickable {
                        scope.launch {
                            val prevPage = pagerState.currentPage -1
                            if (prevPage >= 0) {
                                pagerState.scrollToPage(prevPage)
                            }
                        }
                    },
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        else {
            OutlinedButton(onClick = {
                navController.popBackStack()
                navController.navigate("Home")
            }, modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0x25E92F1E)
                )
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier) {
    Row (
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
    ) {
        repeat(pageCount) {
            IndicatorSingleDot(isSelected = it == currentPage)
        }
    }
}

@Composable
fun IndicatorSingleDot(isSelected: Boolean) {

    val width = animateDpAsState(
        targetValue = if (isSelected) 35.dp else 15.dp,
        label = ""
    )

    Box(
        modifier = Modifier
            .padding(2.dp)
            .height(15.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(if (isSelected) Color(0xFFE92F1E) else Color(0x25E92F1E))
    )
}