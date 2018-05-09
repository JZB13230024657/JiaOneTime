package bwie.com.yikezhongtwo.presenter;

import java.util.Map;

import bwie.com.yikezhongtwo.model.Remen_Model;
import bwie.com.yikezhongtwo.model.Tuijian_Remen_Modle;
import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/9.
 */

public class Tuijian_Remen_presenter implements Remen_Model{
    private Remen_Presenter remen_presenter;
    private final Tuijian_Remen_Modle tuijian_remen_modle_remen_modle;

    public Tuijian_Remen_presenter(Remen_Presenter remen_presenter) {
        this.remen_presenter=remen_presenter;
        tuijian_remen_modle_remen_modle = new Tuijian_Remen_Modle(this);
    }

public void getBannerUrl(String bannerUrl,Map<String,String> map){
        tuijian_remen_modle_remen_modle.getBannerUrl(bannerUrl,map);
}

    @Override
    public void ModelOnSuccess(ResponseBody responseBody) {
        remen_presenter.OnSuccess(responseBody);
    }

    @Override
    public void Error(Throwable e) {
        remen_presenter.Error(e);
    }
}
