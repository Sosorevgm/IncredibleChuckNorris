package com.incredible.chuck.norris.data.fact_datasource

import com.incredible.chuck.norris.data.models.FactModel

class FactRoomImplementation :
    FactDataSource<FactModel> {

    override suspend fun getData(category: String) =
        FactModel(
            "n8JVhyw8STudKZKCWAchig",
            "https://api.chucknorris.io/jokes/n8JVhyw8STudKZKCWAchig",
            "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
            "Chuck Norris can solve a paradox.",
            "2020-01-05 13:42:24.142371"
        )
}