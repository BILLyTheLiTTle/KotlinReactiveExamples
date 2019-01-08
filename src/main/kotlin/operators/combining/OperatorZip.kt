package operators.combining

import io.reactivex.Observable
import io.reactivex.functions.BiFunction

fun main(args: Array<String>) {

    // Both of the Observables should have same quantity of emission items are else they are not emitted at all
    val observable1 = Observable.fromIterable(listOf("One", "Two", "Three")) // contains 3 values, emitted all of them
    val observable2 = Observable.fromIterable(listOf(1, 2, 3, 4)) // contains 4 values, but 3 are emitted

    // Comment - uncomment the below items to see no difference at output
    //Observable.zip(observable1, observable2, BiFunction<String, Int, String> { item1, item2 -> "$item1: $item2" })
    observable1.zipWith(observable2, BiFunction<String, Int, String> { item1, item2 -> "$item1: $item2" })
        .subscribe { println(it) }
}