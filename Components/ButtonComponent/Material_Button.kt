
@Composable
fun BasicButtonUI() {

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
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    Icons.Default.TouchApp,
                    contentDescription = null,
                    tint = Color(0xFF60A5FA),
                    modifier = Modifier.size(55.dp)
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Material Buttons",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "Jetpack Compose Button Components",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )



                Spacer(Modifier.height(30.dp))

                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {

                    Icon(Icons.Default.Check, null)

                    Spacer(Modifier.width(8.dp))

                    Text("Filled Button")
                }

                Spacer(Modifier.height(16.dp))

                ElevatedButton(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 10.dp
                    )
                ) {

                    Icon(Icons.Default.Star, null)

                    Spacer(Modifier.width(8.dp))

                    Text("Elevated Button")

                }

                    Spacer(Modifier.height(16.dp))

                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(
                            1.5.dp,
                            Color(0xFF60A5FA)
                        )
                    ) {

                        Icon(Icons.Default.Email, null)

                        Spacer(Modifier.width(8.dp))

                        Text("Outlined Button")

                    }

                    Spacer(Modifier.height(16.dp))

                    TextButton(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Icon(
                            Icons.Default.ArrowForward,
                            null,
                            tint = Color(0xFF60A5FA)
                        )

                        Spacer(Modifier.width(8.dp))

                        Text(
                            "Text Button",
                            color = Color(0xFF60A5FA)
                        )

                    }


            }

        }

    }

}
