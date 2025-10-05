package com.example.vkrustore

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vkrustore.ui.theme.appTitleStyle
import com.example.vkrustore.ui.theme.categoryTextStyle
import com.example.vkrustore.ui.theme.downloadButton
import com.example.vkrustore.ui.theme.smallHeader
import com.example.vkrustore.ui.theme.subTitleText
import java.net.URLDecoder
import kotlin.sequences.ifEmpty
import java.net.URLEncoder
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import com.example.vkrustore.components.TopBar

data class Category(@DrawableRes val icon: Int, val title: String)

private val categories = listOf(
    Category(R.drawable.education, "Социальные сети"),
    Category(R.drawable.healthicon, "Образ жизни"),
    Category(R.drawable.cameraicon, "Фото"),
    Category(R.drawable.gamepad, "Игры"),
    Category(R.drawable.money, "Финансы"),
    Category(R.drawable.federal, "Государственное"),
)

val games = listOf(
    app("Танчики", R.drawable.gamepad, "Классическая аркадная стрелялка с пиксельной графикой", "Игры", 4.9f),
    app("Гонки PRO", R.drawable.rustore, "Онлайн-гонки с тюнингом авто и турнирами", "Игры", 4.7f),
    app("Пазлы+", R.drawable.education, "Увлекательные головоломки для всех возрастов", "Игры", 4.5f),
    app("Танчики", R.drawable.gamepad, "Классическая аркадная стрелялка с пиксельной графикой", "Игры", 4.9f),
    app("Гонки PRO", R.drawable.rustore, "Онлайн-гонки с тюнингом авто и турнирами", "Игры", 4.7f),
    app("Пазлы+", R.drawable.education, "Увлекательные головоломки для всех возрастов", "Игры", 4.5f)
)

val apps = listOf(
    app("МойКапитал", R.drawable.money, "Управляй финансами: расходы, бюджет, цели", "Финансы", 4.6f),
    app("ВКонтакте: чаты, видео, музыка", R.drawable.vk, "ВКонтакте - это общение, мессенджер и звонки, знакомства и игры, видео и музыка", "Социальные сети", 4.3f),
    app("HealthTrack", R.drawable.healthicon, "Отслеживание активности, сна и водного баланса", "Образ жизни", 4.3f)
)

val educationApps = listOf(
    app("UniPortal", R.drawable.education, "Официальное приложение университета: расписание, оценки, новости", "Государственное", 4.2f),
    app("Photomath", R.drawable.cameraicon, "Решения математических задач по фото и объяснения шаг за шагом", "Фото", 4.4f),
    app("eLibrary", R.drawable.education, "Доступ к электронным учебникам и библиотечным сервисам", "Образ жизни", 4.1f)
)
data class AppItem(
    val title: String,
    @DrawableRes val iconRes: Int,
    val description: String,
    val category: String,
    val rating: Float? = null,
)

fun app(title: String, @DrawableRes icon: Int, description: String, category: String, rating: Float? = null) =
    AppItem(title, icon, description, category, rating)


@Composable
fun HomeScreen(navController: NavController) {
    Column {
        AppSetSection(
            title = "Игровой набор",
            subtitle = "Собрали для вас лучшее",
            items = games,
            itemTitleStyle = appTitleStyle,
            onItemClick = { appItem ->
                val appInfo = AppInfo(
                    name = appItem.title,
                    company = "VK COMPANY",
                    iconResId = appItem.iconRes,
                    description = "Описание приложения ${appItem.title}. " +
                            "Здесь может быть длинный текст и т.п.",
                    rating = (appItem.rating ?: 5.0f).toString(),
                    ageLimit = "12+",
                    category = "Категория",
                    images = listOf(appItem.iconRes, appItem.iconRes, appItem.iconRes)
                )
                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("appInfo", appInfo)
                navController.navigate("app")
            },
            onItemDownload = { /* … */ }
        )

        Spacer(Modifier.height(16.dp))

        AppSetSection(
            title = "Полезные приложения",
            subtitle = "Для жизни и работы",
            items = apps,
            itemTitleStyle = appTitleStyle,
            onItemClick = { appItem ->
                val appInfo = AppInfo(
                    name = appItem.title,
                    company = "VK COMPANY",
                    iconResId = appItem.iconRes,
                    description = "Описание приложения ${appItem.title}. " +
                            "Здесь может быть длинный текст и т.п.",
                    rating = (appItem.rating ?: 5.0f).toString(),
                    ageLimit = "12+",
                    category = "Категория",
                    images = listOf(appItem.iconRes, appItem.iconRes, appItem.iconRes)
                )
                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("appInfo", appInfo)
                navController.navigate("app")
            },
            onItemDownload = {/*...*/}
        )

        Spacer(Modifier.height(16.dp))

        AppSetSection(
            title = "Приложения для учебы",
            subtitle = "Помогут вам развиваться",
            items = educationApps,
            itemTitleStyle = appTitleStyle,
            onItemClick = { appItem ->
                val appInfo = AppInfo(
                    name = appItem.title,
                    company = "VK COMPANY",
                    iconResId = appItem.iconRes,
                    description = "Описание приложения ${appItem.title}. " +
                            "Здесь может быть длинный текст и т.п.",
                    rating = (appItem.rating ?: 5.0f).toString(),
                    ageLimit = "12+",
                    category = "Категория",
                    images = listOf(appItem.iconRes, appItem.iconRes, appItem.iconRes)
                )
                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("appInfo", appInfo)
                navController.navigate("app")
            },
            onItemDownload = {/*...*/}
        )
    }
}


@Composable
fun CategoriesBar(navController: NavController) {
    val rowHeight = 40.dp
    val rowsCount = 1
    LazyHorizontalGrid(
        rows = GridCells.Fixed(rowsCount),
        modifier = Modifier
            .fillMaxWidth()
            .height(rowHeight * rowsCount + 16.dp), // небольшой запас
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categories.size) { i ->
            val encoded = URLEncoder.encode(categories[i].title, "UTF-8")
            CategoryCard(
                categoryName = categories[i].title,
                categoryImgRes = categories[i].icon,
                onClick = { navController.navigate("category/$encoded") } // <- навигация
            )
        }
    }
}

@Composable
fun CategoryCard(categoryName: String,
                 @DrawableRes categoryImgRes: Int,
                 onClick: () -> Unit)
{
    TextButton(
        onClick = onClick,
        modifier = Modifier.height(32.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color(0xF5F5F5F5),
            contentColor   = Color(0xFF0077FF)
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            Image(painter = painterResource(id = categoryImgRes),
                contentDescription = "category",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(end = 4.dp, start = 0.dp)
                    .size(18.dp)
            )
            Text(categoryName, style = categoryTextStyle)
        }

    }
}

@Composable
fun DownloadPill(onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.height(32.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color(0xF5F5F5F5),
            contentColor   = Color(0xFF2F86FF)
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            "Скачать",
            style = downloadButton,
            color = Color(0xFF0077FF)
        )
    }
}


@Composable
fun AppRowCard(
    item: AppItem,
    modifier: Modifier = Modifier,
    titleStyle: TextStyle = appTitleStyle,
    onClick: () -> Unit = {},
    onDownloadClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .width(360.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(start = 16.dp, end = 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(item.iconRes),
                contentDescription = item.title,
                modifier = Modifier.size(40.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(Modifier.width(12.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 3.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = item.title,
                color = Color.Black,
                maxLines = 1,
                style = titleStyle,
                overflow = TextOverflow.Ellipsis
            )
            item.description?.let { desc ->
                Text(
                    text = item.description,
                    style = subTitleText,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            item.category?.let { categ ->
                Text(
                    text = categ,
                    style = subTitleText,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Spacer(Modifier.width(8.dp))
        com.example.vkrustore.DownloadPill(onClick = onDownloadClick)
    }
}


@Composable
fun AppSetSection(
    title: String,
    subtitle: String? = null,
    items: List<AppItem>,
    rows: Int = 3,
    modifier: Modifier = Modifier,
    titleTextStyle: TextStyle = smallHeader,
    subtitleTextStyle: TextStyle = subTitleText,
    itemTitleStyle: TextStyle = appTitleStyle,
    onItemClick: (AppItem) -> Unit = {},
    onItemDownload: (AppItem) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                text = title,
                style = titleTextStyle,
                color = Color.Black
            )
            subtitle?.let {
                Spacer(Modifier.height(2.dp))
                Text(text = it,
                    color = Color.Gray,
                    style = subtitleTextStyle)
            }
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(20.dp)
        )
    }

    LazyHorizontalGrid(
        rows = GridCells.Fixed(rows),
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp),
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items.size) { i ->
            AppRowCard(
                item = items[i],
                onClick = { onItemClick(items[i]) },
                onDownloadClick = { onItemDownload(items[i]) },
                titleStyle = itemTitleStyle
            )
        }
    }
}
