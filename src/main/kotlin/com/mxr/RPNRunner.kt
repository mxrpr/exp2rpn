package com.mxr

import java.lang.Exception
import java.util.*

/**
 * RPNRunner class is responsible to calculate the
 * expression.
 **/
class RPNRunner {

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
    fun calculate(expression: String ): Double? {
        if (expression.trim().isEmpty())
            return null
        val rpnArray = expressionConverter.convert(expression)
        val stack = Stack<Double>()

        for (element in rpnArray) {


            when(element) {
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
                    stack.push(res!!.second / res.first).toString()
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
        return element.toDouble()
    }

    private fun getElementValue(stack: Stack<Double>) : Pair<Double, Double>? {
        if (stack.isEmpty())
            return null
        return Pair(stack.pop(), stack.pop())
    }
}