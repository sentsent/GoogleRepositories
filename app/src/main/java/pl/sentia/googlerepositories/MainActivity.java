package pl.sentia.googlerepositories;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import pl.sentia.googlerepositories.databinding.ActivityMainBinding;
import pl.sentia.googlerepositories.viewmodel.RepositoryViewer;

public class MainActivity extends AppCompatActivity {

    public static final String GITHUB_USERNAME = "google";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RepositoryViewer repositoryViewer = new RepositoryViewer();
        ActivityMainBinding mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityBinding.setRepositoryViewer(repositoryViewer);

        repositoryViewer.loadRepositories(GITHUB_USERNAME);
    }
}
