package com.example.fvbh.boosts.models

import android.content.SharedPreferences
import com.example.fvbh.boosts.AbsBoost
import kotlin.math.ceil


class ActiveBoost: AbsBoost() {
    var inc: Long = 0

    companion object{
        const val PRICE_COEFFICIENT = 2.0
        const val INC_COEFFICIENT = 1.5

        private var shared: SharedPreferences? = null
        private var sharedEditor: SharedPreferences.Editor? = null

        fun init(shared: SharedPreferences){
            this.shared = shared
            sharedEditor = shared.edit()
        }
    }

    override fun buy() {
        apply()
        level++
        price = ceil(price * PRICE_COEFFICIENT).toLong()
        inc = ceil(inc * INC_COEFFICIENT).toLong()
        save()
    }

    override fun load(id: Int) {
        this.id = id

        val getted_title = shared?.getString("boost_${id}_title", null)
        if (getted_title != null){
            title = getted_title
            inc = shared?.getLong("boost_${id}_inc", 1)!!
            price = shared?.getLong("boost_${id}_price", 1)!!
            level = shared?.getInt("boost_${id}_level", 0)!!
        } else{
            //TODO
        }
    }

    override fun save() {
        sharedEditor?.putString("boost_${id}_title", title)
        sharedEditor?.putLong("boost_${id}_inc", inc)
        sharedEditor?.putLong("boost_${id}_price", price)
        sharedEditor?.putInt("boost_${id}_level", level)
        sharedEditor?.commit()
    }

    override fun apply() {
        val oldInc = shared?.getLong("inc", 1)
        if (oldInc != null) {
            sharedEditor?.putLong("inc", oldInc + inc)
            sharedEditor?.commit()
        }
    }

}