package com.example.core.utils

fun String?.value(): String = this ?: ""

fun Boolean?.value(): Boolean = this ?: false

fun String.capitalize(): String = this.lowercase().replaceFirstChar { it.uppercase() }