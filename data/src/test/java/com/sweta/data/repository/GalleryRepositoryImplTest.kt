package com.sweta.data.repository

import androidx.paging.Pager
import com.sweta.data.GalleryApi
import com.sweta.data.network.interceptors.NetworkUnavailableException
import com.sweta.data.network.mappers.ApiMapper
import com.sweta.data.network.models.ApiGalleryItem
import com.sweta.domain.models.GalleryImage
import com.sweta.domain.utils.ResultWrapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response


class GalleryRepositoryImplTest {

    @Mock
    private lateinit var pager: Pager<Int, GalleryImage>

    @Mock
    private lateinit var galleryApi: GalleryApi

    @Mock
    private lateinit var galleryImageMapper: ApiMapper<ApiGalleryItem?, GalleryImage>

    //class or system under test
    private lateinit var galleryRepoUnderTest: GalleryRepositoryImpl

    private val successResponse: Response<ApiGalleryItem> = Response.success(
        ApiGalleryItem(
            id = 123,
            width = 1024,
            height = 2048,
            url = "https://test.com",
            downloadUrl = "https://test.download.com",
            author = "Test Author"
        )
    )

    private val errorBody = "{\n" +
            "  \"error\": {\n" +
            "    \"code\": 400,\n" +
            "    \"message\": \"Bad Request \"\n" +
            "  }\n" +
            "}"
    private val errorResponse: Response<ApiGalleryItem> = Response.error(
        400, errorBody.toResponseBody()
    )

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        galleryRepoUnderTest = GalleryRepositoryImpl(
            pager = pager,
            galleryApi = galleryApi,
            galleryItemMapper = galleryImageMapper
        )
    }

    @After
    fun tearDown() {

    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetRandomGrayScaleImageSuccess() = runTest {

        //mock methods here
        Mockito.`when`(galleryApi.getRandomGrayScaleImage()).thenReturn(
            successResponse
        )

        //call the real method under test
        val result = galleryRepoUnderTest.getRandomGrayScaleImage()

        //Do your assertions
        assert(result is ResultWrapper.Success)

    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetRandomGrayScaleImageErrorResponse() = runTest {

        //mock methods here
        Mockito.`when`(galleryApi.getRandomGrayScaleImage()).thenReturn(
            errorResponse
        )

        //call the real method under test
        val result = galleryRepoUnderTest.getRandomGrayScaleImage()

        //Do your assertions
        assert(result is ResultWrapper.GenericError)

    }


    @ExperimentalCoroutinesApi
    @Test
    fun testGetRandomGrayScaleTestGenericException() = runTest {
        //mock methods here
        Mockito.`when`(galleryApi.getRandomGrayScaleImage()).thenAnswer {
            throw Exception("")
        }

        //call the real method under test
        val result = galleryRepoUnderTest.getRandomGrayScaleImage()

        //Do your assertions
        assert(result is ResultWrapper.GenericError)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetRandomGrayScaleImageError() = runTest {

        //mock methods here
        Mockito.`when`(galleryApi.getRandomGrayScaleImage()).thenAnswer {
            throw NetworkUnavailableException("")
        }

        //call the real method under test
        val result = galleryRepoUnderTest.getRandomGrayScaleImage()

        //Do your assertions
        assert(result is ResultWrapper.NetworkError)

    }


}