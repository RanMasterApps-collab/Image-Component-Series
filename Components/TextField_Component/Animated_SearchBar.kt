@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatedSearchUI() {

    //---------------- STATES ----------------//

    var expanded by remember { mutableStateOf(false) }

    var search by remember { mutableStateOf("") }

    var isLoading by remember { mutableStateOf(false) }

    var searchCompleted by remember { mutableStateOf(false) }

    var favorite by remember { mutableStateOf(false) }

    val focusRequester = remember {
        FocusRequester()
    }

    val keyboard =
        LocalSoftwareKeyboardController.current

    val scope = rememberCoroutineScope()

    val totalSearches = remember {
        mutableIntStateOf(128)
    }

    val resultsFound = remember {
        mutableIntStateOf(0)
    }

    val searchTime = remember {
        mutableStateOf("0.0s")
    }

    //---------------- DATA ----------------//

    val suggestions = listOf(

        "Login UI",
        "Dashboard UI",
        "Animated Buttons",
        "Bottom Navigation",
        "Glassmorphism",
        "Profile Screen",
        "Compose Cards",
        "Search Bar UI",
        "OTP Verification",
        "Food Delivery UI",
        "Travel App UI",
        "Expense Tracker"

    )

    val filteredSuggestions =
        suggestions.filter {

            it.contains(
                search,
                ignoreCase = true
            )

        }

    //---------------- FOCUS ----------------//

    LaunchedEffect(expanded) {

        if (expanded) {

            focusRequester.requestFocus()

            keyboard?.show()

        }

    }

    //---------------- MAIN SCREEN ----------------//

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

            )

    ) {

        Card(

            modifier = Modifier

                .fillMaxWidth(.93f)

                .align(Alignment.Center),

            shape = RoundedCornerShape(28.dp),

            colors = CardDefaults.cardColors(

                containerColor = Color(0xFF1E293B)

            ),

            elevation = CardDefaults.cardElevation(
                12.dp
            )

        ) {
            Spacer(
                Modifier.height(15.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(24.dp)
            ) {

                //---------------- HEADER ----------------//
item {

                Row(

                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Surface(

                        color = Color(0x2206B6D4),

                        shape = CircleShape

                    ) {

                        Icon(

                            imageVector = Icons.Default.ManageSearch,

                            contentDescription = null,

                            tint = Color(0xFF06B6D4),

                            modifier = Modifier
                                .padding(14.dp)
                                .size(34.dp)

                        )

                    }

                    Spacer(
                        Modifier.width(16.dp)
                    )

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(

                            text = "Animated Search UI",

                            color = Color.White,

                            fontSize = 26.sp,

                            fontWeight = FontWeight.Bold

                        )

                        Spacer(
                            Modifier.height(4.dp)
                        )

                        Text(

                            text = "Professional Material 3 Search Experience",

                            color = Color.LightGray,

                            fontSize = 14.sp

                        )

                    }

                    FilledTonalIconButton(

                        onClick = {

                            favorite = !favorite

                        }

                    ) {

                        AnimatedContent(

                            targetState = favorite,

                            label = ""

                        ) { fav ->

                            Icon(

                                imageVector = if (fav)

                                    Icons.Default.Favorite

                                else

                                    Icons.Default.FavoriteBorder,

                                contentDescription = null,

                                tint = if (fav)

                                    Color.Red

                                else

                                    Color.White

                            )

                        }

                    }

                }

                Spacer(
                    Modifier.height(28.dp)
                )}
                //---------------- SEARCH AREA ----------------//
item {
    AnimatedContent(
        targetState = expanded,
        label = "SearchAnimation"
    ) { isExpanded ->

        if (isExpanded) {

            OutlinedTextField(

                value = search,

                onValueChange = {

                    search = it

                    // Previous search hide
                    searchCompleted = false

                    // Hide previous results
                    resultsFound.intValue = 0

                    // Reset statistics
                    searchTime.value = "0.0s"

                    // Agar text empty ho jaye to loading bhi band
                    isLoading = false


                },

                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),

                placeholder = {

                    Text(
                        "Search UI Components...",
                        fontSize = 15.sp

                    )

                },

                leadingIcon = {

                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        tint = Color(0xFF06B6D4)
                    )

                },
trailingIcon = {

                    IconButton(

                        onClick = {

                            if (search.isNotBlank()) {

                                search = ""

                            } else {

                                expanded = false
                                search = ""
                                searchCompleted = false
                                keyboard?.hide()

                            }

                        }

                    ) {

                        Icon(
                            Icons.Default.Close,
                            contentDescription = null
                        )

                    }

                },
                singleLine = true,

                shape = RoundedCornerShape(20.dp),

                colors = OutlinedTextFieldDefaults.colors(

                    focusedBorderColor = Color(0xFF06B6D4),

                    unfocusedBorderColor = Color.Gray,

                    focusedTextColor = Color.White,

                    unfocusedTextColor = Color.White,

                    cursorColor = Color(0xFF06B6D4)

                )

            )

        } else {

            FilledIconButton(

                onClick = {

                    expanded = true

                },

                modifier = Modifier
                    .size(66.dp),

                colors = IconButtonDefaults.filledIconButtonColors(

                    containerColor = Color(0xFF06B6D4)

                )

            ) {

                Icon(

                    Icons.Default.Search,

                    contentDescription = null,

                    tint = Color.White,

                    modifier = Modifier.size(30.dp)

                )

            }

        }

    }

    Spacer(Modifier.height(24.dp))

    //---------------- SEARCH BUTTON ----------------//

    Button(

        onClick = {

            if (search.isNotBlank()) {

                scope.launch {

                    isLoading = true
                    searchCompleted = false

                    delay(2000)

                    isLoading = false
                    searchCompleted = true

                    resultsFound.intValue =
                        (4..15).random()

                    totalSearches.intValue += 1

                    searchTime.value =
                        "${(4..9).random() / 10f}s"

                    keyboard?.hide()

                }

            }

        },

        enabled = !isLoading,

        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp),

        shape = RoundedCornerShape(18.dp),

        colors = ButtonDefaults.buttonColors(

            containerColor = Color(0xFF06B6D4)

        )

    ) {

        AnimatedContent(

            targetState = isLoading,

            label = ""

        ) { loading ->

            if (loading) {

                Row(

                    verticalAlignment = Alignment.CenterVertically

                ) {

                    CircularProgressIndicator(

                        modifier = Modifier.size(22.dp),

                        strokeWidth = 2.dp,

                        color = Color.White

                    )

                    Spacer(Modifier.width(12.dp))

                    Text(

                        "Searching...",

                        fontWeight = FontWeight.Bold,

                        color = Color.White

                    )

                }

            } else {

                Row(

                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Icon(

                        Icons.Default.Search,

                        contentDescription = null

                    )

                    Spacer(Modifier.width(8.dp))

                    Text(

                        "Search Components",

                        fontWeight = FontWeight.Bold

                    )

                }

            }

        }

    }

    Spacer(Modifier.height(24.dp))
}
                //---------------- SUGGESTIONS ----------------//
item {

                AnimatedVisibility(

                    visible =
                        expanded &&
                                search.isNotBlank() &&
                                !isLoading &&
                                !searchCompleted,

                    enter = fadeIn() + expandVertically(),

                    exit = fadeOut() + shrinkVertically()

                ) {

                    Column {

                        Text(

                            text = "Suggestions",

                            color = Color(0xFF06B6D4),

                            fontWeight = FontWeight.Bold,

                            fontSize = 18.sp

                        )

                        Spacer(Modifier.height(12.dp))

                        filteredSuggestions.forEach { item ->

                            ElevatedCard(

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp)
                                    .clickable {

                                        search = item
                                        searchCompleted = true
                                        keyboard?.hide()

                                    },

                                colors = CardDefaults.elevatedCardColors(

                                    containerColor = Color(0xFF263244)

                                ),

                                shape = RoundedCornerShape(18.dp)

                            ) {

                                Row(

                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),

                                    verticalAlignment = Alignment.CenterVertically

                                ) {

                                    Icon(

                                        Icons.Default.Search,

                                        contentDescription = null,

                                        tint = Color(0xFF06B6D4)

                                    )

                                    Spacer(Modifier.width(14.dp))

                                    Text(

                                        text = item,

                                        color = Color.White,

                                        modifier = Modifier.weight(1f)

                                    )

                                    Badge(

                                        containerColor = Color(0xFF06B6D4)

                                    ) {

                                        Text(

                                            "UI",

                                            color = Color.White,

                                            fontSize = 10.sp

                                        )

                                    }

                                }

                            }

                        }

                    }

                }}

                //---------------- RECENT SEARCHES ----------------//
item {
    AnimatedVisibility(

        visible =
            expanded &&
                    search.isBlank(),

        enter = fadeIn(),

        exit = fadeOut()

    ) {

        Column {

            Spacer(Modifier.height(24.dp))

            Text(

                text = "Recent Searches",

                color = Color(0xFFF59E0B),

                fontWeight = FontWeight.Bold,

                fontSize = 18.sp

            )

            Spacer(Modifier.height(14.dp))

            FlowRow(

                horizontalArrangement = Arrangement.spacedBy(10.dp),

                verticalArrangement = Arrangement.spacedBy(10.dp)

            ) {

                listOf(

                    "Compose",

                    "Material 3",

                    "Cards",

                    "Buttons",

                    "Animation",

                    "Profile",

                    "Login",

                    "Dashboard"

                ).forEach { item ->

                    SuggestionChip(

                        onClick = {

                            search = item
                            // Previous result hide
                            searchCompleted = false
                            isLoading = false
                            resultsFound.intValue = 0
                            searchTime.value = "0.0s"
                        },

                        label = {

                            Text(item)

                        },

                        icon = {

                            Icon(

                                Icons.Default.History,

                                contentDescription = null,

                                modifier = Modifier.size(18.dp)

                            )

                        }

                    )

                }

            }

        }

    }

    Spacer(Modifier.height(24.dp))
}
                //---------------- SUCCESS BANNER ----------------//
item {
    AnimatedVisibility(

        visible = searchCompleted &&
                search.isNotBlank() &&
                resultsFound.intValue > 0,/* visible = searchCompleted,
*/
        enter = fadeIn() + slideInVertically(),

        exit = fadeOut()

    ) {

        Card(

            modifier = Modifier.fillMaxWidth(),

            colors = CardDefaults.cardColors(

                containerColor = Color(0xFF14532D)

            ),

            shape = RoundedCornerShape(20.dp)

        ) {

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),

                verticalAlignment = Alignment.CenterVertically

            ) {

                Icon(

                    Icons.Default.CheckCircle,

                    contentDescription = null,

                    tint = Color(0xFF4ADE80),

                    modifier = Modifier.size(34.dp)

                )

                Spacer(Modifier.width(14.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(

                        text = "Search Completed",

                        color = Color.White,

                        fontWeight = FontWeight.Bold,

                        fontSize = 18.sp

                    )

                    Text(

                        text = "${resultsFound.intValue} Components Found",

                        color = Color(0xFFD1FAE5)

                    )

                }

            }

        }

    }

    Spacer(Modifier.height(22.dp))
}
                //---------------- SEARCH STATISTICS ----------------//
item {
    AnimatedVisibility(

        visible = searchCompleted &&
                search.isNotBlank() &&
                resultsFound.intValue > 0,
        enter = fadeIn(),

        exit = fadeOut()

    ) {

        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.spacedBy(12.dp)

        ) {

            StatisticCard(

                modifier = Modifier.weight(1f),

                title = "Results",

                value = resultsFound.intValue.toString(),

                icon = Icons.Default.GridView,

                color = Color(0xFF06B6D4)

            )

            StatisticCard(

                modifier = Modifier.weight(1f),

                title = "Time",

                value = searchTime.value,

                icon = Icons.Default.Timer,

                color = Color(0xFFF59E0B)

            )

            StatisticCard(

                modifier = Modifier.weight(1f),

                title = "Searches",

                value = totalSearches.intValue.toString(),

                icon = Icons.Default.Analytics,

                color = Color(0xFF10B981)

            )

        }

    }

    Spacer(Modifier.height(24.dp))
}
                //---------------- RESULT CARDS ----------------//
item {
    AnimatedVisibility(

        visible = searchCompleted &&
                search.isNotBlank() &&
                resultsFound.intValue > 0,
        enter = fadeIn() + expandVertically(),

        exit = fadeOut()

    ) {

        Column {

            repeat(resultsFound.intValue) { index ->

                ElevatedCard(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),

                    colors = CardDefaults.elevatedCardColors(

                        containerColor = Color(0xFF263244)

                    ),

                    shape = RoundedCornerShape(22.dp)

                ) {

                    Row(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),

                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Surface(

                            color = Color(0x2206B6D4),

                            shape = CircleShape

                        ) {

                            Icon(

                                Icons.Default.Widgets,

                                null,

                                tint = Color(0xFF06B6D4),

                                modifier = Modifier
                                    .padding(14.dp)
                                    .size(26.dp)

                            )

                        }

                        Spacer(Modifier.width(16.dp))

                        Column(

                            modifier = Modifier.weight(1f)

                        ) {

                            Text(

                                text = "$search Component ${index + 1}",

                                color = Color.White,

                                fontWeight = FontWeight.Bold,

                                fontSize = 17.sp

                            )

                            Spacer(Modifier.height(4.dp))

                            Text(

                                text = "Material 3 • Jetpack Compose",

                                color = Color.LightGray,

                                fontSize = 13.sp

                            )

                        }

                        IconButton(

                            onClick = {

                                favorite = !favorite

                            }

                        ) {

                            AnimatedContent(

                                targetState = favorite,

                                label = ""

                            ) { fav ->

                                Icon(

                                    imageVector =

                                        if (fav)

                                            Icons.Default.Favorite
                                        else

                                            Icons.Default.FavoriteBorder,

                                    contentDescription = null,

                                    tint =

                                        if (fav)

                                            Color.Red
                                        else

                                            Color.Gray

                                )

                            }

                        }

                    }

                }

            }

        }

    }
}
            } // Column End

        } // Card End

    } // Box End

} // AnimatedSearchUI End
@Composable
fun StatisticCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    icon: ImageVector,
    color: Color
) {

    Card(

        modifier = modifier,

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1F2937)
        ),

        shape = RoundedCornerShape(18.dp)

    ) {

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Icon(

                icon,

                null,

                tint = color,

                modifier = Modifier.size(28.dp)

            )

            Spacer(Modifier.height(10.dp))

            Text(

                value,

                color = Color.White,

                fontWeight = FontWeight.Bold,

                fontSize = 20.sp

            )

            Text(

                title,

                color = Color.LightGray,

                fontSize = 12.sp

            )

        }

    }

}
