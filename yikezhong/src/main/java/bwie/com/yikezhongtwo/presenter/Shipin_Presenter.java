package bwie.com.yikezhongtwo.presenter;

import java.util.Map;

import bwie.com.yikezhongtwo.model.Shipin_Mod;
import bwie.com.yikezhongtwo.model.Shipin_Model;
import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/11.
 */

public class Shipin_Presenter implements Shipin_Mod{
    private ShipinPre shipinPre;
    private final Shipin_Model shipin_model;

    public Shipin_Presenter(ShipinPre shipinPre) {
        this.shipinPre=shipinPre;
        shipin_model = new Shipin_Model(this);
    }
    public void getShipinData(String url, Map<String,String> map){
        shipin_model.getShipinData(url,map);
    }
    @Override
    public void getShipinSuccess(ResponseBody responseBody) {
        shipinPre.getShipinSuccess(responseBody);
    }

    @Override
    public void getShipinError(Throwable throwable) {
        shipinPre.getShipinError(throwable);
    }
}
