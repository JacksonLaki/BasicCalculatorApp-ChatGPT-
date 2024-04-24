package com.example.basiccalculatorchatgpt

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var displayTextView = this.findViewById(R.id.displayTextView)

        // Set click listeners for all buttons
        val btn0 = findViewById<Button>(R.id.btn_0)
        val btn_1 = findViewById<Button>(R.id.btn_1)
        val btn_2 = findViewById<Button>(R.id.btn_2)
        val btn_3 = findViewById<Button>(R.id.btn_3)
        val btn_4 = findViewById<Button>(R.id.btn_4)
        val btn_5 = findViewById<Button>(R.id.btn_5)
        val btn_6 = findViewById<Button>(R.id.btn_6)
        val btn_7 = findViewById<Button>(R.id.btn_7)
        val btn_8 = findViewById<Button>(R.id.btn_8)
        val btn_9 = findViewById<Button>(R.id.btn_9)
        val btn_dot = findViewById<Button>(R.id.btn_dot)
        val btn_add = findViewById<Button>(R.id.btn_add)
        val btn_subtract = findViewById<Button>(R.id.btn_subtract)
        val btn_multiply = findViewById<Button>(R.id.btn_multiply)
        val btn_divide = findViewById<Button>(R.id.btn_divide)
        val btn_equal = findViewById<Button>(R.id.btn_equal)

        btn0.setOnClickListener { onButtonClick(btn0) }
        btn_1.setOnClickListener { onButtonClick(btn_1) }
        btn_2.setOnClickListener { onButtonClick(btn_2) }
        btn_3.setOnClickListener { onButtonClick(btn_3) }
        btn_4.setOnClickListener { onButtonClick(btn_4) }
        btn_5.setOnClickListener { onButtonClick(btn_5) }
        btn_6.setOnClickListener { onButtonClick(btn_6) }
        btn_7.setOnClickListener { onButtonClick(btn_7) }
        btn_8.setOnClickListener { onButtonClick(btn_8) }
        btn_9.setOnClickListener { onButtonClick(btn_9) }
        btn_dot.setOnClickListener { onButtonClick(btn_dot) }
        btn_add.setOnClickListener { onButtonClick(btn_add) }
        btn_subtract.setOnClickListener { onButtonClick(btn_subtract) }
        btn_multiply.setOnClickListener { onButtonClick(btn_multiply) }
        btn_divide.setOnClickListener { onButtonClick(btn_divide) }
        btn_equal.setOnClickListener { onButtonClick(btn_equal) }
    }

    private fun onButtonClick(view: View) {
        val buttonText = (view as Button).text.toString()
        when (view.id) {
            R.id.btn_equal -> calculateResult()
            else -> appendToExpression(buttonText)
        }
    }

    private fun appendToExpression(text: String) {
        var expression = text
        val displayTextView = null
        displayTextView.text = expression
    }

    private fun calculateResult() {
        try {
            val expressionWithoutSpaces = expression.replace("\\s".toRegex(), "")
            result = evaluateExpression(expressionWithoutSpaces).toString()
            displayTextView.text = result
            expression = result
        } catch (e: ArithmeticException) {
            displayTextView.text = "Error"
            expression = ""
        }
    }

    private fun evaluateExpression(expression: String): Double {
        return ExpressionParser.evaluate(expression)
    }
}

object ExpressionParser {
    fun evaluate(expression: String): Double {
        return Expression(expression).calculate()
    }
}

class Expression(private val expression: String) {
    private var index = -1
    private var currentChar = ' '

    init {
        nextChar()
    }

    private fun nextChar() {
        index++
        currentChar = if (index < expression.length) expression[index] else ' '
    }

    private fun eat(charToEat: Char) {
        while (currentChar == ' ') {
            nextChar()
        }
        if (currentChar == charToEat) {
            nextChar()
        } else {
            throw RuntimeException("Error parsing expression")
        }
    }

    private fun parseNumber(): Double {
        var result = ""
        while (currentChar in '0'..'9' || currentChar == '.') {
            result += currentChar
            nextChar()
        }
        return result.toDouble()
    }

    private fun parseTerm(): Double {
        var result = parseFactor()
        while (currentChar == '*' || currentChar == '/') {
            val op = currentChar
            nextChar()
            if (op == '*') {
                result *= parseFactor()
            } else {
                result /= parseFactor()
            }
        }
        return result
    }

    private fun parseExpression(): Double {
        var result = parseTerm()
        while (currentChar == '+' || currentChar == '-') {
            val op = currentChar
            nextChar()
            if (op == '+') {
                result += parseTerm()
            } else {
                result -= parseTerm()
            }
        }
        return result
    }

    private fun parseFactor(): Double {
        if (currentChar == '(') {
            eat('(')
            val result = parseExpression()
            eat(')')
            return result
        }
        if (currentChar == '-') {
            nextChar()
            return -parseFactor()
        }
        return parseNumber()
    }

    fun calculate(): Double {
        val result = parseExpression()
        if (currentChar != ' ') {
            throw RuntimeException("Unexpected: $currentChar")
        }
        return result

  btn0
    }
}