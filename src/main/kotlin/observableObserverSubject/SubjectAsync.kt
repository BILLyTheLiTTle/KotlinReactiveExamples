package observableObserverSubject

import io.reactivex.Observable
import io.reactivex.subjects.AsyncSubject
import java.util.concurrent.TimeUnit

// AsyncSubject emits only the last one.

fun main(args: Array<String>) {
    val observable = Observable.interval(100, TimeUnit.MILLISECONDS)

    val subject = AsyncSubject.create<Long>()

    observable.subscribe(subject)
    subject.subscribe( {
        //onNext
        println("1st Received $it")
    }, {
        //onError
        it.printStackTrace()
    }, {
        //onComplete
        println("Complete")
    })

    subject.subscribe( {
        //onNext
        println("   2nd Received $it")
    }, {
        //onError
        it.printStackTrace()
    }, {
        //onComplete
        println("   Complete")
    })

    Thread.sleep(2000) // change the value and see what it is printing

    //subject.onNext(-1) // comment - uncomment this line and see what happens
    subject.onComplete()
}