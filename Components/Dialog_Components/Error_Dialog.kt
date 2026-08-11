@Composable
fun DialogCallback(){
    val ErrorRed = Color(0xFFE53935)
    var showErrorDialog by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(26.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = {
                showErrorDialog = true
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = ErrorRed
            ),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(5.dp)
        ) {

            Icon(
                imageVector = Icons.Default.ErrorOutline,
                contentDescription = null
            , tint = Color.White
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = "Show Error Dialog",
                fontWeight = FontWeight.Bold
            , color = Color.White)
        }
    }

    if (showErrorDialog) {

        ErrorDialog(

            onDismiss = {
                showErrorDialog = false
            },

            onRetry = {
                showErrorDialog = false

            }
        )}
}
@Composable
fun ErrorDialog(
    onDismiss: () -> Unit,
    onRetry: () -> Unit
) {
    val ErrorRed = Color(0xFFE53935)
    val DialogWhite = Color(0xFFFFFEFC)
    val TextDark = Color(0xFF172033)
        val shakeX = remember {
            Animatable(0f)
        }

        LaunchedEffect(Unit) {

            delay(150)

            repeat(2) {

                shakeX.animateTo(
                    12f,
                    animationSpec = tween(70)
                )

                shakeX.animateTo(
                    -12f,
                    animationSpec = tween(70)
                )

                shakeX.animateTo(
                    6f,
                    animationSpec = tween(60)
                )

                shakeX.animateTo(
                    0f,
                    animationSpec = tween(60)
                )
            }
        }

        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {

            AnimatedVisibility(
                visible = true,
                enter = fadeIn(
                    animationSpec = tween(250)
                ) + scaleIn(
                    initialScale = 0.85f,
                    animationSpec = tween(
                        350,
                        easing = FastOutSlowInEasing
                    )
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp)
                        .offset {
                            IntOffset(
                                shakeX.value.roundToInt(),
                                0
                            )
                        }
                        .clip(
                            RoundedCornerShape(30.dp)
                        )
                        .background(DialogWhite)
                        .padding(
                            horizontal = 24.dp,
                            vertical = 28.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.error_illustration),
                        contentDescription = "Error",
                        modifier = Modifier
                            .size(115.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(
                        modifier = Modifier.height(15.dp)
                    )

                    Text(
                        text = "Something went wrong!",
                        color = Color(0xFF172033),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        softWrap = false,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "We couldn't complete your request.\nPlease try again shortly.",
                        color = Color(0xFF6B7280),
                        fontSize = 13.sp,
                        lineHeight = 26.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp)
                    )
                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(
                                RoundedCornerShape(14.dp)
                            )
                            .background(
                                Color(0xFFFFF1F2)
                            )
                            .padding(
                                horizontal = 14.dp,
                                vertical = 11.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Default.ErrorOutline,
                            contentDescription = null,
                            tint = ErrorRed,
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(
                            modifier = Modifier.width(10.dp)
                        )

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Request failed",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFC62828)
                            )

                            Spacer(modifier = Modifier.height(3.dp))

                            Text(
                                text = "Please check your connection.",
                                fontSize = 13.sp,
                                color = Color(0xFF6B7280)
                            )
                        }}
                    Spacer(
                        modifier = Modifier.height(22.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        OutlinedButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .weight(1f)
                                .height(54.dp),
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(
                                1.dp,
                                Color(0xFFD1D5DB)
                            )
                        ) {

                            Text(
                                text = "Cancel",
                                color = TextDark,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Button(
                            onClick = onRetry,
                            modifier = Modifier
                                .weight(1f)
                                .height(54.dp)
                           ,
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = ErrorRed
                            )
                        ) {

                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = null
                            )

                            Spacer(
                                modifier = Modifier.width(7.dp)
                            )

                            Text(
                                text = "Retry",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }                   }
                }
            }


}
