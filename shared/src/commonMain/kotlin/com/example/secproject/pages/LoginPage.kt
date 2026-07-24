package com.example.secproject.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.secproject.Button
import com.example.secproject.base.BasePager
import com.example.secproject.base.Utils
import com.example.secproject.replacePage
import com.tencent.kuikly.compose.animation.core.animateFloatAsState
import com.tencent.kuikly.compose.animation.core.tween
import com.tencent.kuikly.compose.foundation.background
import com.tencent.kuikly.compose.foundation.clickable
import com.tencent.kuikly.compose.foundation.layout.Box
import com.tencent.kuikly.compose.foundation.layout.Column
import com.tencent.kuikly.compose.foundation.layout.Row
import com.tencent.kuikly.compose.foundation.layout.Spacer
import com.tencent.kuikly.compose.foundation.layout.fillMaxSize
import com.tencent.kuikly.compose.foundation.layout.fillMaxWidth
import com.tencent.kuikly.compose.foundation.layout.height
import com.tencent.kuikly.compose.foundation.layout.padding
import com.tencent.kuikly.compose.foundation.layout.size
import com.tencent.kuikly.compose.foundation.shape.RoundedCornerShape
import com.tencent.kuikly.compose.material3.Text
import com.tencent.kuikly.compose.setContent
import com.tencent.kuikly.compose.ui.Alignment
import com.tencent.kuikly.compose.ui.Modifier
import com.tencent.kuikly.compose.ui.graphics.Brush
import com.tencent.kuikly.compose.ui.graphics.Color
import com.tencent.kuikly.compose.ui.graphics.graphicsLayer
import com.tencent.kuikly.compose.ui.platform.LocalActivity
import com.tencent.kuikly.compose.ui.text.font.FontWeight
import com.tencent.kuikly.compose.ui.unit.dp
import com.tencent.kuikly.compose.ui.unit.sp
import com.tencent.kuikly.core.annotations.Page
import com.tencent.kuikly.core.pager.Pager
import kotlinx.coroutines.delay

@Page("login", supportInLocal = true)
internal class LoginPage : BasePager() {
    override fun willInit() {
        super.willInit()
        setContent { LoginContent() }
    }
}

@Composable
private fun LoginContent() {
    var account by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var accountError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var submitting by remember { mutableStateOf(false) }
    var loginSuccess by remember { mutableStateOf(false) }
    var pageEntered by remember { mutableStateOf(false) }
    val entryProgress by animateFloatAsState(
        targetValue = if (pageEntered) 1f else 0f,
        animationSpec = tween(520),
        label = "login-entry",
    )
    val pager = LocalActivity.current.getPager() as Pager
    val statusBarHeight = LocalActivity.current.pageData.statusBarHeight

    LaunchedEffect(Unit) { pageEntered = true }
    LaunchedEffect(submitting) {
        if (submitting) {
            delay(850)
            submitting = false
            loginSuccess = true
            Utils.bridgeModule(pager.pagerId).toast("登录成功，欢迎回来")
            delay(200)
            replacePage(pager, "main")
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF9FAFF)),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(245.dp)
                .background(Brush.linearGradient(listOf(Color(0xFF4F46E5), Color(0xFF7C3AED)))),
        )
        AuthBackgroundOrbs(Color(0xFFB9A9FF), Color(0xFFFFC7E8))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp, top = (statusBarHeight + 42).dp),
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .background(Color(0x33FFFFFF), RoundedCornerShape(18.dp)),
                contentAlignment = Alignment.Center,
            ) {
                Text("M", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(20.dp))
            Text("欢迎回来", color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Text("登录后继续你的精彩旅程", color = Color(0xFFDCD8FF), fontSize = 15.sp)

            Spacer(Modifier.height(42.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        alpha = entryProgress
                        translationY = (1f - entryProgress) * 44f
                    }
                    .background(Color.White, RoundedCornerShape(28.dp))
                    .padding(24.dp),
            ) {
                Text("账号登录", color = Color(0xFF101828), fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(24.dp))
                Text("账号", color = Color(0xFF344054), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                AuthInput(account, "请输入手机号或用户名", error = accountError) {
                    account = it
                    accountError = null
                    loginSuccess = false
                }
                AuthHint(accountError)
                Spacer(Modifier.height(18.dp))
                Text("密码", color = Color(0xFF344054), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                AuthInput(password, "请输入密码", isPassword = true, error = passwordError) {
                    password = it
                    passwordError = null
                    loginSuccess = false
                }
                AuthHint(passwordError)
                Spacer(Modifier.height(26.dp))
                Button(
                    onClick = {
                        if (!submitting) {
                            val newAccountError = if (account.trim().isEmpty()) "请输入账号" else null
                            val newPasswordError = when {
                                password.isEmpty() -> "请输入密码"
                                password.length < 6 -> "密码至少需要 6 位"
                                else -> null
                            }
                            accountError = newAccountError
                            passwordError = newPasswordError
                            if (accountError == null && passwordError == null) {
                                submitting = true
                                loginSuccess = false
                            } else {
                                Utils.bridgeModule(pager.pagerId).toast(
                                    newAccountError ?: newPasswordError ?: "请检查输入内容"
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .background(
                            Brush.horizontalGradient(listOf(Color(0xFF4F46E5), Color(0xFF7C3AED))),
                            RoundedCornerShape(16.dp),
                        ),
                ) {
                    Text(
                        if (submitting) "正在登录…" else "登录",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { replacePage(pager, "main") }
                    )
                }
                AuthHint(if (loginSuccess) "登录成功，欢迎回来" else null, success = true)
                Spacer(Modifier.height(22.dp))
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text("还没有账号？", color = Color(0xFF667085), fontSize = 14.sp)
                    Text(
                        "立即注册",
                        color = Color(0xFF5B4CE8),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 4.dp).clickable { replacePage(pager, "register") },
                    )
                }
            }
        }
    }
}
