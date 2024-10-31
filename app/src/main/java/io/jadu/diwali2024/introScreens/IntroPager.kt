package io.jadu.diwali2024.introScreens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import io.jadu.pages.presentation.screens.introScreens.IntroScreenOne


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroPager(navHostController: NavHostController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = {4}
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> IntroScreenOne(pagerState)
                1 -> IntroScreenTwo(navHostController,pagerState,scope)
                2 -> IntroScreenThree(navHostController,pagerState,scope)
                3 -> IntroScreenFour(navHostController,pagerState,scope)
            }
        }
    }
}

/*@Preview
@Composable
fun PreviewIntroPager(){
    val context = LocalContext.current
    IntroPager(navHostController = NavHostController(context), composition = composition)
}*/

