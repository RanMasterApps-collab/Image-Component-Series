
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialTextFieldUI() {

    var promptTitle by remember {
        mutableStateOf("Instagram Post Generator")
    }

    var aiModel by remember {
        mutableStateOf("GPT-5")
    }

    var promptDescription by remember {
        mutableStateOf(
            "Create a modern Instagram post for a coffee shop with warm colors and realistic lighting."
        )
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
            modifier = Modifier.fillMaxWidth(.92f),
            shape = RoundedCornerShape(30.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1E293B)
            ),
            elevation = CardDefaults.cardElevation(14.dp)
        ) {

            Column(
                modifier = Modifier.padding(24.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        Icons.Default.AutoAwesome,
                        null,
                        tint = Color(0xFF60A5FA),
                        modifier = Modifier.size(48.dp)
                    )

                    Spacer(Modifier.width(16.dp))

                    Column {

                        Text(
                            text = "AI Prompt Builder",
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Material TextField Components",
                            color = Color.LightGray,
                            fontSize = 14.sp
                        )

                    }

                }

                Spacer(Modifier.height(28.dp))

//================ Filled TextField ===================

                Text(
                    text = "Filled TextField",
                    color = Color(0xFF60A5FA),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(Modifier.height(10.dp))

                TextField(

                    value = promptTitle,

                    onValueChange = {
                        promptTitle = it
                    },

                    modifier = Modifier.fillMaxWidth(),

                    label = {
                        Text("Prompt Title")
                    },

                    leadingIcon = {

                        Icon(
                            Icons.Default.AutoAwesome,
                            null,
                            tint = Color(0xFF60A5FA)
                        )

                    },

                    trailingIcon = {

                        if (promptTitle.isNotEmpty()) {

                            IconButton(
                                onClick = {
                                    promptTitle = ""
                                }
                            ) {

                                Icon(
                                    Icons.Default.Clear,
                                    null
                                )

                            }

                        }

                    },

                    supportingText = {

                        Text(
                            "Enter a short prompt title",
                            color = Color(0xFF60A5FA)
                        )

                    },

                    shape = RoundedCornerShape(18.dp),

                    colors = TextFieldDefaults.colors(

                        focusedContainerColor = Color(0xFF334155),

                        unfocusedContainerColor = Color(0xFF263244),

                        focusedIndicatorColor = Color(0xFF3B82F6),

                        unfocusedIndicatorColor = Color.Gray,

                        focusedLabelColor = Color.White,

                        focusedTextColor = Color.White,

                        unfocusedTextColor = Color.White,

                        cursorColor = Color(0xFF60A5FA)

                    )

                )

                Spacer(Modifier.height(22.dp))
//================ Outlined TextField ===================

                Text(
                    text = "Outlined TextField",
                    color = Color(0xFF22C55E),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(Modifier.height(10.dp))

                OutlinedTextField(

                    value = aiModel,

                    onValueChange = {
                        aiModel = it
                    },

                    modifier = Modifier.fillMaxWidth(),

                    label = {
                        Text("AI Model")
                    },

                    leadingIcon = {

                        Icon(
                            Icons.Default.Psychology,
                            null,
                            tint = Color(0xFF22C55E)
                        )

                    },

                    supportingText = {

                        Text(
                            "Select your preferred AI model",
                            color = Color(0xFF22C55E)
                        )

                    },

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

                Spacer(Modifier.height(22.dp))

//================ Multi-line TextField ===================

                Text(
                    text = "Multi-line TextField",
                    color = Color(0xFFEC4899),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(Modifier.height(10.dp))

                OutlinedTextField(

                    value = promptDescription,

                    onValueChange = {

                        if (it.length <= 200)
                            promptDescription = it

                    },

                    modifier = Modifier.fillMaxWidth(),

                    label = {
                        Text("Prompt Description")
                    },

                    leadingIcon = {

                        Icon(
                            Icons.Default.Description,
                            null,
                            tint = Color(0xFFEC4899)
                        )

                    },

                    supportingText = {

                        Text(
                            "${promptDescription.length}/200 Characters",
                            color = Color(0xFFEC4899)
                        )

                    },

                    minLines = 4,

                    maxLines = 5,

                    shape = RoundedCornerShape(18.dp),

                    colors = OutlinedTextFieldDefaults.colors(

                        focusedBorderColor = Color(0xFFEC4899),

                        unfocusedBorderColor = Color.Gray,

                        focusedLabelColor = Color.White,

                        focusedTextColor = Color.White,

                        unfocusedTextColor = Color.White,

                        cursorColor = Color(0xFFEC4899)

                    )

                )

                Spacer(Modifier.height(28.dp))

                Button(

                    onClick = {},

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),

                    shape = RoundedCornerShape(18.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3B82F6)
                    )

                ) {

                    Icon(
                        Icons.Default.Send,
                        null
                    )

                    Spacer(Modifier.width(8.dp))

                    Text(
                        "Generate Prompt",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                }

                Spacer(Modifier.height(12.dp))

            }

        }

    }

}
