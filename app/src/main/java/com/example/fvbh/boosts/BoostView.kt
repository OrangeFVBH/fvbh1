package com.example.fvbh.boosts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.fvbh.R
import com.example.fvbh.boosts.models.ActiveBoost

private const val ARG_PARAM_ID = "id"

class BoostView : Fragment() {
    private var boost: ActiveBoost? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = arguments?.getInt(ARG_PARAM_ID)
        if (id != null) {
            boost = ActiveBoost.load(id)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_boost_view, container, false)
    }

    companion object {
        private var countMoney = 0L

        fun updateCountMoney(newCountMoney: Long){
            countMoney = newCountMoney
        }

        @JvmStatic
        fun newInstance(id: Int) =
            BoostView().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM_ID, id)
                }
            }
    }

    private fun updateUI() {
        view?.findViewById<TextView>(R.id.boost_name)?.text = boost?.title
        view?.findViewById<TextView>(R.id.boost_reward)?.text = "+ ${boost?.inc} $"
        view?.findViewById<TextView>(R.id.boost_level)?.text = "lvl ${boost?.level}"
        view?.findViewById<TextView>(R.id.boost_price)?.text = "${boost?.price} $"
        view?.findViewById<View>(R.id.boost_hide)?.isVisible = boost?.price!! > countMoney


        val imageId = resources.getIdentifier("boost_${boost?.id}", "drawable", activity?.packageName)
        if (imageId != 0) {
            view?.findViewById<ImageView>(R.id.boost_img)?.setImageResource(imageId)
        }
    }

    override fun onResume() {
        super.onResume()
        updateUI()

        view?.setOnClickListener{
            boost?.buy()
            updateUI()
        }
    }
}