package backpressureFlowables

import io.reactivex.Flowable
import io.reactivex.processors.ReplayProcessor
import java.util.concurrent.TimeUnit

/* ReplayProcessor emits all emission at any subscription until the new subscription
    catches up with the current emission and then keeps emitting*/

fun main(args: Array<String>) {
    val flowable = Flowable.interval(100, TimeUnit.MILLISECONDS)

    val processor = ReplayProcessor.create<Long>()

    flowable.subscribe(processor)

    processor.subscribe( {
        //onNext
        println("1st Received $it")
    }, {
        //onError
        it.printStackTrace()
    }, {
        //onComplete
        println("1st Complete")
    })

    Thread.sleep(500)

    processor.subscribe( {
        //onNext
        println("   2nd Received $it")
    }, {
        //onError
        it.printStackTrace()
    }, {
        //onComplete
        println("   2nd Complete")
    })

    Thread.sleep(500) // change the value and see what it is printing

    processor.onComplete()

    processor.subscribe( {
        //onNext
        println("       3rd Received $it")
    }, {
        //onError
        it.printStackTrace()
    }, {
        //onComplete
        println("       3rd Complete")
    })

    Thread.sleep(100)
}