package backpressureFlowables

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val flowable = Flowable.create<Int> ({
        it.onNext(0)
        Thread.sleep(500) // 500ms
        it.onNext(500)
        Thread.sleep(600) // 1100ms
        it.onNext(1100)
        Thread.sleep(600) // 1700ms
        it.onNext(1700)
        Thread.sleep(1200) // 2900ms
        it.onNext(2900)
        Thread.sleep(200) // 3100ms
        it.onNext(3100)
        Thread.sleep(200) // 3300ms
        it.onNext(3300)
        Thread.sleep(800) // 4100ms
        it.onNext(4100)
    }, BackpressureStrategy.MISSING)

    flowable
        // Comment - uncomment one of the following
        // emit the first item every after 1s window starts
        .throttleFirst(1, TimeUnit.SECONDS)
        // emit the last item every after tick (1s passed
        //.throttleLast(1, TimeUnit.SECONDS)
        // emit the item only if nothing is emitted in the next 1s from previous emission
        //.throttleWithTimeout(1, TimeUnit.SECONDS)
        .subscribe( {
            //onNext
            println("Received $it")
        }, {
            //onError
            it.printStackTrace()
        }, {
            //onComplete
            println("Complete")
        })

    Thread.sleep(6000)
}