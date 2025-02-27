package com.example.fvbh.boosts

abstract class AbsBoost{
    var price: Long = 0
    var title = ""
    var level = 0
    var id = 0

    abstract fun buy()

    abstract fun save()

    abstract fun apply()
}