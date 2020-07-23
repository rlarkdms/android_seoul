package com.example.crawling2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {
        public static final int REQUEST_CODE = 1001;
        ListView listView;
        ArrayAdapter<String> mGameListAdapter;
        TextView textView;
        Toolbar toolbar;
        ActionBar actionBar;

        String selectedGame;
        int position;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //listView = (ListView) findViewById(R.id.main_listView);
      //  textView = (TextView) findViewById(R.id.main_textView);

            Button festival=(Button)findViewById(R.id.festival);
            Button show=(Button)findViewById(R.id.show);
            Button sight=(Button)findViewById(R.id.sight);
            Button museum=(Button)findViewById(R.id.museum);

            festival.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    selectedGame="축제";
                    position=0;
                    Intent intent = new Intent(getBaseContext(),SecondActivity.class);
                    intent.putExtra("gameTitle", selectedGame);
                    intent.putExtra("gameIndex", position);
                     System.out.println("확인 확인");
                     startActivity(intent);

                    finish();

                }

            });

            show.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    selectedGame="공연";
                    position=3;
                    Intent intent = new Intent(getBaseContext(),SecondActivity.class);
                    intent.putExtra("gameTitle", selectedGame);
                    intent.putExtra("gameIndex", position);
                    System.out.println("확인 확인");
                    startActivity(intent);

                    finish();

                }

            });
            sight.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    selectedGame="명소";
                    position=1;
                    Intent intent = new Intent(getBaseContext(),SecondActivity.class);
                    intent.putExtra("gameTitle", selectedGame);
                    intent.putExtra("gameIndex", position);
                    System.out.println("확인 확인");
                    startActivity(intent);

                    finish();

                 }

            });

            museum.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    selectedGame="미술관&박물관";
                    position=2;
                    Intent intent = new Intent(getBaseContext(),SecondActivity.class);
                    intent.putExtra("gameTitle", selectedGame);
                    intent.putExtra("gameIndex", position);
                    System.out.println("확인 확인");
                    startActivity(intent);

                    finish();

                }

            });

//            toolbar = findViewById(R.id.toolbar);
//            setSupportActionBar(toolbar);
//            actionBar = getSupportActionBar();
//            actionBar.setDisplayShowCustomEnabled(true);
//            actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
//            actionBar.setDisplayHomeAsUpEnabled(true);

       //     setActionBar();

//
//        String[] gameArray = {
//
//                "축제와 행사",
//                "명소",
//                "미술관&박물관",
//                "공연"
//
//       };
//        List<String> gameList = new ArrayList<String>(Arrays.asList(gameArray));
//        mGameListAdapter = new ArrayAdapter<String>(
//                this,
//                R.layout.activity_main,
//                R.id.main_textView,
//                gameList
//        );
//        listView.setAdapter(mGameListAdapter);
//            System.out.println("확인 확인222");
//        listView.setOnItemClickListener(itemClickListener);
    }

//
//    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            String selectedGame = (String)parent.getAdapter().getItem(position);
//            Intent intent = new Intent(getBaseContext(),SecondActivity.class);
//            intent.putExtra("gameTitle", selectedGame);
//            intent.putExtra("gameIndex", position);
//            System.out.println("확인 확인");
//            startActivity(intent);
//
//        }
//     };



    @Override
    public void onBackPressed()
    {
        //뒤로 가기 버튼 누르면 mainactivity로 이동
        Intent intent=new Intent(getBaseContext(),firstActivity.class);
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        ActionBar actionBar = getSupportActionBar();

        // Custom Actionbar를 사용하기 위해 CustomEnabled을 true 시키고 필요 없는 것은 false 시킨다
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);            //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        actionBar.setDisplayShowTitleEnabled(false);        //액션바에 표시되는 제목의 표시유무를 설정합니다.
        actionBar.setDisplayShowHomeEnabled(false);            //홈 아이콘을 숨김처리합니다.


        //layout을 가지고 와서 actionbar에 포팅을 시킵니다.
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionbar = inflater.inflate(R.layout.custom_actionbar, null);

        actionBar.setCustomView(actionbar);

        //액션바 양쪽 공백 없애기
        Toolbar parent = (Toolbar)actionbar.getParent();
        parent.setContentInsetsAbsolute(0,0);

        return true;


    }

//    private void setActionBar(){
//
//        CustomActionBar ca=new CustomActionBar(this, getSupportActionBar());
//        ca.setActionBar();
//
//    }


//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.action_btn1:
//                //select logout item
//                break;
//            case R.id.action_btn2:
//                //select account item
//                break;
//            case android.R.id.home:
//                //select back button
//                finish();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//


//    public static void setListViewHeightBasedOnChildren(ListView listView) {
//        ListAdapter listAdapter = listView.getAdapter();
//        if (listAdapter == null) {
//            // pre-condition
//            return;
//        }
//
//        int totalHeight = 0;
//
//        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
//        for (int i = 0; i < listAdapter.getCount(); i++) {
//            View listItem = listAdapter.getView(i, null, listView);
//            //listItem.measure(0, 0);
//            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
//            totalHeight += listItem.getMeasuredHeight();
//        }
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//
//        params.height = totalHeight;
//        listView.setLayoutParams(params);
//
//        listView.requestLayout();
//    }



    }
