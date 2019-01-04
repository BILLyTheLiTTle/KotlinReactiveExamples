package backpressureFlowables

import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor
import java.util.concurrent.TimeUnit

// PublishProcessor keeps emitting and you will receive what is currently emitted when you subscribe.

fun main(args: Array<String>) {
    val flowable = Flowable.interval(100, TimeUnit.MILLISECONDS)

    val processor = PublishProcessor.create<Long>()

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
        println("   2ndComplete")
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