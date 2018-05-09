package bwie.com.yikezhongtwo.presenter;

import java.util.Map;

import bwie.com.yikezhongtwo.model.DengluMod;
import bwie.com.yikezhongtwo.model.DengluModel;
import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/13.
 */

public class DengluPresenter implements DengluMod{
    private DengluPre dengluPre;
    private final DengluModel dengluModel;

    public DengluPresenter(DengluPre dengluPre) {
        this.dengluPre=dengluPre;
        dengluModel = new DengluModel(this);
    }
    public void getDengluData(String url, Map<String,String> map){
        dengluModel.getDengluData(url,map);
    }
    @Override
    public void getDengluSuccess(ResponseBody responseBody) {
        dengluPre.getDengluSuccess(responseBody);
    }

    @Override
    public void getDengluError(Throwable throwable) {
        dengluPre.getDengluError(throwable);
    }
}
