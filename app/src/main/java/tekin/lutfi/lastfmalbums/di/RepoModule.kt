package tekin.lutfi.lastfmalbums.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tekin.lutfi.lastfmalbums.data.local.dao.AlbumDao
import tekin.lutfi.lastfmalbums.data.remote.LastFMApi
import tekin.lutfi.lastfmalbums.data.repository.LastFMAlbumRepositoryImpl
import tekin.lutfi.lastfmalbums.data.repository.LastFMLocalAlbumRepositoryImpl
import tekin.lutfi.lastfmalbums.domain.repository.LastFMAlbumRepository
import tekin.lutfi.lastfmalbums.domain.repository.LastFMLocalAlbumRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {


    @Singleton
    @Provides
    fun providesLastFMAlbumRepository(api: LastFMApi): LastFMAlbumRepository =
        LastFMAlbumRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideLastFMLocalAlbumRepository(dao: AlbumDao): LastFMLocalAlbumRepository =
        LastFMLocalAlbumRepositoryImpl(dao)

}