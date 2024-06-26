import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
object App {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[App], args: _*)
  }

}
