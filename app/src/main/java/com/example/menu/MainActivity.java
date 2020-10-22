package com.example.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.method.MultiTapKeyListener;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Context context;
    ArrayList<CountriesModel> countriesData;
    ArrayList<CountriesModel> UserSelection; //Tạo List để xóa Listview
    CustomAdapter customAdapter;
    CountriesModel countriesModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);

        //getviews
        listView = findViewById(R.id.listview);
        registerForContextMenu(listView); //Context Menu
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);//Action Menu
        listView.setMultiChoiceModeListener(modeListener);//Action Menu
        countriesData = new ArrayList<>();

        UserSelection = new ArrayList<>(); //Tạo List để xóa Listview

        //add Countries Data
        populateCountriesData();

        customAdapter = new CustomAdapter(context,countriesData);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(context,countriesData.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void populateCountriesData() {
        //country 1
        countriesModel = new CountriesModel();
        countriesModel.setId(1);
        countriesModel.setName("Pakistan");
        countriesModel.setImage(R.drawable.pakistan);
        countriesModel.setArea("796,095 km2");
        countriesModel.setPopulation("203,382,058");
        countriesData.add(countriesModel);

        //country 2
        countriesModel = new CountriesModel();
        countriesModel.setId(2);
        countriesModel.setName("Afghanistan");
        countriesModel.setImage(R.drawable.afghanistan);
        countriesModel.setArea("652,090 km2");
        countriesModel.setPopulation("25,500,100");
        countriesData.add(countriesModel);

        //country 3
        countriesModel = new CountriesModel();
        countriesModel.setId(1);
        countriesModel.setName("India");
        countriesModel.setImage(R.drawable.india);
        countriesModel.setArea("3,287,260 km2");
        countriesModel.setPopulation("1,241,610,000");
        countriesData.add(countriesModel);

        //country 4
        countriesModel = new CountriesModel();
        countriesModel.setId(1);
        countriesModel.setName("Iran");
        countriesModel.setImage(R.drawable.iran);
        countriesModel.setArea("1,648,200 km2");
        countriesModel.setPopulation("77,288,000");
        countriesData.add(countriesModel);

        //country 5
        countriesModel = new CountriesModel();
        countriesModel.setId(1);
        countriesModel.setName("China");
        countriesModel.setImage(R.drawable.china);
        countriesModel.setArea("9,640,820 km2");
        countriesModel.setPopulation("1,363,350,000");
        countriesData.add(countriesModel);

        //country 6
        countriesModel = new CountriesModel();
        countriesModel.setId(1);
        countriesModel.setName("United States");
        countriesModel.setImage(R.drawable.usa);
        countriesModel.setArea("9,629,090 km2");
        countriesModel.setPopulation("317,706,000");
        countriesData.add(countriesModel);

        //country 7
        countriesModel = new CountriesModel();
        countriesModel.setId(1);
        countriesModel.setName("Canada");
        countriesModel.setImage(R.drawable.canada);
        countriesModel.setArea("9,970,610 km2");
        countriesModel.setPopulation("35,295,770");
        countriesData.add(countriesModel);

        //country 8
        countriesModel = new CountriesModel();
        countriesModel.setId(1);
        countriesModel.setName("Morocco");
        countriesModel.setImage(R.drawable.morocco);
        countriesModel.setArea("446,550 km2");
        countriesModel.setPopulation("33,202,300");
        countriesData.add(countriesModel);

        //country 9
        countriesModel = new CountriesModel();
        countriesModel.setId(1);
        countriesModel.setName("South Africa");
        countriesModel.setImage(R.drawable.southafrica);
        countriesModel.setArea("1,221,040 km2");
        countriesModel.setPopulation("52,981,991");
        countriesData.add(countriesModel);

        //country 10
        countriesModel = new CountriesModel();
        countriesModel.setId(1);
        countriesModel.setName("Zimbabwe");
        countriesModel.setImage(R.drawable.zimbabwe);
        countriesModel.setArea("390,757  km2");
        countriesModel.setPopulation("12,973,808");
        countriesData.add(countriesModel);
    }
//    //Delete Listview
    AbsListView.MultiChoiceModeListener modeListener = new AbsListView.MultiChoiceModeListener() {
        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
            if(UserSelection.contains(countriesData.get(position))){
                UserSelection.remove(countriesData.get(position));
            }
            else{
                UserSelection.add(countriesData.get(position));
            }
            mode.setTitle(UserSelection.size() +" items selected...");
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater menuInflater = mode.getMenuInflater();
            menuInflater.inflate(R.menu.example_menu, menu);

            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch (item.getItemId()){
                case R.id.action_delete:
                    customAdapter.removeItems(UserSelection);
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            UserSelection.clear();
        }
    };
    //Hàm tạo context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        getMenuInflater().inflate(R.menu.menu_context, menu);
        menu.setHeaderTitle("Cập nhật");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.them:
                Toast.makeText(this, "Thêm Selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.sua:
                Toast.makeText(this, "Sửa selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.xoa:
                Toast.makeText(this, "Xóa selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);

        }
    }
}