package com.example.cryptoview.base

import android.content.Intent
import androidx.fragment.app.Fragment
import com.thecode.aestheticdialogs.AestheticDialog
import com.thecode.aestheticdialogs.DialogStyle
import com.thecode.aestheticdialogs.DialogType
import com.example.cryptoview.base.BaseFragment.Companion.COIN_UI_MODEL
import com.example.cryptoview.presentation.coindetails.CoinDetailsActivity
import com.example.cryptoview.presentation.main.home.CoinItemUiModel

open class BaseFragment : Fragment() {

    fun displayErrorDialog(errorTitle: String, errorDescription: String) {
        AestheticDialog.Builder(requireActivity(), DialogStyle.RAINBOW, DialogType.ERROR)
            .setTitle(errorTitle)
            .setMessage(errorDescription)
            .setDuration(2000)
            .show()
    }

    fun launchCoinDetailsActivity(coinItem: CoinItemUiModel) {
        val intent = Intent(requireContext(), CoinDetailsActivity::class.java)
        intent.putExtra(COIN_UI_MODEL, coinItem)
        requireActivity().startActivity(intent)
    }

    object Companion {
        const val COIN_UI_MODEL = "COIN_UI_MODEL"
    }
}
