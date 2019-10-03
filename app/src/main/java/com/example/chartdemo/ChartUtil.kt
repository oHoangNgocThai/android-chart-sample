package com.example.chartdemo

import android.content.Context
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlin.random.Random

class ChartUtil {

    companion object {
        // XAxis
        const val X_AXIS_MAX_VALUE = 24F
        const val X_AXIS_MIN_VALUE = 0F
        const val X_AXIS_BLOCK = 1F
        const val X_AXIS_LABEL_BLOCK = 3

        // YAxis Left
        const val Y_AXIS_LEFT_MAX_VALUE = 40F
        const val Y_AXIS_LEFT_MIN_VALUE = -10F
        const val Y_AXIS_LEFT_BLOCK = 1F
        const val Y_AXIS_LEFT_LABEL_BLOCK = 10

        // YAxis Right
        const val Y_AXIS_RIGHT_MAX_VALUE = 100F
        const val Y_AXIS_RIGHT_MIN_VALUE = 0F
        const val Y_AXIS_RIGHT_BLOCK = 10F
        const val Y_AXIS_RIGHT_LABEL_BLOCK = 20
    }

    fun createWarningData(context: Context, fromHours: Int, toHours: Int, maxY: Int, minY: Int): BarData {
        val halfX = 0.5F
        val barEntries = arrayListOf<BarEntry>()
        for (i in fromHours until toHours) {
            if (minY < 0) {
                barEntries.add(BarEntry(i + halfX, maxY.toFloat()))
                barEntries.add(BarEntry(i + halfX, minY.toFloat()))
            } else {
                barEntries.add(BarEntry(i + halfX, maxY.toFloat()))
            }
        }

        val barDataSet = BarDataSet(barEntries, "Warning").apply {
            color = ContextCompat.getColor(context, R.color.colorWarning)
            axisDependency = YAxis.AxisDependency.LEFT
            setDrawValues(false)
        }

        return BarData(barDataSet).apply {
            barWidth =X_AXIS_BLOCK
        }
    }

    fun createLineData(context: Context, count: Int): LineData {
        val lineDataSets = arrayListOf<LineDataSet>()
        for (i in 0 until count) {
            lineDataSets.add(createLineDataSet(randomLineEntry(), i, getColors(context)[i]))
        }

        return LineData(lineDataSets.toList())
    }

    private fun createLineDataSet(entries: List<Entry>, index: Int, _color: Int): LineDataSet {
        return LineDataSet(entries, "Label $index").apply {
            axisDependency = YAxis.AxisDependency.LEFT
            color = _color
            lineWidth = 3F

            mode = LineDataSet.Mode.CUBIC_BEZIER
            cubicIntensity = 0.2f
            circleRadius = 4f

            setDrawCircleHole(false)
            setDrawCircles(false)
            setDrawValues(false)
        }
    }

    private fun randomLineEntry(): List<Entry> {
        val lineEntries = arrayListOf<Entry>()
        for (index in 0 until X_AXIS_MAX_VALUE.toInt()) {
            val randomY = (index - 3) * Random.nextDouble(1.0, 4.0) - (index + 1) * Random.nextDouble(0.1, 2.0)
            lineEntries.add(Entry(index.toFloat(), randomY.toFloat()))
        }
        return lineEntries.toList()
    }

    private fun getColors(context: Context): List<Int> {
        return arrayListOf<Int>().apply {
            add(ContextCompat.getColor(context, android.R.color.holo_orange_dark))
            add(ContextCompat.getColor(context, android.R.color.holo_blue_dark))
            add(ContextCompat.getColor(context, android.R.color.holo_purple))
            add(ContextCompat.getColor(context, android.R.color.holo_green_dark))
        }
    }
}
