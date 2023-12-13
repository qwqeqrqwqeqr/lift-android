package com.gradation.lift.catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import com.gradation.lift.catalog.ui.Catalog
import com.gradation.lift.designsystem.theme.LiftMaterialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiftMaterialTheme {
                Catalog(modifier = Modifier)
            }
        }
    }
}

