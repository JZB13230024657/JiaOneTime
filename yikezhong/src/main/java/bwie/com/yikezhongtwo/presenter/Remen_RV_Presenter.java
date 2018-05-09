package bwie.com.yikezhongtwo.presenter;

import java.util.Map;

import bwie.com.yikezhongtwo.model.RV_Model;
import bwie.com.yikezhongtwo.model.Remen_RV_Model;
import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/10.
 */

public class Remen_RV_Presenter implements RV_Model{
    private RV_Presenter rv_presenter;
    private  Remen_RV_Model remen_rv_model;

    public Remen_RV_Presenter(RV_Presenter rv_presenter) {
        this.rv_presenter=rv_presenter;
        remen_rv_model = new Remen_RV_Model(this);
    }
    public void getTiaomuUrl(String tiaomuUrl,Map<String,String> map){
        remen_rv_model.getTiaomuUrl(tiaomuUrl,map);
    }

    @Override
    public void TiaomuDataOnSuccess(ResponseBody responseBody) {
        rv_presenter.TiaomuDataOnSuccess(responseBody);
    }

    @Override
    public void TiaomuError(Throwable throwable) {
        rv_presenter.TiaomuError(throwable);
    }
}
