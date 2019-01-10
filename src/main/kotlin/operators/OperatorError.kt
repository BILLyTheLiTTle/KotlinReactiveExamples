package operators

import io.reactivex.Observable

fun main(args: Array<String>) {
    val observable = Observable.just(1,0,2)

    val retries = 2
    observable.map { it/it }
        // emit -1 and stop emitting
        //.onErrorReturn { -1 }
        // resume from another observable
        //.onErrorResumeNext(Observable.just(1, 0))
        .retry { t1, t2 ->
            if (t1> retries) {
                println("Retry attempts: $t1")
                println("Error message: ${t2.message}")
                false
            }
            else
                true
        }
        .subscribe { println(it) }
}