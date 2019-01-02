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
        println("1st Complete")
    })

    Thread.sleep(500)

    subject.subscribe( {
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

    //subject.onNext(-1) // comment - uncomment this line and see what happens
    subject.onComplete()

    subject.subscribe( {
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