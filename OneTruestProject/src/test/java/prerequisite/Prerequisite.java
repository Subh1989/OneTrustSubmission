package prerequisite;

import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class Prerequisite {

	public static RequestSpecification getPetPreRequisite()
	{
		RequestSpecification rs = given().header("Content-Type", "application/json");
		return rs;
	}
}
