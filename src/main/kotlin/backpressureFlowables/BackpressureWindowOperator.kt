package backpressureFlowables

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val flowable = Flowable.interval(500, TimeUnit.MILLISECONDS)

    flowable
        // Comment - uncomment one of the following
        // batch emit of 10 items
        //.window(10)
        // batch emit of 10 items and skip next 5 and go on
        //.window(10, 15)
        // batch emit of 15 items including last 5 items of the current(?) batch emission
        //.window(15, 10)
        // batch emit every 5000 ms with a maximum buffer of 2 items
        .window(5000, TimeUnit.MILLISECONDS, 2) //
        .subscribe( {
            //onNext
            println("Received $it")
            it.subscribe( {
                //onNext
                println("Inner Received $it")
            }, {
                //onError
                it.printStackTrace()
            }, {
                //onComplete
                println("Inner Complete")
            })
        }, {
            //onError
            it.printStackTrace()
        }, {
            //onComplete
            println("Complete")
        })

    Thread.sleep(15000)
}