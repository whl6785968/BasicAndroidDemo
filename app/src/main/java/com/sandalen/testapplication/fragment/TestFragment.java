package com.sandalen.testapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sandalen.testapplication.R;

public class TestFragment extends Fragment {

    public static final String TITLE = "title";
    private String mtitle;

    public static TestFragment newInstances(String position){
        TestFragment testFragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE,position);
        testFragment.setArguments(bundle);
        return testFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, null);
        TextView textView = view.findViewById(R.id.text_view);
        textView.setText(mtitle);
        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mtitle = getArguments().getString(TITLE);
        }
    }
}
