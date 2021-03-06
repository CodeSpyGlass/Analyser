import analyser.Analyser
import objectcalisthenics.ObjectCalisthenicsConstraints
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Http4kServer
import org.http4k.server.Netty
import org.http4k.server.asServer

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            createObjectCalisthenicsServer(9000).start()
        }
    }
}

fun createObjectCalisthenicsServer(port: Int): Http4kServer {
    val analyserServer = AnalyserServer(
        Analyser(ObjectCalisthenicsConstraints)
    )
    return routes("/code-analysis" bind analyserServer.requestHandler())
        .asServer(Netty(port))
}