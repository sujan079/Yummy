package com.example.yummy.ui.view.Startup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.yummy.R;
import com.example.yummy.data.model.OnBoardModel;
import com.example.yummy.ui.adapter.OnBoardAdapter;
import com.example.yummy.ui.utils.Common;

import java.util.ArrayList;


public class OnBoardFragment extends Fragment implements View.OnClickListener {

    private NavController navController;

    private OnBoardAdapter onBoardAdapter;
    private ViewPager2 onBoardViewPager;
    private LinearLayout indicatorLinearLayout;

    private Button onBoardButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Common.setStatusBarColor(getActivity(), ContextCompat.getColor(getActivity(), R.color.white), true);

        onBoardButton = view.findViewById(R.id.btn_onboard);

        navController = Navigation.findNavController(view);


        setOnBoardData(view, OnBoardModel.getOnBoardItems());
        setOnBoardIndicator(view);
        setCurrentIndicator(0);


        onBoardViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });

        view.findViewById(R.id.btn_skip).setOnClickListener(this);
        onBoardButton.setOnClickListener(this);


    }

    public void setOnBoardData(View view, ArrayList<OnBoardModel> onBoardModelItems) {

        onBoardViewPager = view.findViewById(R.id.on_board_view_pager);
        onBoardAdapter = new OnBoardAdapter(onBoardModelItems);
        onBoardAdapter.setOnBoardModels(onBoardModelItems);
        onBoardViewPager.setAdapter(onBoardAdapter);
    }

    public void setOnBoardIndicator(View view) {
        indicatorLinearLayout = view.findViewById(R.id.onboard_indicator);
        ImageView[] indicators = new ImageView[onBoardAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );

        layoutParams.setMargins(8, 0, 8, 0);

        for (int i = 0; i < indicators.length; i++) {

            indicators[i] = new ImageView(getContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getContext(),
                    R.drawable.onboar_indicator_inactive
            ));

            indicators[i].setLayoutParams(layoutParams);
            indicatorLinearLayout.addView(indicators[i]);
        }

    }

    public void setCurrentIndicator(int index) {
        int childCount = indicatorLinearLayout.getChildCount();

        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) indicatorLinearLayout.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getContext(), R.drawable.onboard_indicator_active)
                );
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getContext(), R.drawable.onboar_indicator_inactive)
                );
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.btn_onboard):
                if (onBoardViewPager.getCurrentItem() == onBoardAdapter.getItemCount() - 1) {
                    navController.navigate(R.id.action_onBoardFragment_to_getStartedFragment);
                } else {
                    onBoardViewPager.setCurrentItem(onBoardViewPager.getCurrentItem() + 1);

                }
                break;
            case R.id.btn_skip:
                navController.navigate(R.id.action_onBoardFragment_to_getStartedFragment);
                break;
        }
    }

}
