package com.lcfarm.android.find;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lcfarm.android.base.constants.ARouterConstant;
import com.lcfarm.android.base.utils.ARouterUtil;

@Route(path = ARouterConstant.FRAGMENT_FIND)
public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button goActivity = root.findViewById(R.id.goActivity);
        goActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //使用 ARouter无法在 fragment直接接收返回值，只能在所在 activity 中接收
//                ARouterUtil.navigation(ARouterConstant.ACTIVITY_FIND_SECOND, getActivity(), 1001);
//                ARouter.getInstance().build(ARouterConstant.ACTIVITY_FIND_SECOND).navigation();
                startActivityForResult(new Intent(getActivity(),FindSecondActivity.class),1001);
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("code=" + resultCode + ",requestCode=" + requestCode);
        if (resultCode == getActivity().RESULT_OK && requestCode == 1001) {

        }
    }
}