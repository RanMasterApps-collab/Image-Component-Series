
@Composable
fun SocialButtonsUI() {

    var liked by remember { mutableStateOf(false) }
    var bookmarked by remember { mutableStateOf(false) }
    var copied by remember { mutableStateOf(false) }
    var shared by remember { mutableStateOf(false) }
    var commented by remember { mutableStateOf(false) }

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
            modifier = Modifier.fillMaxWidth(.9f),
            shape = RoundedCornerShape(30.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1E293B)
            ),
            elevation = CardDefaults.cardElevation(12.dp)
        ) {

            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    Icons.Default.Public,
                    null,
                    tint = Color(0xFF60A5FA),
                    modifier = Modifier.size(65.dp)
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    "Social Buttons",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )

                Text(
                    "Jetpack Compose Material 3",
                    color = Color.LightGray
                )

                Spacer(Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
//Like Button
                    SocialButton(
                        icon =
                            if (liked)
                                Icons.Default.Favorite
                            else
                                Icons.Default.FavoriteBorder,

                        label = "Like",

                        selected = liked,

                        selectedColor = Color(0xFFE91E63)

                    ) {

                        liked = !liked

                    }
             //Comment
                           SocialButton(
                        icon = Icons.Default.ChatBubbleOutline,
                        label = "Comment",
                        selected = commented,
                        selectedColor = Color(0xFF00BCD4)
                    ) {
                        commented = !commented
                    }

                  //Share
                    SocialButton(
                        icon = Icons.Default.Share,
                        label = "Share",
                        selected = shared,
                        selectedColor = Color(0xFF3B82F6)
                    ) {
                        shared = !shared
                    }

                }

                Spacer(Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
//Save or Bookmark
                    SocialButton(
                        icon =
                            if (bookmarked)
                                Icons.Default.Bookmark
                            else
                                Icons.Default.BookmarkBorder,

                        label = "Save",

                        selected = bookmarked,

                        selectedColor = Color(0xFFFF9800)

                    ) {

                        bookmarked = !bookmarked

                    }
//Copy
                    SocialButton(
                        icon = Icons.Default.Link,
                        label =
                            if (copied)
                                "Copied"
                            else
                                "Copy",

                        selected = copied,

                        selectedColor = Color(0xFF22C55E)

                    ) {

                        copied = !copied

                    }

                }

            }

        }

    }

}


@Composable
fun SocialButton(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    selectedColor: Color,
    onClick: () -> Unit
) {

    val scale by animateFloatAsState(
        targetValue = if (selected) 1.18f else 1.5f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = ""
    )

    val rotation by animateFloatAsState(
        targetValue = when (label) {
            "Share" -> if (selected) 360f else 0f
            "Save" -> if (selected) 180f else 0f
            else -> 0f
        },
        animationSpec = tween(500),
        label = ""
    )

    val color by animateColorAsState(
        targetValue =
            if (selected)
                selectedColor
            else
                Color(0xFF334155),
        animationSpec = tween(350),
        label = ""
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        FilledIconButton(

            onClick = onClick,

            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = color
            ),

            modifier = Modifier
                .then(
                    if (selected) {
                        Modifier.shadow(
                            elevation = 20.dp,
                            shape = CircleShape,
                            ambientColor = selectedColor,
                            spotColor = selectedColor
                        )
                    } else {
                        Modifier.shadow(
                            elevation = 5.dp,
                            shape = CircleShape
                        )
                    }
                )
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    rotationZ = rotation
                }
        )
        {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.graphicsLayer {

                    scaleX = if (selected) 1.18f else 0.8f
                    scaleY = if (selected) 1.18f else 0.8f

                }
            )

        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = label,
            color = Color.White,
            fontSize = 13.sp
        )

    }

}
