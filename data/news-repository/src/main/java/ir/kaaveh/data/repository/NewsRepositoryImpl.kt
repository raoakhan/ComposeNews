package ir.kaaveh.data.repository

import ir.kaaveh.data.mapper.toRemoteNewsDto
import ir.kaaveh.domain.model.News
import ir.kaaveh.domain.repository.NewsRepository
import ir.kaaveh.localdatasource.database.NewsDao
import ir.kaaveh.data.mapper.toLocalNewsDto
import ir.kaaveh.data.mapper.toNews
import ir.kaaveh.remotedatasource.api.NewsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
    private val dao: NewsDao
) : NewsRepository {

    override fun getNews(): Flow<List<News>> =
        dao.getNews().map { list -> list.map { it.toNews() } }

    override fun getFavoriteNews(): Flow<List<News>> =
        dao.getFavoriteNews().map { list -> list.map { it.toNews() } }

    override suspend fun syncNews(): Boolean = try {
        api.getNews()
            .news
            .map { it.toRemoteNewsDto() }
            .onEach { dao.upsertNews(it) }
        true
    } catch (e: Exception) {
        false
    }

    override suspend fun toggleFavoriteNews(oldNews: News) {
        val news = oldNews.toLocalNewsDto().copy(isFavorite = !oldNews.isFavorite)
        dao.insertNews(news)
    }

}