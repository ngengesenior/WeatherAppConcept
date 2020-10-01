package com.ngengeapps.weatherappconcept

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.ngengeapps.weatherappconcept.ui.WeatherAppConceptTheme
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppConceptTheme() {
                Surface(color = Color(0xFF0A0C11)) {
                    WeatherScreen()
                }
            }
        }
    }
}


@Preview
@Composable
fun WeatherScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.preferredHeight(56.dp))
        SearchBar()
        Spacer(modifier = Modifier.height(36.dp))
        Image(
            asset = vectorResource(id = R.drawable.ic_sun),
            modifier = Modifier.gravity(Alignment.CenterHorizontally).preferredSize(80.dp)
        )
        Spacer(modifier = Modifier.height(36.dp))
        Image(
            asset = vectorResource(id = R.drawable.ic_night),
            modifier = Modifier.gravity(Alignment.CenterHorizontally).preferredSize(80.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        WeatherColumn()
        Spacer(modifier = Modifier.height(24.dp))

    }

}

@Composable
fun WeatherItem(weather: Weather) {
    Row(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
        Text(
            text = weather.date.toUpperCase(Locale.getDefault()),
            modifier = Modifier.weight(1f),
            style = TextStyle(fontWeight = FontWeight.Black, color = Color.White)
        )
        Text(
            text = weather.temperature,
            style = TextStyle(fontWeight = FontWeight.Bold, color = Color.White)
        )
    }
}

//@Preview(showBackground = true)
@Composable
fun WeatherItemPreview() {
    WeatherItem(weather = Weather("Today", "28"))
}


@Preview
@Composable
fun WeatherColumn() {
    val itemsState = mutableStateOf(generateWeatherList())
    LazyColumnForIndexed(items = itemsState.value) { index, item ->
        WeatherItem(weather = item)
        if (index != itemsState.value.size - 1) {
            Divider(color = Color.White)
        }
    }


}

@Preview
@Composable
fun SearchBar() {
    Row(modifier = Modifier.padding(12.dp).fillMaxWidth()) {
        Text(text = "TODAY", style = TextStyle(fontWeight = FontWeight.Black, color = Color.White))
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = "21,April,2020",
            modifier = Modifier.weight(1f),
            style = TextStyle(fontWeight = FontWeight.Medium, color = Color.White)
        )
        Icon(asset = Icons.Filled.Search, tint = Color.White)
    }
}

fun generateWeatherList(): MutableList<Weather> {
    val list = mutableListOf<Weather>()
    list.add(Weather("Tomorrow", "28°C"))
    list.add(Weather("Monday", "25°C"))
    list.add(Weather("Tuesday", "24°C"))
    return list
}

data class Weather(
    val date: String,
    val temperature: String
)