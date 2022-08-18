package tekin.lutfi.lastfmalbums.di

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

// This code uses "com.squareup.inject:assisted-inject-annotations-dagger2:0.5.2"
// AssistedInject puts all assisted bindings in the same module.
// We need to make a decision about where to install it.
// In this case, as we only need it in fragments, we install it there.
@InstallIn(FragmentComponent::class)
@AssistedModule
@Module
// Needed until AssistedInject is incorporated into Dagger
interface AssistedInjectModule