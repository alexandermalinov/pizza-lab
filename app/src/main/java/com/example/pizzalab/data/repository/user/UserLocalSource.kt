package com.example.pizzalab.data.repository.user

import android.content.Context
import com.example.pizzalab.data.local.SharedPrefProvider
import com.example.pizzalab.data.local.dao.UserDao
import com.example.pizzalab.data.local.entity.User
import com.example.pizzalab.utils.responsehandler.DatabaseError
import com.example.pizzalab.utils.responsehandler.Either
import com.example.pizzalab.utils.responsehandler.LocalDatabaseError
import com.example.pizzalab.utils.responsehandler.ResponseResultOk
import javax.inject.Inject

class UserLocalSource @Inject constructor(
    private val context: Context,
    private val userDao: UserDao
) : UserRepository.LocalSource {

    override suspend fun registerUser(
        user: User,
        block: suspend (Either<LocalDatabaseError, ResponseResultOk>) -> Unit
    ) {
        if (userDao.saveUser(user) != -1L) {
            block.invoke(Either.right(ResponseResultOk))
        } else {
            block.invoke(Either.left(LocalDatabaseError()))
        }
    }

    override suspend fun setIsSignedIn(isSignedIn: Boolean) {
        SharedPrefProvider.setIsUserSignedIn(context, isSignedIn)
    }

    override suspend fun isSignedIn(): Boolean = SharedPrefProvider.getIsUserSignedIn(context)
}