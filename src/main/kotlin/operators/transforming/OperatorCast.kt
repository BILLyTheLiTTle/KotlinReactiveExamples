package operators.transforming

import io.reactivex.Observable

// This example is not helpful, because "Why to use type casting when you can use generics Observable.just<Hello>?"

fun main(args: Array<String>) {

    val observable = Observable.just<Any>(
        GoodMorning("Bill"),
        GoodEvening("Jim")
    )

    observable
        // comment both lines below to see the compile-time error
        .cast(Hello::class.java) // or you can use the map operator as below
        //.map { it as Hello }
        .subscribe { it.welcome()}
}

open class Hello(open val name: String) {
    fun welcome() = println("${this::class.java.simpleName} ${this.name}")
}

class GoodMorning(override val name: String): Hello(name)
class GoodEvening(override val name: String): Hello(name)