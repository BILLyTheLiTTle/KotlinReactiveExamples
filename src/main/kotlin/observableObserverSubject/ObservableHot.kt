package observableObserverSubject

import io.reactivex.Observable
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    runBlocking {

        // Depending on the commented line below the situation of received items is much different
        //val observable = Observable.range(1,1000).publish()
        val observable = Observable.interval(500, TimeUnit.MILLISECONDS).publish()

        // Comment - uncomment the connect() lines to play a little bit
        //observable.connect() // start to transmit item no matter who is receiving and what

        Thread.sleep(1000)

        observable.subscribe({
            println("1st Observable Received: $it")
        },{
            println("Error ${it.message}")
        },{
            println("Done")
        })

        delay(1500)

        observable.subscribe({
            println("   2nd Observable Received: $it")
        },{
            println("Error ${it.message}")
        },{
            println("Done")
        })

        observable.connect() // All observers receive items from first item

        delay(4000) // no need for delay if range is used
    }
}