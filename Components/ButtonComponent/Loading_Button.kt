@Composable
fun LoadingButton(){
var isLoading by remember {
    mutableStateOf(false)
}


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

                Icon(
                    imageVector = Icons.Default.CloudDownload,
                    contentDescription = null,
                    tint = Color(0xFF60A5FA),
                    modifier = Modifier.size(70.dp)
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "Loading Button",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "Jetpack Compose Material 3",
                    fontSize = 16.sp,
                    color = Color.LightGray
                )

                Spacer(Modifier.height(40.dp))

               Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.08f)
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                Button(
onClick = {

    isLoading = true

},
enabled = !isLoading,
modifier = Modifier
.fillMaxWidth()
.height(58.dp),
shape = RoundedCornerShape(18.dp)
) {

    if (isLoading) {

        CircularProgressIndicator(
            modifier = Modifier.size(22.dp),
            strokeWidth = 2.dp,
            color = Color.White
        )

        Spacer(Modifier.width(12.dp))

        Text("Loading...")

    } else {

        Icon(
            Icons.Default.Download,
            null
        )

        Spacer(Modifier.width(8.dp))

        Text("Download")

    }
}
}}

            }}}}

