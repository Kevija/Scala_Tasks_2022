package com.github.Kevija.ScalaTasks
import scala.xml.{Node, XML}

object Day22ParsingXML extends App {
  val src = "src/resources/xml/books.xml"

  val xml = XML.loadFile(src) //so we load the whole structure into xml Elem data type - so root element

  println(xml)

  //in Scala 2 i can still make XML directly, - XML is a first class citizen
  val myOwnXMl = <book id="bk305">
    <title>Best Fruit Cocktails
    </title>
    <author>Doe, John
    </author>
  </book>

  println(myOwnXMl)

  val books = xml \ "book" //i am saying get all book tags that are direct children of this xml element(catalog here)

  val firstBook = books.head
  println(firstBook)

  //we can get the contents of some attribute considering the fact that the atribute might not exist at all
  val id = firstBook.attribute("id").getOrElse("bk0").toString
  println(s"Our book id is $id")

  //get children tag text contents
  println((firstBook \ "author").text) //theoretically if we had multiple author tags we would get ALL the text from those tags
  println((firstBook \ "title").text)
  println((firstBook \ "genre").text)
  println((firstBook \ "price").text)
  println((firstBook \ "publish_date").text)
  println((firstBook \ "description").text)

  def getBook(node: Node): BookUnit = {
    val id = node.attribute("id").getOrElse("bk0").toString
    val author = (node \ "author").text
    val title = (node \ "title").text
    val genre = (node \ "genre").text
    val price = (node \ "price").text.toDouble
    val publish_date = (node \ "publish_date").text
    val description = (node \ "description").text
    //TODO add the missing fields
    BookUnit(id, author, title, genre, price, publish_date, description)
  }

  val bookUnits = (for (book <- books) yield getBook(book)).toArray

  println(bookUnits.head)
  println(bookUnits.last)

  val genres = bookUnits.map(_.genre).distinct
  println(genres.mkString(","))

  //TODO find the 5 cheapest expensive books

  println("5 most expensive books")
  val prices = bookUnits.map(_.price)
  val expBooks = bookUnits.sortBy(_.price)
  expBooks.reverse.take(5).foreach(println)

  //TODO find the 5 most expensive books

  println("5 most cheapest books")
  val pricesLow = bookUnits.map(_.price)
  val chepBooks = bookUnits.sortBy(_.price)
  chepBooks.take(5).foreach(println)


  //TODO find all romance books

  println("all romance books")
  val genresmap = bookUnits.map(_.genre)
  val onlyRomance = bookUnits.filter(_.genre == "romance")
  println(onlyRomance.mkString(","))


  //TODO get all distinct authors in alphabetical order

  println("Authors in alphabetic order")
  val myauthors = bookUnits.map(_.author)
  val alphabeticAuthors = bookUnits.sortBy(_.author)
  println(alphabeticAuthors.mkString(","))
  println("Authors in alphabetic order2")
  alphabeticAuthors.foreach(println)

}




