package com.example.yummy.ui.View.Startup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yummy.R;
import com.example.yummy.ui.Utils.Common;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetStartedFragment extends Fragment implements View.OnClickListener {

    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_started, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        Common.setStatusBarColor(getActivity(), ContextCompat.getColor(getActivity(), R.color.white), true);

        view.findViewById(R.id.btn_sign_in).setOnClickListener(this);
        view.findViewById(R.id.btn_sign_up).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_up:
                navController.navigate(R.id.action_getStartedFragment_to_signUpFragment);
                break;
            case R.id.btn_sign_in:
                navController.navigate(R.id.action_getStartedFragment_to_signInFragment);
                break;
        }
    }
}
