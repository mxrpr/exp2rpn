package com.mxr

import java.lang.Exception
import java.util.*

/**
 * RPNRunner class is responsible to calculate the
 * expression.
 **/
class RPNRunner(private val valueProvider: ValueProvider? = null) {

    private val expressionConverter = Exp2rpn()

    /**
     * Calculate expression
     *
     *  @param expression Normal expression, which will be transformed to RPN
     * and will be calculated. Default value is
     *
     *
     * @return Double In case the expression is an empty string, then the method will
     * return with null
     */
    fun calculate(expression: String): Double? {
        if (expression.trim().isEmpty())
            return null
        val rpnArray = expressionConverter.convert(expression)
        val stack = Stack<Double>()

        for (element in rpnArray) {


            when (element) {
                "+" -> {
                    val res = this.getElementValue(stack)
                    stack.push(res!!.first + res.second)
                }
                "-" -> {
                    val res = this.getElementValue(stack)
                    stack.push(res!!.second - res.first)
                }
                "*" -> {
                    val res = this.getElementValue(stack)
                    stack.push(res!!.first * res.second)
                }
                "/" -> {
                    val res = this.getElementValue(stack)
                    stack.push(res!!.second / res.first)
                }
                else -> {
                    stack.push(this.getDoubleValue(element))
                }
            }
        }

        if (stack.size > 1) {
            throw Exception("Invalid expression: '$expression'")
        }
        return stack.pop()
    }

    private fun getDoubleValue(element: String): Double {
        val value = doubleOrString(element)

        return if (value is Number) {
            value as Double
        } else {
            this.valueProvider?.getValue(value as String)!!
        }
    }

    private fun getElementValue(stack: Stack<Double>): Pair<Double, Double>? {
        if (stack.isEmpty())
            return null
        val first: Double?
        val second: Double?

        var value = doubleOrString(stack.pop())
        first = if (value is Number) {
            value as Double
        } else {
            this.valueProvider?.getValue(value as String)
        }

        value = doubleOrString(stack.pop())
        second = if (value is Number) {
            value as Double
        } else {
            this.valueProvider?.getValue(value as String)
        }

        return Pair(first!!, second!!)
    }

    private fun doubleOrString(element: Any) = try {
        element.toString().toDouble()
    } catch (e: NumberFormatException) {
        element
    }

}