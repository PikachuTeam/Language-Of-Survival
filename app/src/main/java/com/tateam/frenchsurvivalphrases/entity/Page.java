package com.tateam.frenchsurvivalphrases.entity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tateam.frenchsurvivalphrases.app.BaseActivity;
import com.tateam.frenchsurvivalphrases.app.BaseFragment;


/**
 * Created by ThanhNH on 9/11/2015.
 */
public abstract class Page {

    protected BaseActivity activity;
    protected  BaseFragment fragment;
    protected View content;

    protected abstract int getContentId();

    public Page(BaseActivity activity) {
        this(activity, null);
    }

    public Page(BaseActivity activity, ViewGroup parent) {
        this.activity = activity;
        if (parent != null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
            content = inflater.inflate(getContentId(), parent, false);
        } else {
            content = View.inflate(activity, getContentId(), null);
        }
    }
    public Page(BaseFragment baseFragment, ViewGroup parent) {
        this.fragment = baseFragment;
        if (parent != null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
            content = inflater.inflate(getContentId(), parent, false);
        } else {
            content = View.inflate(activity, getContentId(), null);
        }
    }

    public View getContent() {
        return content;
    }

    public void destroy() {
    }
}
