package starter.petstore;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.steps.UIInteractions;
import static net.serenitybdd.rest.SerenityRest.*;
import org.hamcrest.Matchers;
import io.restassured.response.Response;

public class PetApiActions extends UIInteractions {
    //extend UIInteractions class that comes with the Serenity BDD to help us interact with APIs
    RequestSpecification res;
    Response ResponseBody, ResponseTest;
    TestDataBuild bodyData = new TestDataBuild();

    @Given("Kitty is available in the pet store")
    public Long givenKittyIsAvailableInPetStore() {
        Pet pet = new Pet("Kitty", "available");

        Long newId = given()
                .baseUri("https://petstore.swagger.io")
                .basePath("/v2/pet")
                .body(pet, ObjectMapperType.GSON)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON).post().getBody().as(Pet.class, ObjectMapperType.GSON).getId();
        return newId;
    }

    @When("I ask for a pet using Kitty's ID: {0}")
    public void whenIAskForAPetWithId(Long id) {
        when().get("/" + id);
    }

    @Then("I get Kitty as result")
    public void thenISeeKittyAsResult() {
        then().body("name", Matchers.equalTo("Kitty"));
    }

    @Then("I get Kitty as result")
    public void thenISeeKittyAsResult2() {
        then().log().all().assertThat().statusCode(200);
    }

    @Given("Rahul test post api")
    public void givenRahulPostMethod(){
        /*
        given().baseUri("https://rahulshettyacademy.com").basePath("/maps/api/place/add/json").queryParam("key", "qaclick123")
                .body(bodyData.addPlacePayload("AAhouse", "English", "World cross center")).accept(ContentType.JSON)
                .contentType(ContentType.JSON).post();

        then().assertThat().statusCode(200);
        */

        // res = given().spec(requestSpecsBuilder()).body(bodyData.addPlacePayload(name, language, address));
        res = given().baseUri("https://rahulshettyacademy.com").basePath("/maps/api/place/add/json").queryParam("key", "qaclick123")
                .body(bodyData.addPlacePayload("AAhouse", "English", "World cross center")).accept(ContentType.JSON)
                .contentType(ContentType.JSON);

        ResponseBody = res.when().log().all().post();

        System.out.println(ResponseBody.getStatusCode());
        System.out.println(getJsonpath(ResponseBody, "status"));
        System.out.println(getJsonpath(ResponseBody, "place_id"));
        //System.out.println(ResponseBody);
    }

    public String getJsonpath(Response responseBod, String key) {
        // Returns specific key value of JSON response body
        String ResponseStr = responseBod.asString();
        // parse JSON string or file
        JsonPath jp = new JsonPath(ResponseStr);
        return jp.get(key).toString();
    }

}
