@Composable
fun RatingDialogDemo() {

    var showRatingDialog by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = {
                showRatingDialog = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFC107)
            )
        ) {

            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color.White
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = "Rate Our App",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }

    // ⭐ Show Rating Dialog
    if (showRatingDialog) {

        RatingDialog(

            onDismiss = {
                showRatingDialog = false
            },

            onSubmit = { rating ->
                // Selected rating will appear here
                println("User Rating: $rating")

                when (rating) {
                    5 -> {
                        // 5 star logic
                    }

                    4 -> {
                        // 4 star logic
                    }

                    3 -> {
                        // 3 star logic
                    }

                    2 -> {
                        // 2 star logic
                    }

                    1 -> {
                        // 1 star logic
                    }
                }
                showRatingDialog = false

                }
        )
    }
}
@Composable
fun RatingDialog(
    onDismiss: () -> Unit,
    onSubmit: (Int) -> Unit
) {

    val DialogWhite = Color(0xFFFFFEFC)
    val TextDark = Color(0xFF172033)
    val TextGray = Color(0xFF6B7280)
    val Gold = Color(0xFFFFC107)

    var selectedRating by remember {
        mutableIntStateOf(0)
    }

    val dialogScale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing
        ),
        label = "dialogScale"
    )
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
                .graphicsLayer {
                    scaleX = dialogScale
                    scaleY = dialogScale
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

            // Canva Illustration
            Image(
                painter = painterResource(
                    R.drawable.rating_illustration
                ),
                contentDescription = "Rating",
                modifier = Modifier
                    .size(120.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "Enjoying the app?",
                color = TextDark,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Your feedback helps us make the app even better.",
                color = TextGray,
                fontSize = 14.sp,
                lineHeight = 21.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(
                    horizontal = 10.dp
                )
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // ⭐ Stars
            RatingStars(
                selectedRating = selectedRating,
                onRatingSelected = {
                    selectedRating = it
                }
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )
//Submit button
            Button(
                onClick = {
                    if (selectedRating > 0) {
                        onSubmit(selectedRating)
                    }
                },
                enabled = selectedRating > 0,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Gold,
                    disabledContainerColor = Color.LightGray
                )
            ) {
                Text(
                    text = "Submit Rating",
                    color = TextDark,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
@Composable
fun RatingStars(
    selectedRating: Int,
    onRatingSelected: (Int) -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        repeat(5) { index ->

            val rating = index + 1

            Box(
                modifier = Modifier
                    .size(46.dp)
                    .pointerInput(rating) {
                        detectTapGestures {
                            onRatingSelected(rating)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(
                        if (rating <= selectedRating) {
                            R.drawable.star_filled
                        } else {
                            R.drawable.star_empty
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(38.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}
