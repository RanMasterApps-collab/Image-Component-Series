

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
class bottomsheet_components {
}
@Composable
fun ActionBottomSheetDemo() {

    var showSheet by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = {
                showSheet = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF7C3AED)
            )
        ) {

            Icon(
                imageVector = Icons.Default.Bolt,
                contentDescription = null,
                tint = Color.White
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = "Open Actions",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }

    if (showSheet) {

        ActionBottomSheet(

            onDismiss = {
                showSheet = false
            },

            onEdit = {
                println("Edit clicked")
                showSheet = false
            },

            onDuplicate = {
                println("Duplicate clicked")
                showSheet = false
            },

            onDelete = {
                println("Delete clicked")
                showSheet = false
            }
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionBottomSheet(
    onDismiss: () -> Unit,
    onEdit: () -> Unit,
    onDuplicate: () -> Unit,
    onDelete: () -> Unit
) {

    val Purple = Color(0xFF7C3AED)
    val DarkText = Color(0xFF172033)
    val GrayText = Color(0xFF6B7280)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFFFFFEFC),
        tonalElevation = 0.dp,
        shape = RoundedCornerShape(
            topStart = 30.dp,
            topEnd = 30.dp
        ),
        dragHandle = {

            Box(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .width(42.dp)
                    .height(5.dp)
                    .clip(
                        RoundedCornerShape(50)
                    )
                    .background(
                        Color(0xFFD1D5DB)
                    )
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp,
                    vertical = 14.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Canva Illustration

            Image(
                painter = painterResource(
                    R.drawable.action_illustration
                ),
                contentDescription = "Quick Actions",
                modifier = Modifier
                    .size(105.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = "Quick Actions",
                color = DarkText,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "Choose an action for this item.",
                color = GrayText,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            ActionItem(
                icon = Icons.Default.Edit,
                title = "Edit",
                subtitle = "Modify this item",
                iconBackground = Color(0xFFEDE9FE),
                iconTint = Color(0xFF7C3AED),
                onClick = onEdit
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            ActionItem(
                icon = Icons.Default.ContentCopy,
                title = "Duplicate",
                subtitle = "Create a copy of this item",
                iconBackground = Color(0xFFDBEAFE),
                iconTint = Color(0xFF2563EB),
                onClick = onDuplicate
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            ActionItem(
                icon = Icons.Default.DeleteOutline,
                title = "Delete",
                subtitle = "Remove this item permanently",
                iconBackground = Color(0xFFFFE4E6),
                iconTint = Color(0xFFE53935),
                onClick = onDelete
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            TextButton(
                onClick = onDismiss,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {

                Text(
                    text = "Cancel",
                    color = Color(0xFF6B7280),
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )
        }
    }
}
@Composable
fun ActionItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    iconBackground: Color,
    iconTint: Color,
    onClick: () -> Unit
) {

    var pressed by remember {
        mutableStateOf(false)
    }

    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.97f else 1f,
        animationSpec = tween(100),
        label = "actionScale"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(
                RoundedCornerShape(20.dp)
            )
            .background(
                Color(0xFFF8F9FC)
            )
            .clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }
            ) {

                onClick()
            }
            .padding(
                horizontal = 14.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(
                    RoundedCornerShape(15.dp)
                )
                .background(
                    iconBackground
                ),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(
            modifier = Modifier.width(13.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF172033)
            )

            Spacer(
                modifier = Modifier.height(2.dp)
            )

            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = Color(0xFF6B7280)
            )
        }

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = Color(0xFF9CA3AF),
            modifier = Modifier.size(21.dp)
        )
    }
}
