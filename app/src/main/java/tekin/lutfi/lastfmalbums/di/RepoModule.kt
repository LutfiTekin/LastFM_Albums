package tekin.lutfi.lastfmalbums.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import tekin.lutfi.lastfmalbums.data.remote.LastFMApi
import tekin.lutfi.lastfmalbums.data.repository.LastFMAlbumRepositoryImpl
import tekin.lutfi.lastfmalbums.domain.respository.LastFMAlbumRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {



    @Singleton
    @Provides
    fun providesLastFMAlbumRepository(api: LastFMApi): LastFMAlbumRepository{
        return LastFMAlbumRepositoryImpl(api)
    }

}