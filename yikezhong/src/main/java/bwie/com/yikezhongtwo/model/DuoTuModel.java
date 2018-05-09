package bwie.com.yikezhongtwo.model;

import java.util.List;
import java.util.Map;

import bwie.com.yikezhongtwo.bean.DuanziBean;
import bwie.com.yikezhongtwo.presenter.DuoTuPresenter;
import bwie.com.yikezhongtwo.presenter.ZhuCePre;
import bwie.com.yikezhongtwo.utils.RetrofitUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/5/2.
 */

public class DuoTuModel {
    private DuotuMod duotuMod;
    public DuoTuModel(DuotuMod duotuMod) {
    this.duotuMod=duotuMod;
    }
    public void getZhuceData(String url, List<MultipartBody.Part> parts){
        RetrofitUtils.getService().fabiaoDuanzi(parts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DuanziBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DuanziBean duanziBean) {
                        duotuMod.getDengluSuccess(duanziBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        duotuMod.getDengluError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
