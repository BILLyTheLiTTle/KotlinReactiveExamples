package observableObserverSubject

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

fun main(args: Array<String>) {
    val observer = object: Observer<String>{
        override fun onComplete() {
            println("Completed")
        }

        override fun onNext(item: String) {
            println("Key: $item")
        }

        override fun onError(e: Throwable) {
            println("Error ${e.message}")
        }

        override fun onSubscribe(d: Disposable) {
            println("New Subscription ")
        }
    }

    val observable = Observable.create<String> {
        var key = readLine()
        while(key != "0") {
            it.onNext(key?:"no character")
            key = readLine()
        }
        it.onComplete()
    }

    observable.subscribe(observer)
}