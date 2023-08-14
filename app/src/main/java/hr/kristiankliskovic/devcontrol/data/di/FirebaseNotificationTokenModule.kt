package hr.kristiankliskovic.devcontrol.data.di

import hr.kristiankliskovic.devcontrol.data.repository.firebaseNotificationToken.FirebaseNotificationTokenRepository
import hr.kristiankliskovic.devcontrol.data.repository.firebaseNotificationToken.FirebaseNotificationTokenRepositoryImpl
import org.koin.dsl.module

val firebaseNotificationTokenModule = module {
    single<FirebaseNotificationTokenRepository> {
        FirebaseNotificationTokenRepositoryImpl(get())
    }
}
