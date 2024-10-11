package com.example.swordcattest.presentation

import com.example.swordcattest.CoroutineTestRule
import com.example.swordcattest.fakes.FakeCat
import com.example.swordcattest.fakes.FakeCatRepository
import com.example.swordcattest.feature_cat_listing.domain.model.CatItem
import com.example.swordcattest.feature_cat_listing.domain.repository.DatabaseRepository
import com.example.swordcattest.feature_cat_listing.domain.usecase.GetCats
import com.example.swordcattest.feature_cat_listing.presentation.catlist.CatViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.kotlin.verify

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class CatViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val rule = CoroutineTestRule(testDispatcher)

    private lateinit var sut: CatViewModel
    private lateinit var getCats: GetCats

    private val catRepository = FakeCatRepository()
    private val fakeDatabaseRepository = mock(DatabaseRepository::class.java)

    @Before
    fun setup() {
        getCats = GetCats(catRepository)
        sut = CatViewModel(
            getCats,
            fakeDatabaseRepository
        )
    }

    @Test
    fun `onSearchTextChange updates searchText state`() {
        val searchText = "Test"

        sut.onSearchTextChange(searchText)

        assert(sut.searchText.value == searchText)
    }

    @Test
    fun `onSearchTextChange updates searchText state to empty string`() {
        val searchText = ""

        sut.onSearchTextChange(searchText)

        assert(sut.searchText.value == searchText)
    }

    @Test
    fun `onFavoritesClick updates cat and saves to database`() = runTest {
        val initialCat = FakeCat.cat
        val updatedCat = initialCat.copy(favourite = true)

        sut.setCats(listOf(initialCat))

        sut.onFavoritesClick(updatedCat)

        assert(sut.getCatById("1")?.favourite == true)

        verify(fakeDatabaseRepository).insertCats(updatedCat)
    }

    @Test
    fun `onFavoritesClick does not update if cat is not found`() = runTest {
        val initialCat = FakeCat.cat
        val nonExistentCat = CatItem(id = "2", emptyList(),1,"",1,false)

        sut.setCats(listOf(initialCat))

        sut.onFavoritesClick(nonExistentCat)

        assert(sut.getCatById("1")?.favourite == false)

        verify(fakeDatabaseRepository, never()).insertCats(nonExistentCat)
    }
}