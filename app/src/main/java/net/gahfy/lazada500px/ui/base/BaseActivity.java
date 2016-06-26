package net.gahfy.lazada500px.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import net.gahfy.lazada500px.injection.module.ActivityModule;

import dagger.ObjectGraph;

/**
 * Base Activity which all activities of the application must extend.
 * @author Gaëtan HERFRAY
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ObjectGraph objectGraph = ObjectGraph.create(new ActivityModule(this));
        objectGraph.inject(this);
    }
}
