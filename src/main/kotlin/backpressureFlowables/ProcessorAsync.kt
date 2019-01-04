package backpressureFlowables

import io.reactivex.Flowable
import io.reactivex.processors.AsyncProcessor
import java.util.concurrent.TimeUnit

// AsyncProcessor emits only the last one.

fun main(args: Array<String>) {
    val flowable = Flowable.interval(100, TimeUnit.MILLISECONDS)

    val processor = AsyncProcessor.create<Long>()

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

    //processor.onNext(-1) // comment - uncomment this line and see what happens
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