package operators.transforming

import io.reactivex.Observable

fun main(args: Array<String>) {
    val observable = Observable.fromIterable(listOf("1", "2", "3"))

    observable
        // add the previous emission to the current and emit
        //.scan { previous, current -> "$previous - $current" }
        // keep adding the previous emission to the current but emit only when onComplete happens
        .reduce { previous, current -> "$previous - $current" }
        .subscribe { println("Emission: $it") }


    // collect the emissions in a list
    val list = mutableListOf<String>()
    observable
        .collect( { println("Collecting...") } , { _, item -> list.add(item) } )
        .subscribe { _ -> println(list) }
}