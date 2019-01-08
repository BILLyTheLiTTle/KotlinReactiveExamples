package operators.merging

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val observable1 = Observable.interval(300, TimeUnit.MILLISECONDS).map { "1st Observable: $it" }
    val observable2 = Observable.interval(100, TimeUnit.MILLISECONDS).map { "2nd Observable: $it" }

    val obsArray = arrayOf(Observable.fromIterable(
        listOf("array 1", "array 2")), Observable.fromIterable(listOf("array A", "array B")))

    // Comment - uncomment on of the following
    //Observable.merge(observable1, observable2)
    //Observable.mergeArray(observable1, observable2, *obsArray)
    observable1.mergeWith(observable2)
        .subscribe { println(it) }

    Thread.sleep(1000)
}
