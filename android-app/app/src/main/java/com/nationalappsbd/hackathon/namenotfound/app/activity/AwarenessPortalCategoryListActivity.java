package com.nationalappsbd.hackathon.namenotfound.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.inject.Inject;
import com.nationalappsbd.hackathon.namenotfound.app.R;
import com.nationalappsbd.hackathon.namenotfound.app.domain.AwarenessCategory;
import com.nationalappsbd.hackathon.namenotfound.app.service.CommonService;
import com.nationalappsbd.hackathon.namenotfound.app.util.view.adapter.ViewHolder;
import com.oneous.log4android.Logger;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mushfekur Rahman
 */
@ContentView(R.layout.list)
public class AwarenessPortalCategoryListActivity extends RoboListActivity {

    private static final Logger log = Logger.getLogger(AwarenessPortalCategoryListActivity.class);

    private static final String EXTRA_AWARENESS_CATEGORY_ID = "awareness_category_id";

    @Inject
    private LayoutInflater inflater;

    @Inject
    private CommonService commonService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new AwarenessPortalCategoryListAdapter(commonService.getCategories()));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, AwarenessPortalThreadListActivity.class);
        intent.putExtra(EXTRA_AWARENESS_CATEGORY_ID, ((AwarenessCategory) getListAdapter().getItem(position)).getId());

        startActivity(intent);
    }

    private class AwarenessPortalCategoryListAdapter extends ArrayAdapter<AwarenessCategory> {

        public AwarenessPortalCategoryListAdapter(List<AwarenessCategory> categoryList) {
            super(AwarenessPortalCategoryListActivity.this, 0, categoryList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.awareness_category_list_item, parent, false);
            }

            AwarenessCategory category = getItem(position);

            TextView awarenessCategoryView = ViewHolder.get(convertView, R.id.category_title);
            awarenessCategoryView.setText(category.getTitle());

            return convertView;
        }
    }
}
