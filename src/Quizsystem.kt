interface QuizActions {
    fun startQuiz()
    fun showResult()
}

abstract class QuizBase {
    protected var score: Int = 0
    abstract fun loadQuestions()
}

open class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswer: Int
)

class OnlineQuiz : QuizBase(), QuizActions {

    private val questions = mutableListOf<Question>()

    override fun loadQuestions() {
        questions.add(
            Question(
                "1. Kotlin is developed by?",
                listOf("Google", "JetBrains", "Microsoft", "Apple"),
                2
            )
        )

        questions.add(
            Question(
                "2. Which keyword is used for inheritance?",
                listOf("extends", "inherits", ":", "super"),
                3
            )
        )

        questions.add(
            Question(
                "3. Which loop is guaranteed to run at least once?",
                listOf("for", "while", "do-while", "foreach"),
                3
            )
        )
    }

    override fun startQuiz() {
        loadQuestions()

        println("===== ONLINE QUIZ SYSTEM =====")
        println("Enter your answers (1-4)\n")

        for (q in questions) {
            println(q.questionText)
            for ((index, option) in q.options.withIndex()) {
                println("${index + 1}. $option")
            }

            print("Your answer: ")
            val userAnswer = readLine()!!.toInt()

            if (userAnswer == q.correctAnswer) {
                score++
            }
            println()
        }

        showResult()
    }

    override fun showResult() {
        println("===== QUIZ RESULT =====")
        println("Total Questions: ${questions.size}")
        println("Correct Answers: $score")
        println("Score: $score/${questions.size}")

        if (score >= 2) {
            println("Result: PASS!!! ")
        } else {
            println("Result: FAIL...")
        }
    }
}

fun main() {
    val quiz = OnlineQuiz()
    quiz.startQuiz()
}

