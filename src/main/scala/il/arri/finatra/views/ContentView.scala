package il.arri.finatra.views

import com.twitter.finatra.View

class ContentView(values: Map[String, String]) extends View {

  override def template = "templates/content.mustache"

  def content: Map[String, String] = {
    values
  }

}