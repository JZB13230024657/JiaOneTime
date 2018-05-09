package bwie.com.yikezhongtwo.presenter;

import java.util.List;

import bwie.com.yikezhongtwo.bean.DuanziBean;
import bwie.com.yikezhongtwo.model.DuoTuModel;
import bwie.com.yikezhongtwo.model.DuotuMod;
import okhttp3.MultipartBody;

/**
 * Created by 10419 on 2018/5/2.
 */

public class DuoTuPresenter implements DuotuMod{
    private DuoTuPre duoTuPre;
    private final DuoTuModel duoTuModel;

    public DuoTuPresenter(DuoTuPre duoTuPre) {
        this.duoTuPre=duoTuPre;
        duoTuModel = new DuoTuModel(this);
    }
    public void getZhuceData(String url, List<MultipartBody.Part> parts){
        duoTuModel.getZhuceData(url,parts);
    }

    @Override
    public void getDengluSuccess(DuanziBean duanziBean) {
        duoTuPre.getDengluSuccess(duanziBean);
    }

    @Override
    public void getDengluError(Throwable throwable) {
        duoTuPre.getDengluError(throwable);
    }
}
