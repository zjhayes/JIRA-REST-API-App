package com.hayes.app;

import org.joda.time.DateTime;

public class IssuePOJO
{
	private String projectKey;
	private String summary;
	private String issueType;
	private String description;
	private DateTime dueDate;

	// Getters and Setters
	public String getProjectKey()
	{
		return projectKey;
	}
	public void setProjectKey(String projectKey)
	{
		this.projectKey = projectKey;
	}
	public String getSummary()
	{
		return summary;
	}
	public void setSummary(String summary)
	{
		this.summary = summary;
	}
	public String getIssueType()
	{
		return issueType;
	}
	public void setIssueType(String issueType)
	{
		this.issueType = issueType;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public DateTime getDueDate()
	{
		return dueDate;
	}
	public void setDueDate(DateTime dueDate)
	{
		this.dueDate = dueDate;
	}
	public String getJsonString()
	{
		return "{"
				+ "\"fields\": {"
				+ "\"project\":"
				+ "{"
				+    "\"key\":" + " \"" + projectKey + "\""
				+ "},"
				+ "\"summary\":" + " \"" + summary +"\","
				+ "\"description\":" + " \"" + description + "\","
				+ "\"issuetype\": {"
				+ "\"name\":" + " \"" + issueType + "\""
				+ "},"
				+ "\"duedate\":" + " \"" + dueDate.toString("yyyy-MM-dd") + "\""
				+ "}"
				+ "}";
	}
}
