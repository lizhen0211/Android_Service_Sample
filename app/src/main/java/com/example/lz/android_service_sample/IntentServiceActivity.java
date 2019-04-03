package com.example.lz.android_service_sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.Serializable;
import java.util.Random;

public class IntentServiceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
    }

    public void onStartIntentServiceClick(View view) {
        Random random = new Random();
        int age = random.nextInt(30);
        Log.e("MyIntentService", "input age:" + age);
        Person person = new Person("jack", age, "male");
        Intent intent = new Intent(IntentServiceActivity.this, MyIntentService.class);
        intent.putExtra("person", person);
        startService(intent);
    }
}

class Person implements Serializable {
    private String name;
    private int age;
    private String sex;

    public Person(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
