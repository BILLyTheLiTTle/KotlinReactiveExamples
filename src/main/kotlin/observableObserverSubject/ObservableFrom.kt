package observableObserverSubject

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

fun main(args: Array<String>) {
    val observer = object: Observer<String> {
        override fun onComplete() {
            println("Completed")
        }

        override fun onNext(item: String) {
            println("Item: $item")
        }

        override fun onError(e: Throwable) {
            println("Error ${e.message}")
        }

        override fun onSubscribe(d: Disposable) {
            println("New Subscription ")
        }
    }

    val list = listOf("Item 1","Item 2","Item 3","Item 4")

    val observable = Observable.fromIterable(list)

    observable.subscribe(observer)

}