package com.sandalen.testapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sandalen.testapplication.R;

public class ListFragment extends Fragment {
    public static final String BUNDLE_TITLE = "bundle_title";
    String mtilte;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            mtilte = getArguments().getString(BUNDLE_TITLE);
        }
    }

    public static ListFragment newInstance(String title){
        ListFragment listFragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE,title);
        listFragment.setArguments(bundle);

        return listFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(mtilte);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onTitleClickListenr != null){
                    onTitleClickListenr.onClick(mtilte);
                }
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private OnTitleClickListenr onTitleClickListenr;

    public void setOnTitleClickListenr(OnTitleClickListenr onTitleClickListenr) {
        this.onTitleClickListenr = onTitleClickListenr;
    }

    public interface OnTitleClickListenr{
        void onClick(String title);
    }
}
