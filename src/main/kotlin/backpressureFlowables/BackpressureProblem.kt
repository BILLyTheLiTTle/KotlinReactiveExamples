package backpressureFlowables

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

// The mapping is running normally until the end of the range but the subscription cannot follow!

fun main(args: Array<String>) {
    val observable = Observable.range(1,1000)

    observable.map {
        fun printThis() {
            println ("Mapped $it")
        }
        printThis()
        it
    }
        .observeOn(Schedulers.computation()) // just provide a thread to run the subscription
        .subscribe( {
        //onNext
        println("Received $it")
        Thread.sleep(500)
    }, {
        //onError
        it.printStackTrace()
    }, {
        //onComplete
        println("Complete")
    })

    Thread.sleep(2000) // increase this value to understand how it works
}