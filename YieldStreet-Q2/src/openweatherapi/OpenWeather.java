package openweatherapi;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;

public class OpenWeather {

	final static String API = "https://api.openweathermap.org/data/2.5";
	final static String APIID = "e294642d895319e7b51ef4bd760ac02a";
	final static String city ="london";
	final static int id = 2643743;
	final static double lon = -0.13;
	final static double lat = 51.51;
	static Response baseCaseResp;
	
	@Test
	public static void test(){
		String req = API+"/weather?q="+city+"&appid="+APIID;
		RestAssured.defaultParser = Parser.JSON;
		Response response = RestAssured.get(req).then().contentType(ContentType.JSON).extract().response();
		//Verify HTTP Reponse Status Code
		Assert.assertEquals(response.getStatusCode(),200) ;
		//Verify Response for the ID
		Assert.assertEquals(response.jsonPath().getInt("id"), id);
		//Verify Coords
		Assert.assertEquals(response.jsonPath().getDouble("coord.lon"), lon);
		Assert.assertEquals(response.jsonPath().getDouble("coord.lat"), lat);
	}
}

