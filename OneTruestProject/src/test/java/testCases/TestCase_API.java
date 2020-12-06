package testCases;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import prerequisite.Prerequisite;
import resources.Utils;

public class TestCase_API extends Utils {

	int PostId;
	@Test(priority=1)
	public void countPostsForSpecificUserId() throws IOException
	{
		RestAssured.baseURI = getGlobalValues();
		String response = Prerequisite.getPetPreRequisite().when().get(getpostsResources()).then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js = getJsonPath(response);
		List<Integer> al = js.getList("userId");
		int k=0;
		for(int i=0; i<al.size(); i++)
		{
			int val = al.get(i);
			
			if(val==9)
			{
				k++;
			}
		
		}
		System.out.println("Number of posts user id '9' has published: "+k);
		
	}
	
	@Test(priority=2)
	public void getPostIdFromComments()
	{
		String response = Prerequisite.getPetPreRequisite().when().get(getcommentsResources()).then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js = getJsonPath(response);
		List<Integer> al = js.getList("postId");
		PostId = 0;
		for(int i=0; i<al.size(); i++)
		{
			int val = al.get(i);
			if(val==3)
			{
				PostId = al.get(i);
				break;
			}
		}
		System.out.println("Get a specific postId from Comments: "+PostId);

	}
	
	@Test(priority=3)
	public void countCommentsForSpecificPostId()
	{
		String response = Prerequisite.getPetPreRequisite().pathParam("postId", PostId).when().get(getcommentsSpecificPosts()).then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js = getJsonPath(response);
		List<Integer> al = js.getList("body");
		System.out.println("Number of comments user id '3' has got for all his posts: "+al.size());
	}
	
	@Test(priority=4)
	public void getAllUsers()
	{
		String response = Prerequisite.getPetPreRequisite().when().get(getusersResource()).then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(response);
	}
}
