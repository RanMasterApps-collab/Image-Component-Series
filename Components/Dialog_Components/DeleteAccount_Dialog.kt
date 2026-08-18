
@Composable
fun DeleteAccountDialogDemo() {

    var showDeleteDialog by remember {
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
                showDeleteDialog = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 5.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFEF4444)
            )
        ) {

            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = Color.White
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = "Delete Account",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }

    // 🔴 Show Delete Account Dialog
    if (showDeleteDialog) {

        DeleteAccountDialog(

            onDismiss = {
                showDeleteDialog = false
            },

            onDelete = {

                // Account deletion logic
                println("Account Delete Confirmed")

                showDeleteDialog = false
            }
        )
    }
}
@Composable
fun DeleteAccountDialog(
    onDismiss: () -> Unit,
    onDelete: () -> Unit
) {

    val ErrorRed = Color(0xFFEF4444)
    val DarkText = Color(0xFF172033)
    val GrayText = Color(0xFF6B7280)
    val DialogWhite = Color(0xFFFFFEFC)
    val LightRed = Color(0xFFFFF1F2)

    val dialogScale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(
            durationMillis = 350,
            easing = FastOutSlowInEasing
        ),
        label = "dialogScale"
    )
    var isDeleting by remember {
        mutableStateOf(false)
    }

    val deleteScale by animateFloatAsState(
        targetValue = if (isDeleting) 0.96f else 1f,
        animationSpec = tween(120),
        label = "deleteScale"
    )
    val illustrationScale = remember {
        Animatable(0.5f)
    }

    LaunchedEffect(Unit) {
        illustrationScale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
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
                    durationMillis = 350,
                    easing = FastOutSlowInEasing
                )
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp)
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

                Box(
                    modifier = Modifier.size(160.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(
                            R.drawable.delete_account_illustration
                        ),
                        contentDescription = "Delete Account Warning",
                        modifier = Modifier
                            .size(145.dp)
                            .graphicsLayer {
                                scaleX = illustrationScale.value
                                scaleY = illustrationScale.value
                            },
                        contentScale = ContentScale.Fit
                    )

                    // ✨ Small premium sparkle
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = Color(0xFFFFC94A),
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(
                                x = (-22).dp,
                                y = 7.dp
                            )
                            .size(17.dp)
                    )

                    // ✨ Small secondary sparkle
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = Color(0xFFFFD966),
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .offset(
                                x = 3.dp,
                                y = (1).dp
                            )
                            .size(12.dp)
                    )
                }
                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(
                    text = "Delete your account?",
                    color = DarkText,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Your account and saved data will be permanently removed.",
                    color = GrayText,
                    fontSize = 14.sp,
                    lineHeight = 21.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(
                        horizontal = 8.dp
                    )
                )

                Spacer(
                    modifier = Modifier.height(22.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .background(Color(0xFFFFF1F2))
                        .padding(
                            horizontal = 14.dp,
                            vertical = 14.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(0xFFFFE4E6)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = null,
                            tint = ErrorRed,
                            modifier = Modifier.size(25.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Permanent deletion",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFC62828)
                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        Text(
                            text = "Your profile, saved data and account history will be permanently removed.",
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            color = Color(0xFF6B7280)
                        )
                    }
                }

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        // Cancel
                        OutlinedButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .weight(1f)
                                .height(54.dp),
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(
                                1.dp,
                                Color(0xFFD1D5DB)
                            ),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = DarkText
                            )
                        ) {

                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null,
                                modifier = Modifier.size(19.dp)
                            )

                            Spacer(
                                modifier = Modifier.width(7.dp)
                            )

                            Text(
                                text = "Cancel",
                                fontWeight = FontWeight.SemiBold
                            )
                        }


                        // Delete Account
                        Button(
                            onClick = {

                                isDeleting = true

                                onDelete()
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(54.dp)
                                .graphicsLayer {
                                    scaleX = deleteScale
                                    scaleY = deleteScale
                                },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = ErrorRed
                            )
                        ) {

                            Icon(
                                imageVector = Icons.Default.DeleteForever,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(
                                modifier = Modifier.width(7.dp)
                            )

                            Text(
                                text = if (isDeleting) {
                                    "Deleting..."
                                } else {
                                    "Delete"
                                },
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }
            }
        }

    }}
