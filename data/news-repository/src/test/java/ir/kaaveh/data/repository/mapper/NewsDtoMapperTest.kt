package ir.kaaveh.data.repository.mapper

import ir.kaaveh.data.mapper.toNews
import ir.kaaveh.data.mapper.toRemoteNewsDto
import ir.kaaveh.domain.test.notFavoriteNews
import ir.kaaveh.localdatasource.test.remoteNewsDto
import ir.kaaveh.remotedatasource.test.newsDto
import org.junit.Assert.assertEquals
import org.junit.Test

class NewsDtoMapperTest{

    @Test
    fun newsDtoToNews() {
        val mappedNews = newsDto.toNews()
        assertEquals(mappedNews, notFavoriteNews)
    }

    @Test
    fun newsDtoToRemoteNewsDto() {
        val mappedRemoteNews = newsDto.toRemoteNewsDto()
        assertEquals(mappedRemoteNews, remoteNewsDto)
    }

}