@Composable
fun ImageCarouselUI() {
    data class TravelPlace(
        val image: Int,
        val title: String,
        val location: String,
        val rating: String
    )

    val places = listOf(
        TravelPlace(R.drawable.mountain, "Mountain Escape", "Switzerland", "4.9"),
        TravelPlace(R.drawable.beach, "Beach Paradise", "Maldives", "4.8"),
        TravelPlace(R.drawable.forest, "Forest Adventure", "Canada", "4.7")
    )

    val pagerState = rememberPagerState(
        pageCount = { 3 }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF111827))
    ) {

        Spacer(Modifier.height(40.dp))

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 32.dp),
            pageSpacing = 16.dp
        ) { page ->
            val place = places[page]

            val scale by animateFloatAsState(
                targetValue = if (pagerState.currentPage == page) 1f else 0.90f,
                label = ""
            )
            Card(
                modifier = Modifier
                    .width(320.dp)
                    .height(430.dp),
                shape = RoundedCornerShape(24.dp)
            ) {

                Box {

                    Image(
                        painter = painterResource(
                            place.image
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                            }
                            .clip(RoundedCornerShape(24.dp)),
                        contentScale = ContentScale.Crop
                    )
              // Gradient Overlay
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.35f),
                                Color.Black.copy(alpha = 0.85f)
                            )
                        )

                    )
            )

                Box(modifier = Modifier.align(Alignment.TopEnd).padding(10.dp).size(42.dp).background(
                   Color.Gray.copy(alpha = 0.18f),
                   CircleShape
               ), contentAlignment = Alignment.Center){
                   Box(
                       modifier = Modifier
                           .align(Alignment.TopEnd)
                           .padding(0.dp)
                           .size(42.dp)
                           .clip(CircleShape)
                           .background(Color(0xCCFFFFFF).copy(alpha = 0.12f))
                           .border(
                               0.8.dp,
                               Color.White.copy(alpha = 0.18f),
                               CircleShape
                           ),
                       contentAlignment = Alignment.Center
                   ) {
                       Icon(
                           imageVector = Icons.Default.Favorite,
                           contentDescription = null,
                           tint = Color(0xFFFF4D9D),
                           modifier = Modifier.size(25.dp)
                       )
                   }               }
               // Bottom Content
               Column(
                   modifier = Modifier
                       .align(Alignment.BottomStart)
                       .padding(22.dp)
               ) {

                   // ⭐ Rating
                   Row(
                       verticalAlignment = Alignment.CenterVertically
                   ) {

                       Icon(
                           imageVector = Icons.Default.Star,
                           contentDescription = null,
                           tint = Color(0xFFFFD54F),
                           modifier = Modifier.size(18.dp)
                       )

                       Spacer(modifier = Modifier.width(6.dp))

                       Text(
                           text = place.rating,
                           color = Color.White,
                           fontSize = 16.sp,
                           fontWeight = FontWeight.SemiBold
                       )
                   }

                   Spacer(modifier = Modifier.height(20.dp))

                   // Destination
                   Text(
                       text = place.title,
                       color = Color.White,
                       fontSize = 30.sp,
                       fontWeight = FontWeight.Bold
                   )

                   Spacer(modifier = Modifier.height(6.dp))

                   // Location
                   Row(
                       verticalAlignment = Alignment.CenterVertically
                   ) {

                       Icon(
                           imageVector = Icons.Default.LocationOn,
                           contentDescription = null,
                           tint = Color.White.copy(alpha = 0.9f),
                           modifier = Modifier.size(18.dp)
                       )

                       Spacer(modifier = Modifier.width(4.dp))

                       Text(
                           text = place.location,
                           color = Color.White.copy(alpha = 0.85f),
                           fontSize = 17.sp
                       )
                   }

                   Spacer(modifier = Modifier.height(20.dp))

                                     // Explore Button
                                      Button(
                                          onClick = {},
                                          colors = ButtonDefaults.buttonColors(
                                              containerColor = Color.Transparent
                                          ),
                                          contentPadding = PaddingValues(),
                                          shape = RoundedCornerShape(16.dp),
                                          modifier = Modifier
                                              .width(170.dp)
                                              .height(40.dp)
                                      ) {

                                          Box(
                                              modifier = Modifier
                                                  .fillMaxSize()
                                                  .background(
                                                      brush = Brush.horizontalGradient(
                                                          colors = listOf(
                                                              Color(0xFFFF4D9D),
                                                              Color(0xFFC026D3)
                                                          )
                                                      ),
                                                      shape = RoundedCornerShape(16.dp)
                                                  ),
                                              contentAlignment = Alignment.Center
                                          ) {

                                              Row(
                                                  verticalAlignment = Alignment.CenterVertically
                                              ) {

                                                  Text(
                                                      text = "Explore",
                                                      color = Color.White,
                                                      fontSize = 18.sp,
                                                      fontWeight = FontWeight.Bold
                                                  )

                                                  Spacer(modifier = Modifier.width(6.dp))

                                                  Icon(
                                                      imageVector = Icons.Default.ArrowForward,
                                                      contentDescription = null,
                                                      tint = Color.White,
                                                      modifier = Modifier.size(16.dp)
                                                  )
                                              }
                                          } }           }

                      }        }}}}
