package bwie.com.yikezhongtwo.model;

import java.util.Map;

import bwie.com.yikezhongtwo.utils.RetrofitUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/11.
 */

public class Shipin_Model {
    private Shipin_Mod shipin_mod;
    public Shipin_Model(Shipin_Mod shipin_mod) {
        this.shipin_mod=shipin_mod;
    }
    public void getShipinData(String url, Map<String,String>map){
        RetrofitUtils.getService().doGet(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        shipin_mod.getShipinSuccess(responseBody);
                    }

                    @Override
                    public void onError(Throwable e) {
                        shipin_mod.getShipinError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
