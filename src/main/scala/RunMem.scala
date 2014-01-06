import scala.util.Random
object World {
  val random = new Random()
  def rangeFrom(low: Int, high: Int) =
    random.nextInt(1 + (high - low)) + low

  def oneIn(num: Int, obj:Any):Option[Any] =
    if (random.nextInt(num) == 0)
      Some(obj)
    else
      None

  class Store {

  }


  class Person {

  }

  class Dog {

  }

  class Fire {


  }

  def yesNo = random.nextBoolean

  class Window {

  }
  class Wall(building: Building) {
    val windowCount = rangeFrom(2, 4)
    val fireEscape = yesNo
    def buildWindows():List[Window] = (for {
      i <- 1 to windowCount
    } yield new Window()).toList

    val store = oneIn(4, new Store())
    val windows:List[Either[Option[Any], List[Window]]] = (for {
      story <- 1 to building.stories
    } yield {
      if (story == 1 && store != None)
        Left(store)
      else
        Right(buildWindows)
      }).toList
    override def toString = "Windows: " + windowCount + " || Fire Escape: " + fireEscape
  }

  class Building(val number: Int) {
    val stories = rangeFrom(3, 6)
    val front = new Wall(this)
    val side = new Wall(this)
    override def toString:String = "Building number " + number + "\nNumber of stories: " + stories + "\nSide: " + side + "\nFront: " + front
  }

  class Scene(buildingCount: Int = 3) {
    val buildings: List[Building] = (for {
      i <- 1 to buildingCount
    } yield (new Building(i))).toList


    override def toString = buildings mkString "\n\n"
  }
}

object RunMem {
  def main(args: Array[String]) = print(new World.Scene())
}
