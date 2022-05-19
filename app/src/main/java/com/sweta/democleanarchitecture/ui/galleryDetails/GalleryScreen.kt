package com.sweta.democleanarchitecture.ui.galleryDetails

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.sweta.democleanarchitecture.ui.animations.Heart
import com.sweta.democleanarchitecture.ui.theme.DemoCleanArchitectureTheme
import com.sweta.democleanarchitecture.ui.theme.ShimmerColorShades
import com.sweta.domain.models.GalleryImage
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import javax.microedition.khronos.opengles.GL


@Composable
fun GalleryDetailView(
    state: GalleryDetailContract.State,
    effectFlow: Flow<GalleryDetailContract.Effect>?,
    onEventSent: (event: GalleryDetailContract.Event) -> Unit,
    id: Int
) {
    val heartCount = remember { mutableStateOf(0) }
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    // Listen for side effects from the VM
    LaunchedEffect(effectFlow) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is GalleryDetailContract.Effect.ShowToast -> {
                    scaffoldState.snackbarHostState.showSnackbar(effect.message)
                }
                is GalleryDetailContract.Effect.Navigation -> {
                    //onNavigationRequested(effect)
                }

            }
        }?.collect()
    }


    when (state.galleryDetailState) {
        is GalleryDetailContract.GalleryDetailState.Loading -> {
            ShimmerAnimation()
        }
        is GalleryDetailContract.GalleryDetailState.Idle -> {
            Text("Idle...")
        }
        is GalleryDetailContract.GalleryDetailState.Success -> {


                Box(modifier = Modifier.fillMaxSize()) {

                   Column(modifier = Modifier.align(Alignment.TopCenter).padding(16.dp)) {
                        val image = rememberCoilPainter(
                            request = state.galleryDetailState.image.downloadUrl,
                            fadeIn = false
                        )


                       Text(
                           modifier = Modifier.fillMaxWidth().height(40.dp),
                           text = "Photo By: ${state.galleryDetailState.image.author}",
                           fontWeight = FontWeight.Bold,
                           style = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Center),
                           color = Color.Black
                       )

                        Image(
                            painter = image,
                            contentDescription = "gallery image",
                            modifier = Modifier
                                .fillMaxHeight(0.90f).shadow(elevation = 0.dp),
                            contentScale = ContentScale.Crop

                        )

                    }
                    repeat(heartCount.value) {

                        Heart(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 36.dp),
                            horizontalPadding = 24,
                            bottomMargin = 110,

                            )

                    }
                    Button(
                        onClick = {
                            heartCount.value++
                        },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(24.dp)
                            .wrapContentHeight()
                            .wrapContentWidth()
                    ) {
                        Text(
                            text = "Like",
                            color = Color.White
                        )
                    }
                }//end box

        }
        is GalleryDetailContract.GalleryDetailState.Error -> {
            //show error view
            Text("Error")
        }
        else -> {
            Text("Generic Error")
        }
    }



}

@Composable
fun ShimmerView(brush: Brush){

    // Column composable containing spacer shaped like a rectangle,
    // set the [background]'s [brush] with the brush receiving from [ShimmerAnimation]
    // Composable which is the Animation you are gonna create.
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(brush = brush)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.90f)
                .padding(top = 16.dp)
                .background(brush = brush)
        )

        Spacer(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 24.dp)
                .height(40.dp)
                .width(80.dp)
                .background(brush = brush)
        )
    }

}

@Composable
fun ShimmerAnimation(
) {

    /*
    Create InfiniteTransition
    which holds child animation like [Transition]
    animations start running as soon as they enter
    the composition and do not stop unless they are removed
    */
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        /*
        Specify animation positions,
        initial Values 0F means it
        starts from 0 position
        */
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(


            // Tween Animates between values over specified [durationMillis]
            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        )
    )

    /*
    Create a gradient using the list of colors
    Use Linear Gradient for animating in any direction according to requirement
    start=specifies the position to start with in cartesian like system Offset(10f,10f) means x(10,0) , y(0,10)
    end = Animate the end position to give the shimmer effect using the transition created above
    */
    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

    ShimmerView(brush = brush)
}
