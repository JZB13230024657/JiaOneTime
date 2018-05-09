package bwie.com.yikezhongtwo.presenter;

import java.util.Map;

import bwie.com.yikezhongtwo.model.ZhuceMod;
import bwie.com.yikezhongtwo.model.ZhuceModel;
import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/13.
 */

public class ZhucePresenter implements ZhuceMod{
    private ZhuCePre zhuCePre;
    private final ZhuceModel zhuceModel;

    public ZhucePresenter(ZhuCePre zhuCePre) {
        this.zhuCePre=zhuCePre;
        zhuceModel = new ZhuceModel(this);
    }

    public void getZhuceData(String url, Map<String,String>map){
        zhuceModel.getZhuceData(url,map);
    }
    @Override
    public void getZhuceSuccess(ResponseBody responseBody) {
        zhuCePre.getZhuceSuccess(responseBody);
    }

    @Override
    public void getZhuceError(Throwable throwable) {
        zhuCePre.getZhuceError(throwable);
    }
}
