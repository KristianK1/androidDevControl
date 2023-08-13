package hr.kristiankliskovic.devcontrol.ui.triggerSettings_seeAll.mapper

import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.model.ITrigger
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_seeAll.SeeAllTriggersViewState

interface SeeAllTriggersMapper {
    fun triggersToSeeAllTriggersViewState(triggers: List<ITrigger>, devices: List<Device>): SeeAllTriggersViewState
}
