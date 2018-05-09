package bwie.com.yikezhongtwo.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.donkingliang.imageselector.ImageSelectorActivity;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwie.com.yikezhongtwo.R;
import bwie.com.yikezhongtwo.adapter.ImgGridViewAdapter;
import bwie.com.yikezhongtwo.bean.DengluBean;
import bwie.com.yikezhongtwo.bean.DuanziBean;
import bwie.com.yikezhongtwo.presenter.DengluPre;
import bwie.com.yikezhongtwo.presenter.DengluPresenter;
import bwie.com.yikezhongtwo.presenter.DuoTuPre;
import bwie.com.yikezhongtwo.presenter.DuoTuPresenter;
import bwie.com.yikezhongtwo.utils.CommonUtils;
import bwie.com.yikezhongtwo.utils.Constant;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class FaDuanziActivity extends AppCompatActivity implements DengluPre,DuoTuPre {

    @BindView(R.id.quxiao)
    TextView quxiao;
    @BindView(R.id.fabiao_duanzi)
    TextView fabiaoDuanzi;
    @BindView(R.id.show_duanzi)
    EditText showDuanzi;
    @BindView(R.id.imggrid)
    GridView imggrid;
    @BindView(R.id.jiatupian)
    ImageView jiatupian;
    private DengluPresenter dengluPresenter;
    private ArrayList<String> images;
    private int code = 1;
    private List<Bitmap> list;
    private ImgGridViewAdapter imgGridViewAdapter;
    private String duanzi;
    private DuoTuPresenter duoTuPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_duanzi);
        ButterKnife.bind(this);
        dengluPresenter = new DengluPresenter(this);
        duoTuPresenter = new DuoTuPresenter(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && data != null) {
            //获取选择器返回的数据
            images = data.getStringArrayListExtra(
                    ImageSelectorUtils.SELECT_RESULT);
            code = 0;
            list = new ArrayList<>();
            for (int i = 0; i < images.size(); i++) {
                String s = images.get(i);
                Bitmap bitmap = BitmapFactory.decodeFile(s);
                list.add(bitmap);
            }
            if (imgGridViewAdapter == null) {
                imgGridViewAdapter = new ImgGridViewAdapter(list, FaDuanziActivity.this);
                imggrid.setAdapter(imgGridViewAdapter);
            } else {
                imgGridViewAdapter.setList(list);
            }

        }
    }

    @OnClick({R.id.quxiao, R.id.fabiao_duanzi,R.id.jiatupian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.quxiao:
                finish();
                break;
                case R.id.jiatupian:
                    ImageSelectorActivity.openActivity(FaDuanziActivity.this, 200, false, 9,images);
                break;
            case R.id.fabiao_duanzi:
                duanzi = showDuanzi.getText().toString();
                if (images==null){
                    Map<String, String> map = new HashMap<>();
                    map.put("content", duanzi);
                    dengluPresenter.getDengluData(Constant.FABU_DUANZI_URL, map);
                }else if (images!=null){
                    MultipartBody.Builder builder = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("uid", CommonUtils.getString("uid"))
                            .addFormDataPart("source", "android")
                            .addFormDataPart("appVersion", "101")
                            .addFormDataPart("token", CommonUtils.getString("token"))
                            .addFormDataPart("content", duanzi);
                    for (int i=0; i<images.size(); i++){
                        File file = new File(images.get(i));
                        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-date"), file);
                        builder.addFormDataPart("jokeFiles",file.getName(),requestBody);
                    }
                    List<MultipartBody.Part> parts = builder.build().parts();
                    duoTuPresenter.getZhuceData(Constant.FABU_DUANZI_URL, parts);
                }

                break;
        }
    }

    @Override
    public void getDengluSuccess(ResponseBody responseBody) {
        try {
            String string = responseBody.string();
            DengluBean dengluBean = new Gson().fromJson(string, DengluBean.class);
            String code = dengluBean.getCode();
            if (code.equals("0")) {
                Toast.makeText(FaDuanziActivity.this, dengluBean.getMsg(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FaDuanziActivity.this, FabiaoChenggong.class);
                startActivity(intent);
            } else {
                Toast.makeText(FaDuanziActivity.this, dengluBean.getMsg(), Toast.LENGTH_SHORT).show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDengluSuccess(DuanziBean duanziBean) {
        Toast.makeText(FaDuanziActivity.this, duanziBean.getMsg(), Toast.LENGTH_SHORT).show();
        Toast.makeText(FaDuanziActivity.this, duanziBean.getCode(), Toast.LENGTH_SHORT).show();
        if (duanziBean.getCode().equals("0")){
            Toast.makeText(this, duanziBean.getMsg(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(FaDuanziActivity.this, FabiaoChenggong.class);
            startActivity(intent);
            list.clear();
            imgGridViewAdapter.notifyDataSetChanged();
        }else {
            Toast.makeText(this, "发布失败", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void getDengluError(Throwable throwable) {

    }
}
