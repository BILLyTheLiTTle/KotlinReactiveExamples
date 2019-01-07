package operators.filtering

import io.reactivex.Observable

fun main(args: Array<String>) {
    val observable = Observable.fromIterable(listOf(1,2,3,4,5,5,6,7,8,8,3,4,9))

    observable
        // Remove any duplicates (values: 5, 8, 3, 4)
        //.distinct()
        // Remove the value from original stream if the calculated value below has already be computed
        .distinct {
            println("Distinct $it % 2: ${it%3}") // 1%2=1 (remember 1), 4%2=1 (1 has already passed, so remove value 4)
            it%3
        }
        // Removes a value if it is duplicate of the previous (values 5, 8)
        //.distinctUntilChanged()
        .subscribe {
            println(it)
        }
}