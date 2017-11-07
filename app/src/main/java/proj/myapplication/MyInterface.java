package proj.myapplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

/**
 * Created by sai on 7/11/17.
 */

public interface MyInterface {
    @GET("/contacts")
    Call<Example> getAllContact();
}
