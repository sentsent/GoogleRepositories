package pl.sentia.googlerepositories;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import pl.sentia.googlerepositories.databinding.ActivityMainBinding;
import pl.sentia.googlerepositories.databinding.RepositoryDetailsLayoutBinding;
import pl.sentia.googlerepositories.model.GitHubRepository;
import pl.sentia.googlerepositories.viewmodel.RepositoryViewer;

public class MainActivity extends AppCompatActivity {

    public static final String GITHUB_USERNAME = "google";
    public static MainActivity MAIN_ACTIVITY;
    public RepositoryViewer repositoryViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MAIN_ACTIVITY = this;
        repositoryViewer = new RepositoryViewer();
        bindToMainActivity(repositoryViewer);
        repositoryViewer.loadRepositories(GITHUB_USERNAME);
    }

    public static void bindToMainActivity(RepositoryViewer repositoryViewer) {
        ActivityMainBinding mainActivityBinding = DataBindingUtil.setContentView(MAIN_ACTIVITY, R.layout.activity_main);
        mainActivityBinding.setRepositoryViewer(repositoryViewer);
    }

    public static void bindToRepositoryDetails(GitHubRepository repository) {
        RepositoryDetailsLayoutBinding repositoryDetailsLayoutBinding = DataBindingUtil.setContentView(MAIN_ACTIVITY, R.layout.repository_details_layout);
        repositoryDetailsLayoutBinding.setRepository(repository);
    }

    public static void announce(String message) {
        Toast.makeText(MAIN_ACTIVITY, message, Toast.LENGTH_LONG).show();
    }

    public void onClickRepository(View v) {
        if (repositoryViewer != null) {
            repositoryViewer.showRepositoryDetails(v);
        }
    }

    @Override
    public void onBackPressed() {
        goToMainScreen();
    }

    private void goToMainScreen() {
        if (repositoryViewer != null) {
            bindToMainActivity(repositoryViewer);
            repositoryViewer.setRepositoryDetailsVisible(false);
            repositoryViewer.setLoadingRepositoriesProgressBarVisible(false);
        }
    }
}
