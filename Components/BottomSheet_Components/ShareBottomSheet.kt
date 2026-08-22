@Composable
fun ShareBottomSheetDemo() {

    var showShareSheet by remember {
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
                showShareSheet = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5B5FEF)
            )
        ) {

            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null,
                tint = Color.White
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = "Share",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }

    if (showShareSheet) {

        ShareBottomSheet(
            onDismiss = {
                showShareSheet = false
            },

            onShare = {
                showShareSheet = false
            },

            onCopyLink = {
                showShareSheet = false
            }
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareBottomSheet(
    onDismiss: () -> Unit,
    onShare: () -> Unit,
    onCopyLink: () -> Unit
) {

    val SheetWhite = Color(0xFFFFFEFC)
    val TextDark = Color(0xFF172033)
    val TextGray = Color(0xFF6B7280)
    val Primary = Color(0xFF5B5FEF)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = SheetWhite,
        shape = RoundedCornerShape(
            topStart = 30.dp,
            topEnd = 30.dp
        ),
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .width(42.dp)
                    .height(5.dp)
                    .clip(
                        RoundedCornerShape(50)
                    )
                    .background(
                        Color(0xFFD1D5DB)
                    )
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp,
                    vertical = 10.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Canva Illustration
            Image(
                painter = painterResource(
                    R.drawable.share_bottom_sheet_illustration
                ),
                contentDescription = "Share",
                modifier = Modifier
                    .size(120.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "Share with friends",
                color = TextDark,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(7.dp)
            )

            Text(
                text = "Spread the word and share this with someone you know.",
                color = TextGray,
                fontSize = 14.sp,
                lineHeight = 21.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(
                    horizontal = 12.dp
                )
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // Share options
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                ShareOption(
                    icon = Icons.Default.Share,
                    title = "Share",
                    color = Primary,
                    modifier = Modifier.weight(1f),
                    onClick = onShare
                )

                ShareOption(
                    icon = Icons.Default.Link,
                    title = "Copy link",
                    color = Color(0xFF8B5CF6),
                    modifier = Modifier.weight(1f),
                    onClick = onCopyLink
                )

                ShareOption(
                    icon = Icons.Default.Email,
                    title = "Email",
                    color = Color(0xFFEC4899),
                    modifier = Modifier.weight(1f),
                    onClick = onShare
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // Main share button
            Button(
                onClick = onShare,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(17.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary
                )
            ) {

                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text(
                    text = "Share Now",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )
        }
    }
}
@Composable
fun ShareOption(
    icon: ImageVector,
    title: String,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(58.dp)
                .clip(
                    RoundedCornerShape(18.dp)
                )
                .background(
                    color.copy(alpha = 0.12f)
                )
                .clickable(
                    indication = null,
                    interactionSource = remember {
                        MutableInteractionSource()
                    }
                ) {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = color,
                modifier = Modifier.size(25.dp)
            )
        }

        Spacer(
            modifier = Modifier.height(7.dp)
        )

        Text(
            text = title,
            color = Color(0xFF374151),
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
