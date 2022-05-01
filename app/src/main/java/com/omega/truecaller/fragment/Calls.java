package com.omega.truecaller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.omega.truecaller.adapter.LogCallAdapterHorizontal;
import com.omega.truecaller.adapter.LogCallAdapterVertiacal;
import com.omega.truecaller.callbacks.GetListLogCall;
import com.omega.truecaller.callbacks.OnClickCall;
import com.omega.truecaller.data.model.Call;
import com.omega.truecaller.databinding.FragmentCallsBinding;
import com.omega.truecaller.presenter.CallPresenter;

import java.util.ArrayList;

public class Calls extends Fragment implements GetListLogCall, OnClickCall {
    private CallPresenter mPresenter;
    private LogCallAdapterVertiacal mLogCallAdapter;
    private LogCallAdapterHorizontal mOftenCallAdapter;
    private FragmentCallsBinding mBinding;

    public Calls() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentCallsBinding.inflate(inflater, container, false);
        initData();
        initView();
        return mBinding.getRoot();
    }


    private void initView() {
        if (mPresenter.getPermission(getContext(), getActivity())) {//check quyền lấy nhật ký gọi
            mPresenter.getCallLog();//Lấy danh sách nhật ký gọi
            mPresenter.getListOftenCall();//lấy danh sách các số hay liên lạc
        }
    }

    private void initData() {
        mPresenter = new CallPresenter(getActivity(), this);//khởi tạo presenter fragment call
    }

    @Override
    public void getListLogCall(ArrayList<Call> listLogCall) {
        mLogCallAdapter = new LogCallAdapterVertiacal(this, getContext(), listLogCall);
        mBinding.rvContactAll.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvContactAll.setAdapter(mLogCallAdapter);
    }

    @Override
    public void getListOftenCall(ArrayList<Call> listOftenCall) {
        mOftenCallAdapter = new LogCallAdapterHorizontal(getContext(), listOftenCall);
        mBinding.rvContactRecent.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mBinding.rvContactRecent.setAdapter(mOftenCallAdapter);
    }


    @Override
    public void clickCall(Call call) {
        Toast.makeText(getActivity(), "CALL", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clickCallDetail(Call call) {
        Toast.makeText(getActivity(), "DETAIL", Toast.LENGTH_SHORT).show();
    }
}