package operators.custom

import io.reactivex.Observable
import io.reactivex.ObservableOperator
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

fun main(args: Array<String>) {
val observable = Observable.fromIterable(listOf("Bill", "Jim", 1, Any()))
    observable.lift(WordCounter<Any>())
        .subscribe { println(it) }
}

class WordCounter<T>: ObservableOperator<Pair<T, Int>, T> {
    override fun apply(observer: Observer<in Pair<T, Int>>): Observer<in T> {
        return object: Observer<T> {
            override fun onComplete() {
                observer.onComplete()
            }
            override fun onSubscribe(d: Disposable) {
                observer.onSubscribe(d)
            }
            override fun onError(e: Throwable) {
                observer.onError(e)
            }
            override fun onNext(t: T) {
                observer.onNext(Pair(t,t.toString().length))
            }
        }
    }
}