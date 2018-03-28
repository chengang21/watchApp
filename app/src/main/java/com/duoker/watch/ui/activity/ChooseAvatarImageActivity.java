package com.duoker.watch.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.duoker.watch.R;
import com.duoker.watch.ui.base.BaseActivity;
import com.duoker.watch.utils.AvatarUploadUtils;
import com.duoker.watch.utils.ImageUtils;
import com.duoker.watch.utils.PathUtils;

import java.io.File;

public class ChooseAvatarImageActivity extends BaseActivity
        implements View.OnClickListener {
    public static final int CAMERA_REQUEST_CODE = 1;
    public static final int CROP_BIG_CODE = 2;
    public static final String FILE_PATH = "FILE_PATH";
    public static final int IMAGE_OPEN_DOCUMENT_CODE = 3;
    public static final int IMAGE_PICK_CODE = 4;
    public static final int IMAGE_REQUEST_CODE = 5;
    public static final String TAG = ChooseAvatarImageActivity.class.getSimpleName();
    private View mChoosePicLayout;
    private String mFilePath;
    private ImageView[] mImageViewArray = new ImageView[8];
    private View mTakePicLayout;

    private void getImageToView() {
        Uri localUri = Uri.fromFile(AvatarUploadUtils.getPortraitFile(getApplicationContext()));
        this.mFilePath = AvatarUploadUtils.saveMyBitmap(getApplicationContext(), localUri);
        Intent localIntent = new Intent();
        localIntent.putExtra("FILE_PATH", this.mFilePath);
        setResult(-1, localIntent);
        finish();
    }

    private void initListener() {
        for (int i = 0; i < 8; i++)
            this.mImageViewArray[i].setOnClickListener(this);
        this.mTakePicLayout.setOnClickListener(this);
        this.mChoosePicLayout.setOnClickListener(this);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View paramAnonymousView) {
                finish();
            }
        });
    }

    private void initView() {
        this.mImageViewArray[0] = ((ImageView) findViewById(R.id.imageview_head_1));
        this.mImageViewArray[1] = ((ImageView) findViewById(R.id.imageview_head_2));
        this.mImageViewArray[2] = ((ImageView) findViewById(R.id.imageview_head_3));
        this.mImageViewArray[3] = ((ImageView) findViewById(R.id.imageview_head_4));
        this.mImageViewArray[4] = ((ImageView) findViewById(R.id.imageview_head_5));
        this.mImageViewArray[5] = ((ImageView) findViewById(R.id.imageview_head_6));
        this.mImageViewArray[6] = ((ImageView) findViewById(R.id.imageview_head_7));
        this.mImageViewArray[7] = ((ImageView) findViewById(R.id.imageview_head_8));
        this.mTakePicLayout = findViewById(R.id.take_pic_layout);
        this.mChoosePicLayout = findViewById(R.id.choose_pic_layout);
    }

    @Override
    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);

        if (paramInt2 != 0)
            switch (paramInt1) {
                case 0:
                    startBigPhotoZoom(paramIntent.getData());
                    break;
                case 1:
                    startBigPhotoZoom(Uri.fromFile(AvatarUploadUtils.getPortraitFile(getApplicationContext())));
                    break;
                case 3:
                    Uri localUri = paramIntent.getData();
                    String str = PathUtils.getPath(getApplicationContext(), localUri);
                    if (str != null)
                        localUri = Uri.fromFile(new File(str));
                    Log.e(TAG, "uri=" + localUri);
                    startBigPhotoZoom(localUri);
                    break;
                case 4:
                    startBigPhotoZoom(paramIntent.getData());
                    break;
                case 2:
            }

        getImageToView();
    }

    public void onClick(View paramView) {
        int id = paramView.getId();
        Bitmap bitmap = null;
        switch (id) {
            default:
            case R.id.imageview_head_1:
                bitmap = ImageUtils.drawableToBitmap(getResources().getDrawable(R.drawable.iflytek_face_01));
                break;
            case R.id.imageview_head_2:
                bitmap = ImageUtils.drawableToBitmap(getResources().getDrawable(R.drawable.iflytek_face_02));
                break;
            case R.id.imageview_head_3:
                bitmap = ImageUtils.drawableToBitmap(getResources().getDrawable(R.drawable.iflytek_face_03));
                break;
            case R.id.imageview_head_4:
                bitmap = ImageUtils.drawableToBitmap(getResources().getDrawable(R.drawable.iflytek_face_04));
                break;
            case R.id.imageview_head_5:
                bitmap = ImageUtils.drawableToBitmap(getResources().getDrawable(R.drawable.iflytek_face_05));
                break;
            case R.id.imageview_head_6:
                bitmap = ImageUtils.drawableToBitmap(getResources().getDrawable(R.drawable.iflytek_face_06));
                break;
            case R.id.imageview_head_7:
                bitmap = ImageUtils.drawableToBitmap(getResources().getDrawable(R.drawable.iflytek_face_07));
                break;
            case R.id.imageview_head_8:
                bitmap = ImageUtils.drawableToBitmap(getResources().getDrawable(R.drawable.iflytek_face_08));
                break;
            case R.id.take_pic_layout:
            case R.id.choose_pic_layout:
                break;
        }
        if (bitmap != null) {
            this.mFilePath = AvatarUploadUtils.saveMyBitmap(getApplicationContext(), bitmap);
            Intent localIntent2 = new Intent();
            localIntent2.putExtra("FILE_PATH", this.mFilePath);
            setResult(-1, localIntent2);
            finish();
        }

        Intent localIntent3 = new Intent("android.media.action.IMAGE_CAPTURE");
        localIntent3.putExtra("output", Uri.fromFile(AvatarUploadUtils.getPortraitFile(getApplicationContext())));
        startActivityForResult(localIntent3, 1);
        bitmap = null;


        try {
            selectPhoto();
            bitmap = null;
        } catch (Exception localException) {
            localException.printStackTrace();
            Intent localIntent1 = new Intent();
            localIntent1.setType("image/*");
            if (Build.VERSION.SDK_INT < 19) {
                localIntent1.setAction("android.intent.action.GET_CONTENT");
                startActivityForResult(localIntent1, 0);
                bitmap = null;
            } else {
                localIntent1.setAction("android.intent.action.OPEN_DOCUMENT");
                startActivityForResult(localIntent1, 3);
                bitmap = null;
            }
        }
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_choose_avatar_image);
        initToolbar();
        initView();
        initListener();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void selectPhoto() {
        startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 4);
    }

    public void startBigPhotoZoom(Uri paramUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(paramUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra("output", Uri.fromFile(AvatarUploadUtils.getPortraitFile(getApplicationContext())));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, 2);
    }
}