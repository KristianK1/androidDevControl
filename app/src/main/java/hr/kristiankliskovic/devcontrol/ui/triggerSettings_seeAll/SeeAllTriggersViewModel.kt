package hr.kristiankliskovic.devcontrol.ui.triggerSettings_seeAll

import androidx.lifecycle.ViewModel
import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepository
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_seeAll.mapper.SeeAllTriggersMapper

class SeeAllTriggersViewModel(
    deviceRepository: DeviceRepository,
    mapper: SeeAllTriggersMapper,
) : ViewModel(){
}
