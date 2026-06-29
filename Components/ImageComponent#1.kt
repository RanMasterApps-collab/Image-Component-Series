import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun ProfileCardImage() {
    val buttonGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFFF4D9D),
            Color(0xFFC026D3)
        )
    )
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // Background Image
        Image(
            painter = painterResource(id = R.drawable.profile_bg_b),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(R.drawable.profile_avatar),
            contentDescription = null,
            modifier = Modifier
                .size(270.dp)
                .align(Alignment.TopCenter)
                .padding(top = 70.dp)
                .clip(CircleShape),
            //contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 350.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Name + Verified
            Row(
                verticalAlignment = Alignment.CenterVertically
,                        modifier = Modifier
                    .padding(end = 80.dp),
            ) {

                Text(
                    text = "ReWear Team",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(6.dp))

                Icon(
                    imageVector = Icons.Default.Verified,
                    contentDescription = null,
                    tint = Color(0xFFFF4D9D),
                    modifier = Modifier.size(22.dp)
                )

            }

            Spacer(modifier = Modifier.height(12.dp))
//profession
            Text(
                text = "Android Developer",
                color = Color(0xFFE7E2FF),
                fontSize = 17.sp
,                        modifier = Modifier.padding(end = 120.dp)
            )

            Spacer(modifier = Modifier.height(50.dp))

            HorizontalDivider(
                color = Color.White.copy(alpha = 0.15f),
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 32.dp)
            )

            Spacer(modifier = Modifier.height(22.dp))

            // Stats
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                StatItem("120", "Posts")
                StatItem("15K", "Followers")
                StatItem("860", "Following")

            }

            Spacer(modifier = Modifier.height(18.dp))

            HorizontalDivider(
                color = Color.White.copy(alpha = 0.15f),
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 32.dp)
            )

            Spacer(modifier = Modifier.height(38.dp))

            // Follow Button
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = PaddingValues(),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 45.dp)   // 👈 Padding

                    .height(50.dp)
                    ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = buttonGradient,
                            shape = RoundedCornerShape(24.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "FOLLOW",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                }
            }
        }
    }
}
@Composable
fun StatItemImage(number: String, title: String) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = number,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = title,
            color = Color(0xFFD5CFFF),
            fontSize = 14.sp
        )

    }    }


