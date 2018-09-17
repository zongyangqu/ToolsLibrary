package qzy.com.utilslib.appupdate;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import qzy.com.utilslib.R;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2018/09/17
 * desc   :  更新弹窗
 * version: 1.0
 */


public class UpdateConfirmDialog extends Dialog {

    UpdateCallback callback;
    boolean forceUpdate;
    private TextView content;
    private TextView sureBtn;
    private TextView cancleBtn;

    public UpdateConfirmDialog(Context context, boolean forceUpdate,UpdateCallback callback) {
        super(context, R.style.UpdateCustomDialog);
        this.callback = callback;
        this.forceUpdate = forceUpdate;
        setCustomDialog();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_confirm, null);
        sureBtn = (TextView)mView.findViewById(R.id.dialog_confirm_sure);
        cancleBtn = (TextView)mView.findViewById(R.id.dialog_confirm_cancle);
        content = (TextView) mView.findViewById(R.id.dialog_confirm_title);


        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.callback(1);
                UpdateConfirmDialog.this.cancel();
            }
        });
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!forceUpdate){
                    callback.callback(0);
                    UpdateConfirmDialog.this.cancel();
                }
            }
        });
        super.setContentView(mView);
    }


    public UpdateConfirmDialog setContent(String s){
        content.setText(s);
        return this;
    }


}
