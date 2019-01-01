package observableObserverSubject

import io.reactivex.Observable
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    runBlocking {

        val observable = Observable.interval(500, TimeUnit.MILLISECONDS)

        observable.subscribe({//2
            println("1st Observable Received: $it")
        },{
            println("Error ${it.message}")
        },{
            println("Done")
        })

        delay(1500)

        observable.subscribe({//3
            println("   2nd Observable Received: $it")
        },{
            println("Error ${it.message}")
        },{
            println("Done")
        })

        delay(4000)
    }
}