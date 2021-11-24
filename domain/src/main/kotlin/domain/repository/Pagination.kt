package domain.repository

data class Pagination(
    val itemsPerPage: Int,
    val page: Int,
    val search: String? = null
) {
    fun offset() = (page - 1) * itemsPerPage.toLong()
}

open class PaginationResult<T : Any>(
    val items: List<T>,
    val total: Long
) {
    constructor(pagination: PaginationResult<T>) : this(pagination.items, pagination.total)

    fun <U : Any> transform(transformer: (entity: T) -> U) = PaginationResult(items.map(transformer), total)
}
