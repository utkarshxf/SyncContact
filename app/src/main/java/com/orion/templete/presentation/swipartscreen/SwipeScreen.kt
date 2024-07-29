package com.orion.templete.presentation.swipartscreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alexstyl.swipeablecard.Direction.Down
import com.alexstyl.swipeablecard.Direction.Left
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi
import com.alexstyl.swipeablecard.rememberSwipeableCardState
import com.alexstyl.swipeablecard.swipableCard
import com.orion.templete.ui.MatchProfile
import com.orion.templete.ui.profiles

@OptIn(ExperimentalSwipeableCardApi::class)
@Composable
fun SwipeScreen(modifier: Modifier = Modifier , viewModel: MyViewModel = hiltViewModel()) {
    Box {
        val states = profiles.reversed().map { it to rememberSwipeableCardState() }
        val scope = rememberCoroutineScope()
        var isSwipedLeft by remember { mutableStateOf(false) }

        Box(
            Modifier
                .padding(24.dp)
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            states.forEach { (matchProfile, state) ->
                LaunchedEffect(state.swipedDirection) {
                    isSwipedLeft = state.swipedDirection == Left
                }
                val swipeOffset = state.offset
                val backgroundColor = if (isSwipedLeft) Color.Red else Color.White
                if (state.swipedDirection == null) {
                    ProfileCard(
                        modifier = modifier
                            .fillMaxSize()
                            .swipableCard(state = state,
                                blockedDirections = listOf(Down),
                                onSwiped = {
                                    Log.d("Swipeable-Card", state.swipedDirection.toString())
                                    isSwipedLeft = state.swipedDirection == Left
                                },
                                onSwipeCancel = {
                                    Log.d("Swipeable-Card", "Cancelled swipe")
                                }), matchProfile = matchProfile
                    )
                }
            }
        }
    }
}


@Composable
private fun ProfileCard(
    modifier: Modifier,
    matchProfile: MatchProfile,
) {
    Card(
        modifier = modifier,
        shape = shapes.large
    ) {
        Box {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(matchProfile.drawableResId),
                contentDescription = null
            )
            Column(Modifier.align(Alignment.BottomStart)) {
                Text(
                    text = matchProfile.name,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}
