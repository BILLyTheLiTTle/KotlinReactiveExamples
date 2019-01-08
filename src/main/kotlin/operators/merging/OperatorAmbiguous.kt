package operators.merging

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    // Reverse the period values to see what is going on
    val observable1 = Observable.interval(300, TimeUnit.MILLISECONDS).map { "1st Observable: $it" }
    val observable2 = Observable.interval(100, TimeUnit.MILLISECONDS).map { "2nd Observable: $it" }

    // Continue with the observable that has emitted first
    Observable.ambArray(observable1, observable2)
        .subscribe { println(it) }

    Thread.sleep(1000)
}