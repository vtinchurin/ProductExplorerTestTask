package ru.vtinchurin.productsexplorer.presentation.mapper

import android.annotation.SuppressLint
import ru.vtinchurin.productsexplorer.domain.LoadResult
import ru.vtinchurin.productsexplorer.domain.model.Item
import ru.vtinchurin.productsexplorer.presentation.ItemUi
import ru.vtinchurin.productsexplorer.presentation.MainScreenState
import java.text.SimpleDateFormat
import java.util.Date

interface ScreenStateMapper : LoadResult.Mapper<List<Item>, MainScreenState> {

    class Base: ScreenStateMapper {

        private val itemMapper = ItemUiMapper()

        override fun mapSuccess(data: List<Item>): MainScreenState =
             MainScreenState.Content(data.map { it.map(itemMapper) })

        override fun mapError(message: String): MainScreenState {
            TODO("Not yet implemented")
        }

        override fun mapEmpty(): MainScreenState = MainScreenState.Empty


        private inner class ItemUiMapper : Item.Mapper<ItemUi> {
            @SuppressLint("SimpleDateFormat")
            override fun map(
                id: Long, title: String, amount: Int, date: Long, tags: List<String>
            ): ItemUi {
                val stringDate = SimpleDateFormat("dd.MM.yyyy").format(Date(date))
                return ItemUi(
                    id = id,
                    title = title,
                    amount = amount.toString(),
                    date = stringDate,
                    tags = tags
                )
            }
        }
    }
}