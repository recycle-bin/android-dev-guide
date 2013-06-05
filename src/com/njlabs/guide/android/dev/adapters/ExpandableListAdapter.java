package com.njlabs.guide.android.dev.adapters;

import java.util.List;
import java.util.Map;
  
import com.njlabs.guide.android.dev.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class ExpandableListAdapter extends BaseExpandableListAdapter {
 
    private Activity context;
    private Map<String, List<String>> mainCategories;
    private List<String> subCategories;
 
    public ExpandableListAdapter(Activity context, List<String> subCategories,
            Map<String, List<String>> mainCategories) {
        this.context = context;
        this.mainCategories = mainCategories;
        this.subCategories = subCategories;
    }
 
    public Object getChild(int groupPosition, int childPosition) {
        return mainCategories.get(subCategories.get(groupPosition)).get(childPosition);
    }
 
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    public View getChildView(final int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
        final String sub_category = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();
 
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_item, null);
        }
 
        TextView item = (TextView) convertView.findViewById(R.id.sub_category);
 
       item.setText(sub_category);
        return convertView;
    }
 
    public int getChildrenCount(int groupPosition) {
        return mainCategories.get(subCategories.get(groupPosition)).size();
    }
 
    public Object getGroup(int groupPosition) {
        return subCategories.get(groupPosition);
    }
 
    public int getGroupCount() {
        return subCategories.size();
    }
 
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        String subCategoryText = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.group_item,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.sub_category);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(subCategoryText);
		ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        if (subCategoryText.equals("Basics")) {
        	imageView.setImageResource(R.drawable.about_android);
        } else if (subCategoryText.equals("Initial Setup"))
        	imageView.setImageResource(R.drawable.setup);
        else if (subCategoryText.equals("User Interface"))
        	imageView.setImageResource(R.drawable.activity);
        else if (subCategoryText.equals("Threading"))
        	imageView.setImageResource(R.drawable.services);
        else if (subCategoryText.equals("Intents and types"))
        	imageView.setImageResource(R.drawable.intent);
        else if (subCategoryText.equals("Android System APIs"))
        	imageView.setImageResource(R.drawable.api);
        else
        	imageView.setImageResource(R.drawable.ndk);

        
        return convertView;
    }
 
    public boolean hasStableIds() {
        return true;
    }
 
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}