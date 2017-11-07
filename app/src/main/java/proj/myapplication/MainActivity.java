package proj.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    ListView listview;
    List<Contact> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview= (ListView) findViewById(R.id.list);
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
                    arrayList.add(contact);

                    Phone phone=contact.getPhone();

                    Log.d("mmmmm",phone.getMobile());

                }



listview.setAdapter(new MyAdapter(MainActivity.this,list));

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }

    public class MyAdapter extends BaseAdapter{

        Context context;
        List<Contact> contactArrayList;

        public MyAdapter(Context context, List<Contact> contactArrayList) {
            this.context = context;
            this.contactArrayList = contactArrayList;
        }

        @Override
        public int getCount() {
            return contactArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return contactArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.itemlayout,null);

            Contact contact=contactArrayList.get(position);
            TextView textView=convertView.findViewById(R.id.text);
            textView.setText(contact.getEmail());

            return convertView;
        }
    }
}
