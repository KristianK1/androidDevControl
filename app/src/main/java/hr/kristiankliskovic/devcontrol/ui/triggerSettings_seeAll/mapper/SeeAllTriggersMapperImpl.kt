package hr.kristiankliskovic.devcontrol.ui.triggerSettings_seeAll.mapper

import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.model.ITrigger
import hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents.TriggerItemViewState
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_seeAll.SeeAllTriggersViewModel
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_seeAll.SeeAllTriggersViewState
import org.koin.core.qualifier.named

//class SeeAllTriggersMapperImpl : SeeAllTriggersMapper {
//    override fun triggersToSeeAllTriggersViewState(triggers: List<ITrigger>, devices: List<Device>): SeeAllTriggersViewState {
//        return SeeAllTriggersViewState(
//            triggers = triggers.map{
//
//                TriggerItemViewState(
//                    id = it.id,
//                    nameViewState = named<>()
//                )
//            }
//        )
//    }
//}
