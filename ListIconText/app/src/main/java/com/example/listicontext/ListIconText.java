package com.example.listicontext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListIconText extends AppCompatActivity {
    ArrayList<MyItem> arItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewtest);

        arItem = new ArrayList<MyItem>();
        MyItem mi;
        mi = new MyItem(R.mipmap.ic_launcher, "삼성 노트북");
        arItem.add(mi);
        mi = new MyItem(R.drawable.ic_launcher_background, "LG 세탁기");
        arItem.add(mi);
        mi = new MyItem(R.drawable.ic_launcher_background, "대우 마티즈");
        arItem.add(mi);

        MyListAdapter MyAdapter = new MyListAdapter(this, R.layout.icontext, arItem);

        ListView MyList;
        MyList = (ListView) findViewById(R.id.list);
        MyList.setAdapter(MyAdapter);
    }
}

// 리스트 뷰에 출력할 항목
class MyItem {
    int icon;
    String name;

    MyItem(int aIcon, String aName) {
        icon = aIcon;
        name = aName;
    }
}

// 어댑터 클래스
class MyListAdapter extends BaseAdapter {
    Context maincon;
    LayoutInflater Inflater;
    ArrayList<MyItem> arSrc;
    int layout;

    public MyListAdapter(Context context, int alayout, ArrayList<MyItem> arSrc) {
        this.maincon = context;
        Inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arSrc = arSrc;
        this.layout = alayout;
    }

    @Override
    public int getCount() {
        return arSrc.size();
    }

    @Override
    public Object getItem(int position) {
        return arSrc.get(position).name;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        if (convertView == null){
            convertView = Inflater.inflate(layout, parent, false);
        }
        ImageView img = (ImageView) convertView.findViewById(R.id.img);
        img.setImageResource(arSrc.get(position).icon);

        TextView txt = (TextView) convertView.findViewById(R.id.text);
        txt.setText(arSrc.get(position).name);

        Button btn = (Button) convertView.findViewById(R.id.btn);
        btn.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                String str = arSrc.get(pos).name + "를 주문합니다.";
                Toast.makeText(maincon, str, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}