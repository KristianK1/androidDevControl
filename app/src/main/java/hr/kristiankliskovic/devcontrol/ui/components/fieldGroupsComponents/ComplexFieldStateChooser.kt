package hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
                width = 2.dp,
                color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.5f),
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
                color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.5f)
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
                color = MaterialTheme.colorScheme.inverseSurface,
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
        AlertDialog(
            onDismissRequest = {
                dialogOpen = false
            },
            buttons = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    itemsIndexed(
                        items = items,
                    ) { index, item ->
                        Text(
                            text = item.stateName,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.inverseSurface,
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
                Text(
                    text = stringResource(id = R.string.complexGroup_stateChooser_dialog_title),
                    color = MaterialTheme.colorScheme.inverseSurface
                )
            },
            modifier = Modifier // Set the width and padding
                .fillMaxWidth()
                .padding(32.dp)
                .clip(Shapes.small)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.6f)
                ),
            shape = RoundedCornerShape(5.dp),
            backgroundColor = MaterialTheme.colorScheme.background,
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        )
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
                    dialogOpen = true
                }
                .background(
                    color = MaterialTheme.colorScheme.primary
                )
                .fillMaxHeight()
                .padding(5.dp)
        ) {
            Text(
                text = stringResource(id = R.string.complexGroup_stateChooser_button),
                color = MaterialTheme.colorScheme.background,
                fontSize = 20.sp,
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
