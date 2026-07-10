@Composable
fun ButtonScreen() {

    val context = LocalContext.current

    val interactionSource =
        remember {
        MutableInteractionSource()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Content Column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "Let's Design Your Dream App",
                fontSize = 27.sp,
                color = Color(0xFF1F2937),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(
                text = "Tell us about your idea",
                fontSize = 16.sp,
                color = Color(0xFF6B7280),
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Image(
                painter = painterResource(id = R.drawable.contact_button),
                contentDescription = "Contact Button",
                modifier = Modifier
                    .size(width = 220.dp, height = 70.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = ripple(
                            bounded = true,
                            radius = 40.dp
                        )
                    ) {

                        Toast.makeText(
                            context,
                            "Contact button clicked",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
            )

            Spacer(
                modifier = Modifier.height(360.dp)
            )
        }
    }
}
