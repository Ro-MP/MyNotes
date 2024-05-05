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
    val burguer = Food(
        "Burguer",
        500,
        listOf("Bread", "Meet", "mayonnaise")
    )

    val bbqSauce = Food(
        "BBQ sauce",
        100,
        listOf("Sugar", "Tomato", "Garlic")
    )

    val bbqBurguer = burguer + bbqSauce
    bbqBurguer.print()
}

private fun Food.print() {
    println("Name:  ${this.name}")
    println("Weight:  ${this.weight}")
    print("Ingredients:  ")
    this.Ingredients.forEach {
        print("$it, ")
    }
}