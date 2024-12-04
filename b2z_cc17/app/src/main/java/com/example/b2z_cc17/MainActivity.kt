package com.example.b2z_cc17

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.b2z_cc17.ui.theme.B2z_cc17Theme
import com.rahad.riobottomnavigation.composables.RioBottomNavItemData
import com.rahad.riobottomnavigation.composables.RioBottomNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            B2z_cc17Theme {
                // Call MainScreen instead of Greeting
                MainScreen() // Display the MainScreen composable
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun MainScreen() {
    // Constants for icon resources and labels
    val items = listOf(
        R.drawable.home_default,
        R.drawable.explore_default,
        R.drawable.food_hub_default,
        R.drawable.user_profile_default,
    )

    val labels = listOf(
        " ",
        " ",
        " ",
        " "
    )

    // Use rememberSaveable to retain state across configuration changes
    var selectedIndex = rememberSaveable { mutableIntStateOf(0) }

    // Create RioBottomNavItemData for the bottom navigation buttons
    val buttons = items.mapIndexed { index, iconData ->
        RioBottomNavItemData(
            imageVector = ImageVector.vectorResource(iconData),
            selected = index == selectedIndex.intValue,
            onClick = { selectedIndex.intValue = index },
            label = labels[index]
        )
    }

    // Main Scaffold setup
    Scaffold(
        bottomBar = {
            BottomNavigationBar(buttons = buttons)
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        // Handle the screen content based on the selected index
        ScreenContent(selectedIndex.intValue, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun ScreenContent(selectedIndex: Int, modifier: Modifier = Modifier) {
    when (selectedIndex) {
        0 -> ShowText("Homepage")
        1 -> ShowText("Explore")
        2 -> ShowText("Food Hub")
        3 -> ShowText("User")
    }
}

@Composable
fun ShowText(x0: String) {
    Text(text = x0, modifier = Modifier.padding(16.dp))
}


@Composable
fun BottomNavigationBar(buttons: List<RioBottomNavItemData>) {
    // Move only the fabIcon and bottom nav icons
    Box(modifier = Modifier.offset(y = (-0).dp)) {
        RioBottomNavigation(
            fabIcon = ImageVector.vectorResource(id = R.drawable.qr_default),
            buttons = buttons,
            fabSize = 80.dp,
            barHeight = 80.dp,
            selectedItemColor = colorResource(id = R.color.blue),
            fabBackgroundColor = colorResource(id = R.color.blue)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    B2z_cc17Theme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    B2z_cc17Theme {
        MainScreen() // Preview the MainScreen composable
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenContentPreview() {
    B2z_cc17Theme {
        ScreenContent(selectedIndex = 0) // Preview with a sample index value
    }
}

