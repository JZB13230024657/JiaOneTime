package bwie.com.yikezhongtwo.presenter;

import java.util.Map;

import bwie.com.yikezhongtwo.model.Duanzi_Mod;
import bwie.com.yikezhongtwo.model.Duanzi_Model;
import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/11.
 */

public class Duanzi_Presenter implements Duanzi_Mod{
    private Duanzi_Pre duanzi_pre;
    private final Duanzi_Model duanzi_model;

    public Duanzi_Presenter(Duanzi_Pre duanzi_pre) {
        this.duanzi_pre=duanzi_pre;
        duanzi_model = new Duanzi_Model(this);
    }
    public void getDuanziData(String url, Map<String,String>map){
        duanzi_model.getDuanziData(url,map);
    }

    @Override
    public void getDuanziSuccess(ResponseBody responseBody) {
        duanzi_pre.getDuanziSuccess(responseBody);
    }

    @Override
    public void getDuanziError(Throwable throwable) {
        duanzi_pre.getDuanziError(throwable);
    }
}
