package il.arri.finatra.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.twitter.finagle.Http
import com.twitter.finatra.{Logging, Controller, ResponseBuilder}
import com.twitter.io.Charsets.Utf8
import com.twitter.util.Promise
import il.arri.finatra.mappers.ContentMapper
import il.arri.finatra.views.ContentView
import org.jboss.netty.handler.codec.http.HttpMethod.GET
import org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1
import org.jboss.netty.handler.codec.http._

class ContentController(mapper: ObjectMapper) extends Controller /*with Logging*/ {

  get("/") {
    request =>
      val renderPromise = new Promise[ResponseBuilder]
      //
      // Make call to content Api
      //
      val service  = Http.newService("localhost:7755")
      val request  = new DefaultHttpRequest(HTTP_1_1, GET, "/api")
      val response = service(request)
      //
      // Handle response callbacks
      //
      response onSuccess {
        resp: HttpResponse =>
        //
        // Parse json from content service
        //
        val contentJson = resp.getContent.toString(Utf8)
        val jsonContent = mapper.readValue(contentJson, classOf[ContentMapper])
        val content = Map(
          "id"    -> jsonContent.id,
          "title" -> jsonContent.title,
          "copy"  -> jsonContent.copy,
          "byline"-> jsonContent.byline
        )
        //
        // Send back view as promised
        //
        renderPromise.setValue(render.view(new ContentView(content)))
      }
      //
      // Log an error
      //
      response onFailure {
        t => {
          log.error(t.getMessage)
        }
      }

      //
      // Return future rendered view
      //
      renderPromise
  }

}
