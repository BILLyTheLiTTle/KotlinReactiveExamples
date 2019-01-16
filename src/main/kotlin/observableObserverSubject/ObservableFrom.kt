package observableObserverSubject

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable
import java.util.concurrent.Executors

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

    // -- Simple example with Callable --
    println("\n<-- Simple Callable experiments as a small introduction! -->")
    // run in the main thread
    println("Direct call to the call() function: ${MyCallable().call()}")
    // run in a different thread
    val service = Executors.newFixedThreadPool(5)
    println("Call from a Thread the call() function: ${service.submit(MyCallable()).get()}")
    service.shutdown()

    // -- Reactive example with Callable --
    println("\n<-- Reactive Callable experiments as a small introduction! -->")
    // Comment - uncomment the lines below to see the thread in which the onNext() and Callable() are running
    Observable.fromCallable(MyCallable())
        // affects the thread where the following operators are running
        //.observeOn(Schedulers.io()) // Comment - uncomment
        // affects the thread where the callable is running and all the following operators
        .subscribeOn(Schedulers.newThread()) // Comment - uncomment
        .subscribe { println("Callable Thread name: $it\nObserver Thread name: ${Thread.currentThread()}")}
    // For a better explanation please @see concurrencyNparallelism.ThreadAssignment.kt


    Thread.sleep(1000)
}

class MyCallable: Callable<String> {
    override fun call(): String = Thread.currentThread().toString()
}