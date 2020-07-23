package com.example.crawling2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1001;
    ListView listView;
    TextView second_gameTitle, second_gameReleaseDate;
   // Button returnBtn;
    ArrayAdapter<String> mEventListAdapter;
    Toast toast;
    String htmlContent;
    private RecyclerView recyclerView;
    private ArrayList<ItemObject> list=new ArrayList();

    String gameTitle;

    int i=1;//JsoupAsyncTask1 num 개수를 판단해줄 변수
    int j=1;
    int k=1;
    int q=1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        System.out.println("ㅇ아아아아아아아아아ㅏ아앙ㅇ");
//      listView=(ListView)findViewById(R.id.main_listView01);
        second_gameTitle = (TextView) findViewById(R.id.second_gameTitle);
//      second_gameReleaseDate = (TextView) findViewById(R.id.second_gameReleaseDate);
//      returnBtn = (Button) findViewById(R.id.second_returnBtn);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);

        recyclerView.addOnScrollListener(onScrollListener);//이부분 스크롤 내리면 마지막인지 알려주는
        Intent intent = getIntent();
        gameTitle = intent.getStringExtra("gameTitle");
        int gameIndex = intent.getExtras().getInt("gameIndex");

        setTitle(gameTitle);

        System.out.println("ㅇ아아아아아아아아아ㅏ아앙ㅇ");

        switch (gameIndex){

            case 0:

                JsoupAsyncTask1 jsoupAsyncTask1=new JsoupAsyncTask1();
                jsoupAsyncTask1.execute();

                break;

            case 1:

                JsoupAsyncTask2 jsoupAsyncTask2 =new JsoupAsyncTask2();
                jsoupAsyncTask2.execute();

                break;

            case 2:

                JsoupAsyncTask3 jsoupAsyncTask3 =new JsoupAsyncTask3();
                jsoupAsyncTask3.execute();
                break;

            case 3:

                System.out.println("4 들어간것만 확인하기");
                JsoupAsyncTask4 jsoupAsyncTask4 =new JsoupAsyncTask4();
                jsoupAsyncTask4.execute();

                break;

            case 4:


                break;

        }


//        returnBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//
//                finish();
//            }
//
//        });



    }

    @Override
    public void onBackPressed()
    {
        //뒤로 가기 버튼 누르면 mainactivity로 이동
        Intent intent=new Intent(getBaseContext(),MainActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        ActionBar actionBar = getSupportActionBar();

        // buttonclicker가 있어야함
        // Custom Actionbar를 사용하기 위해 CustomEnabled을 true 시키고 필요 없는 것은 false 시킨다
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);            //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        actionBar.setDisplayShowTitleEnabled(false);        //액션바에 표시되는 제목의 표시유무를 설정합니다.
        actionBar.setDisplayShowHomeEnabled(false);            //홈 아이콘을 숨김처리합니다.


        //layout을 가지고 와서 actionbar에 포팅을 시킵니다.
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionbar = inflater.inflate(R.layout.custom_actionbar_item, null);

        actionBar.setCustomView(actionbar);

        //액션바 양쪽 공백 없애기
        Toolbar parent = (Toolbar)actionbar.getParent();
        parent.setContentInsetsAbsolute(0,0);

        return true;

    }

    //    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            String selectedGame = (String)parent.getAdapter().getItem(position);
//            Intent intent = new Intent(getBaseContext(),ThirdActivity.class);
//            intent.putExtra("gameTitle", selectedGame);
//            intent.putExtra("gameIndex", position);
//            startActivity(intent);
//        }
//    };

    //5가지 정도
    //1.액션바 만들기-> 검색기능+뒤로가기(보기좋으라고)+kakaoLink오른 메뉴 탭
    //2.layout-> layout를 만질 줄 모름. 넣었는데..눌리거나 이동을 어떻게 시키는지 모르겠음...
    //3.내용정리해야돼 내가 테마마다 태그 맞춰야 함.
    //4.영문판 만들기->이거는 사이트만 바꾸면 될듯
    //5.와이파이 속도->이거 해야한다

    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
            int totalItemCount = layoutManager.getItemCount();
            int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();

            if (lastVisible >= totalItemCount - 1){

                JsoupAsyncTask4 jsoupAsyncTask4=new JsoupAsyncTask4();
                jsoupAsyncTask4.execute();//다시 실행시키는게 아니라 어떻게 다른식으로 바꿔야함....? 근데 잘 모르겠어,,,

            }
        }
    };

    private class JsoupAsyncTask1 extends AsyncTask<Void,Void,Void> {


        private ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = new ProgressDialog(SecondActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage("잠시 기다려 주세요.");
                progressDialog.show();

            }

            @Override
            public Void doInBackground(Void... params) {
                try {


                    String count="";//이 변수가 string으로 페이지 개수를 크롤링해서 저장시키는 변수

                    int num=0;//그 변수를 for문 돌리기 위해 int형으로 변형시키기 위해 있는 변수


                    String url="http://korean.visitseoul.net/events";




                    Document doc = Jsoup.connect(url).get();



                    Elements page=doc.select("div[class='paging-lst'] a");
//여기 이제 다음 페이지에 있는것도 가져와야 함




                    for(Element em:page){

                        count = em.attr("href");

                    }

                    count=count.substring(9,10);

                    System.out.println("페이지개수"+count);

                    num=Integer.parseInt(count);







                    //Elements titles = doc.select("ul[class='activities-box clearfix'] li[class='clearfix'] div[class='box-txt'] h4");
            //        Elements titles = doc.select("li[class='item']");

                    System.out.println("여기까지 되는지 알아보기");
//                String space=titles.select("a").text();
//                System.out.println("이게 되는지 봅시다"+space);
//
//                System.out.println("-------------------------------------------");

//그 스크롤 내리면 다시 추가로 정보 받아오게 하려면 내리서 맨 마지막이 되면 i++하는 방법 밖에 없음
// 리스트 뷰의 끝이라고 생각 되면  i를 하나나 올리고 만약 i가 num이 되면 toast로 맨 마지막 페이지 입니다
// 설정하고 태그 하나씩 고치기 그리고 이미지도 불러오고

     //               for(int i=1; i<=num; i++) {

                    if(i<=num){


                        String url1 = url + "?curPage=" + i;


                        Document doc1 = Jsoup.connect(url1).get();

                        Elements titles = doc1.select("li[class='item']");


                        for (Element e : titles) {

                            String title = e.select("a").attr("title");
                            String date = e.select("span[class='small-text text-dot-d']").text();


                            String img_url = e.select("div.thumb").attr("style");

                            System.out.println(img_url + "입니다");
                            img_url = "http://korean.visitseoul.net" + img_url.substring(21, 98);

                            System.out.println(img_url);
                            String event_URL = e.select("a").attr("href");

                            event_URL = "http://korean.visitseoul.net" + event_URL;
                            System.out.println(event_URL);

                            String space = "";
//                    htmlContent += e.select("span.title").text().trim() + "\n";
//                    htmlContent += name.trim() + "\n";

                            list.add(new ItemObject(title, img_url, event_URL, date, space));

                        }

                        System.out.println("여기까지 되는지 알아보기!!~~~~~1111111~~~~");

       //             }
                    //Log.d("debug:","List"+titles);

          //              Toast.makeText(getApplicationContext(),"마지막입니다",Toast.LENGTH_SHORT).show();

                        System.out.println("여기까지 되는지 알아보기!!~~~~222222~~~~~");

                    }
                    i++;
                } catch (
                        IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void result){

                //   second_gameTitle.setText(htmlContent);

                MyAdapter myAdapter = new MyAdapter(getApplicationContext(),list);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(myAdapter);
                progressDialog.dismiss();


        }

    }


    private class JsoupAsyncTask2 extends AsyncTask<Void,Void,Void> {


        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(SecondActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("잠시 기다려 주세요.");
            progressDialog.show();

        }

        @Override
        public Void doInBackground(Void... params) {
            try {


                String count="";//이 변수가 string으로 페이지 개수를 크롤링해서 저장시키는 변수

                int num=0;//그 변수를 for문 돌리기 위해 int형으로 변형시키기 위해 있는 변수


                String url="http://korean.visitseoul.net/attractions";




                Document doc = Jsoup.connect(url).get();



                Elements page=doc.select("div[class='paging-lst'] a");
//여기 이제 다음 페이지에 있는것도 가져와야 함

                for(Element em:page){

                    count = em.attr("href");

                }

                count=count.substring(9,10);

                System.out.println("페이지개수"+count);

                num=Integer.parseInt(count);



                //Elements titles = doc.select("ul[class='activities-box clearfix'] li[class='clearfix'] div[class='box-txt'] h4");
//                Elements titles = doc.select("li[class='item']");

                System.out.println("여기까지 되는지 알아보기");
//                String space=titles.select("a").text();
//                System.out.println("이게 되는지 봅시다"+space);
//
//                System.out.println("-------------------------------------------");



                for(int i=1; i<=num; i++) {


                    String url1 = url + "?curPage=" + i;


                    Document doc1 = Jsoup.connect(url1).get();

                    Elements titles = doc1.select("li[class='item']");


                    for (Element e : titles) {

                        String title = e.select("a").attr("title");
                        String date = e.select("span[class='small-text text-dot-d']").text();


                        String img_url = e.select("div.thumb").attr("style");

                        System.out.println(img_url + "입니다");
                        img_url = "http://korean.visitseoul.net" + img_url.substring(21, 98);

                        System.out.println(img_url);
                        String event_URL = e.select("a").attr("href");

                        event_URL = "http://korean.visitseoul.net" + event_URL;
                        System.out.println(event_URL);

                        String space = "";
//                    htmlContent += e.select("span.title").text().trim() + "\n";
//                    htmlContent += name.trim() + "\n";

                        list.add(new ItemObject(title, img_url, event_URL, date, space));

                    }

                }
                //Log.d("debug:","List"+titles);

            } catch (
                    IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result){

            //   second_gameTitle.setText(htmlContent);

            MyAdapter myAdapter = new MyAdapter(getApplicationContext(),list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myAdapter);
            progressDialog.dismiss();


        }

    }


    private class JsoupAsyncTask3 extends AsyncTask<Void,Void,Void> {


        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(SecondActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("잠시 기다려 주세요.");
            progressDialog.show();

        }

        @Override
        public Void doInBackground(Void... params) {
            try {

                String count="";//이 변수가 string으로 페이지 개수를 크롤링해서 저장시키는 변수

                int num=0;//그 변수를 for문 돌리기 위해 int형으로 변형시키기 위해 있는 변수


                String url="http://korean.visitseoul.net/museum";




                Document doc = Jsoup.connect(url).get();



                Elements page=doc.select("div[class='paging-lst'] a");
//여기 이제 다음 페이지에 있는것도 가져와야 함

                for(Element em:page){

                    count = em.attr("href");

                }

                count=count.substring(9,10);

                System.out.println("페이지개수"+count);

                num=Integer.parseInt(count);



                //Elements titles = doc.select("ul[class='activities-box clearfix'] li[class='clearfix'] div[class='box-txt'] h4");
//                Elements titles = doc.select("li[class='item']");

                System.out.println("여기까지 되는지 알아보기");
//                String space=titles.select("a").text();
//                System.out.println("이게 되는지 봅시다"+space);
//
//                System.out.println("-------------------------------------------");


                for(int i=1; i<=num; i++) {


                    String url1 = url+"?curPage=" + i;


                    Document doc1 = Jsoup.connect(url1).get();

                    Elements titles = doc1.select("li[class='item']");


                    for (Element e : titles) {

                        String title = e.select("a").attr("title");
                        String date = e.select("span[class='small-text text-dot-d']").text();


                        String img_url = e.select("div.thumb").attr("style");

                        System.out.println(img_url + "입니다");
                        img_url = "http://korean.visitseoul.net" + img_url.substring(21, 98);

                        System.out.println(img_url);
                        String event_URL = e.select("a").attr("href");

                        event_URL = "http://korean.visitseoul.net" + event_URL;
                        System.out.println(event_URL);

                        String space = "";
//                    htmlContent += e.select("span.title").text().trim() + "\n";
//                    htmlContent += name.trim() + "\n";

                        list.add(new ItemObject(title, img_url, event_URL, date, space));

                    }

                }
                //Log.d("debug:","List"+titles);

            } catch (
                    IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result){

            //   second_gameTitle.setText(htmlContent);

            MyAdapter myAdapter = new MyAdapter(getApplicationContext(),list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myAdapter);
            progressDialog.dismiss();


        }

    }



    private class JsoupAsyncTask4 extends AsyncTask<Void,Void,Void> {


        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(SecondActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("잠시 기다려 주세요.");
            progressDialog.show();

        }

        @Override
        public Void doInBackground(Void... params) {
            try {

                String count="";//이 변수가 string으로 페이지 개수를 크롤링해서 저장시키는 변수

                int num=0;//그 변수를 for문 돌리기 위해 int형으로 변형시키기 위해 있는 변수

                String url="http://korean.visitseoul.net/culture";




                Document doc = Jsoup.connect(url).get();




                Elements page=doc.select("div[class='paging-lst'] a");
//여기 이제 다음 페이지에 있는것도 가져와야 함

                for(Element em:page){

                    count = em.attr("href");

                }

                count=count.substring(9,10);

                System.out.println("페이지개수"+count);

                num=Integer.parseInt(count);


                //Elements titles = doc.select("ul[class='activities-box clearfix'] li[class='clearfix'] div[class='box-txt'] h4");

                System.out.println("여기까지 되는지 알아보기");
//                String space=titles.select("a").text();
//                System.out.println("이게 되는지 봅시다"+space);
//
//                System.out.println("-------------------------------------------");


//                for(int i=1; i<=num; i++) {

                if(q<=num){

                    String url1="http://korean.visitseoul.net/culture?curPage="+q;


                    Document doc1 = Jsoup.connect(url1).get();

                    Elements titles = doc1.select("li[class='item']");


                    for (Element e : titles) {

                        String title = e.select("a").attr("title");
                        String date = e.select("span[class='small-text text-dot-d']").text();


                        System.out.println(date + "여기도 확인 해보기");
                        String img_url = e.select("div.thumb img").attr("src");

                        System.out.println(img_url + "입니다");
                        //img_url="http://korean.visitseoul.net"+img_url.substring(21,98);

                        System.out.println(img_url);
                        String event_URL = e.select("a").attr("href");

                        event_URL = "http://korean.visitseoul.net" + event_URL;
                        System.out.println(event_URL);

                        String space = "";
//                    htmlContent += e.select("span.title").text().trim() + "\n";
//                    htmlContent += name.trim() + "\n";


                        list.add(new ItemObject(title, img_url, event_URL, date, space));

                    }

                }
//                Log.d("debug:","List"+titles);

                q++;

                System.out.println("여기까지 가능11111111 "+q);


            } catch (
                    IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result){

            //   second_gameTitle.setText(htmlContent);

            MyAdapter myAdapter = new MyAdapter(getApplicationContext(),list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myAdapter);
            progressDialog.dismiss();


        }

    }


//
//
//    private class JsoupAsyncTask2 extends AsyncTask<Void,Void,Void> {
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//        }
//
//        @SuppressLint("WrongThread")
//        @Override
//        public Void doInBackground(Void... params) {
//            try {
//
//                Document doc = Jsoup.connect("http://korean.visitseoul.net/events").get();
//
//            //    Elements titles = doc.select("div[class='infor-element-inner']");
//                Elements titles = doc.select("li.item");
//
//                System.out.println("-------------------------------------------");
//                for (Element e : titles) {
//                    System.out.println("여기가 doInBackground부분");
//                    String name = e.text();
//
//
//                   String title = e.select("span.title").text();
//           //         String date = e.select("span.small-text text-dot-d").text();
//
//
//                   String img_url=e.select("div.thumb").attr("style");
//                 //  String event_URL=e.select("a").attr("href");
//
//                    System.out.println(title + "입니다 \n");
//                    htmlContent += img_url+"\n";
//
//                }
//                //  System.out.println(htmlContent);
//                //  second_gameTitle.setText("확인용");
//
//
//            } catch (
//                    IOException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result){
//
//            second_gameTitle.setText(htmlContent);
//
//        }
//
//
//
//    }
//
//    private class JsoupAsyncTask3 extends AsyncTask<Void,Void,Void> {
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//        }
//
//        @SuppressLint("WrongThread")
//        @Override
//        public Void doInBackground(Void... params) {
//            try {
//
//                Document doc = Jsoup.connect("https://www.seoul.go.kr/thismteventfstvl/list.do").get();
//
//                Elements titles = doc.select("div[class='item'] em[class='subject']");
//
//                System.out.println("-------------------------------------------");
//                for (Element e : titles) {
//                    System.out.println("여기가 doInBackground부분");
//                    String name = e.text();
//
//
//                    System.out.println(name + "입니다 \n");
//
//                    htmlContent += e.text().trim() + "\n";
//
//                }
//                //  System.out.println(htmlContent);
//                //  second_gameTitle.setText("확인용");
//
//
//            } catch (
//                    IOException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result){
//
//            second_gameTitle.setText(htmlContent);
//
//        }
//
//
//
//    }
//
//    private class JsoupAsyncTask4 extends AsyncTask<Void,Void,Void> {
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//        }
//
//        @SuppressLint("WrongThread")
//        @Override
//        public Void doInBackground(Void... params) {
//            try {
//
//                Document doc = Jsoup.connect("https://www.seoul.go.kr/thismteventfstvl/list.do").get();
//
//                Elements titles = doc.select("div[class='item'] em[class='subject']");
//
//                System.out.println("-------------------------------------------");
//                for (Element e : titles) {
//                    System.out.println("여기가 doInBackground부분");
//                    String name = e.text();
//
//
//                    System.out.println(name + "입니다 \n");
//
//                    htmlContent += e.text().trim() + "\n";
//
//                }
//                //  System.out.println(htmlContent);
//                //  second_gameTitle.setText("확인용");
//
//
//            } catch (
//                    IOException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result){
//
//            second_gameTitle.setText(htmlContent);
//
//        }
//
//
//    }
//
//    private class JsoupAsyncTask5 extends AsyncTask<Void,Void,Void> {
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//        }
//
//        @SuppressLint("WrongThread")
//        @Override
//        public Void doInBackground(Void... params) {
//            try {
//
//                Document doc = Jsoup.connect("https://www.seoul.go.kr/thismteventfstvl/list.do").get();
//
//                Elements titles = doc.select("div[class='item'] em[class='subject']");
//
//                System.out.println("-------------------------------------------");
//                for (Element e : titles) {
//                    System.out.println("여기가 doInBackground부분");
//                    String name = e.text();
//
//
//                    System.out.println(name + "입니다 \n");
//
//                    htmlContent += e.text().trim() + "\n";
//
//                }
//                //  System.out.println(htmlContent);
//                //  second_gameTitle.setText("확인용");
//
//
//            } catch (
//                    IOException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result){
//
//            second_gameTitle.setText(htmlContent);
//
//        }
//
//
//
//    }
//
//    private class JsoupAsyncTask6 extends AsyncTask<Void,Void,Void> {
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//        }
//
//        @SuppressLint("WrongThread")
//        @Override
//        public Void doInBackground(Void... params) {
//            try {
//
//                Document doc = Jsoup.connect("https://www.seoul.go.kr/thismteventfstvl/list.do").get();
//
//                Elements titles = doc.select("div[class='item'] em[class='subject']");
//
//                System.out.println("-------------------------------------------");
//                for (Element e : titles) {
//                    System.out.println("여기가 doInBackground부분");
//                    String name = e.text();
//
//
//                    System.out.println(name + "입니다 \n");
//
//                    htmlContent += e.text().trim() + "\n";
//
//                }
//                //  System.out.println(htmlContent);
//                //  second_gameTitle.setText("확인용");
//
//
//            } catch (
//                    IOException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result){
//
//            second_gameTitle.setText(htmlContent);
//
//        }
//
//
//
//    }
//


}
