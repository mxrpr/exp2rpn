package com.mxr

import java.lang.Exception

/**
 *
 * Reverse Polish notation (RPN), also known as Polish postfix notation or simply postfix notation, is a mathematical
 * notation in which operators follow their operands, in contrast to Polish notation (PN), in which operators precede
 * their operands. It does not need any parentheses as long as each operator has a fixed number of operands.
 *
 * @author Ferenc Tollas
 */
class Exp2rpn {

    private val precedence = mapOf(
            "+" to 0,
            "-" to 0,
            "*" to 5,
            "/" to 5
    )

    /**
     * Converts an expression to RPN
     * @param expression String
     *
     * @return Array<String> Array of variables and operators in RPN format
     */
    fun convert(expression: String): Array<String> {
        val stack = mutableListOf<String>()
        val output = mutableListOf<String>()

        val originalStringComponents = this.convert2StringComponents(expression)
        for (component in originalStringComponents) {

            if (component == "(") {
                stack.add(component)
            } else if (component == ")") {
                while (!stack.isEmpty()) {
                    val last = stack.removeAt(stack.size - 1)
                    if (last != "(") {
                        output.add(last)
                        continue
                    }
                    break
                }
            }
            // if it is an operator
            else if (precedence.containsKey(component)) {
                if (stack.size == 0) {

                    for (i in stack.size - 1 downTo 0) {
                        if (!precedence.containsKey(stack[i]))
                            break
                        if (precedence[component]!! <= precedence[stack[i]]!!) {
                            output.add(stack[i])
                            stack.removeAt(i)
                            continue
                        }
                    }
                }
                stack.add(component)

            } else {
                output.add(component)
            }
        }
        // While there's operators on the stack, pop them to the queue
        if (!stack.isEmpty()) {
            while (!stack.isEmpty()) {
                val element = stack.removeAt(stack.size - 1)
                if (element == "(" || element == ")") {
                    throw Exception("Syntax error in expression: $expression  at '$element'")
                }
                output.add(element)
            }
        }

        return output.toTypedArray()
    }

    /**
     * Convert expression to array. This is needed to transform the expression to RPN
     * @param expression The String expression
     *
     * @return Array of Strings, which contains the operators and the variables/numbers
     */

    private fun convert2StringComponents(expression: String): Array<String> {
        val result = mutableListOf<String>()
        var prevIndex = 0
        for (index in 0 until expression.length) {
            when (expression[index]) {
                '+', '-', '*', '/', '(', ')' -> {
                    if (!expression.substring(prevIndex, index).trim().isEmpty())
                        result.add(expression.substring(prevIndex, index))
                    result.add(expression[index].toString())
                    prevIndex = index + 1
                }
            }
        }
        if (prevIndex != expression.length)
            result.add(expression.substring(prevIndex, expression.length))

        return result.toTypedArray()
    }
}

fun main(args: Array<String>) {
    val expRunner = RPNRunner()
    val expression = "(2*3)+4/2"
    val result = expRunner.calculate(expression)
    println("Result: $result")
}