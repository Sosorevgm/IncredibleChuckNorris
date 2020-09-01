package com.incredible.chuck.norris.model.datasource

class CategoryRoomImplementation : DataSource<List<String>> {
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