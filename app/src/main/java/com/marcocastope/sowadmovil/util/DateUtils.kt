package com.marcocastope.sowadmovil.util


val parser = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
val formatter = java.text.SimpleDateFormat("dd.MM.yyyy HH:mm")

fun formatDateToText(date: String): String = formatter.format(parser.parse(date))

