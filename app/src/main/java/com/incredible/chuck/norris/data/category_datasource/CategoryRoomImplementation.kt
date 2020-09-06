package com.incredible.chuck.norris.data.category_datasource

class CategoryRoomImplementation :
    CategoryDataSource<List<String>> {
    override suspend fun getData() = listOf(
        "animal",
        "career",
        "celebrity",
        "dev",
        "explicit",
        "fashion",
        "food",
        "history",
        "money",
        "movie",
        "music",
        "political",
        "religion",
        "science",
        "sport",
        "travel"
    )
}