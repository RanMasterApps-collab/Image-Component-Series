//******************ParallexCard*************

@Composable
fun TravelExplorerScreen() {

    val listState = rememberLazyListState()
    val topDestinations = listOf(



        TopDestination(
            "Maldives",
            "Indian Ocean",
            4.8f,
            R.drawable.maldive
        ),
        TopDestination(
            "Switzerland",
            "Swiss Alps",
            4.9f,
            R.drawable.swizerland
        ),
        TopDestination(
            "Canada",
            "Banff",
            4.7f,
            R.drawable.canada
        ),
        TopDestination(
            "Japan",
            "Tokyo",
            4.7f,
            R.drawable.japan
        )

    )
    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFF111827)),
    ) {

        item {

            ParallaxHeader(
                image = R.drawable.explore,
                listState = listState
            )

        }

        item {

            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Search Destination") },
                leadingIcon = {
                    Icon(Icons.Default.Search, null)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp)
            )

        }

        item {

            SectionTitle("Featured Destinations")
            FeaturedDestinationSection()
        }

        item {

            SectionTitle("Popular Categories")

            CategoryGrid()

        }

               item {

                    SectionTitle("Top Destinations")
        }
                itemsIndexed(topDestinations) { index, item ->

                    TopDestinationCard(
                        destination = item,
                        listState = listState
                    )
                }
                item {

                    CTASection()

                }

                item {

                    Spacer(Modifier.height(30.dp))

                    Text(
                        "© 2026 Travel Explorer",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        textAlign = TextAlign.Center,
                        color = Color.Gray
                    )

                }

    }

}
@Composable
fun ParallaxHeader(
    image: Int,
    listState: LazyListState
) {

    val offset = listState.firstVisibleItemScrollOffset * .5f

    Box(
        Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(bottomStart = 2.dp, bottomEnd = 2.dp))
    ) {

        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .height(420.dp)     // Header se badi image
                .offset {
                    IntOffset(
                        x = 0,
                        y = (listState.firstVisibleItemScrollOffset * 0.4f).toInt()
                    )
                },
            contentScale = ContentScale.Crop
        )

        Box(
            Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black.copy(.7f)
                        )
                    )
                )
        )

        Column(
            Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ) {

            Text(
                "Travel Explorer",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                "Discover Beautiful Places",
                color = Color.White
            )

        }

    }

}
@Composable
fun TopDestinationCard(
    destination: TopDestination,
    listState: LazyListState
) {

    var cardY by remember {
        mutableFloatStateOf(0f)
    }

    val parallax by animateFloatAsState(
        targetValue = -(cardY * .18f),
        animationSpec = tween(300),
        label = ""
    )

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .onGloballyPositioned {
                cardY = it.positionInWindow().y
            },
        shape = RoundedCornerShape(28.dp)
    ) {

        Box {

            Image(
                painter = painterResource(destination.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)          // Card se badi image
                    .graphicsLayer {

                        translationY = parallax

                        scaleX = 1.35f
                        scaleY = 1.35f

                    }
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black.copy(.70f)
                            )
                        )
                    )
            )

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
                    .background(Color.Black)//.copy(alpha = 0.45f)
                    .padding(horizontal = 16.dp, vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = destination.title,
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "📍 ${destination.location}",
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 14.sp
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End
                ) {

                    Text(
                        text = "⭐ ${destination.rating}",
                        color = Color(0xFFFFD54F),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    FilledTonalButton(
                        onClick = {},
                        shape = RoundedCornerShape(50)
                    ) {
                        Text("Explore")
                    }
                }
            }/* Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp)
                    .fillMaxWidth(.65f)
                    .background(
                        Color.Black.copy(.35f),
                        RoundedCornerShape(18.dp)
                    )
                    .padding(16.dp)
            ) {

                Text(
                    destination.title,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    "📍 ${destination.location}",
                    color = Color.White.copy(.9f)
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    "⭐ ${destination.rating}",
                    color = Color.Yellow
                )

                Spacer(Modifier.height(12.dp))

                Button(
                    onClick = {},
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Explore")
                }

            }*/

        }

    }
}
data class TopDestination(
    val title: String,
    val location: String,
    val rating: Float,
    @DrawableRes val image: Int
)
@Composable
fun FeaturedDestinationSection() {

    val listState = rememberLazyListState()

    LazyRow(
        state = listState,
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        itemsIndexed(destinationList) { index, item ->

            PremiumParallaxCard(
                item = item,
                index = index,
                listState = listState
            )

        }

    }

}
data class Destination(
    val title: String,
    @DrawableRes val image: Int
)

val destinationList = listOf(

    Destination("Maldives", R.drawable.maldive),
    Destination("Switzerland", R.drawable.swizerland),
    Destination("Canada", R.drawable.canada),
    Destination("Japan", R.drawable.japan)

)
@Composable
fun PremiumParallaxCard(
    item: Destination,
    index: Int,
    listState: LazyListState
) {

    val layoutInfo = listState.layoutInfo

    val itemInfo = layoutInfo.visibleItemsInfo.find {
        it.index == index
    }

    val screenCenter = layoutInfo.viewportEndOffset / 2f

    val distance = if (itemInfo != null) {

        val itemCenter = itemInfo.offset + itemInfo.size / 2f

        (itemCenter - screenCenter)

    } else 0f

    val fraction = (distance / screenCenter)
        .coerceIn(-1f, 1f)

    val scale = 1f - kotlin.math.abs(fraction) * .12f

    val rotation = fraction * -8f

    Card(
        modifier = Modifier
            .width(280.dp)
            .height(200.dp)
            .graphicsLayer {

                scaleX = scale
                scaleY = scale

                rotationY = rotation

                cameraDistance = 18 * density

            },
        shape = RoundedCornerShape(28.dp),
        elevation = CardDefaults.cardElevation(12.dp)
    ) {

        Box {

            Image(
                painter = painterResource(item.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(380.dp)
                    .graphicsLayer {

                        translationX = -fraction * 130f

                        scaleX = 1.4f
                        scaleY = 1.4f

                    }
            )

            Box(
                Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black.copy(.65f)
                            )
                        )
                    )
            )

            Column(
                Modifier
                    .align(Alignment.BottomStart)
                    .padding(18.dp)
            ) {

                Text(
                    item.title,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "Explore Now",
                    color = Color.White.copy(.85f)
                )

            }

        }

    }

}
@Composable
fun SectionTitle(title: String) {

    Text(
        text = title,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier.padding(16.dp)
    )

}
@Composable
fun CategoryGrid() {

    Column(
        Modifier.padding(horizontal = 16.dp)
    ) {

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            FilterChip(
                selected = false,
                onClick = {},
                label = { Text("🏔 Mountains") }
            )

            FilterChip(
                selected = false,
                onClick = {},
                label = { Text("🏖 Beaches") }
            )

        }

        Spacer(Modifier.height(12.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            FilterChip(
                selected = false,
                onClick = {},
                label = { Text("🌲 Forests") }
            )

            FilterChip(
                selected = false,
                onClick = {},
                label = { Text("🏕 Camping") }
            )

        }

    }

}
@Composable
fun DestinationItem(title: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {

        Text(
            title,
            modifier = Modifier.padding(18.dp),
            fontSize = 18.sp
        )

    }

}
@Composable
fun CTASection() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {

        Column(
            Modifier.padding(20.dp)
        ) {

            Text(
                "Plan Your Next Trip",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )

            Spacer(Modifier.height(8.dp))

            Text(
                "Explore the world's most beautiful places."
            )

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {}
            ) {

                Text("Get Started")

            }

        }

    }

}
