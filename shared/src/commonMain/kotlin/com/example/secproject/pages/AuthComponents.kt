package com.example.secproject.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.secproject.TextField
import com.tencent.kuikly.compose.animation.core.RepeatMode
import com.tencent.kuikly.compose.animation.core.animateFloat
import com.tencent.kuikly.compose.animation.core.infiniteRepeatable
import com.tencent.kuikly.compose.animation.core.rememberInfiniteTransition
import com.tencent.kuikly.compose.animation.core.tween
import com.tencent.kuikly.compose.foundation.background
import com.tencent.kuikly.compose.foundation.border
import com.tencent.kuikly.compose.foundation.clickable
import com.tencent.kuikly.compose.foundation.layout.Box
import com.tencent.kuikly.compose.foundation.layout.fillMaxWidth
import com.tencent.kuikly.compose.foundation.layout.height
import com.tencent.kuikly.compose.foundation.layout.offset
import com.tencent.kuikly.compose.foundation.layout.padding
import com.tencent.kuikly.compose.foundation.layout.size
import com.tencent.kuikly.compose.foundation.shape.RoundedCornerShape
import com.tencent.kuikly.compose.material3.Text
import com.tencent.kuikly.compose.ui.Alignment
import com.tencent.kuikly.compose.ui.Modifier
import com.tencent.kuikly.compose.ui.graphics.Brush
import com.tencent.kuikly.compose.ui.graphics.Color
import com.tencent.kuikly.compose.ui.text.TextStyle
import com.tencent.kuikly.compose.ui.text.font.FontWeight
import com.tencent.kuikly.compose.ui.text.input.PasswordVisualTransformation
import com.tencent.kuikly.compose.ui.text.input.VisualTransformation
import com.tencent.kuikly.compose.ui.unit.dp
import com.tencent.kuikly.compose.ui.unit.sp

/** Consistent, high-contrast input field shared by the authentication pages. */
@Composable
internal fun AuthInput(
    value: String,
    placeholder: String,
    isPassword: Boolean = false,
    error: String? = null,
    onValueChange: (String) -> Unit,
) {
    var focused by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    val borderColor = when {
        error != null -> Color(0xFFE5484D)
        focused -> Color(0xFF6757E8)
        else -> Color.Transparent
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(Color(0xFFF5F7FB), RoundedCornerShape(16.dp))
            .border(1.dp, borderColor, RoundedCornerShape(16.dp))
            .padding(start = 18.dp, end = if (isPassword) 52.dp else 18.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            placeholder = placeholder,
            placeholderColor = Color(0xFF98A2B3),
            autoFocus = false,
            textStyle = TextStyle(color = Color(0xFF1D2939), fontSize = 15.sp),
            cursorBrush = Brush.verticalGradient(listOf(Color(0xFF6757E8), Color(0xFF6757E8))),
            singleLine = true,
            maxLines = 1,
            visualTransformation = if (isPassword && !passwordVisible) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            onFocus = { focused = true },
            onBlur = { focused = false },
            onValueChange = { onValueChange(it) },
        )
        if (isPassword) {
            Text(
                text = if (passwordVisible) "隐藏" else "显示",
                color = if (focused) Color(0xFF6757E8) else Color(0xFF667085),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterEnd).clickable {
                    passwordVisible = !passwordVisible
                },
            )
        }
    }
}

@Composable
internal fun AuthBackgroundOrbs(primary: Color, secondary: Color) {
    val transition = rememberInfiniteTransition(label = "auth-background")
    val driftX by transition.animateFloat(
        initialValue = -16f,
        targetValue = 18f,
        animationSpec = infiniteRepeatable(tween(3200), RepeatMode.Reverse),
        label = "orb-x",
    )
    val driftY by transition.animateFloat(
        initialValue = 8f,
        targetValue = -18f,
        animationSpec = infiniteRepeatable(tween(4100), RepeatMode.Reverse),
        label = "orb-y",
    )

    Box(
        Modifier.size(170.dp).offset(x = (220f + driftX).dp, y = (-35f + driftY).dp)
            .background(primary.copy(alpha = 0.22f), RoundedCornerShape(85.dp)),
    )
    Box(
        Modifier.size(108.dp).offset(x = (-34f - driftX).dp, y = (126f - driftY).dp)
            .background(secondary.copy(alpha = 0.18f), RoundedCornerShape(54.dp)),
    )
    Box(
        Modifier.size(18.dp).offset(x = (62f + driftX / 2f).dp, y = (54f + driftY).dp)
            .background(Color.White.copy(alpha = 0.36f), RoundedCornerShape(9.dp)),
    )
}

@Composable
internal fun AuthHint(text: String?, success: Boolean = false) {
    if (text != null) {
        Text(
            text = text,
            color = if (success) Color(0xFF079455) else Color(0xFFE5484D),
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 4.dp, top = 6.dp),
        )
    }
}
