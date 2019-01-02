package backpressureFlowables

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

// Partial solution for backpressure because this is not handling any memory problem!

fun main(args: Array<String>) {
    val flowable = Flowable.range(1,1000)

    flowable.map {
        fun printThis() {
            println ("Mapped $it")
        }
        printThis()
        it
    }.observeOn(Schedulers.computation()) // just provide a thread to run the subscription
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