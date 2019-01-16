package concurrencyNparallelism

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

// For something nifty about thread assignment @see observableObserverSubject.ObservableFrom.kt, the Callable example

fun main(args: Array<String>) {
    val observable = Observable.range(1,10)

    // Comment - uncomment observeOn and subscribeOn operator as you want
    observable.take(1)
        // for every below operator observe in io thread
        //.observeOn(Schedulers.io()) // comment - uncomment
        .map {
            println("Thread (io): ${Thread.currentThread()}\n")
            it
        }
        // for every below operator observe in computation thread
        .observeOn(Schedulers.computation()) // comment - uncomment
        .map {
            println("Thread (computation): ${Thread.currentThread()}\n")
            it
        }.map {
            println("Thread (computation again): ${Thread.currentThread()}\n")
            it
        }
        // subscribe and observe in new thread if no observeOn operator has not set up something already
        // subscribeOn() seems to work like it is the first thread assignment operator no matter where it is written
        .subscribeOn(Schedulers.newThread()) // comment - uncomment
        .subscribe{ println("<-- Item Arrived on ${Thread.currentThread()} -->\n$it") }

    Thread.sleep(1000)
}