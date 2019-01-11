package concurrencyNparallelism

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    // It uses thread according to me expectations
    val observable = Observable.range(0, 10)
    // It uses threads from computation pool
    //val observable = Observable.intervalRange(0, 10, 100, 100, TimeUnit.MILLISECONDS)

    val executor = Executors.newFixedThreadPool(1)

    // From a thread with unbounded number of worker threads
    observable.take(1).subscribeOn(Schedulers.io()).
        subscribe {
            Thread.sleep(880)
            println("1. IO Thread:\n${Thread.currentThread()}\nValue: $it\n")
        }

    // From a thread with bounded number of worker threads
    observable.take(1).subscribeOn(Schedulers.computation())
        .subscribe {
            Thread.sleep(760)
            println("2. Computation Thread:\n${Thread.currentThread()}\nValue: $it\n")
        }

    // From a new thread every time
    observable.take(1).subscribeOn(Schedulers.newThread())
        .subscribe {
            Thread.sleep(653)
            println("3-1. New Thread:\n${Thread.currentThread()}\nValue: $it\n")
        }
    observable.take(1).subscribeOn(Schedulers.newThread())
        .subscribe {
            Thread.sleep(650)
            println("3-2. New Thread:\n${Thread.currentThread()}\nValue: $it\n")
        }

    // From a single, but new, thread which is the same for every new observer
    observable.take(1).subscribeOn(Schedulers.single())
        .subscribe {
            Thread.sleep(543)
            println("4-1. Single Thread:\n${Thread.currentThread()}\nValue: $it\n")
        }
    observable.take(1).subscribeOn(Schedulers.single())
        .subscribe {
            Thread.sleep(540)
            println("4-2. Single Thread:\n${Thread.currentThread()}\nValue: $it\n")
        }

    // From a single, but on the thread it was called
    observable.take(1).subscribeOn(Schedulers.trampoline())
        .subscribe {
            Thread.sleep(430)
            println("5. Trampoline Thread:\n${Thread.currentThread()}\nValue: $it\n")
        }

    // From an executor
    observable.take(1).subscribeOn(Schedulers.from(executor))
        .subscribe {
            Thread.sleep(320)
            println("6. Executor Thread 1:\n${Thread.currentThread()}\nValue: $it\n")
        }
    observable.take(1).subscribeOn(Schedulers.from(executor))
        .subscribe {
            Thread.sleep(210)
            println("7. Executor Thread 2:\n${Thread.currentThread()}\nValue: $it\n")
        }

    Thread.sleep(2000)
    System.exit(1)
}