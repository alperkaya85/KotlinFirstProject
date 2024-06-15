package machine

class CoffeeMachine() {
    var mlWater = 400
    var mlMilk = 540
    var grCoffee = 120
    var cups = 9
    var money = 550
    var state: State? = null


    fun doSomething(opt: String) {
        this.state = State.valueOf(opt.uppercase())
        doAction()
    }

    private fun doAction() {
        when (this.state) {
            State.REMAINING -> {
                printStatus(mlWater, mlMilk, grCoffee, cups, money)
            }

            State.BUY -> {buy()}
            State.FILL -> {
                println("Write how many ml of water you want to add:\n> ")
                val mlWater = readln().toInt();
                println("Write how many ml of milk you want to add:\n> ")
                val mlMilk = readln().toInt();
                println("Write how many grams of coffee beans you want to add:\n> ")
                val grCoffee = readln().toInt();
                println("Write how many disposable cups you want to add:\n> ")
                val cups = readln().toInt();

                this.mlWater += mlWater
                this.mlMilk += mlMilk
                this.grCoffee += grCoffee
                this.cups += cups
            }
            State.TAKE -> { money = 0}
            else -> {}
        }

    }

    private fun printStatus(
        mlWater: Int,
        mlMilk: Int,
        grCoffee: Int,
        cups: Int,
        money: Int
    ) {
        println("The coffee machine has:")
        println("$mlWater ml of water")
        println("$mlMilk ml of milk")
        println("$grCoffee g of coffee beans")
        println("$cups disposable cups")
        println("\$$money of money")
    }

    private fun buy() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:\n> ")
        val type = readln()

        if (type == "1") {
            if (mlWater < 250) {
                println("Sorry, not enough water!")
            } else if (grCoffee < 16) {
                println("Sorry, not enough coffee!")
            } else if (cups == 0) {
                println("Sorry, not enough cups!")
            } else {
                println("I have enough resources, making you a coffee!\n\n")
                mlWater -= 250
                grCoffee -= 16
                money += 4

                cups--
            }

        } else if (type == "2") {
            if (mlWater < 350) {
                println("Sorry, not enough water!")
            } else if (mlMilk < 75) {
                println("Sorry, not enough coffee!")
            } else if (grCoffee < 20) {
                println("Sorry, not enough coffee!")
            } else if (cups == 0) {
                println("Sorry, not enough cups!")
            } else {
                mlWater -= 350
                mlMilk -= 75
                grCoffee -= 20
                money += 7

                cups--
            }
        } else if (type == "3") {
            if (mlWater < 200) {
                println("Sorry, not enough water!")
            } else if (mlMilk < 100) {
                println("Sorry, not enough coffee!")
            } else if (grCoffee < 12) {
                println("Sorry, not enough coffee!")
            } else if (cups == 0) {
                println("Sorry, not enough cups!")
            } else {
                mlWater -= 200
                mlMilk -= 100
                grCoffee -= 12
                money += 6

                cups--
            }
        }

    }
}

enum class State(val ack: String) {
    BUY("buy"), FILL("fill"), TAKE("take"), REMAINING("remaining"), EXIT("exit")
}

fun main() {
    var machine = CoffeeMachine()


    println("Write action (buy, fill, take, remaining, exit):\n> ")
    var option = readln();

    while (option != "exit") {
        machine.doSomething(option)
        println()
        println("Write action (buy, fill, take, remaining, exit):\n> ")
        option = readln();
    }

}