@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextFieldUI() {

    var email by remember {
        mutableStateOf("john.doe@gmail.com")
    }

    var password by remember {
        mutableStateOf("Compose@123")
    }

    var phone by remember {
        mutableStateOf("+1 987 654 3210")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    var rememberMe by remember {
        mutableStateOf(true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                                Color(0xFF0F172A),
                                Color(0xFF16213E),
                                Color(0xFF1E3A5F)
                            )

                )
            ),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(.92f),
            shape = RoundedCornerShape(30.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1E293B)
            ),
            elevation = CardDefaults.cardElevation(12.dp)
        ) {
            Spacer(Modifier.height(10.dp))

            Column(
                modifier = Modifier.padding(24.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        Icons.Default.Lock,
                        null,
                        tint = Color(0xFF60A5FA),
                        modifier = Modifier.size(48.dp)
                    )

                    Spacer(Modifier.width(16.dp))

                    Column {

                        Text(
                            "Secure Login",
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            "TextField Components",
                            color = Color.LightGray
                        )

                    }

                }

                Spacer(Modifier.height(20.dp))

//================ Email ====================

                Text(
                    "Email TextField",
                    color = Color(0xFF60A5FA),
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(8.dp))

                TextField(

                    value = email,

                    onValueChange = {
                        email = it
                    },

                    modifier = Modifier.fillMaxWidth(),

                    label = {
                        Text("Email Address")
                    },

                    leadingIcon = {

                        Icon(
                            Icons.Default.Email,
                            null,
                            tint = Color(0xFF60A5FA)
                        )

                    },

                    supportingText = {

                        Text(
                            "Enter your email",
                            color = Color(0xFF60A5FA)
                        )

                    },

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),

                    shape = RoundedCornerShape(18.dp)

                )

                Spacer(Modifier.height(22.dp))
                //================ Password ====================

                Text(
                    "Password TextField",
                    color = Color(0xFFA855F7),
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(8.dp))

                OutlinedTextField(

                    value = password,

                    onValueChange = {
                        password = it
                    },

                    modifier = Modifier.fillMaxWidth(),

                    label = {
                        Text("Password")
                    },

                    leadingIcon = {

                        Icon(
                            Icons.Default.Lock,
                            null,
                            tint = Color(0xFFA855F7)
                        )

                    },

                    trailingIcon = {

                        IconButton(
                            onClick = {
                                passwordVisible = !passwordVisible
                            }
                        ) {

                            Icon(
                                imageVector =
                                    if (passwordVisible)
                                        Icons.Default.Visibility
                                    else
                                        Icons.Default.VisibilityOff,
                                contentDescription = null
                            )

                        }

                    },

                    visualTransformation =
                        if (passwordVisible)
                            VisualTransformation.None
                        else
                            PasswordVisualTransformation(),

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),

                    supportingText = {

                        Text(
                            "Minimum 8 characters",
                            color = Color(0xFFA855F7)
                        )

                    },

                    shape = RoundedCornerShape(18.dp),

                    colors = OutlinedTextFieldDefaults.colors(

                        focusedBorderColor = Color(0xFFA855F7),

                        unfocusedBorderColor = Color.Gray,

                        focusedLabelColor = Color.White,

                        focusedTextColor = Color.White,

                        unfocusedTextColor = Color.White,

                        cursorColor = Color(0xFFA855F7)

                    )

                )

                Spacer(Modifier.height(22.dp))

//================ Phone ====================

                Text(
                    "Phone Number",
                    color = Color(0xFF22C55E),
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(8.dp))

                OutlinedTextField(

                    value = phone,

                    onValueChange = {
                        phone = it
                    },

                    modifier = Modifier.fillMaxWidth(),

                    label = {
                        Text("Phone Number")
                    },

                    leadingIcon = {

                        Icon(
                            Icons.Default.Phone,
                            null,
                            tint = Color(0xFF22C55E)
                        )

                    },

                    supportingText = {

                        Text(
                            "Include country code",
                            color = Color(0xFF22C55E)
                        )

                    },

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone
                    ),

                    shape = RoundedCornerShape(18.dp),

                    colors = OutlinedTextFieldDefaults.colors(

                        focusedBorderColor = Color(0xFF22C55E),

                        unfocusedBorderColor = Color.Gray,

                        focusedLabelColor = Color.White,

                        focusedTextColor = Color.White,

                        unfocusedTextColor = Color.White,

                        cursorColor = Color(0xFF22C55E)

                    )

                )

                Spacer(Modifier.height(24.dp))
                //================ Remember Me ====================

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {

                        Checkbox(
                            checked = rememberMe,
                            onCheckedChange = {
                                rememberMe = it
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFF3B82F6)
                            )
                        )

                        Text(
                            "Remember Me",
                            color = Color.White
,    fontSize = 13.sp

                        )

                    }

                    TextButton(
                        onClick = {}
                    ) {

                        Text(
                            "Forgot Password?",
                            color = Color(0xFF60A5FA)
                        )

                    }

                }

                Spacer(Modifier.height(18.dp))

//================ Sign In Button ====================

                Button(

                    onClick = {},

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),

                    shape = RoundedCornerShape(18.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3B82F6)
                    )

                ) {

                    Icon(
                        Icons.Default.Login,
                        null
                    )

                    Spacer(Modifier.width(8.dp))

                    Text(
                        "Sign In",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )

                }

                Spacer(Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Divider(
                        modifier = Modifier.weight(1f),
                        color = Color.Gray
                    )

                    Text(
                        "  OR  ",
                        color = Color.LightGray
                    )

                    Divider(
                        modifier = Modifier.weight(1f),
                        color = Color.Gray
                    )

                }

                Spacer(Modifier.height(10.dp))

//================ Google Button ====================

                OutlinedButton(

                    onClick = {},

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),

                    shape = RoundedCornerShape(18.dp),

                    border = BorderStroke(
                        1.dp,
                        Color.Gray
                    )

                ) {

                    Icon(
                        Icons.Default.AccountCircle,
                        contentDescription = null,
                        tint = Color.White
                    )

                    Spacer(Modifier.width(10.dp))

                    Text(
                        "Continue with Google",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )

                }

                Spacer(Modifier.height(12.dp))

            }

        }

    }

}
