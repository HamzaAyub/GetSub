package com.example.hp.beyfikar.orderfrontpageactivity;

import com.example.hp.beyfikar.Retrofit.UserPojo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by hp on 12/3/2017.
 */

public interface OrderService {
    @POST("userorder")
    Call<OrderPojo> userOrder(@Body OrderPojo o);

}
