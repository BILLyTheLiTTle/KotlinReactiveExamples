package operators.concatenating

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val observable1 = Observable.just(1, 2, 3). map { "1st Observable: $it" }
    val observable2 = Observable.interval(100, TimeUnit.MILLISECONDS).map { "2nd Observable: $it" }

    Observable.concat(observable1, Observable.just("------"), observable2)
        .subscribe { println(it) }

    Thread.sleep(1000)
}