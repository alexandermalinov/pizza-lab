package com.example.pizzalab.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pizzalab.data.local.entity.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: User): Long

    @Query("SELECT * FROM user WHERE user_email == :email AND user_password == :password")
    suspend fun getUser(email: String, password: String): User?

    @Query("SELECT * FROM user")
    suspend fun getUserByEmail(): User?
}