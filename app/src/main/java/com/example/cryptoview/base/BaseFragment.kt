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

    fun showErrorDialog(title: String, description: String) {
        AestheticDialog.Builder(requireActivity(), DialogStyle.RAINBOW, DialogType.ERROR)
            .setTitle(title)
            .setMessage(description)
            .setDuration(2000)
            .show()
    }

    fun showSuccessDialog(title: String, description: String) {
        AestheticDialog.Builder(requireActivity(), DialogStyle.RAINBOW, DialogType.SUCCESS)
            .setTitle(title)
            .setMessage(description)
            .setDuration(2000)
            .show()
    }

    fun openCoinDetailsActivity(coin: CoinItemUiModel) {
        val intent = Intent(context, CoinDetailsActivity::class.java)
        intent.putExtra(COIN_UI_MODEL, coin)
        requireActivity().startActivity(intent)
    }

    object Companion {
        const val COIN_UI_MODEL = "COIN_UI_MODEL"
    }
}
