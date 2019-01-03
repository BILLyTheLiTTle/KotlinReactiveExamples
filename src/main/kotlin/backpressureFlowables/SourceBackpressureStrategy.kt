package backpressureFlowables

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

/* The create() function keeps running no matter what.
    The generate function runs whenever needed (by map operator at this example)
    -- see the "Item: XXX" vs the "Mapped Item: XXX" vs the "Received Item: XXX" in console*/

fun main(args: Array<String>) {

    // Comment - uncomment any of the following
    //// --- Generate function ---
    /*var item = 0
    val flowable = Flowable.generate<String> {
        println("Item: $item")
        it.onNext("Item: $item")
        item++
    }*/
    //// --- Generate function ---
    //// --- Create function ---
    val flowable = Flowable.create<String>({
        var item = 0
        while (item < 1001) {
            println("Item: $item")
            it.onNext("Item: $item")
            item++
        }
    }, BackpressureStrategy.BUFFER)
    //// --- Create function ---

    flowable.map {
        fun printThis() {
            println ("Mapped $it")
        }
        printThis()
        it
    }.observeOn(Schedulers.computation()) // just provide a thread to run the subscription
        .subscribe( object : Subscriber<String> {
            var subscription: Subscription? = null
            override fun onSubscribe(s: Subscription?) {
                subscription = s
                subscription?.request(200) // increase this value to understand how it works
            }
            override fun onNext(t: String?) {
                println("Received $t")
                Thread.sleep(50)
                //subscription?.request(1) // comment - uncomment this method if you want to request more
            }
            override fun onError(t: Throwable?) {
                t?.printStackTrace()
            }
            override fun onComplete() {
                println("Complete")
            }
        })

    Thread.sleep(15000) // keep it big
}