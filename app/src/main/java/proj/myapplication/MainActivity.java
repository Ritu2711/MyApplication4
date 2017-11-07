package proj.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyInterface myInterface=ApiClient.getClient().create(MyInterface.class);
        Call<Example> call=myInterface.getAllContact();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {


               List<Contact> list=response.body().getContacts();


                for (int i=0;i<list.size();i++)
                {
                    Contact contact=list.get(i);
                    Log.d("mmmmm",contact.getEmail());

                    Phone phone=contact.getPhone();

                    Log.d("mmmmm",phone.getMobile());

                }




            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}
