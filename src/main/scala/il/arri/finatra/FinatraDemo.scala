package il.arri.finatra

import java.lang.System.setProperty

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.twitter.finatra.FinatraServer
import il.arri.finatra.controllers.{RestApiController, ContentController}

object FinatraDemo extends FinatraServer {
  setProperty("com.twitter.finatra.config.port", ":7755")

  val mapper = new ObjectMapper()
      mapper.registerModule(DefaultScalaModule)

  //
  // Register controllers
  //
  register(new RestApiController())
  register(new ContentController(mapper))

}

