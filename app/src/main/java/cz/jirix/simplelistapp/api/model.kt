package cz.jirix.simplelistapp.api

import cz.jirix.simplelistapp.model.User

data class GetUsersResponse(
    val users: List<User>
)
