package com.sweta.data.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.sweta.data.GalleryApi
import com.sweta.data.network.mappers.ApiGalleryItemMapper
import com.sweta.data.network.mappers.ApiMapper
import com.sweta.data.network.models.ApiGalleryItem
import com.sweta.data.paging.GalleryDataSource
import com.sweta.data.repository.GalleryRepositoryImpl
import com.sweta.data.repository.PAGE_SIZE
import com.sweta.domain.models.GalleryImage
import com.sweta.domain.repositories.GalleryRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single { provideGalleryDataSource(get(),get(named("ApiGalleryItemMapper"))) }
    single { provideGalleryPager(get()) }
    single {
        GalleryRepositoryImpl(
            pager = get(),
            galleryApi = get(),
            galleryItemMapper = get(named("ApiGalleryItemMapper")),

        ) as GalleryRepository
    }
}

fun provideGalleryDataSource(galleryApi: GalleryApi, galleryItemMapper: ApiMapper<ApiGalleryItem?,GalleryImage>):GalleryDataSource{

    return GalleryDataSource(
        galleryApi = galleryApi,
        apiGalleryItemMapper = galleryItemMapper
    )

}

fun provideGalleryPager(dataSource: GalleryDataSource):Pager<Int,GalleryImage>{
    return Pager(PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false)){
        dataSource
    }
}