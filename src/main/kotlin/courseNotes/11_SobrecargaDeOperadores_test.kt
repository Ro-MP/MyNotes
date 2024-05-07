package courseNotes

fun main(){

    overloadingOperatorTest()

}

private data class Food(
    val name: String,
    val weight: Int,
    val Ingredients: List<String>
)

private operator fun Food.plus(other: Food): Food{
    val ingredients: MutableList<String> = mutableListOf()
    ingredients.addAll(this.Ingredients)
    ingredients.addAll(other.Ingredients)
    return Food(
        name = "${this.name} with ${other.name}",
        weight = this.weight + other.weight,
        Ingredients = ingredients
    )
}

private fun overloadingOperatorTest(){
    val burger = Food(
        "Burger",
        500,
        listOf("Bread", "Meet", "mayonnaise")
    )

    val bbqSauce = Food(
        "BBQ sauce",
        100,
        listOf("Sugar", "Tomato", "Garlic")
    )

    val frenchFries = Food(
        "French fries",
        150,
        listOf("Potatoes", "Cheese", "Ketchup")
    )

    val bbqBurger = burger + bbqSauce + frenchFries
    bbqBurger.print()
}

private fun Food.print() {
    println("Name:  ${this.name}")
    println("Weight:  ${this.weight}")
    print("Ingredients:  ")
    this.Ingredients.forEach {
        print("$it, ")
    }
}