package bwie.com.yikezhongtwo.presenter;

import java.util.Map;

import bwie.com.yikezhongtwo.model.DengluMod;
import bwie.com.yikezhongtwo.model.GuanzhuModel;
import bwie.com.yikezhongtwo.utils.RetrofitUtils;
import bwie.com.yikezhongtwo.view.activity.WodeGuanzhuActivity;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/17.
 */

public class GuanzhuPresenter implements DengluMod{
    private DengluPre dengluPre;
    private final GuanzhuModel guanzhuModel;

    public GuanzhuPresenter(DengluPre dengluPre) {
        this.dengluPre=dengluPre;
        guanzhuModel = new GuanzhuModel(this);
    }
    public void getData(String url, Map<String,String>map){
       guanzhuModel.getData(url,map);
    }

    @Override
    public void getDengluSuccess(ResponseBody responseBody) {
        dengluPre.getDengluSuccess(responseBody);
    }

    @Override
    public void getDengluError(Throwable throwable) {
        dengluPre.getDengluError(throwable);
    }
}
