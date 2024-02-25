package com.gradation.lift.feature.home.home.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.home.data.model.bannerList
import com.gradation.lift.feature.home.home.data.state.HomeScreenState
import com.gradation.lift.ui.modifier.noRippleClickable

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun BannerView(
    modifier: Modifier = Modifier,
    navigateHomeGraphToInquiryGraph: () -> Unit,
    homeScreenState: HomeScreenState,
) {
    val bannerNavigationList = listOf(navigateHomeGraphToInquiryGraph).zip(bannerList)

    Column(
        modifier = modifier
    ) {
        HorizontalPager(state = homeScreenState.pagerState) { page ->
            bannerNavigationList[page].also {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .noRippleClickable { it.first() }
                        .padding(horizontal = LiftTheme.space.space20)
                ) {
                    GlideImage(
                        modifier = modifier,
                        model = it.second.image,
                        contentDescription = it.second.image.toString(),
                        contentScale = ContentScale.FillWidth
                    )
                    Row(
                        modifier = modifier
                            .align(Alignment.BottomEnd)
                            .padding(
                                end = LiftTheme.space.space16,
                                bottom = LiftTheme.space.space8
                            )
                    ) {
                        Box(
                            modifier = modifier
                                .background(
                                    LiftTheme.colorScheme.no9.copy(0.5f),
                                    RoundedCornerShape(LiftTheme.space.space12)
                                )
                                .padding(
                                    horizontal = LiftTheme.space.space8,
                                    vertical = LiftTheme.space.space4
                                )
                        ) {
                            LiftMultiStyleText(
                                modifier,
                                LiftTheme.colorScheme.no5,
                                LiftTextStyle.No8,
                                listOf(
                                    TextWithStyle(text = "${page + 1} / "),
                                    TextWithStyle(
                                        text = "${bannerList.size}",
                                        color = LiftTheme.colorScheme.no2,
                                        style = LiftTextStyle.No8
                                    )
                                ),
                                TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}