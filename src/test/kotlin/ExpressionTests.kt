import com.mxr.RPNRunner
import org.junit.Assert
import org.junit.Test

class ExpressionTests {

    private val runner = RPNRunner()

    @Test
    fun emptyExpression() {
        val result: Double? = this.runner.calculate("")
        Assert.assertEquals(result, null)
    }

    @Test(expected = Exception::class)
    fun wrongExpression() {
        val result: Double? = this.runner.calculate("((")
        Assert.assertEquals(result, 234.0)
    }

    @Test
    fun justNumberInExpression() {
        val result: Double? = this.runner.calculate("234")
        Assert.assertEquals(result, 234.0)
    }

    @Test
    fun basicOperation_add() {
        val result: Double? = this.runner.calculate("2+3")
        Assert.assertEquals(result, 5.0)
    }

    @Test
    fun basicOperation_minus() {
        val result: Double? = this.runner.calculate("2-3")
        Assert.assertEquals(result, -1.0)
    }

    @Test
    fun basicOperation_multiply() {
        val result: Double? = this.runner.calculate("2*3")
        Assert.assertEquals(result, 6.0)
    }

    @Test
    fun basicOperation_divide() {
        val result: Double? = this.runner.calculate("6/3")
        Assert.assertEquals(result, 2.0)
    }

    @Test
    fun expressionWithParenthesis() {
        val result: Double? = this.runner.calculate("2+(3*2)")
        Assert.assertEquals(result, 8.0)
    }

    @Test(expected = Exception::class)
    fun expressionWithNonDigits() {
        val result: Double? = this.runner.calculate("abcde")
        Assert.assertEquals(result, 8.0)
    }


    @Test(expected = Exception::class)
    fun expressionWithWrongParenthesis() {
        val result: Double? = this.runner.calculate("2+(3*2")
        Assert.assertEquals(result, 8.0)
    }

    @Test
    fun expressionWithDoubleParenthesis() {
        val result: Double? = this.runner.calculate("2+((3*2))")
        Assert.assertEquals(result, 8.0)
    }

}