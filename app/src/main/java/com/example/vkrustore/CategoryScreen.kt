import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vkrustore.AppInfo
import com.example.vkrustore.AppItem
import com.example.vkrustore.AppRowCard
import com.example.vkrustore.apps
import com.example.vkrustore.components.TopBar
import com.example.vkrustore.educationApps
import com.example.vkrustore.games
import com.example.vkrustore.ui.theme.bigHeader
import com.example.vkrustore.ui.theme.smallHeader
import com.example.vkrustore.ui.theme.subTitleText
import java.net.URLDecoder
import kotlin.collections.ifEmpty




@Composable
fun CategoryScreen(navController: NavController, rawCategoryName: String) {
    val categoryName = try {
        java.net.URLDecoder.decode(rawCategoryName, "UTF-8")
    } catch (e: Exception) {
        rawCategoryName
    }
    val itemsForCategory = remember(categoryName) {
        getItemsForCategory(categoryName)
    }

    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Box(){
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically){
                TopBar(navController = navController)
                Column(
                ){
                    Text(categoryName, style= bigHeader)
                    Text(text = "количество приложений: ${itemsForCategory.size}", style=smallHeader, fontSize = 15.sp, modifier = Modifier.padding(start=7.dp))
                }


            }
        }

        val itemsForCategory = getItemsForCategory(categoryName)

        if (itemsForCategory.isEmpty()) {
            // сообщение об отсутствии приложений
            Text(
                text = "Пока нет приложений в категории \"$categoryName\"",
                modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(itemsForCategory) { appItem ->
                    AppRowCard(
                        item = appItem,
                        onClick = {
                            val appInfo = AppInfo(
                                name = appItem.title,
                                company = "VK COMPANY",
                                iconResId = appItem.iconRes,
                                description = appItem.description,
                                rating = (appItem.rating ?: 5.0f).toString(),
                                ageLimit = "12+",
                                category = appItem.category,
                                images = listOf(appItem.iconRes, appItem.iconRes, appItem.iconRes)
                            )
                            navController.currentBackStackEntry
                                ?.savedStateHandle
                                ?.set("appInfo", appInfo)
                            navController.navigate("app")
                        },
                        onDownloadClick = { /* ... */ }
                    )
                }
            }
        }
    }
}

fun getItemsForCategory(categoryName: String): List<AppItem> {
    val all = games + apps + educationApps
    val key = categoryName.trim().lowercase()
    return all.filter { it.category.trim().lowercase() == key }
}