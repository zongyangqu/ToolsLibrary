package qzy.com.toolslibrary.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import qzy.com.toolslibrary.R;
import qzy.com.utilslib.appupdate.AppUpdateUtils;
import qzy.com.utilslib.appupdate.UpdateCallback;
import qzy.com.utilslib.appupdate.UpdateConfirmDialog;
import qzy.com.utilslib.base.BaseActivity;
import util.UpdateAppUtils;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/5/24
 *
 * 类描述：app更新下载安装
 *
 * github：https://github.com/teprinciple/UpdateAppDemo
 */

public class AppUpdateActivity extends BaseActivity  implements View.OnClickListener {

    //服务器apk path,这里放了百度云盘的apk 作为测试
    String apkPath = "http://issuecdn.baidupcs.com/issue/netdisk/apk/BaiduNetdisk_7.15.1.apk";
    private int code = 0;

    private boolean isForce = true;



    @Override
    protected void onViewCreated() {
    }

    public void updateApp(View view) {
        checkAndUpdate(1);
    }

    public void downloadByWeb(View view) {
        checkAndUpdate(2);
    }


    public void forceUpdate(View view) {
        checkAndUpdate(3);
    }


    public void checkByName(View view) {
        checkAndUpdate(4);
    }

    public void kotlin(View view) {
        checkAndUpdate(5);
    }

    private void checkAndUpdate(int code) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            realUpdate(code);
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                realUpdate(code);
            } else {//申请权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }


    private void realUpdate(int code) {
        this.code = code;
        switch (code) {
            case 1:
                updat1();
                break;
            case 2:
                update2();
                break;
            case 3:
                update3();
                break;
            case 4:
                update4();
                break;
        }
    }


    //基本更新
    private void updat1() {
        /*UpdateAppUtils.from(this)
                .serverVersionCode(2)
                .serverVersionName("2.0")
                .apkPath(apkPath)
                .updateInfo("1.修复若干bug\n2.美化部分页面\n3.增加微信支付方式")
                .isForce(false)
//                .showNotification(false)
//                .needFitAndroidN(false)
                .update();*/


        AppUpdateUtils.from(this)
                .serverVersionCode(2)
                .serverVersionName("2.0")
                .apkPath(apkPath)
                .updateInfo("1.修复若干bug\n2.美化部分页面\n3.增加微信支付方式")
                .isForce(isForce)
//                .showNotification(false)
//                .needFitAndroidN(false)
                .update();
    }

    //通过浏览器下载
    private void update2() {
        AppUpdateUtils.from(this)
                .serverVersionCode(2)
                .serverVersionName("2.0")
                .apkPath(apkPath)
                .downloadBy(AppUpdateUtils.DOWNLOAD_BY_BROWSER)
                .update();
    }

    //强制更新
    private void update3() {
        AppUpdateUtils.from(this)
                .serverVersionCode(2)
                .serverVersionName("2.0")
                .updateInfo("1.修复若干bug\n2.美化部分页面\n3.增加微信支付方式")
                .apkPath(apkPath)
                .isForce(isForce)
                .update();
    }

    //根据versionName判断跟新
    private void update4() {
        AppUpdateUtils.from(this)
                .checkBy(AppUpdateUtils.CHECK_BY_VERSION_NAME)
                .serverVersionName("2.0")
                .serverVersionCode(2)
                .apkPath(apkPath)
                .updateInfo("1.修复若干bug\n2.美化部分页面\n3.增加微信支付方式")
                .downloadBy(AppUpdateUtils.DOWNLOAD_BY_BROWSER)
                .isForce(isForce)
                .update();
    }


    //权限请求结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    realUpdate(code);
                } else {
                    new UpdateConfirmDialog(this, isForce,new UpdateCallback() {
                        @Override
                        public void callback(int position) {
                            if (position==1){
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.parse("package:" + getPackageName())); // 根据包名打开对应的设置界面
                                startActivity(intent);
                            }
                        }
                    }).setContent("暂无读写SD卡权限\n是否前往设置？").show();
                }
                break;
        }

    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_download;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
