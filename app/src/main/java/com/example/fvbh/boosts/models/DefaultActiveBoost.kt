package com.example.fvbh.boosts.models

object DefaultActiveBoost0 : ActiveBoost(){
    init {
        id = 0
        title = "Буст +"
        level = 1
        price = 100
        inc = 1
    }
}

object DefaultActiveBoost1 : ActiveBoost(){
    init {
        id = 1
        title = "Буст +-"
        level = 2
        price = 500
        inc = 50
    }
}

object DefaultActiveBoost2 : ActiveBoost(){
    init {
        id = 2
        title = "Бусти"
        level = 3
        price = 1000
        inc = 100
    }
}