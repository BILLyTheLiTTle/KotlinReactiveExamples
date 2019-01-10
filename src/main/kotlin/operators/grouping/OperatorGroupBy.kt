package operators.grouping

import io.reactivex.Observable

fun main(args: Array<String>) {
    val observable = Observable.range(1,30)

    observable.groupBy { it / 10 }
        .subscribe {
            println("\nKEY: ${it.key}")
            it.subscribe { println(it) }
        }
}