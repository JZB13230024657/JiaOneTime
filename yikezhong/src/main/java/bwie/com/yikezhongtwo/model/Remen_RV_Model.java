package bwie.com.yikezhongtwo.model;

import java.util.Map;

import bwie.com.yikezhongtwo.utils.RetrofitUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/10.
 */

public class Remen_RV_Model {
    private RV_Model rv_model;
    public Remen_RV_Model(RV_Model rv_model) {
        this.rv_model=rv_model;
    }
    public void getTiaomuUrl(String tiaomuUrl,Map<String,String> map){
        RetrofitUtils.getService().doGet(tiaomuUrl,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        rv_model.TiaomuDataOnSuccess(responseBody);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        rv_model.TiaomuError(throwable);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
