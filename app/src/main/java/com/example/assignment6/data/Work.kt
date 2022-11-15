package com.example.assignment6.data

import java.io.Serializable

data class Work(
    val title: String,
    val position: String,
    val duration: String,
    val logo: Int
) : Serializable