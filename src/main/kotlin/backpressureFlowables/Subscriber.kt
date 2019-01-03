package backpressureFlowables

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

// The same example from Flowable file but with a Subscriber object instead of lambda

fun main(args: Array<String>) {
    val flowable = Flowable.range(1,1000)

    flowable.map {
        fun printThis() {
            println ("Mapped $it")
        }
        printThis()
        it
    }.observeOn(Schedulers.computation()) // just provide a thread to run the subscription
        .subscribe( object : Subscriber<Int> {
            var subscription: Subscription? = null
            override fun onSubscribe(s: Subscription?) {
                subscription = s
                subscription?.request(2) // increase this value to understand how it works
            }
            override fun onNext(t: Int?) {
                println("Received $t")
                Thread.sleep(500)
                //subscription?.request(1) // comment - uncomment this method if you want to request more
            }
            override fun onError(t: Throwable?) {
                t?.printStackTrace()
            }
            override fun onComplete() {
                println("Complete")
            }
        })

    Thread.sleep(5000) // increase this value if you want to request items for more time
}