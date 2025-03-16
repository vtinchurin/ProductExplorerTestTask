package ru.vtinchurin.productsexplorer.domain.model

data class Item(
    private val id:Long,
    private val title: String,
    private val amount: Int,
    private val date: Long,
    private val tags: List<String>
){
    interface Mapper<T: Any>{
        fun map(id: Long,title: String,amount: Int,date: Long, tags: List<String>):T
    }

    fun<T: Any> map(mapper: Mapper<T>) = mapper.map(id,title,amount,date,tags)
    fun contains(query: String): Boolean = title.contains(query,true)
}