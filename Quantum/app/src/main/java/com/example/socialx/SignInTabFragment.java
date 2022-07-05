package com.example.socialx;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class SignInTabFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
    {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signout_tab_fragment,container,false);
        return root;
    }
}
