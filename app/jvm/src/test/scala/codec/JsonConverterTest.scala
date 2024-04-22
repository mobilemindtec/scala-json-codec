package codec

import br.com.mobilemind.json.codec.infra.annotations.JsonValue
import br.com.mobilemind.json.codec.converter.base
import br.com.mobilemind.json.codec.converter.base.JsonCreator
import br.com.mobilemind.json.codec.converter.auto.JsonConverter
import br.com.mobilemind.json.codec.defs
import org.scalatest.funsuite.AnyFunSuite

class JsonConverterTest extends AnyFunSuite:

  given JsonCreator with
    override def empty: base.JsonObject = new defs.JsonObject()

  test("native converter test") {

    case class Group(
        @JsonValue id: Int = 0,
        @JsonValue description: String = ""
    ) derives JsonConverter

    case class Person(
        @JsonValue id: Int = 0,
        @JsonValue name: String,
        @JsonValue(omitNull = true) group: Group = Group(10, "G10"),
        @JsonValue(omitNull = true) g: Option[Group] = Some(Group(20, "G20"))
    ) derives JsonConverter

    val person = Person(name = "Ricardo")
    val json = person.toJson
    println(json)
    val p = JsonConverter[Person].fromJson(json)
    println(p)

  }
