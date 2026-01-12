interface QuizActions {
    fun startQuiz()
    fun showResult()
}


abstract class QuizBase {
    protected var score: Int = 0
    abstract fun loadQuestions()
}

 class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswer: Int
)

class OnlineQuiz : QuizBase(), QuizActions {

    val questions = arrayListOf<Question>()
    val userAnswers = arrayListOf<Int>()
    var userName = ""

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
                "3. Which loop runs at least once?",
                listOf("for", "while", "do-while", "foreach"),
                3
            )
        )
    }

    override fun startQuiz() {

        print("Enter your name: ")
        userName = readln().toString()

        println("\nHi $userName")
        println("Welcome to the quiz")
        println("Quiz is starting...\n")

        loadQuestions()

        for (q in questions) {
            println(q.questionText)

            for ((index, option) in q.options.withIndex()) {
                println("${index + 1}. $option")
            }

            print("Enter your answer: ")
            val ans = readln().toInt()

            userAnswers.add(ans)

            if (ans == q.correctAnswer) {
                score++
            }

            println()
        }

        showResult()
    }

    override fun showResult() {

        println("------------ RESULT ------------")
        println("Name  : $userName")
        println("Score : $score / ${questions.size}")

        if (score >= 2) {
            println("Status: Pass")
        } else {
            println("Status: Fail")
        }

        println("\n------ Answer Review ------")

        for (i in questions.indices) {

            val q = questions[i]
            val userAns = userAnswers[i]

            println(q.questionText)
            println("Your answer    : ${q.options[userAns - 1]}")
            println("Correct answer : ${q.options[q.correctAnswer - 1]}")

            if (userAns == q.correctAnswer) {
                println("Result: Correct")
            } else {
                println("Result: Wrong")
            }

            println("---------------------------")
        }

        println("Quiz completed. Thank you.")
    }
}


fun main() {
    val quizObj = OnlineQuiz()
    quizObj.startQuiz()
}