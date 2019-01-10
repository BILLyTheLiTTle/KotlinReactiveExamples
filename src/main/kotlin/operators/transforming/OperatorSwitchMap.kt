package operators.transforming

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val observable = Observable.range(1,10)

    // Comment - uncomment half the line below and run the example
    observable.switchMap {
        return@switchMap Observable.just(it)//.delay(100, TimeUnit.MILLISECONDS)
    }
        .subscribe { println(it) }

    Thread.sleep(100)
}