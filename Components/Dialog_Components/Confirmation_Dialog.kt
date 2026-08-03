
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay

@Composable
fun ConfirmationDialogUI() {

    var showTitle by remember { mutableStateOf(false) }
    var showButtons by remember { mutableStateOf(false) }
    var showDialog by remember {
        mutableStateOf(true)
    }

    val infiniteTransition = rememberInfiniteTransition(label = "")

    val floating by infiniteTransition.animateFloat(
        initialValue = -4f,
        targetValue = 4f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1800,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )
    val illustrationScale by animateFloatAsState(
        targetValue = if (showDialog) 1f else 0.5f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessVeryLow
        ),
        label = ""
    )

    val illustrationRotation by animateFloatAsState(
        targetValue = if (showDialog) 0f else -15f,
        animationSpec = tween(500),
        label = ""
    )

    LaunchedEffect(showDialog) {
        if (showDialog) {
            delay(250)   // after Dialog open
            showTitle = true

            delay(150)   // then buttons
            showButtons = true
        } else {
            showTitle = false
            showButtons = false
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Button(
            modifier = Modifier
                .graphicsLayer {
                    translationY = floating
                },
            onClick = {
                showDialog = true
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFACC15),
                contentColor = Color(0xFF0F172A)
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                "Show Dialog",
                fontWeight = FontWeight.SemiBold
            )
        }

        AnimatedVisibility(
            visible = showDialog,
            enter =
                fadeIn(
                    animationSpec = tween(350)
                ) +
                        scaleIn(
                            initialScale = .75f,
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessLow
                            )
                        ),

            exit =
                fadeOut(
                    animationSpec = tween(220)

                ) +
                        scaleOut(
                            targetScale = .90f,
                            animationSpec = tween(220)
                        )
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = .45f)),
                contentAlignment = Alignment.Center
            ) {

                Box(
                    modifier = Modifier
                        .width(340.dp)
                        .height(430.dp)
                        .clip(RoundedCornerShape(30.dp))
                ) {
                    Image(
                        painter = painterResource(R.drawable.dialog_bg),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )


                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 28.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(30.dp))

                        Image(
                            painter = painterResource(R.drawable.delete_illustration),
                            contentDescription = null,
                            modifier = Modifier
                                .size(130.dp)
                                .graphicsLayer {
                                    scaleX = illustrationScale
                                    scaleY = illustrationScale
                                    rotationZ = illustrationRotation
                                },
                            contentScale = ContentScale.Fit
                        )
                        Spacer(modifier = Modifier.height(22.dp))
                        AnimatedVisibility(
                            visible = showTitle,
                            enter = fadeIn() + slideInVertically { it / 3 },
                            exit = fadeOut()
                        ) {

                            Text(
                                text = "Delete Project?",

                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0F172A)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        AnimatedVisibility(
                            visible = showTitle,
                            enter = fadeIn(animationSpec = tween(500)),
                            exit = fadeOut()
                        ) {
                            Text(
                                text = "This action can't be undone.\nAre you sure you want to continue?",
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp,
                                lineHeight = 22.sp,
                                color = Color(0xFF64748B)
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))

                        AnimatedVisibility(
                            visible = showButtons,
                            enter = slideInVertically(
                                initialOffsetY = { it / 2 }
                            ) + fadeIn(),
                            exit = fadeOut()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 28.dp),
                                horizontalArrangement = Arrangement.spacedBy(14.dp)
                            ) {

                                // Cancel Button
                                Button(

                                    onClick = {
                                        showDialog = false
                                    },

                                    modifier = Modifier
                                        .weight(1f)
                                        .height(56.dp),

                                    shape = RoundedCornerShape(18.dp),

                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFF8FAFC)
                                    ),

                                    border = BorderStroke(
                                        1.dp,
                                        Color(0xFFCBD5E1)
                                    ),

                                    elevation = ButtonDefaults.buttonElevation(
                                        defaultElevation = 2.dp
                                    )

                                ) {

                                    Image(
                                        painter = painterResource(R.drawable.cancel_btn),
                                        contentDescription = null,
                                        modifier = Modifier.size(25.dp)
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))

                                    Text(
                                        text = "Cancel",
                                        color = Color(0xFF334155),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )

                                }
                                // Delete Button
                                Button(

                                    onClick = {
                                        showDialog = false
                                    },

                                    modifier = Modifier
                                        .weight(1f)
                                        .height(56.dp),

                                    shape = RoundedCornerShape(18.dp),

                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFEF4444)
                                    ),

                                    elevation = ButtonDefaults.buttonElevation(
                                        defaultElevation = 6.dp
                                    )

                                ) {

                                    Image(
                                        painter = painterResource(R.drawable.delete_btn),
                                        contentDescription = null,
                                        modifier = Modifier.size(25.dp)
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))

                                    Text(
                                        text = "Delete",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )

                                }

                            }
                        }
                    }

                }

            }

        }

    }

}
