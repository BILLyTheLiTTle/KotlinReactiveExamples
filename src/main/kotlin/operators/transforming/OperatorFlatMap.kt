package operators.transforming

import io.reactivex.Observable

fun main (args: Array<String>) {
    val observable = Observable.fromIterable(listOf(1,2,3,4,5,5,6,7,8,8,3,4,9))

    observable
        .flatMap { Observable.fromIterable(listOf("From $it emit:", "${it*10}", "${it*100}", "---")) }
        // I am just playing around with the below code
        /*.flatMap { item -> Observable.create<String> {
            it.onNext("From $item emit:")
            it.onNext("${item*10}")
            it.onNext("${item*100}")
            it.onNext("---")
        } }*/
        // Why do I keep playing and writing the following code? I should take life more seriously!
        /*.flatMap { item -> ObservableSource<String> {
            it.onNext("From $item emit:")
            it.onNext("${item*10}")
            it.onNext("${item*100}")
            it.onNext("---")
        } }*/
        .subscribe { println(it) }
}