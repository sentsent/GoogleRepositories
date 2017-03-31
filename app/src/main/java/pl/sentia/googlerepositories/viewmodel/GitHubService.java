package pl.sentia.googlerepositories.viewmodel;

import java.util.List;

import pl.sentia.googlerepositories.model.GitHubRepository;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {

    @GET("users/{user}/repos")
    Call<List<GitHubRepository>> listRepositories(@Path("user") String user);

}
