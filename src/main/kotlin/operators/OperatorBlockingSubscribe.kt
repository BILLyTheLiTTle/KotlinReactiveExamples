package operators

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/* The example is from StackOverflow
    (https://stackoverflow.com/questions/54128079/rxjava2-blockingsubscribe-vs-subscribe/54129360#54129360) */
fun main(args: Array<String>) {
    println("Before blockingSubscribe")
    println("Before Thread: " + Thread.currentThread())

    Observable.interval(1, TimeUnit.SECONDS)
        .take(5)
        // comment - uncomment one of the following and see the console output
        .subscribe {
        //.blockingSubscribe{
            println("Thread: " + Thread.currentThread())
            println("Value:  $it")
        }

    println("After blockingSubscribe")
    println("After Thread: " + Thread.currentThread())

    // RxJava uses daemon threads, without this, the app would quit immediately
    Thread.sleep(10000)

    println("Done")
}