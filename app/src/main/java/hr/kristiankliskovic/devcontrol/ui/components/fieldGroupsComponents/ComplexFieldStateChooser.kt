package hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes


@Composable
fun ComplexGroupStateChooser(
    items: List<DeviceComplexGroupStateViewState>,
    readOnly: Boolean,
    currentState: Int,
    changeState: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .border(
                2.dp,
                Color.Gray,
            ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(5.dp, 0.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = stringResource(id = R.string.complexGroup_stateChooser_title),
                fontSize = 20.sp,
                color = Color.Gray
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(5.dp, 0.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = items[currentState].stateName,
                fontSize = 30.sp
            )
        }
        if (!readOnly) {
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(2.dp)
            ) {
                ComplexStateChooserDialog(
                    items = items,
                    selectValue = {
                        changeState(it)
                    })
            }
        }
    }
}


@Composable
fun ComplexStateChooserDialog(
    items: List<DeviceComplexGroupStateViewState>,
    selectValue: (Int) -> Unit,
) {

    var dialogOpen by remember {
        mutableStateOf(false)
    }

    if (dialogOpen) {
        AlertDialog(onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality,
            // simply leave this block empty.
            dialogOpen = false
        },
            buttons = {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    itemsIndexed(
                        items = items,
                    ) { index, item ->
                        Text(
                            text = item.stateName,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(15.dp)
                                .clickable {
                                    selectValue(index)
                                    dialogOpen = false
                                }
                        )
                    }
                }
            },
            title = {
                Text(text = stringResource(id = R.string.complexGroup_stateChooser_dialog_title))
            },
            modifier = Modifier // Set the width and padding
                .fillMaxWidth()
                .padding(32.dp),
            shape = RoundedCornerShape(5.dp),
            backgroundColor = Color.White,
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true))
    }


    Box(
        modifier = Modifier
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(Shapes.small)
                .clickable {
                    dialogOpen = false
                }
                .background(colorResource(id = R.color.fieldComponent_button_background))
                .padding(5.dp)
        ) {
            Text(
                text = stringResource(id = R.string.complexGroup_stateChooser_button),
                modifier = Modifier.padding(
                    horizontal = 10.dp,
                    vertical = 0.dp
                )
            )
        }
    }

}

@Preview
@Composable
fun PreviewComplexGroupStateChooser() {
    val states = listOf(
        DeviceComplexGroupStateViewState(
            stateId = 0,
            stateName = "state1",
            fields = listOf()
        ),
        DeviceComplexGroupStateViewState(
            stateId = 0,
            stateName = "state2",
            fields = listOf()
        ),
        DeviceComplexGroupStateViewState(
            stateId = 0,
            stateName = "state3",
            fields = listOf()
        ),
        DeviceComplexGroupStateViewState(
            stateId = 0,
            stateName = "state4",
            fields = listOf()
        ),
    )
    ComplexGroupStateChooser(
        items = states,
        readOnly = false,
        currentState = 0,
        changeState = {

        },
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    )
}
