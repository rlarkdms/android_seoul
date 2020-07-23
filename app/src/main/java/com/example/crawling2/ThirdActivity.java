package com.example.crawling2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1001;

    ImageView imageView01;
    String html_detail;
    TextView textView_title;
    TextView textView_url;
    TextView textView_detail;
    TextView textView_date;
    URL img_url;//img 가져오기 위해 선언
    String url;//그 전 recycleView에서 가져온 url표현
    String name;
    String detail;//그 웹사이트 detail표현
    String date="<br>";//특이사항 그 요일 금액 등등
    String web="";//하이퍼 링크를 위해 사용
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);
        Intent intent = getIntent();
        String Title = intent.getStringExtra("title");
        setTitle(Title);
        url=intent.getStringExtra("event_url");
        textView_title = (TextView) findViewById(R.id.title);
        textView_url=(TextView)findViewById(R.id.URL);
        textView_detail=(TextView)findViewById(R.id.detail);
        textView_date=(TextView)findViewById(R.id.date);
        imageView01 =findViewById(R.id.imageView01);
        System.out.println("3번째 activity 확인");
        System.out.println(url);
        textView_title.setText(Title);
      //textView_url.setText(url);

        JsoupAsyncTask jsoupAsyncTask=new JsoupAsyncTask();
        jsoupAsyncTask.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        ActionBar actionBar = getSupportActionBar();


        // Custom Actionbar를 사용하기 위해 CustomEnabled을 true 시키고 필요 없는 것은 false 시킨다
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);            //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        actionBar.setDisplayShowTitleEnabled(false);        //액션바에 표시되는 제목의 표시유무를 설정합니다.
        actionBar.setDisplayShowHomeEnabled(false);            //홈 아이콘을 숨김처리합니다.


        //layout을 가지고 와서 actionbar에 포팅을 시킵니다.
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionbar = inflater.inflate(R.layout.custom_actionbar_detail, null);

        actionBar.setCustomView(actionbar);

        //액션바 양쪽 공백 없애기
        Toolbar parent = (Toolbar)actionbar.getParent();
        parent.setContentInsetsAbsolute(0,0);

        return true;

    }

    public class JsoupAsyncTask extends AsyncTask<Void,Void,Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @SuppressLint("WrongThread")
        @Override
        public Void doInBackground(Void... params) {
            try {


                Document doc = (Document) Jsoup.connect(url).get();

                Elements titles=doc.select("div.item");

                Elements details=doc.select("div.text-area");

                Elements dates=doc.select("div.detail-map-infor.first.border dl");

                //Elements titles = doc.select("ul[class='activities-box clearfix'] li[class='clearfix'] div[class='box-txt'] h4");
               // Elements titles = doc.select("li.item div.infor-element-inner");

                System.out.println(url+"잘 들어왔는지 확인");

                  String title=titles.select("img").attr("src");
//다른 방법을 강구해야한다...! 형식이 안맞아서 그런가...? Glide 형식값대로 했는데 안된다!...

                  detail=details.select("p").text();


                  System.out.println(title);
                  title="http://korean.visitseoul.net"+title;
//                System.out.println("이게 되는지 봅시다"+space);
//
//                img_url=new URL(title);

//                System.out.println("-------------------------------------------");
                web=dates.select("dd a").attr("href");
                for (Element e : dates) {

                     date = date+"<strong>"+e.select("dt").text()+"</strong>"+"<br>";
                     date= date+e.select("dd").text()+"<br>";




                }

                date=date.replace("웹사이트 보기",web);//웹이면 하이퍼 링크로 바꾸기

                System.out.println(date);
                System.out.println("확인 확인");
                System.out.println(title);
                //  System.out.println(htmlContent);
                //  second_gameTitle.setText("확인용");

                System.out.println(detail);



            } catch (
                    IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result){

            textView_detail.setText(detail);
            textView_date.setText(Html.fromHtml(date));



        }



    }

}
