package il.arri.finatra.controllers

import com.twitter.finatra.{ResponseBuilder, Request, Controller}
import com.twitter.util.Future


class RestApiController extends Controller {

  get("/api")/*(checkRequestType(_)*/ {
    request =>
      //@formatter:off
      val mock = Map(
        "id"    -> "123",
        "title" -> "Charlie Sheen continues his epic rant against Kim Kardashian... then apologizes",
        "copy"  -> "Dear Kim, my extreme bad. really embarrassed by my actions. I was already pissed about some other crap that had nothing to do with you. I heard a story that bothered me. wrote some trash you didn't deserve. Ever. I'm an idiot as often as I'm a genius. that day, clearly I was the former. xox #ShutUpSheen",
        "byline"-> "TMZ"
      )
      //@formatter:on
      render.json(mock).toFuture
  }/*)*/

//  def checkRequestType(request: Request)(callback: Request => Future[ResponseBuilder]) =
//    request.headers().get("Accept").split(",").map(_.trim) match {
//      case array if array.contains("*/*") || array.contains("application/json+mock") => callback(request)
//      case _ => throw new UnsupportedOperationException("Oops!")
//    }

}