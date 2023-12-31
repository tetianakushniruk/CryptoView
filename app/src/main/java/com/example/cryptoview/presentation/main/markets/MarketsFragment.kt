package com.example.cryptoview.presentation.main.markets

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoview.R
import com.example.cryptoview.base.BaseFragment
import com.example.cryptoview.core.domain.DataState
import com.example.cryptoview.core.domain.ExchangeItemDomainModel
import com.example.cryptoview.databinding.FragmentMarketsBinding
import com.example.cryptoview.presentation.main.home.CoinCardOnClickListener
import com.example.cryptoview.presentation.main.home.CoinItemUiModel
import com.example.cryptoview.presentation.main.home.toUiModels
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter


@AndroidEntryPoint
class MarketsFragment : BaseFragment(), CoinCardOnClickListener, ExchangeOnClickListener {

    private val viewModel: MarketViewModel by viewModels()

    private var coinOnClickListener: CoinCardOnClickListener = this
    private var exchangeOnClickListener: ExchangeOnClickListener = this

    private var _binding: FragmentMarketsBinding? = null
    private val binding get() = _binding!!

    private lateinit var marketRecyclerAdapter: MarketRecyclerViewAdapter
    private lateinit var exchangeRecyclerAdapter: ExchangeRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarketsBinding.inflate(inflater, container, false)
        subscribeObservers()
        initViews()
        fetchMarkets()
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchMarkets() {
        viewModel.getCoins(getString(R.string.usd))
        binding.recyclerviewCryptoMarkets.scheduleLayoutAnimation()
    }

    private fun fetchExchanges() {
        viewModel.getExchanges()
        binding.recyclerviewCryptoExchanges.scheduleLayoutAnimation()
    }

    private fun showInternetConnectionErrorLayout() {
        if (marketRecyclerAdapter.itemCount > 0 || exchangeRecyclerAdapter.itemCount > 0) {
            displayErrorDialog(
                getString(R.string.network_error),
                getString(R.string.check_internet)
            )
        } else {
            binding.included.apply {
                layoutBadState.isVisible = true
                textState.text = getString(R.string.internet_connection_error)
                btnRetry.isVisible = true
            }
        }
    }

    private fun showBadStateLayout() {
        if (marketRecyclerAdapter.itemCount > 0 || exchangeRecyclerAdapter.itemCount > 0) {
            displayErrorDialog(
                getString(R.string.error),
                getString(R.string.service_unavailable)
            )
        } else {
            binding.included.apply {
                layoutBadState.isVisible = true
                textState.text = getString(R.string.no_result_found)
                btnRetry.isVisible = true
            }
        }
    }

    private fun showRecyclerViewMarket(state: Boolean) {
        binding.apply {
            recyclerviewCryptoMarkets.isVisible = state
            recyclerviewCryptoExchanges.isVisible = !state
        }
    }

    private fun hideBadStateLayout() {
        binding.included.layoutBadState.isVisible = false
    }

    private fun subscribeObservers() {
        viewModel.coinState.observe(
            viewLifecycleOwner
        ) {
            when (it) {
                is DataState.Success -> {
                    hideBadStateLayout()
                    hideLoadingProgress()
                    showRecyclerViewMarket(true)
                    populateRecyclerViewMarket(it.data.coins.toUiModels())
                }

                is DataState.Loading -> showLoadingProgress()

                is DataState.Error -> {
                    hideLoadingProgress()
                    showInternetConnectionErrorLayout()
                    Toast.makeText(
                        activity,
                        getString(R.string.internet_connection_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        viewModel.exchangeState.observe(
            viewLifecycleOwner
        ) {
            when (it) {
                is DataState.Success -> {
                    hideBadStateLayout()
                    hideLoadingProgress()
                    showRecyclerViewMarket(false)
                    populateRecyclerViewExchange(it.data.exchanges)
                }

                is DataState.Loading -> showLoadingProgress()

                is DataState.Error -> {
                    hideLoadingProgress()
                    showInternetConnectionErrorLayout()
                    Toast.makeText(
                        activity,
                        getString(R.string.internet_connection_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun hideLoadingProgress() {
        binding.refreshLayout.isRefreshing = false
    }

    private fun showLoadingProgress() {
        binding.refreshLayout.isRefreshing = true
    }

    private fun initRecyclerViews() {
        //Crypto Market
        marketRecyclerAdapter = MarketRecyclerViewAdapter(coinOnClickListener)
        binding.recyclerviewCryptoMarkets.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(
                DividerItemDecoration(
                    activity,
                    LinearLayoutManager.VERTICAL
                )
            )
            adapter = SlideInBottomAnimationAdapter(marketRecyclerAdapter)
        }

        //Exchanges
        exchangeRecyclerAdapter = ExchangeRecyclerViewAdapter(exchangeOnClickListener)
        binding.recyclerviewCryptoExchanges.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(
                DividerItemDecoration(
                    activity,
                    LinearLayoutManager.VERTICAL
                )
            )
            adapter = SlideInBottomAnimationAdapter(exchangeRecyclerAdapter)
        }
    }

    private fun initViews() {
        binding.apply {
            included.btnRetry.setOnClickListener { if (btnCrypto.isSelected) fetchMarkets() else fetchExchanges() }
            refreshLayout.setOnRefreshListener {
                if (btnCrypto.isSelected) fetchMarkets() else fetchExchanges()
            }
            themedButtonGroup.apply {
                selectButton(btnCrypto)
                setOnSelectListener {
                    when (it) {
                        btnCrypto -> fetchMarkets()
                        btnExchange -> fetchExchanges()
                    }
                }
            }
        }
        initRecyclerViews()
    }

    private fun populateRecyclerViewMarket(coins: List<CoinItemUiModel>) {
        if (coins.isEmpty()) {
            showBadStateLayout()
        } else {
            val coinArrayList: ArrayList<CoinItemUiModel> = ArrayList()
            for (i in coins.indices) {
                val coin = coins[i]
                coinArrayList.add(coin)
                marketRecyclerAdapter.setCoinListItems(coinArrayList)
                binding.recyclerviewCryptoMarkets.scheduleLayoutAnimation()
            }
        }
    }

    private fun populateRecyclerViewExchange(exchanges: List<ExchangeItemDomainModel>) {
        if (exchanges.isEmpty()) {
            showBadStateLayout()
        } else {
            val exchangeArrayList: ArrayList<ExchangeItemDomainModel> = ArrayList()
            for (i in exchanges.indices) {
                val exchange = exchanges[i]
                exchangeArrayList.add(exchange)
                exchangeRecyclerAdapter.setExchangeListItems(exchangeArrayList)
                binding.recyclerviewCryptoExchanges.scheduleLayoutAnimation()
            }
        }
    }

    private fun openExchangeDetailsActivity(exchange: ExchangeItemDomainModel) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(exchange.url)))
    }

    override fun openCoinDetails(coin: CoinItemUiModel) {
        launchCoinDetailsActivity(coin)
    }

    override fun openExchangeDetails(exchange: ExchangeItemDomainModel) {
        openExchangeDetailsActivity(exchange)
    }
}
