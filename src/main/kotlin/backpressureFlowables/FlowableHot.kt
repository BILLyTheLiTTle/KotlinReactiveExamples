package backpressureFlowables

import io.reactivex.Flowable
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {

    // Depending on the commented line below the situation of received items is much different
    val flowable = Flowable.interval(500, TimeUnit.MILLISECONDS).publish()
    //val flowable = Flowable.range(1,1000).publish()

    flowable.connect() // start to transmit item no matter who is receiving and what

    flowable.subscribe( {
        //onNext
        println("1st Received $it")
        runBlocking { delay(500) }
    }, {
        //onError
        it.printStackTrace()
    }, {
        //onComplete
        println("Complete")
    })

    Thread.sleep(1500)

    flowable.subscribe( {
        //onNext
        println("   2nd Received $it")
    }, {
        //onError
        it.printStackTrace()
    }, {
        //onComplete
        println("Complete")
    })

    //flowable.connect() // All subscribers receive items from first item

    Thread.sleep(2000) // no need for sleep if range is used
}