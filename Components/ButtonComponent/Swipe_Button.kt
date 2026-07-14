@Composable
fun PremiumSwipeButtonScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF0F172A),
                        Color(0xFF1E293B),
                        Color(0xFF334155)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f),
            shape = RoundedCornerShape(30.dp),
            elevation = CardDefaults.cardElevation(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1E293B)
            )

        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(70.dp))

                Icon(
                    imageVector = Icons.Default.Swipe,
                    contentDescription = null,
                    tint = Color(0xFF60A5FA),
                    modifier = Modifier.size(60.dp)
                )

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "Swipe Button",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Jetpack Compose Material 3",
                    fontSize = 16.sp,
                    color = Color.LightGray
                )

                Spacer(modifier = Modifier.height(50.dp))

                PremiumSwipeButton()

            }
        }}}
@Composable
fun PremiumSwipeButton() {

    var offsetX by remember { mutableFloatStateOf(0f) }
    var completed by remember { mutableStateOf(false) }

    val density = LocalDensity.current

    var buttonWidth by remember { mutableFloatStateOf(0f) }

    val thumbSize = with(density) { 60.dp.toPx() }
    val horizontalPadding = with(density) { 12.dp.toPx() }

    val maxOffset = (buttonWidth - thumbSize - horizontalPadding)
    val progress = offsetX / maxOffset

    Box(
        modifier = Modifier
            .width(340.dp)
            .height(72.dp)
            .padding(horizontal = 6.dp)
            .clip(RoundedCornerShape(40.dp))
            .onSizeChanged {
                buttonWidth = it.width.toFloat()
            }
    ) {


        // Background
        Image(
            painter = painterResource(R.drawable.swipe_bg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        // Progress Fill
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(progress)
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            Color(0xFF3B82F6),
                            Color(0xFF60A5FA)
                        )
                    )
                )
        )

        // Text
        Text(
            text = if (completed) "✓ Completed" else "Swipe to Continue",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )

        // Swipe Thumb
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(offsetX.roundToInt(), 0)
                }
                .padding(6.dp)
                .size(60.dp)
                .shadow(10.dp, CircleShape)
                .clip(CircleShape)
                .background(Color.White)
                .pointerInput(Unit) {

                    detectDragGestures(

                        onDrag = { _, drag ->

                            if (!completed) {

                                offsetX =
                                    (offsetX + drag.x)
                                        .coerceIn(0f, maxOffset)

                            }

                        },
                 onDragEnd = {

                            if (offsetX > maxOffset * .85f) {

                                offsetX = maxOffset
                                completed = true

                            } else {

                                offsetX = 0f

                            }

                        }

                    )

                }
        ) {

            Icon(
                imageVector =
                    if (completed)
                        Icons.Default.Check
                    else
                        Icons.Default.ArrowForward,
                contentDescription = null,
                tint =
                    if (completed)
                        Color(0xFF16A34A)
                    else
                        Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )

        }

    }

}
