package operators.combining

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val observable1 = Observable.intervalRange(1, 9, 0, 100, TimeUnit.MILLISECONDS)
    val observable2 = Observable.intervalRange(100, 9, 10, 150, TimeUnit.MILLISECONDS)

    // combine the latest emission every time an observable is emitting
    Observable.combineLatest(observable1, observable2,
        BiFunction<Long, Long, String> {
                item1, item2 ->
                    // just wasted 10 minutes (ok, more!) of my life playing with the paddings in console text!
                    "$item1".padStart("1st Observable".length/2+1) +
                    "-".padStart("1st Observable".length/2+1) +
                    "$item2".padStart("2nd Observable".length/2+2) })
        .subscribe { println("1st Observable - 2nd Observable\n$it".trimMargin()) }

    Thread.sleep(1000)
}