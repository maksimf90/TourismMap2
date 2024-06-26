package com.example.tourismmap2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class activity_help extends AppCompatActivity {

    List<helpData> hlist;
    HelpAdapters adapters;
    RecyclerView listhelp;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        listhelp = findViewById(R.id.listhelp);
        searchView = findViewById(R.id.searchhelp);
        searchView.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity_help.this, 1);
        listhelp.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity_help.this);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();

        hlist = new ArrayList<>();

        adapters = new HelpAdapters(activity_help.this, hlist);
        listhelp.setAdapter(adapters);

        databaseReference = FirebaseDatabase.getInstance("https://tourismmap-24b45-default-rtdb.europe-west1.firebasedatabase.app").getReference("TourismMap DataHelp");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hlist.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    helpData helpdata = itemSnapshot.getValue(helpData.class);
                    hlist.add(helpdata);
                }
                adapters.notifyDataSetChanged();
                dialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });
    }
    public void searchList(String text){
        ArrayList<helpData> helpList = new ArrayList<>();
        for (helpData hData: hlist){
            if (hData.getNameHelp().toLowerCase().contains(text.toLowerCase())) {
                helpList.add(hData);
            }
        }
        adapters.searchHelpList(helpList);
    }
}






















       /* hlayout = findViewById(R.id.listhelp);

        helplist = new ArrayList<>();
        helplist.add(new helpitem(R.drawable.iconbelongings, 1, "Что брать с собой?", "— Палатка, спальный мешок и коврик\n" +
                "Если вы запланировали поход с ночёвкой то без этих вещей вам просто не обойтись. Сегодня на рынке туристических товаров есть огромный выбор всевозможных палаток на любой вкус и кошелёк. Не стоит брать дорогую платку, это касается и спального мешка. Как правило, дорогие палатки имеют легкий вес, и они выполнены из специальных материалов для сложных условий. Скажем так, все это предназначено для серьезных походов.\n" +
                "При выборе палатки, обратите внимание на качество её изготовления в целом, отдельных элементов и фурнитуры. Молнии не должны заедать, швы должны быть аккуратно прострочены и герметизированы. Нагруженные элементы должны быть усилены. Металлические элементы-люверсы должны быть выполнены аккуратно и без заусенцев. Перейдём к спальнику  выбирайте его так, чтобы вам было комфортно лежать в привычной позе, а его ткань не вызывала неприятных тактильных ощущений.\n" +

                "Если вы собираетесь в поход летом, то не нужно переплачивать за слишком теплый спальник — достаточно купить спальник вида T, max или T, comfort. С ковриком всё проще. Для летнего похода достаточно взять классический туристический коврик: это дешёвый и надёжный вариант. Его можно проткнуть, прожечь, отрезать от него куски и эксплуатировать дальше. Пенки часто привязывают снаружи рюкзака — им не страшны потёртости и острые ветки. Туристический рюкзак\n" +
                "У вас будут проблемы во время путешествия, если вы отправитесь в поход без туристического рюкзака. Современные туристические рюкзаки оснащены дождевиками, имеют анатомически правильную форму. Отделения в таких рюкзаках расположены так, чтобы без труда можно было удобно поместить всю необходимую кладь.\n" +
                "\n" +
                "— Одежда и обувь\n" +
                "Вряд ли вы планируете отправиться в экстремальный поход, а значит, вам не понадобится специализированная одежда. Главное, чтобы она была удобной для вас. Примерный список должен выглядеть следующим образом: 2-3 футболки, спортивные штаны, легкая спортивная ветровка, теплая одежда, сменная обувь, 2-3 комплекта носков, одежда для сна, головной убор, солнцезащитные очки, дождевик, рабочие перчатки.\n" +
                "\n" +
                "— КЛМН\n" +
                "Кружка, ложка, миска, нож  так расшифровывается эта аббревиатура. Если вы не планируете много тратиться  то можно обойтись домашними столовыми приборами. Но лучше взять туристическую посуду. Такая посуда выполнена из специальных материалов и имеет особую форму, за счет чего есть из неё удобно в походных условиях, а продукты сохраняют температуру. Столовые приборы часто выполнены в складном, эргономичном формате.\n" +
                "\n" +
                "— Отдельно стоит остановиться на ноже  любой опытный турист вам скажет, что хороший походный нож  обязательная вещь. Советуем вам внимательно подойти к выбору этого инструмента. Самый распространенный нож в походе  складной. Он удобный, функциональный, надёжный. Конечно же, вам ещё понадобится котёл, куда без него. Подойдёт любой котелок, но желательно из огнеупорных материалов.\n" +
                "\n" +
                "— Газовая горелка, спички и сухое топливо\n" +
                "Огонь развести не всегда просто. Чтобы облегчить себе задачу, стоит взять с собой туристические спички и сухое топливо. Если есть газовая горелка — прекрасно! Ей не страшен дождь, да и мороки намного меньше. Да, зажигалка тоже лишней не будет.\n" +
                "\n" +
                "— Продукты питания\n" +
                "Важно понимать, что в походе вам придётся изменить свой привычный рацион питания. Очевидно, что с собой необходимо взять те продукты, которые можно приготовить при помощи туристических горелок или на специальной походной плите. Также они не должны испортиться. Хорошим вариантом будет взять с собой герметично упакованные консервы, крупы, сублимированные супы и каши, орехи и сухофрукты, шоколад и сгущенку, чай и кофе. Настоятельно рекомендуется не брать с собой скоропортящиеся продукты.\n" +
                "\n" +
                "— Аптечка\n" +
                "Сложно недооценить важность аптечки в походе. Она должна быть у каждого туриста. Остановимся на базовом наборе.\n" +
                "\n" +
                "— бинт;\n" +
                "— антисептическое средство (перекись водорода 3%, хлоргексидин);\n" +
                "— медицинский жгут;\n" +
                "— нашатырный спирт;\n" +
                "— таблетки от головной боли, диареи, температуры."));
        helplist.add(new helpitem(R.drawable.icontent, 2, "Как правильно выбрать палатку?", "Палатка"));
        helplist.add(new helpitem(R.drawable.iconbackpacks, 3, "Выбор туристического рюкзака", "Аптечка"));
        helplist.add(new helpitem(R.drawable.iconhospital, 4, "Первая медицинская помощь в походе", "Аптечка"));
        helplist.add(new helpitem(R.drawable.iconmedicalkit, 5, "Походная аптечка", "Аптечка"));
        helplist.add(new helpitem(R.drawable.iconrestaurant, 6, "Хранение продуктов в походе", "Аптечка"));
        helplist.add(new helpitem(R.drawable.iconcompass, 7, "Как не заблудиться в походе?", "Аптечка"));


        adapters = new HelpAdapters(helplist, this);
        hlayout.setLayoutManager(new GridLayoutManager(this, 1));
        hlayout.setAdapter(adapters);*/

