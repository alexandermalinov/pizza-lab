package com.example.pizzalab.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val id: String,
    @ColumnInfo(name = "user_email")
    val userEmail: String,
    @ColumnInfo(name = "user_password")
    val userPassword: String,
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,
    @ColumnInfo(name = "address")
    val address: String
)