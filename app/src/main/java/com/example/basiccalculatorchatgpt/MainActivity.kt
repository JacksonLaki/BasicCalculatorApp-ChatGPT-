class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var currentNumber = ""
    private var firstNumber: Double = 0.0
    private var secondNumber: Double = 0.0
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.result_text)

        val button0 = findViewById<Button>(R.id.button_0)
        val button1 = findViewById<Button>(R.id.button_1)
        val button2 = findViewById<Button>(R.id.button_2)
        val button3 = findViewById<Button>(R.id.button_3)
        val button4 = findViewById<Button>(R.id.button_4)
        val button5 = findViewById<Button>(R.id.button_5)
        val button6 = findViewById<Button>(R.id.button_6)
        val button7 = findViewById<Button>(R.id.button_7)
        val button8 = findViewById<Button>(R.id.button_8)
        val button9 = findViewById<Button>(R.id.button_9)
        val buttonAdd = findViewById<Button>(R.id.button_add)
        val buttonSubtract = findViewById<Button>(R.id.button_subtract)
        val buttonMultiply = findViewById<Button>(R.id.button_multiply)
        val buttonDivide = findViewById<Button>(R.id.button_divide)
        val buttonDecimal = findViewById<Button>(R.id.button_decimal)
        val buttonClear = findViewById<Button>(R.id.button_clear)
        val buttonEquals = findViewById<Button>(R.id.button_equals)

        button0.setOnClickListener { appendNumber("0") }
        button1.setOnClickListener { appendNumber("1") }
        button2.setOnClickListener { appendNumber("2") }
        button3.setOnClickListener { appendNumber("3") }
        button4.setOnClickListener { appendNumber("4") }
        button5.setOnClickListener { appendNumber("5") }
        button6.setOnClickListener { appendNumber("6") }
        button7.setOnClickListener { appendNumber("7") }
        button8.setOnClickListener { appendNumber("8") }
        button9.setOnClickListener { appendNumber("9") }
        buttonDecimal.setOnClickListener { appendDecimal() }
        buttonClear.setOnClickListener { clearAll() }
        buttonAdd.setOnClickListener { performOperation("+") }
        buttonSubtract.setOnClickListener { performOperation("-") }
        buttonMultiply.setOnClickListener { performOperation("*") }
        buttonDivide.setOnClickListener { performOperation("/") }
        buttonEquals.setOnClickListener { calculateResult() }
    }

    private fun appendNumber(number: String) {
        currentNumber += number
        resultTextView.text = currentNumber
    }

    private fun appendDecimal() {
        if (!currentNumber.contains(".")) {
            currentNumber += "."
            resultTextView.text = currentNumber
        }
    }

    private fun clearAll() {
        currentNumber = ""
        firstNumber = 0.0
        secondNumber = 0.0
        operator = ""
        resultTextView.text = "0"
    }

    private fun performOperation(op: String) {
        operator = op
        firstNumber = currentNumber.toDouble()
        currentNumber = ""
    }

    private fun calculateResult() {
        if (currentNumber.isEmpty()) {
            return  // Handle case where equals is pressed without second number
        }
        secondNumber = currentNumber.toDouble()
        val result = when (operator) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> {
                if (secondNumber == 0.0) {
                    // Handle division by zero (show error or set default value)
                    "Error"  // Or set a specific error message
                } else {
                    firstNumber / secondNumber
                }
            }
            else -> 0.0  // Handle unexpected operator
        }
        currentNumber = result.toString()
        resultTextView.text = currentNumber
        operator = ""  // Reset operator
