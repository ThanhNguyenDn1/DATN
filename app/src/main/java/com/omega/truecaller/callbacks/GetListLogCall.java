package com.omega.truecaller.callbacks;

import com.omega.truecaller.data.model.Call;

import java.util.ArrayList;

public interface GetListLogCall {
    public void getListLogCall(ArrayList<Call> listLogCall);

    void getListOftenCall(ArrayList<Call> listOftenCall);
}
