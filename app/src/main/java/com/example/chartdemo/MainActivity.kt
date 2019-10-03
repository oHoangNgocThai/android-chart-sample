package com.example.chartdemo

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Line Chart Demo"

        chart?.apply {
            setBackgroundColor(Color.WHITE)
            setDrawGridBackground(true)
            description.isEnabled = false

            setTouchEnabled(true)

            isDragEnabled = true
            setScaleEnabled(true)
            setDrawBarShadow(false)
            isHighlightFullBarEnabled = false
        }

        chart.xAxis.apply {
            val size = ChartUtil.X_AXIS_MAX_VALUE - ChartUtil.X_AXIS_MIN_VALUE
            labelCount = (size / ChartUtil.X_AXIS_BLOCK).toInt()
            axisMaximum = ChartUtil.X_AXIS_MAX_VALUE
            axisMinimum = ChartUtil.X_AXIS_MIN_VALUE
            valueFormatter = CustomValueFormatted(ChartUtil.X_AXIS_LABEL_BLOCK)

            isGranularityEnabled = true
            granularity = ChartUtil.X_AXIS_BLOCK

            textSize = 11F
            textColor = Color.BLACK

            setDrawAxisLine(true)
            axisLineColor = Color.BLACK
            axisLineWidth = 1F

            setDrawGridLines(true)
            gridColor = ContextCompat.getColor(applicationContext, R.color.colorGridBackground)
            gridLineWidth = 0.5F

            position = XAxis.XAxisPosition.BOTTOM
        }

        chart.axisLeft.apply {
            val size = ChartUtil.Y_AXIS_LEFT_MAX_VALUE - ChartUtil.Y_AXIS_LEFT_MIN_VALUE
            labelCount = (size / ChartUtil.Y_AXIS_LEFT_BLOCK).toInt()
            axisMaximum = ChartUtil.Y_AXIS_LEFT_MAX_VALUE
            axisMinimum = ChartUtil.Y_AXIS_LEFT_MIN_VALUE
            valueFormatter = CustomValueFormatted(ChartUtil.Y_AXIS_LEFT_LABEL_BLOCK)

            isGranularityEnabled = true
            granularity = ChartUtil.Y_AXIS_LEFT_BLOCK

            textSize = 11F
            textColor = Color.BLACK

            setDrawAxisLine(false)
            setDrawGridLines(true)
            gridColor = ContextCompat.getColor(applicationContext, R.color.colorGridBackground)
            gridLineWidth = 0.5F
        }

        chart.axisRight.apply {
            isEnabled = true
            val size = ChartUtil.Y_AXIS_RIGHT_MAX_VALUE - ChartUtil.Y_AXIS_RIGHT_MIN_VALUE
            labelCount = (size / ChartUtil.Y_AXIS_RIGHT_BLOCK).toInt()
            axisMaximum = ChartUtil.Y_AXIS_RIGHT_MAX_VALUE
            axisMinimum = ChartUtil.Y_AXIS_RIGHT_MIN_VALUE
            valueFormatter = CustomValueFormatted(ChartUtil.Y_AXIS_RIGHT_LABEL_BLOCK)

            textSize = 11F
            textColor = Color.BLACK
            axisLineColor = Color.BLACK

            setDrawAxisLine(false)
            setDrawGridLines(false)
            setDrawZeroLine(false)
        }

        chart.data = CombinedData().apply {
            setData(ChartUtil().createLineData(applicationContext, 4))
            setData(ChartUtil().createWarningData(applicationContext, 9, 12,
                    ChartUtil.Y_AXIS_LEFT_MAX_VALUE.toInt(), ChartUtil.Y_AXIS_LEFT_MIN_VALUE.toInt()))
        }

        chart.legend.apply {
            form = Legend.LegendForm.LINE
            yEntrySpace = 50F
            // get data color
        }

        chart.animateXY(2000, 2000)
        chart.invalidate()
    }
}
