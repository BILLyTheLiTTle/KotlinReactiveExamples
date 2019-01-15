package operators.custom

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer

fun main(args: Array<String>) {
    val observable = Observable.range(1,10)

    observable.compose(ActionInfo<Int>(onSubscribe = "Adding a subscriber", onNext = "Sending Item"))
        .subscribe { println("The item: $it") }

    Thread.sleep(1000)
}

class ActionInfo<T>(private val onSubscribe: String = "Subscribing...",
                    private val onNext: String = "Sending...",
                    private val onComplete: String = "Completing...")
    : ObservableTransformer<T,T> {

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.doOnSubscribe { println(onSubscribe) }
            .doOnNext { println("$onNext : $it") }
            .doOnComplete { println(onComplete) }
    }
}