package argela;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import sweet_app.GDisplayStatistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;


public class GatherAndDisplayStatistics {
GDisplayStatistics gatherAndDisplayStatistics;
private List<GDisplayStatistics.User>currentUser;
public GatherAndDisplayStatistics(){

    gatherAndDisplayStatistics=GDisplayStatistics.getInstance();

}
    @Given("the following users are registered:")
    public void the_following_users_are_registered(io.cucumber.datatable.DataTable dataTable) {
       List<Map<String,String>>row=dataTable.asMaps(String.class,String.class);
       for (Map<String,String>columns:row){
           String name=columns.get("Name");
           String city=columns.get("City");
           try {
               gatherAndDisplayStatistics.addUser(name,city);
               assertTrue(true);
           }catch (Exception e){
               fail();
           }

       }

    }

    @Then("the statictics should be display as follows:")
    public void the_statictics_should_be_display_as_follows(io.cucumber.datatable.DataTable dataTable) {
       List<Map<String,String>>expectedData=dataTable.asMaps(String.class,String.class);
       Map<String, Integer>expectedStatistics=new HashMap<>();
       for (Map<String,String>row:expectedData){
           String city=row.get("City");
           int count =Integer.parseInt(row.get("Number of users"));
           expectedStatistics.put(city,count);


       }
       Map<String,Integer>actualStatistics=gatherAndDisplayStatistics.gatherStatisticsByCity();
       assertEquals(expectedStatistics,actualStatistics);
    }

}
