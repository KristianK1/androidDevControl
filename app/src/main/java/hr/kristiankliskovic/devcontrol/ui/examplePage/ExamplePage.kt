//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.dimensionResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import hr.kristiankliskovic.devcontrol.R
//import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.OutlineTextWrapper
//import hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents.TypeOfSource
//
//@Composable
//fun UserInterface() {
//    var itemName by remember { mutableStateOf("") }
//    var selectedItem by remember { mutableStateOf("option1") }
//    var dropdown1Value by remember { mutableStateOf("") }
//    var dropdown2Value by remember { mutableStateOf("") }
//    var dropdown3Value by remember { mutableStateOf("") }
//
//    // Other state variables for additional fields
//    val scrollState = rememberScrollState()
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(dimensionResource(id = R.dimen.addTrigger_screen_padding))
//            .verticalScroll(state = scrollState)
//    ) {
//        OutlineTextWrapper(
//            initValue = viewState.triggerName,
//            label = stringResource(id = R.string.addTrigger_triggerName_label),
//            placeholder = stringResource(id = R.string.addTrigger_triggerName_placeholder),
//            onChange = {
//                changeName(it)
//            },
//            modifier = Modifier
//                .padding(
//                    horizontal = 0.dp,
//                    vertical = dimensionResource(id = R.dimen.addTrigger_triggerNamePadding)
//                )
//        )
//        TypeOfSource(
//            typeSelected = viewState.sourceType.value,
//            chooseType = {
//                changeSourceType(it)
//            }
//        )
//
//    }
//}
//@Preview
//@Composable
//fun rffg(){
//    UserInterface()
//}
