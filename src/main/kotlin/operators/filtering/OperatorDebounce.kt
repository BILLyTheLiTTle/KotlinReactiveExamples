package operators.filtering

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

// The downstream will wait for a period of time before doing stuff after each emission

fun main(args: Array<String>) {
    // simulate user typing, change the values below to see what happens
    val observable = Observable.create<String> {
        it.onNext("Ko")
        Thread.sleep(100)
        it.onNext("Kotlin")
        Thread.sleep(600)
        it.onNext("Kotlin Re")
        Thread.sleep(100)
        it.onNext("Kotlin React")
        Thread.sleep(100)
        it.onNext("Kotlin Reactive")
        Thread.sleep(600)
        it.onNext("Kotlin Reactive Ex")
        Thread.sleep(1000)
        it.onNext("Kotlin Reactive Examp")
        Thread.sleep(200)
        it.onNext("Kotlin Reactive Examples")
        Thread.sleep(600)
        it.onNext("Kotlin Reactive Examples!")
        it.onComplete()
    }

    observable.debounce(500, TimeUnit.MILLISECONDS)
        .subscribe {
            println(it)
        }
}