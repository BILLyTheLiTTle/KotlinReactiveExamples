package backpressureFlowables

import io.reactivex.BackpressureOverflowStrategy
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

fun main(args: Array<String>) {
    val observable = Observable.create<String>{
        var item = 0
        while (item < 1001) {
            println("Item: $item")
            it.onNext("Item: $item")
            item++
        }
    }

    // Comment - uncomment one of the following to see what happens
    // -- no backpressure implementation --
    observable.toFlowable(BackpressureStrategy.MISSING)
    // -- no backpressure implementation, throws error --
    //observable.toFlowable(BackpressureStrategy.ERROR)
    // -- drop every new emission if the downstream cannot keep up
    //observable.toFlowable(BackpressureStrategy.DROP)
    // -- drop every new emission if the downstream cannot keep up but emit the last one
    //observable.toFlowable(BackpressureStrategy.LATEST)
        // Comment - uncomment any of the following when you use BackpressureStrategy.MISSING above
        // -- Drop the oldest element in the buffer and put the current.
        .onBackpressureBuffer(16, { println("Oops") }, BackpressureOverflowStrategy.DROP_OLDEST)
        // -- Ignore the current value, keep the old values and the last one.
        //.onBackpressureBuffer(16, { println("Oops") }, BackpressureOverflowStrategy.DROP_LATEST)
        // -- drop every new emission if the downstream cannot keep up
        //.onBackpressureDrop { println("Dropped $it") }
        // -- drop every new emission if the downstream cannot keep up but emit the last one
        //.onBackpressureLatest()
        .observeOn(Schedulers.computation()) // just provide a thread to run the subscription
        .subscribe( {
            //onNext
            println("Received $it")
            Thread.sleep(50)
        }, {
            //onError
            it.printStackTrace()
        }, {
            //onComplete
            println("Complete")
        })

    Thread.sleep(10000) // leave it big, soooo big
}