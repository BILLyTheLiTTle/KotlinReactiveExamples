package operators.utility

import io.reactivex.Observable

fun main(args: Array<String>) {
    val observable = Observable.fromIterable(listOf(1, 2, 3))

    // various utility operators from the Observable's point of view
    observable
        .doOnNext { println("You are going to receive $it!") }
        .doOnComplete { println("You are going to complete!") }
        .doOnSubscribe { println("You are going to subscribe!") }
        .subscribe({
            println("   Received: $it")
        },{
            println("   Error ${it.message}")
        },{
            println("   Completed")
        })
}