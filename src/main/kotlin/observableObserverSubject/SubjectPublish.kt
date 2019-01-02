package observableObserverSubject

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

// PublishSubject keeps emitting and you will receive what is currently emitted when you subscribe.

fun main(args: Array<String>) {
    val observable = Observable.interval(100, TimeUnit.MILLISECONDS)

    val subject = PublishSubject.create<Long>()

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
        println("   2ndComplete")
    })

    Thread.sleep(500) // change the value and see what it is printing

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