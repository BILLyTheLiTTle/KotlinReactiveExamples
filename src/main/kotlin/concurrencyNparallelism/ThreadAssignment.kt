package concurrencyNparallelism

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

fun main(args: Array<String>) {
    val observable = Observable.range(1,10)

    // Comment - uncomment observeOn and subscribeOn operator as you want
    observable.take(1)
        // for every below operator observe in io thread
        //.observeOn(Schedulers.io()) // comment - uncomment
        .map {
            val item = "Thread (io): ${Thread.currentThread()}\nValue: $it\n"
            println(item)
            item
        }
        // for every below operator observe in computation thread
        //.observeOn(Schedulers.computation()) // comment - uncomment
        .map {
            val item = "Thread (computation): ${Thread.currentThread()}\nValue: $it\n"
            println(item)
            item
        }.map {
            val item = "Thread (computation again): ${Thread.currentThread()}\nValue: $it\n"
            println(item)
            item
        }
        // subscribe and observe in new thread if no observeOn operator has not set up something already
        .subscribeOn(Schedulers.newThread()) // comment - uncomment
        .subscribe{ println("<-- Item Arrived on ${Thread.currentThread()} -->\n$it") }

    Thread.sleep(1000)
}