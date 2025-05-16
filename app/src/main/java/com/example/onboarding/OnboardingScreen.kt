package com.example.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(viewModel: OnboardingViewModel) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val pages = listOf(
        OnboardingItem(R.drawable.rep, "Make Every Rep Count", "Your Journey Begins Here"),
        OnboardingItem(R.drawable.food, "Build Your Workout Paradise", "Your Journey Begins Here"),
        OnboardingItem(R.drawable.bike, "Embrace the Burn", "Your Journey Begins Here")
    )

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = pages.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            OnboardingPage(item = pages[page])
        }

        Text(
            text = "Skip",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .clickable {
                    viewModel.completeOnboarding()
                }
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PagerIndicator(size = pages.size, currentPage = pagerState.currentPage)
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    if (pagerState.currentPage + 1 < pages.size) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        viewModel.completeOnboarding()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                shape = RoundedCornerShape(50),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (pagerState.currentPage == pages.size - 1) "SIGN UP" else "Next",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
