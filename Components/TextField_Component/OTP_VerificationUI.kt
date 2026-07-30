@Composable
fun OTPVerificationUI() {
    var otp1 by remember { mutableStateOf("") }
    var otp2 by remember { mutableStateOf("") }
    var otp3 by remember { mutableStateOf("") }
    var otp4 by remember { mutableStateOf("") }
    var otp5 by remember { mutableStateOf("") }
    var otp6 by remember { mutableStateOf("") }

    var isLoading by remember { mutableStateOf(false) }

    val focus1 = remember { FocusRequester() }
    val focus2 = remember { FocusRequester() }
    val focus3 = remember { FocusRequester() }
    val focus4 = remember { FocusRequester() }
    val focus5 = remember { FocusRequester() }
    val focus6 = remember { FocusRequester() }

    val keyboard = LocalSoftwareKeyboardController.current

    var verified by remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()

    var isVerified by remember { mutableStateOf(false) }

    var showError by remember { mutableStateOf(false) }

    var timer by remember { mutableIntStateOf(30) }

    var resendEnabled by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {

        while (timer > 0) {

            delay(1000)

            timer--

        }

        resendEnabled = true

    }
    val otp = otp1 + otp2 + otp3 + otp4 + otp5 + otp6

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
                Modifier.height(45.dp)
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

                        Icon(
                            Icons.Default.Password,
                            contentDescription = null,
                            tint = Color(0xFF8B5CF6),
                            modifier = Modifier.size(46.dp)
                        )

                        Spacer(Modifier.width(16.dp))

                        Column {

                            Text(
                                text = "OTP Verification",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )

                            Text(
                                text = "Material TextField Components",
                                color = Color.LightGray
                            )

                        }

                    }
                }
                item {
                    Spacer(Modifier.height(26.dp))

                    Text(
                        text = "Enter Verification Code",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "We've sent a 6-digit code to your registered phone number.",
                        color = Color.LightGray,
                        fontSize = 14.sp
                    )
                }
                item {
                    Spacer(Modifier.height(26.dp))
                    Row(

                        modifier = Modifier.fillMaxWidth(),

                        horizontalArrangement = Arrangement.SpaceEvenly

                    ) {

                        OTPTextField(
                            value = otp1,
                            onValueChange = { otp1 = it },
                            focusRequester = focus1,
                            nextFocus = focus2
                        )

                        OTPTextField(
                            value = otp2,
                            onValueChange = { otp2 = it },
                            focusRequester = focus2,
                            nextFocus = focus3
                        )

                        OTPTextField(
                            value = otp3,
                            onValueChange = { otp3 = it },
                            focusRequester = focus3,
                            nextFocus = focus4
                        )

                        OTPTextField(
                            value = otp4,
                            onValueChange = { otp4 = it },
                            focusRequester = focus4,
                            nextFocus = focus5
                        )

                        OTPTextField(
                            value = otp5,
                            onValueChange = { otp5 = it },
                            focusRequester = focus5,
                            nextFocus = focus6
                        )

                        OTPTextField(
                            value = otp6,
                            onValueChange = { otp6 = it },
                            focusRequester = focus6
                        )

                    }}
item {
    Spacer(Modifier.height(30.dp))
    Button(

        enabled = otp.length == 6 && !isLoading,

        onClick = {

            scope.launch {

                isLoading = true

                showError = false

                delay(1800)

                isLoading = false

                if (otp == "123456") {

                    isVerified = true

                } else {

                    showError = true

                }

            }

        },

        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),

        shape = RoundedCornerShape(18.dp),

        colors = ButtonDefaults.buttonColors(

            containerColor = Color(0xFF8B5CF6)

        )

    ) {

        if (isLoading) {

            CircularProgressIndicator(

                modifier = Modifier.size(20.dp),

                strokeWidth = 2.dp,

                color = Color.White

            )

            Spacer(Modifier.width(10.dp))

            Text("Verifying...")

        }

        else {

            Icon(
                Icons.Default.Verified,
                null
            )

            Spacer(Modifier.width(8.dp))

            Text("Verify OTP")

        }

    }
    }
                item { Spacer(Modifier.height(22.dp))

                    Row(

                        modifier = Modifier.fillMaxWidth(),

                        horizontalArrangement = Arrangement.Center,

                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Text(

                            "Didn't receive the code?",

                            color = Color.LightGray

                        )

                        Spacer(Modifier.width(6.dp))

                        TextButton(

                            enabled = resendEnabled,

                            onClick = {

                                timer = 30

                                resendEnabled = false

                            }

                        ) {

                            Text(

                                if (resendEnabled)
                                    "Resend OTP"
                                else
                                    "00:$timer",

                                color = Color(0xFF8B5CF6)

                            )

                        }

                    }
                AnimatedVisibility(showError) {

                    Text(

                        text = "Invalid OTP. Please try again.",

                        color = Color.Red,

                        modifier = Modifier.padding(top = 12.dp)

                    )

                }}    }            }
            }

    if (isVerified) {

        AlertDialog(

            onDismissRequest = {

                isVerified = false

            },

            confirmButton = {

                Button(

                    onClick = {

                        isVerified = false

                    }

                ) {

                    Text("Continue")

                }

            },

            icon = {

                Icon(

                    Icons.Default.CheckCircle,

                    null,

                    tint = Color(0xFF22C55E),

                    modifier = Modifier.size(60.dp)

                )

            },

            title = {

                Text("Verification Successful")

            },

            text = {

                Text("Welcome back!")

            }

        )

    }
        }

@Composable
fun OTPTextField(

    value: String,

    onValueChange: (String) -> Unit,

    focusRequester: FocusRequester,

    nextFocus: FocusRequester? = null

) {

    BasicTextField(

        value = value,

        onValueChange = {

            if (it.length <= 1 && it.all { c -> c.isDigit() }) {

                onValueChange(it)

                if (it.isNotEmpty()) {

                    nextFocus?.requestFocus()

                }

            }

        },

        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),

        singleLine = true,

        textStyle = TextStyle(

            color = Color.White,

            fontSize = 24.sp,

            fontWeight = FontWeight.Bold,

            textAlign = TextAlign.Center

        ),

        modifier = Modifier
            .size(40.dp)
            .focusRequester(focusRequester),

        decorationBox = { innerTextField ->

            Box(

                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        2.dp,
                        Color(0xFF8B5CF6),
                        RoundedCornerShape(14.dp)
                    )
                    .background(
                        Color(0xFF263244),
                        RoundedCornerShape(14.dp)
                    ),

                contentAlignment = Alignment.Center

            ) {

                innerTextField()

            }

        }

    )

}
