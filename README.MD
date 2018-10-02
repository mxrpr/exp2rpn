**Convert expression to Reverse Polish notation. Simple library which is able to run the expression and calculate the result.** </BR>

As an example, the following expression _"1 + ( 2 * 3 ) + 4"_ will be transformed to: _"1 2 3 * + 4 +"_ </BR>


Expression can contain variables:
```kotlin
"{{var1}} + {{var2}}"
```

*Sample usage*
```Kotlin
fun main(args: Array<String>) {
    val expRunner = RPNRunner()
    val expression = "(2*3)+4/2"
    println("Example for expression $expression :")
    val result = expRunner.calculate(expression)
    println("Result: $result")
}
```
