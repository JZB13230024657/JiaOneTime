package bwie.com.yikezhongtwo.model;


import java.util.Map;

import bwie.com.yikezhongtwo.utils.RetrofitUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/9.
 */

public class Tuijian_Remen_Modle {
    private Remen_Model remen_model;
    public Tuijian_Remen_Modle(Remen_Model remen_model) {
        this.remen_model=remen_model;
    }
    public void getBannerUrl(String bannerUrl,Map<String,String> map){
        RetrofitUtils.getService().doGet(bannerUrl,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        remen_model.ModelOnSuccess(responseBody);
                    }

                    @Override
                    public void onError(Throwable e) {
                        remen_model.Error(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
