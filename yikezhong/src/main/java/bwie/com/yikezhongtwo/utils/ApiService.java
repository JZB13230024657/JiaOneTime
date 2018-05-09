package bwie.com.yikezhongtwo.utils;


import java.util.List;
import java.util.Map;

import bwie.com.yikezhongtwo.bean.DuanziBean;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;


/**
 * Created by ASUS on 2018/3/17.
 */

public interface ApiService {
    @GET("{url}")
    Observable<ResponseBody> doGet(@Path(value = "url", encoded = true) String url, @QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST("{url}")
    Observable<ResponseBody> doPost(@Path(value = "url", encoded = true) String url, @FieldMap Map<String, String> map);
    /**
     * 上传一张图片
     * @param mapParam 除了文件之外,其他参数封装到这个map集合中
     * @param file 图片文件 需要封装成MultipartBody.Part对象
     * @return
     */
    @Multipart
    @POST("file/upload")
    Call<ResponseBody> uploadOnePic(@QueryMap Map<String , String> mapParam,@Part MultipartBody.Part file);


    /**
     * 上传一张图片
     * @param mapParam 除了文件之外,其他参数封装到这个map集合中
     * @param listParts 多张图片文件 需要封装成Mlist集合中
     * @return
     */
    @Multipart
    @POST("quarter/publishJoke")
    Call<ResponseBody> uploadMorePic(@QueryMap Map<String , String> mapParam, @Part List<MultipartBody.Part> listParts);
    @Multipart
    @POST("quarter/publishJoke")
    Observable<DuanziBean> fabiaoDuanzi(@Part List<MultipartBody.Part> parts);
}
