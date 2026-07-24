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

@Page("register", supportInLocal = true)
internal class RegisterPage : BasePager() {
    override fun willInit() {
        super.willInit()
        setContent { RegisterContent() }
    }
}

@Composable
private fun RegisterContent() {
    var account by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var accountError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmError by remember { mutableStateOf<String?>(null) }
    var submitting by remember { mutableStateOf(false) }
    var registerSuccess by remember { mutableStateOf(false) }
    var pageEntered by remember { mutableStateOf(false) }
    val entryProgress by animateFloatAsState(
        targetValue = if (pageEntered) 1f else 0f,
        animationSpec = tween(520),
        label = "register-entry",
    )
    val pager = LocalActivity.current.getPager() as Pager
    val statusBarHeight = LocalActivity.current.pageData.statusBarHeight

    LaunchedEffect(Unit) { pageEntered = true }
    LaunchedEffect(submitting) {
        if (submitting) {
            delay(850)
            submitting = false
            registerSuccess = true
            Utils.bridgeModule(pager.pagerId).toast("账号创建成功，现在可以去登录")
        }
    }

    Box(Modifier.fillMaxSize().background(Color(0xFFFAFAFF))) {
        Box(
            modifier = Modifier.fillMaxWidth().height(218.dp)
                .background(Brush.linearGradient(listOf(Color(0xFF0F766E), Color(0xFF14B8A6))), RoundedCornerShape(bottomEnd = 42.dp)),
        )
        AuthBackgroundOrbs(Color(0xFF91FFF3), Color(0xFFFFE39A))
        Column(
            modifier = Modifier.fillMaxSize().padding(start = 24.dp, end = 24.dp, top = (statusBarHeight + 38).dp),
        ) {
            Box(Modifier.size(52.dp).background(Color(0x33FFFFFF), RoundedCornerShape(18.dp)), contentAlignment = Alignment.Center) {
                Text("M", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(18.dp))
            Text("创建新账号", color = Color.White, fontSize = 29.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(7.dp))
            Text("只需一分钟，开启全新体验", color = Color(0xFFD4FFFA), fontSize = 15.sp)
            Spacer(Modifier.height(35.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
                    .graphicsLayer {
                        alpha = entryProgress
                        translationY = (1f - entryProgress) * 44f
                    }
                    .background(Color.White, RoundedCornerShape(28.dp)).padding(24.dp),
            ) {
                Text("注册", color = Color(0xFF101828), fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(20.dp))
                Text("账号", color = Color(0xFF344054), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                AuthInput(account, "设置手机号或用户名", error = accountError) {
                    account = it
                    accountError = null
                    registerSuccess = false
                }
                AuthHint(accountError)
                Spacer(Modifier.height(15.dp))
                Text("密码", color = Color(0xFF344054), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                AuthInput(password, "设置登录密码", isPassword = true, error = passwordError) {
                    password = it
                    passwordError = null
                    confirmError = null
                    registerSuccess = false
                }
                AuthHint(passwordError)
                Spacer(Modifier.height(15.dp))
                Text("确认密码", color = Color(0xFF344054), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                AuthInput(confirmPassword, "再次输入密码", isPassword = true, error = confirmError) {
                    confirmPassword = it
                    confirmError = null
                    registerSuccess = false
                }
                AuthHint(confirmError)
                Spacer(Modifier.height(23.dp))
                Button(
                    onClick = {
                        if (!submitting) {
                            val newAccountError = if (account.trim().length < 3) "账号至少需要 3 个字符" else null
                            val newPasswordError = if (password.length < 6) "密码至少需要 6 位" else null
                            val newConfirmError = when {
                                confirmPassword.isEmpty() -> "请再次输入密码"
                                confirmPassword != password -> "两次输入的密码不一致"
                                else -> null
                            }
                            accountError = newAccountError
                            passwordError = newPasswordError
                            confirmError = newConfirmError
                            if (accountError == null && passwordError == null && confirmError == null) {
                                submitting = true
                                registerSuccess = false
                            } else {
                                Utils.bridgeModule(pager.pagerId).toast(
                                    newAccountError ?: newPasswordError ?: newConfirmError ?: "请检查输入内容"
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(54.dp)
                        .background(Brush.horizontalGradient(listOf(Color(0xFF0F766E), Color(0xFF14B8A6))), RoundedCornerShape(16.dp)),
                ) {
                    Text(
                        if (submitting) "正在创建…" else "创建账号",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
                AuthHint(if (registerSuccess) "账号创建成功，现在可以去登录" else null, success = true)
                Spacer(Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text("已有账号？", color = Color(0xFF667085), fontSize = 14.sp)
                    Text("去登录", color = Color(0xFF0F8F85), fontSize = 14.sp, fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 4.dp).clickable { replacePage(pager, "login") })
                }
            }
        }
    }
}
