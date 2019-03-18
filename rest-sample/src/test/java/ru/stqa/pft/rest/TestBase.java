package ru.stqa.pft.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

  @BeforeClass
  public void init() {
    RestAssured.authentication = RestAssured
            .basic("28accbe43ea112d9feb328d2c00b3eed", "");
  }

  public boolean isIssueOpen(int issueId) {
    String json = RestAssured
            .get("http://demo.bugify.com/api/issues/" + issueId + ".json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    String status = parsed.getAsJsonObject().get("status").toString();
    if (status.equals("resolved") || status.equals("closed")) {
      return false;
    } else {
      return true;
    }
  }

  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
