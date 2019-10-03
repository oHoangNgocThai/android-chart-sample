package com.example.chartdemo

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

open class CustomValueFormatted(
    private val block: Int
) : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return value.toString()
    }

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return if (value.toInt() % block == 0) {
            value.toInt().toString()
        } else {
            ""
        }
    }
}
