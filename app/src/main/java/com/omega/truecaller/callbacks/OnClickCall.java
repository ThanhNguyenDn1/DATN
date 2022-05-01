package com.omega.truecaller.callbacks;

import com.omega.truecaller.data.model.Call;

public interface OnClickCall {
    void clickCall(Call call);

    void clickCallDetail(Call call);
}
