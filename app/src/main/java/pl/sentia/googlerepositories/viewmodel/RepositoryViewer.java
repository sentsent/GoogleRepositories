package pl.sentia.googlerepositories.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.view.View;
import android.widget.TextView;
import com.google.gson.GsonBuilder;
import java.util.List;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import pl.sentia.googlerepositories.BR;
import pl.sentia.googlerepositories.R;
import pl.sentia.googlerepositories.model.Repository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static pl.sentia.googlerepositories.MainActivity.MAIN_ACTIVITY;

public class RepositoryViewer extends BaseObservable {

    public static final String API_URL = "https://api.github.com/";
    public final ObservableList<Repository> repositories = new ObservableArrayList<>();
    public final ItemBinding<Repository> repositoriesBinding = ItemBinding.of(BR.repository, R.layout.repository_layout);
    public boolean loadingRepositoriesProgressBarVisible;
    public boolean repositoryDetailsVisible;

    public void loadRepositories(String username) {
        setLoadingRepositoriesProgressBarVisible(true);
        setRepositoryDetailsVisible(false);
        GitHubService service = initGitHubService();
        runRepositoriesLoadingRequest(username, service);
    }

    public void showRepositoryDetails(View v) {
        TextView repositoryTitle = (TextView) v.findViewById(R.id.title);
        Repository repository = getRepository(repositoryTitle.getText().toString());
        MAIN_ACTIVITY.bindToRepositoryDetails(repository);
        setRepositoryDetailsVisible(true);
    }

    @Bindable
    public boolean isRepositoryDetailsVisible() {
        return repositoryDetailsVisible;
    }

    public void setRepositoryDetailsVisible(boolean repositoryDetailsVisible) {
        this.repositoryDetailsVisible = repositoryDetailsVisible;
        notifyPropertyChanged(BR.repositoryDetailsVisible);
    }

    @Bindable
    public boolean isLoadingRepositoriesProgressBarVisible() {
        return loadingRepositoriesProgressBarVisible;
    }

    public void setLoadingRepositoriesProgressBarVisible(boolean visibility) {
        loadingRepositoriesProgressBarVisible = visibility;
        notifyPropertyChanged(BR.loadingRepositoriesProgressBarVisible);
    }

    private Repository getRepository(String title) {
        if (repositories != null) {
            for (Repository repo: repositories) {
                if (repo.getTitle().equals(title)) {
                    return repo;
                }
            }
        }
        return null;
    }

    private void runRepositoriesLoadingRequest(String username, GitHubService service) {
        service.listRepositories(username).enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                if(response.isSuccessful()) {
                    setLoadingRepositoriesProgressBarVisible(false);
                    repositories.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {

            }
        });
    }

    private GitHubService initGitHubService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();

        return retrofit.create(GitHubService.class);
    }

}
