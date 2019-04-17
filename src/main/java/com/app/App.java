package com.hayes.app;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.naming.AuthenticationException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

/**
 * Zachary Hayes
 * Mumo project
 */
public class App 
{
    private static final String BASEURL = "";  // ENTER BASE URL
    private static String projectUrl = BASEURL + "/rest/api/2/project";
    private static String issueUrl = BASEURL + "/rest/api/2/issue";
    private static final String USER = "";  // ENTER JIRA USERNAME
    private static final String PASSWORD = ""; // ENTER PASSWORD
    private static String auth = new String(Base64.encode(USER + ":" + PASSWORD));

    public static void main(String[] args ) throws Exception
    {
        System.out.println( "Running... " );

        getProjects();
        createIssue();

        System.out.print("Finished.");
    }

    @GET
    @Produces("application/json")
    public static void getProjects() throws AuthenticationException
    {
        ClientResponse response = buildClient(projectUrl, auth).get(ClientResponse.class);
        checkResponseStatus(response);

        String jsonResponse = response.getEntity(String.class);

        JSONArray projectArray = new JSONArray(jsonResponse);
        for (int i = 0; i < projectArray.length(); i++) {
            JSONObject proj = projectArray.getJSONObject(i);
            System.out.println("Key:"+proj.getString("key")+", Name:"+proj.getString("name"));
        }
    }

    @POST
    @Produces("application/json")
    public static void createIssue() throws AuthenticationException
    {
        String issueJson = setupIssue().getJsonString();

        ClientResponse response = buildClient(issueUrl, auth).post(ClientResponse.class, issueJson);
        checkResponseStatus(response);

        System.out.println(response.getEntity(String.class));
    }

    private static WebResource.Builder buildClient(String url, String auth)
    {
        Client client = Client.create();
        WebResource webResource = client.resource(url);

        return webResource.header("Authorization", "Basic " + auth)
                .type("application/json")
                .accept("application/json");
    }

    private static void checkResponseStatus(ClientResponse response) throws AuthenticationException {
        int statusCode = response.getStatus();
        if (statusCode == 401) {
            throw new AuthenticationException("Invalid Username or Password");
        }
    }
    
    private static IssuePOJO setupIssue()
    {
        IssuePOJO issuePreferences = new IssuePOJO();
        issuePreferences.setProjectKey("TEST");
        issuePreferences.setSummary("REST Test 10");
        issuePreferences.setIssueType("Task");
        issuePreferences.setDescription("An issue created from the Java app.");
        issuePreferences.setDueDate(new DateTime(2018, 11, 19, 12, 00));
        
        return issuePreferences;
    }
}
