package operators.resources

import io.reactivex.Observable
import java.io.BufferedInputStream
import java.io.Closeable
import java.lang.Exception

fun main(args: Array<String>) {

    Observable.using({
        MyResource()
    },{
            resource:MyResource->
        Observable.just(resource)
    },{
            resource:MyResource->
        resource.close()
    }).subscribe {
        println("Resource Data: ${it.data}")
    }

    // A much more real example
    println("\nType one character")
    val bis = BufferedInputStream(System.`in`)
    Observable.using({
        bis
    },{
            resource:BufferedInputStream->
        Observable.just(resource)
    },{
            resource:BufferedInputStream->
        resource.close()
    }).subscribe {
        println("ASCII Data: ${it.read()}")
    }

    println(try { bis.available() } catch (e: Exception) {"Closed!"})
}

class MyResource(val data: String = "Default data"): Closeable {
    override fun close() {
        println("Close called")
    }
}