package com.example.pizzalab.data.repository.user

import com.example.pizzalab.data.local.entity.User
import com.example.pizzalab.utils.responsehandler.DatabaseError
import com.example.pizzalab.utils.responsehandler.Either
import com.example.pizzalab.utils.responsehandler.LocalDatabaseError
import com.example.pizzalab.utils.responsehandler.ResponseResultOk
import javax.inject.Inject

class UserRepository @Inject constructor(private val local: UserLocalSource) {

    /* --------------------------------------------------------------------------------------------
     * Sources
     ---------------------------------------------------------------------------------------------*/
    interface LocalSource {

        suspend fun registerUser(
            user: User,
            block: suspend (Either<LocalDatabaseError, ResponseResultOk>) -> Unit
        )

        suspend fun setIsSignedIn(isSignedIn: Boolean)

        suspend fun isSignedIn(): Boolean
    }

    suspend fun registerUser(
        user: User,
        block: suspend (Either<LocalDatabaseError, ResponseResultOk>) -> Unit
    ) {
        local.registerUser(user, block)
    }

    suspend fun setIsSignedIn(isSignedIn: Boolean) {
        local.setIsSignedIn(isSignedIn)
    }

    suspend fun isSignedIn() = local.isSignedIn()
}