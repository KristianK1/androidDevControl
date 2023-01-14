package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hr.kristiankliskovic.devcontrol.R

@Composable
fun SeeAllPermissionsRoute(
    viewModel: SeeAllPermissionsViewModel,
){
    val viewState by viewModel.viewState.collectAsState()
    SeeAllPermissionsScreen(viewState = viewState)
}

@Composable
fun SeeAllPermissionsScreen(
    viewState: SeeAllPermissionsViewState,
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
    ) {
        Text(
            text = stringResource(id = R.string.seeAllPermissions_devicePermissions_title)
        )
        for (devRight in viewState.permissions) {
            UserRightLabel(
                username = devRight.username,
                isWrite = !devRight.readOnly,
                onLongClick = {

                }
            )
        }
        Text(
            text = stringResource(id = R.string.seeAllPermissions_groupPermissions_title)
        )
        for(group in viewState.groupPermissions) {
            Column(
                modifier = Modifier
                    .border(
                        color = colorResource(id = R.color.seeAllPermissionScreen_rights_borders),
                        width = dimensionResource(id = R.dimen.seeAllPermissionScreen_rights_borders)
                    )
            ) {
                for(groupRight in group.permissions)
                UserRightLabel(
                    username = groupRight.username,
                    isWrite = !groupRight.readOnly,
                    onLongClick = {

                    }
                )
                Text(
                    text = stringResource(id = R.string.seeAllPermissions_fieldPermissions_title)
                )
                for(field in group.fields){
                    Column(
                        modifier = Modifier
                            .border(
                                color = colorResource(id = R.color.seeAllPermissionScreen_rights_borders),
                                width = dimensionResource(id = R.dimen.seeAllPermissionScreen_rights_borders)
                            )
                    ) {
                        for(fieldRight in field.permissions)
                        UserRightLabel(
                            username = fieldRight.username,
                            isWrite = !fieldRight.readOnly,
                            onLongClick = {

                            }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun UserRightLabel(
    username: String,
    isWrite: Boolean,
    onLongClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$username: ${
            if (isWrite) stringResource(id = R.string.seeAllPermissions_write_permission_label)
            else stringResource(id = R.string.seeAllPermissions_read_permission_label)
        }")
        Icon(Icons.Filled.Delete, "")
    }
}

@Preview
@Composable
fun PreviewSeeAllPermissionsScreen(){

}
