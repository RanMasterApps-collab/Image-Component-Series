@Composable
fun SuccessDialog(){
    var showDialog by remember {
        mutableStateOf(false)
    }

    var showTitle by remember {
        mutableStateOf(false)
    }

    var showDescription by remember {
        mutableStateOf(false)
    }

    var showButton by remember {
        mutableStateOf(false)
    }

    var pressed by remember {
        mutableStateOf(false)
    }

    val buttonScale by animateFloatAsState(

        targetValue = if (pressed) .94f else 1f,

        animationSpec = tween(120),

        label = ""

    )
    val illustrationScale by animateFloatAsState(

        targetValue = if (showDialog) 1f else .5f,

        animationSpec = spring(

            dampingRatio = Spring.DampingRatioMediumBouncy,

            stiffness = Spring.StiffnessLow

        ),

        label = ""

    )
    val scope = rememberCoroutineScope()
    LaunchedEffect(showDialog) {

        if (showDialog) {

            showTitle = false
            showDescription = false
            showButton = false

            delay(250)

            showTitle = true

            delay(180)

            showDescription = true

            delay(180)

            showButton = true

        }

    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Button(

            onClick = {

                showDialog = true

            },

            shape = RoundedCornerShape(18.dp),

            colors = ButtonDefaults.buttonColors(

                containerColor = Color(0xFF22C55E)

            )

        ) {

            Icon(
                Icons.Default.CheckCircle,
                null
            )

            Spacer(Modifier.width(8.dp))

            Text("Show Success")

        }

    }
    AnimatedVisibility(

        visible = showDialog,

        enter = fadeIn() + scaleIn(),

        exit = fadeOut() + scaleOut()

    ) {

        Box(

            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(.45f)),

            contentAlignment = Alignment.Center

        ) {

            Card(

                modifier = Modifier
                    .fillMaxWidth(.88f),

                shape = RoundedCornerShape(30.dp),

                colors = CardDefaults.cardColors(

                    containerColor = Color.White

                )

            ) {

                AnimatedVisibility(

                    visible = showDialog,

                    enter = fadeIn() + scaleIn(),

                    exit = fadeOut() + scaleOut()

                ) {

                    Box(

                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.45f)),

                        contentAlignment = Alignment.Center

                    ) {

                        Card(

                            modifier = Modifier.fillMaxWidth(0.88f),

                            shape = RoundedCornerShape(30.dp),

                            colors = CardDefaults.cardColors(
                                containerColor =Color(0xFFFCFBF3)// Color.White
                            ),

                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 24.dp
                            )

                        ) {

                            Column(

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 24.dp,
                                        vertical = 30.dp
                                    ),

                                horizontalAlignment = Alignment.CenterHorizontally

                            ) {
                                //================ Success Illustration ==================
                                Box(
                                    contentAlignment = Alignment.Center
                                ) {

                                    Image(
                                        painter = painterResource(R.drawable.success_illustration)//success_illustration),
                                        , contentDescription = null,
                                        modifier = Modifier
                                            .size(130.dp)
                                            .graphicsLayer {

                                                scaleX = illustrationScale
                                                scaleY = illustrationScale

                                            }
                                    )
//add sparkle
                                    Icon(
                                        Icons.Default.AutoAwesome,
                                        null,
                                        tint = Color(0xFFFFD54F),
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .offset(x = (-8).dp, y = 6.dp)
                                            .size(24.dp)
                                    )
 Icon(
                                        Icons.Default.AutoAwesome,
                                        null,
                                        tint = Color(0xFFFFD54F),
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .offset(x = 10.dp, y = (-12).dp)
                                            .size(14.dp)
                                    )

                                    Icon(
                                        Icons.Default.AutoAwesome,
                                        null,
                                        tint = Color(0xFFFFD54F),
                                        modifier = Modifier
                                            .align(Alignment.BottomStart)
                                            .offset(x = 6.dp, y = (-8).dp)
                                            .size(18.dp)

                                    )
                                    Icon(
                                        Icons.Default.AutoAwesome,
                                        null,
                                        tint = Color(0xFFFFD54F),
                                        modifier = Modifier
                                            .align(Alignment.BottomStart)
                                            .offset(x = 24.dp, y = 4.dp)
                                            .size(12.dp)                                    )

                                }

                                Spacer(Modifier.height(22.dp))

                                //================ Title ==================
                                AnimatedVisibility(

                                    visible = showTitle,

                                    enter = fadeIn() + slideInVertically(),

                                    exit = fadeOut()

                                ) {

                                    Text(

                                        text = "Success!",

                                        color = Color(0xFF111827),

                                        fontSize = 30.sp,

                                        fontWeight = FontWeight.ExtraBold

                                    )

                                }
                                Spacer(Modifier.height(14.dp))
                                //================ Description ==================
                                AnimatedVisibility(

                                    visible = showDescription,

                                    enter = fadeIn() + slideInVertically(),

                                    exit = fadeOut()

                                ) {

                                    Text(

                                        text = "Everything went perfectly!",

                                        color = Color(0xFF6B7280),

                                        fontSize = 16.sp,

                                        textAlign = TextAlign.Center,

                                        lineHeight = 24.sp

                                    )

                                }
                                Spacer(Modifier.height(30.dp))
                                AssistChip(

                                    onClick = {},

                                    label = {

                                        Text("Verified Successfully")

                                    },

                                    leadingIcon = {

                                        Icon(
                                            Icons.Default.Verified,
                                            null
                                        )

                                    },

                                    colors = AssistChipDefaults.assistChipColors(

                                        containerColor = Color(0xFFE8F5E9),

                                        labelColor = Color(0xFF16A34A),

                                        leadingIconContentColor = Color(0xFF16A34A)

                                    )

                                )
                                Spacer(Modifier.height(14.dp))
                                //================ Continue Button ==================
                                AnimatedVisibility(

                                    visible = showButton,

                                    enter = fadeIn() + slideInVertically(),

                                    exit = fadeOut()

                                ) {

                                    Button(

                                        onClick = {

                                            scope.launch {

                                                delay(800)

                                                showDialog = false

                                            }
                                        },

                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(56.dp)
                                            .shadow(
                                                elevation = 18.dp,
                                                RoundedCornerShape(20.dp),
                                                ambientColor = Color(0xFF22C55E),
                                                spotColor = Color(0xFF22C55E)
                                            )

                                            .graphicsLayer {

                                                scaleX = buttonScale

                                                scaleY = buttonScale

                                            },

                                        shape = RoundedCornerShape(18.dp),

                                        colors = ButtonDefaults.buttonColors(

                                            containerColor = Color(0xFF22C55E)

                                        )

                                    ) {

                                        Icon(

                                            Icons.Default.CheckCircle,

                                            null

                                        )

                                        Spacer(Modifier.width(10.dp))

                                        Text(

                                            "Continue",

                                            fontWeight = FontWeight.Bold,

                                            fontSize = 18.sp

                                        )

                                    }

                                }

                            }

                        }

                    }

                }}}}       }
