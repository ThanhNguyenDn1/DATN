package com.omega.truecaller.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.CallLog;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.omega.truecaller.R;
import com.omega.truecaller.callbacks.GetListLogCall;
import com.omega.truecaller.data.model.Call;
import com.omega.truecaller.utils.Utils;

import java.util.ArrayList;
import java.util.Date;

public class CallPresenter {
    private ArrayList<Call> listLogCall, listOftenCall;


    private static final int REQUEST_CODE_CALL_LOG_PERMISSION = 1;
    private Activity activity;
    private GetListLogCall getListLogCall;

    public CallPresenter(Activity activity, GetListLogCall getListLogCall) {
        this.activity = activity;
        this.getListLogCall = getListLogCall;
    }

    public void getListOftenCall() {
        listOftenCall = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            listOftenCall.add(new Call("Thanh", "123456789", "MISSED", "0000"));//tạo dũ liệu fake những số thường xuyên liên lạc

        //viết code lấy danh sách các số thường xuyên liên lạc ở đây và cmt lại vòng for tạo dữ liệu fake ở trên
        getListLogCall.getListOftenCall(listOftenCall);//truyên list lấy được vào hàm này

    }

    public void getCallLog() {
        GetListCallLog getListCallLog = new GetListCallLog();
        getListCallLog.execute();
    }

    class GetListCallLog extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            getCallLogs();//lấy nhật ký điện thoại
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            getListLogCall.getListLogCall(listLogCall);//update ui sau khi lấy list nhật ký thành công

        }
    }

    public boolean getPermission(Context myContext, Activity myActivity) {

        try {
            int writeExternalStoragePermission = ContextCompat.checkSelfPermission(myContext, Manifest.permission.READ_CALL_LOG);
            if (writeExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(myActivity, new String[]{
                        Manifest.permission.READ_CALL_LOG
                }, REQUEST_CODE_CALL_LOG_PERMISSION);
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
                    try {
                        Intent intent = new Intent();
                        Uri uri = Uri.fromParts("package", myActivity.getPackageName(), null);
                        intent.setData(uri);
                        myActivity.startActivityForResult(intent, REQUEST_CODE_CALL_LOG_PERMISSION);
                        return false;
                    } catch (Exception e) {//if anything needs adjusting it would be this
                    }
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } catch (Exception ex) {
        }
        return false;
    }


    private void getCallLogs() {
        listLogCall = new ArrayList<Call>();
        listLogCall.clear();
        Cursor managedCursor = activity.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null, null, null, null);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        int name = managedCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        while (managedCursor.moveToNext() && listLogCall.size() < 100) {
            String phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            String nameCall = managedCursor.getString(name);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);
            String dir = null;
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = activity.getString(R.string.call_out_going);
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir = activity.getString(R.string.call_in_coming);
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    dir = activity.getString(R.string.call_missed);
                    break;
            }

            listLogCall.add(new Call(nameCall, phNumber, dir, Utils.covertTimestampToHours(callDayTime.getTime())));
    /*        calllogsBuffer.add("\nPhone Number: " + phNumber + " \nCall Type: "
                    + dir + " \nCall Date: " + callDayTime
                    + " \nCall duration in sec : " + callDuration + "\n");*/

        }
        managedCursor.close();
    }

}
