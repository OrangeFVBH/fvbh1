package com.example.fvbh.boosts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.fvbh.R
import java.util.logging.Level

private const val ARG_PARAM_TITLE = "title"
private const val ARG_PARAM_LEVEL = "level"
private const val ARG_PARAM_PRICE = "price"
private const val ARG_PARAM_INC = "inc"
private const val ARG_PARAM_ID = "id"

class BoostView : Fragment() {
    private var title: String? = null
    private var level: Int? = null
    private var price: Long? = null
    private var inc: Long? = null
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = arguments?.getString(ARG_PARAM_TITLE)
        level = arguments?.getInt(ARG_PARAM_LEVEL)
        price = arguments?.getLong(ARG_PARAM_PRICE)
        inc = arguments?.getLong(ARG_PARAM_INC)
        id = arguments?.getInt(ARG_PARAM_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_boost_view, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(title: String, level: Int, price: Long, inc: Long, id: Int) =
            BoostView().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TITLE, title)
                    putInt(ARG_PARAM_LEVEL, level)
                    putLong(ARG_PARAM_PRICE, price)
                    putLong(ARG_PARAM_INC, inc)
                    putInt(ARG_PARAM_ID, id)
                }
            }
    }

    override fun onResume() {
        super.onResume()
        view?.findViewById<TextView>(R.id.boost_name)?.text = title
        view?.findViewById<TextView>(R.id.boost_reward)?.text = "+ $inc $"
        view?.findViewById<TextView>(R.id.boost_level)?.text = "lvl $level"
        view?.findViewById<TextView>(R.id.boost_price)?.text = "$price $"

        val imageId = resources.getIdentifier("boost_$id", "drawable", activity?.packageName)
        if (imageId != 0) {
            view?.findViewById<ImageView>(R.id.boost_img)?.setImageResource(imageId)
        }
    }
}