package operators.transforming

import io.reactivex.Observable

fun main(args: Array<String>) {
    val observable = Observable.fromIterable(listOf(1,2,3,4,5,6,7,8,9))

    observable
        // the filtering below results in an empty observable
        .filter { it > 20 }
        // send -1 value if the observable is empty
        //.defaultIfEmpty(-1)
        // switch to another observable if you are coming from an empty one
        .switchIfEmpty(Observable.just(-1, -1))
        .subscribe { println(it) }
}