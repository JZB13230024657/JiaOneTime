package bwie.com.yikezhongtwo.model;

import java.util.Map;

import bwie.com.yikezhongtwo.presenter.GuanzhuPresenter;
import bwie.com.yikezhongtwo.utils.RetrofitUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/17.
 */

public class GuanzhuModel {
    private DengluMod dengluMod;
    public GuanzhuModel(DengluMod dengluMod) {
        this.dengluMod=dengluMod;
    }
    public void getData(String url, Map<String,String> map){
        RetrofitUtils.getService().doPost(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                            dengluMod.getDengluSuccess(responseBody);
                    }

                    @Override
                    public void onError(Throwable e) {
                            dengluMod.getDengluError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
