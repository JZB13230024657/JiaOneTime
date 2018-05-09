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

public class Duanzi_Model {
    private Duanzi_Mod duanzi_mod;
    public Duanzi_Model(Duanzi_Mod duanzi_mod) {
        this.duanzi_mod=duanzi_mod;
    }
    public void getDuanziData(String url, Map<String,String>map){
        RetrofitUtils.getService().doGet(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        duanzi_mod.getDuanziSuccess(responseBody);
                    }

                    @Override
                    public void onError(Throwable e) {
                        duanzi_mod.getDuanziError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
