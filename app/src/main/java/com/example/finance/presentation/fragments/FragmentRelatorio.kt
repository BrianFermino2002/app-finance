package com.example.finance.presentation.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.finance.databinding.FragmentRelatorioBinding
import org.eazegraph.lib.charts.ValueLineChart
import org.eazegraph.lib.models.ValueLinePoint
import org.eazegraph.lib.models.ValueLineSeries

private const val TAG = "MainActivity"

class FragmentRelatorio: Fragment() {

    lateinit var lanc: List<String>
    private lateinit var binding: FragmentRelatorioBinding
    private val viewModel by activityViewModels<LancamentoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRelatorioBinding.inflate(inflater, container, false)
        val graphView: ValueLineChart = binding.cubiclinechart
        val series: ValueLineSeries = ValueLineSeries()
        val series2: ValueLineSeries = ValueLineSeries()

        series2.color = Color.RED
        series.color = Color.BLUE

        series.addPoint(ValueLinePoint("jan", 2.4f))
        series.addPoint(ValueLinePoint("Feb", 3.4f))
        series.addPoint(ValueLinePoint("Mar", .4f))
        series.addPoint(ValueLinePoint("Apr", 1.2f))
        series.addPoint(ValueLinePoint("Mai", 2.6f))
        series.addPoint(ValueLinePoint("Jun", 1.0f))
        series.addPoint(ValueLinePoint("Jul", 3.5f))
        series.addPoint(ValueLinePoint("Aug", 2.4f))
        series.addPoint(ValueLinePoint("Sep", 2.4f))
        series.addPoint(ValueLinePoint("Oct", 3.4f))
        series.addPoint(ValueLinePoint("Nov", .4f))
        series.addPoint(ValueLinePoint("Dec", 1.3f))


        series2.addPoint(ValueLinePoint("jan", 1.4f))
        series2.addPoint(ValueLinePoint("Feb", 2.4f))
        series2.addPoint(ValueLinePoint("Mar", .1f))
        series2.addPoint(ValueLinePoint("Apr", .2f))
        series2.addPoint(ValueLinePoint("Mai", 1.6f))
        series2.addPoint(ValueLinePoint("Jun", .5f))
        series2.addPoint(ValueLinePoint("Jul", 1.5f))
        series2.addPoint(ValueLinePoint("Aug", 1.4f))
        series2.addPoint(ValueLinePoint("Sep", 1.4f))
        series2.addPoint(ValueLinePoint("Oct", 5.4f))
        series2.addPoint(ValueLinePoint("Nov", .9f))
        series2.addPoint(ValueLinePoint("Dec", 4.3f))

        graphView.addSeries(series2)
        graphView.addSeries(series)
        graphView.startAnimation()

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



}