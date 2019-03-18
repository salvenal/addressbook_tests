package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitHubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("e4c492731d91d40c15f081c9d3d61e63d2065322");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("salvenal", "addressbook_tests")).commits();
    for (RepoCommit commit: commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }

  }
}
