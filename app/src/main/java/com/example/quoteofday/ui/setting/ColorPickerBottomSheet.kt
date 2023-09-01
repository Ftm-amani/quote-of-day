package com.example.quoteofday.ui.setting

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quoteofday.navigation.AppScreens
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    navController: NavController,
    onDismiss: () -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = bottomSheetState) {

        scope.launch {
            bottomSheetState.expand()
        }
    }

    ModalBottomSheet(
        onDismissRequest = {
            scope.launch {
                bottomSheetState.hide()
                onDismiss()
            }
                           },
        sheetState = bottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
             ColorPicker(
                 navController = navController,
                 onSaveClick = {
                     onDismiss()
                 })
    }
    
}

@Composable
fun ColorPicker(
    navController: NavController,
    onSaveClick:() -> Unit ) {
    val controller = rememberColorPickerController()
    
    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Selected Color: ",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            AlphaTile(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(6.dp)),
                controller = controller,
            )
        }
        HsvColorPicker(
            modifier = Modifier
                .height(400.dp)
                .padding(16.dp),
            controller = controller,
            onColorChanged = { colorEnvelope: ColorEnvelope ->
                // do something
            }
        )
        BrightnessSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(35.dp),
            controller = controller,
        )
        AlphaSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(35.dp),
            controller = controller,
        )
        Button(onClick = {
            navController.navigate(AppScreens.HomeScreen.name)
            onSaveClick.invoke()
        }) {
            Text("Save")
        }
    }
}