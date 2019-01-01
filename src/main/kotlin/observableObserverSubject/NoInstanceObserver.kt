package observableObserverSubject

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    Observable.intervalRange(0,10, 100, 500, TimeUnit.MILLISECONDS)
        .subscribe({
            println("Item: $it")
        }, {
            println("Error: ${it.message}")
        }, {
            println("Completed")
        })

    // Make the main thread to wait while waiting for the observable to finish
    Thread.sleep(6000)
}