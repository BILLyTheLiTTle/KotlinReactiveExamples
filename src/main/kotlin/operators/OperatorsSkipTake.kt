package operators

import io.reactivex.Observable
import java.util.concurrent.TimeUnit


fun main(args: Array<String>) {
    val observable = Observable.intervalRange(0, 10,0,100, TimeUnit.MILLISECONDS)
    val observable2 = Observable.intervalRange(100, 1000,400,200, TimeUnit.MILLISECONDS)

    observable
        // skip / take the first 5 items
        //.skip(5)
        //.take(5)
        // skip / take the first items emitted in 200ms
        //.skip(200, TimeUnit.MILLISECONDS)
        //.take(200, TimeUnit.MILLISECONDS)
        // skip / take the last 3 items
        //.skipLast(3)
        //.takeLast(3)
        // skip item while the condition is true, if it becomes false get all the others (change it to it > 4 ) and see
        //.skipWhile { it < 4 }
        // take item while the condition is true, if it becomes false get nothing (change it to it > 4 ) and see
        //.takeWhile { it < 4 }
        // skip the item until another observable start emitting, then get items from the first observable
        //.skipUntil(observable2)
        // take the item until another observable start emitting, then get nothing
        .takeUntil(observable2)
        .subscribe { println(it) }

    Thread.sleep(1000)
}