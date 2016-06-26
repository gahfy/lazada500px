package net.gahfy.lazada500px.ui.category.view;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import net.gahfy.lazada500px.R;
import net.gahfy.lazada500px.data.model.Category;
import net.gahfy.lazada500px.ui.base.BaseActivity;
import net.gahfy.lazada500px.ui.category.CategoryListAdapter;
import net.gahfy.lazada500px.ui.category.presenter.CategoryListPresenter;

import java.util.Collection;

import javax.inject.Inject;

/**
 * The Activity with the list of Categories.
 * @author Gaëtan HERFRAY
 */
public class CategoryListActivity extends BaseActivity implements CategoryListView {
    /** The RecyclerView with the list of categories */
    RecyclerView rvCategoryList;

    /** The adapter of the RecyclerView */
    @Inject
    CategoryListAdapter categoryItemAdapter;

    /** The presenter of the Activity */
    @Inject
    CategoryListPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category_list);

        rvCategoryList = (RecyclerView) findViewById(R.id.rv_category_list);
        rvCategoryList.setHasFixedSize(true);
        rvCategoryList.setLayoutManager(new LinearLayoutManager(this));
        rvCategoryList.setAdapter(categoryItemAdapter);

        // Show Snackbar for optional permission if not granted
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                showPermissionSnackbar();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.bindView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        presenter.unbindView();
    }

    @Override
    public void showCategories(Collection<Category> categories) {
        categoryItemAdapter.clearAndAddAll(categories);
    }

    @Override @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void showPermissionSnackbar(){
        Snackbar snackbar = Snackbar.make(
                findViewById(R.id.lyt_category_list_container),
                R.string.permission_notice,
                Snackbar.LENGTH_LONG
        )
        .setAction(R.string.grant, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(CategoryListActivity.this,
                        new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                        },
                        1);
            }
        });

        View view = snackbar.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(ContextCompat.getColor(this, R.color.txt_default_white));

        snackbar.show();
    }
}
