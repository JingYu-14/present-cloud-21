package com.example.administrator.daoyunapplication.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.daoyunapplication.Model.Classes;
import com.example.administrator.daoyunapplication.Model.User;
import com.example.administrator.daoyunapplication.R;
import com.example.administrator.daoyunapplication.SignActivity.SignActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2020/4/23 0023.
 */

public class createClassAdapter  extends ArrayAdapter<Classes> {
    private int resourceId;
    private Context context;
    private User user;
    public createClassAdapter(@NonNull Context context, int resource, @NonNull List<Classes> objects,User user) {
        super(context, resource, objects);
        this.context = context;
        this.resourceId = resource;
        this.user=user;
    }
    public void setResourceId(int resource){
        this.resourceId = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.e("Listposition:",position +" ");
        int itemViewType=getItemViewType(position);
        View view = null;
        if(itemViewType==0) {
            setResourceId(R.layout.creater_class_button);
            view = LayoutInflater.from(context).inflate(resourceId, null);
            Button bt_create_class = (Button) view.findViewById(R.id.bt_create_class);
            bt_create_class.setOnClickListener(new CreateClassButton(position));
            Button bt_add_class = (Button) view.findViewById(R.id.bt_add_class);
            bt_add_class.setOnClickListener(new CreateClassButton(position));


        }
        return view;
        //获取对应的Class班级对象
//        Classes aClass = getItem(position);
//        View view = LayoutInflater.from(context).inflate(resourceId,null);
//        ImageView imageView_headpic=(ImageView)view.findViewById(R.id.imageView_headpic);
//        TextView textView_course_name = (TextView)view.findViewById(R.id.textView_course_name);
//        TextView textView_teacher_name = (TextView)view.findViewById(R.id.textView_teacher_name);
//        TextView textView_class_name = (TextView)view.findViewById(R.id.textView_class_name);
//        imageView_headpic.setImageResource(R.drawable.ic_launcher);
//        textView_course_name.setText(aClass.getNewsCourseName());
//        textView_teacher_name.setText(aClass.getNewsTeacherName());
//        textView_class_name.setText(aClass.getNewsClassName());


    }
//    EditText uid;
    EditText code;
    EditText class_name;
    public void createClass(View view){
//        uid=(EditText) view.findViewById(R.id.uid);
        code=(EditText)view.findViewById(R.id.code);
        class_name=(EditText)view.findViewById(R.id.class_name);
    }
    public void yesaddClass(){
        final OkHttpClient client = new OkHttpClient();
        String path="http://3r1005r723.wicp.vip/daoyunapi/public/index.php/";
        path = path + "studentClasses";
        int id=user.getUserId();//用户id
        Log.e("uid",id+"");
        Gson gson = new Gson();
        Map<Object,Object> map = new HashMap<>();
        map.put("uid",id);
        map.put("code", code.getText().toString());
        String params = gson.toJson(map);
        MediaType JSON= MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON,params);
        ///////////////////////////
        Request request = new Request.Builder()
                .url(path)//请求的url
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                Toast.makeText(getContext(), "网络连接失败！", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String info = response.body().string();//获取服务器返回的json格式数据

                Log.e("TAG", "onResponse: "+info );
                JsonObject jsonObject = new JsonParser().parse(info).getAsJsonObject();
                JsonObject jsonObjectMeta =jsonObject.get("meta").getAsJsonObject();
                int code = jsonObjectMeta.get("status").getAsInt();
                String msg="";
                msg=jsonObjectMeta.get("msg").getAsString();
                if (201==code)//如果code等于200，则说明存在该用户，而且服务器还返回了该用户的信息
                {
                    //这边要注意，获取的是用户对象getAsString();不行.getAsJsonObject();用这个
                    // JsonObject result = jsonObject.get("data").getAsJsonObject();//取出用户信息
                    Looper.prepare();
                    Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }else{
                    Looper.prepare();
                    Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }

            }
        });



        ////////////////

    }
    public void createClassDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("创建班级:");
        View view=  LayoutInflater.from(getContext()).inflate(R.layout.create_class,null);
        createClass(view);
        dialog.setView(view);

        //  dialog.setView(textView);
        dialog.setCancelable(false);

        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                yescreateClass();
            }
        });
        dialog.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.show();
    }
    public void yescreateClass(){
        final OkHttpClient client = new OkHttpClient();
        String path="http://3r1005r723.wicp.vip/daoyunapi/public/index.php/";
        path = path + "teacherClasses";
        int id=user.getUserId();//用户id
        Log.e("uid",id+"");
        Gson gson = new Gson();
        Map<Object,Object> map = new HashMap<>();
        map.put("uid",id);
        map.put("code", code.getText().toString());
        map.put("name",class_name.getText().toString());
        String params = gson.toJson(map);
        MediaType JSON= MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON,params);
        ///////////////////////////
        Request request = new Request.Builder()
                .url(path)//请求的url
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                Toast.makeText(getContext(), "网络连接失败！", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String info = response.body().string();//获取服务器返回的json格式数据

                Log.e("TAG", "onResponse: "+info );
                JsonObject jsonObject = new JsonParser().parse(info).getAsJsonObject();
                JsonObject jsonObjectMeta =jsonObject.get("meta").getAsJsonObject();
                int code = jsonObjectMeta.get("status").getAsInt();
                String msg="";
                msg=jsonObjectMeta.get("msg").getAsString();
                if (201==code)//如果code等于200，则说明存在该用户，而且服务器还返回了该用户的信息
                {
                    //这边要注意，获取的是用户对象getAsString();不行.getAsJsonObject();用这个
                    // JsonObject result = jsonObject.get("data").getAsJsonObject();//取出用户信息
                    Looper.prepare();
                    Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }else{
                    Looper.prepare();
                    Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }

            }
        });



        ////////////////

    }
    public void addClassDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("创建班级:");
        View view=  LayoutInflater.from(getContext()).inflate(R.layout.add_class,null);
        createClass(view);
        dialog.setView(view);

        //  dialog.setView(textView);
        dialog.setCancelable(false);

        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                yesaddClass();
            }
        });
        dialog.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.show();
    }
    public class CreateClassButton implements View.OnClickListener {
        int mPosition;
        public CreateClassButton(int inPosition){
            mPosition= inPosition;
        }
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                //签到按钮
                case R.id.bt_create_class:
//                    Context currentActivity=v.getContext();
//                    //跳到签到页面
//                    Intent intent = new Intent(currentActivity, SignActivity.class);
//                    intent.putExtra("user",user);
//                    intent.putExtra("classes",c);
//                    currentActivity.startActivity(intent);
                     Toast.makeText(v.getContext(), mPosition+"dfd", Toast.LENGTH_SHORT).show();
                    createClassDialog();
                    break;
                case R.id.bt_add_class:
                    Toast.makeText(v.getContext(), mPosition+"dfd", Toast.LENGTH_SHORT).show();
                    addClassDialog();//学生加入班级
                    break;

            }
        }

    }
}