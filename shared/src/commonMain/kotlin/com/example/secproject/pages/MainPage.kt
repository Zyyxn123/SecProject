package com.example.secproject.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.secproject.base.BasePager
import com.tencent.kuikly.compose.foundation.background
import com.tencent.kuikly.compose.foundation.layout.Box
import com.tencent.kuikly.compose.foundation.layout.Column
import com.tencent.kuikly.compose.foundation.layout.Spacer
import com.tencent.kuikly.compose.foundation.layout.fillMaxSize
import com.tencent.kuikly.compose.foundation.layout.fillMaxWidth
import com.tencent.kuikly.compose.foundation.layout.height
import com.tencent.kuikly.compose.foundation.layout.offset
import com.tencent.kuikly.compose.foundation.layout.padding
import com.tencent.kuikly.compose.foundation.layout.size
import com.tencent.kuikly.compose.foundation.shape.RoundedCornerShape
import com.tencent.kuikly.compose.material3.Tab
import com.tencent.kuikly.compose.material3.TabRow
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

@Page("main", supportInLocal = true)
internal class MainPage : BasePager() {
    override fun willInit() {
        super.willInit()
        setContent { MainContent() }
    }
}

private data class MainNavItem(
    val mark: String,
    val label: String,
)

@Composable
private fun MainContent() {
    var selectedIndex by remember { mutableStateOf(0) }
    val statusBarHeight = LocalActivity.current.pageData.statusBarHeight
    val items = remember {
        listOf(
            MainNavItem("01", "首页"),
            MainNavItem("02", "功能"),
            MainNavItem("03", "消息"),
            MainNavItem("04", "我的"),
        )
    }

    Box(
        modifier = Modifier.fillMaxSize().background(
            Brush.linearGradient(
                listOf(Color(0xFFF3F5FF), Color(0xFFF8FAFF), Color(0xFFF1FBF9))
            )
        ),
    ) {
        Box(
            Modifier.size(210.dp).offset(x = (-34).dp, y = 18.dp)
                .background(Color(0x1F7C6EF6), RoundedCornerShape(105.dp)),
        )
        Box(
            Modifier.size(150.dp).offset(x = 270.dp, y = 176.dp)
                .background(Color(0x1F20BFA9), RoundedCornerShape(75.dp)),
        )

        Column(
            modifier = Modifier.fillMaxSize().padding(
                start = 20.dp,
                end = 20.dp,
                top = (statusBarHeight + 28).dp,
                // Reserve space for the floating tab bar, which overlays the page content.
                bottom = 128.dp,
            ),
        ) {
            Text("主页面", color = Color(0xFF101828), fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Text("应用内容将在这里展开", color = Color(0xFF667085), fontSize = 14.sp)
            Spacer(Modifier.height(28.dp))

            Box(
                modifier = Modifier.fillMaxWidth().height(190.dp)
                    .background(Color(0xEFFFFFFF), RoundedCornerShape(28.dp))
                    .padding(24.dp),
            ) {
                Column {
                    Text(
                        items[selectedIndex].mark,
                        color = Color(0xFF6757E8),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        items[selectedIndex].label,
                        color = Color(0xFF101828),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(Modifier.height(10.dp))
                    Text("当前栏目内容暂留空，后续可直接替换。", color = Color(0xFF667085), fontSize = 14.sp)
                }
            }

            Spacer(Modifier.weight(1f))
        }

        FloatingBottomNavigation(
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(start = 20.dp, end = 20.dp, bottom = 24.dp),
            items = items,
            selectedIndex = selectedIndex,
            onSelected = { selectedIndex = it },
        )
    }
}

@Composable
private fun FloatingBottomNavigation(
    modifier: Modifier,
    items: List<MainNavItem>,
    selectedIndex: Int,
    onSelected: (Int) -> Unit,
) {
    val navigationShape = RoundedCornerShape(26.dp)
    Box(
        modifier = modifier.fillMaxWidth().height(76.dp)
            .graphicsLayer {
                this.shape = navigationShape
                clip = true
                shadowElevation = 20f
                alpha = 0.98f
            }
            .background(
                Brush.linearGradient(
                    listOf(Color(0xCFFFFFFF), Color(0xB8F5F7FF), Color(0xC7FFFFFF))
                ),
                navigationShape,
            ),
    ) {
        TabRow(
            selectedTabIndex = selectedIndex,
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            contentColor = Color(0xFF6757E8),
            divider = {},
        ) {
            items.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedIndex,
                    onClick = { onSelected(index) },
                    text = {
                        Text(
                            item.label,
                            fontSize = 11.sp,
                            fontWeight = if (index == selectedIndex) {
                                FontWeight.Bold
                            } else {
                                FontWeight.Normal
                            },
                        )
                    },
                    icon = {
                        Text(item.mark, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    },
                    selectedContentColor = Color(0xFF4F46C7),
                    unselectedContentColor = Color(0xFF667085),
                )
            }
        }
    }
}
