package com.blogspot.atifsoftwares.firebaseapp;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blogspot.atifsoftwares.firebaseapp.adapters.AdapterPosts;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelPost;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    //firebase auth
    FirebaseAuth firebaseAuth;

    RecyclerView recyclerView;
    List<ModelPost> postList;
    AdapterPosts adapterPosts;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // inflate : 뷰가 객체화 돼서 실제 화면에 보여지는것
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        //init
        firebaseAuth = FirebaseAuth.getInstance();

        //recycler view : 데이터에 해당하는 뷰가 포함된 뷰그룹
        recyclerView = view.findViewById(R.id.postsRecyclerview);
        // LayoutManger는 목록의 개별요소 정렬, LinearLayoutManager : 항목을 1차원목록으로 정렬, 2차원은 Grid
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //스택을 활용하여 새로운 포스트를 앞으로 기존 포스트를 뒤로 보내는 형식
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        //레이아웃을 리사이클러뷰에 설정
        recyclerView.setLayoutManager(layoutManager);

        //포스트 리스트 배열 선언
        postList = new ArrayList<>();

        loadPosts();

        return view;
    }

    private void loadPosts() {
        //path of all posts
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Posts");
        //get all data from this ref
        //경로에서 데이터를 읽고 변경사항을 수신 대기하려면 addValueEventListener() 메서드를 사용하여 DatabaseReference를 ValueEventListener에 추가해야 합니다.
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postList.clear();
                //데이터 목록에 ValueEventListener를 연결하면 전체 데이터 목록이 단일 DataSnapshot으로 반환되며 이를 루프 처리하여 개별 하위 요소에 액세스할 수 있습니다.
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    ModelPost modelPost = ds.getValue(ModelPost.class);

                    postList.add(modelPost); // post추가

                    //adapter
                    adapterPosts = new AdapterPosts(getActivity(), postList); // postlist가져오기
                    //set adapter to recyclerview
                    recyclerView.setAdapter(adapterPosts);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //in case of error
                Toast.makeText(getActivity(), ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchPosts(final String searchQuery){ // 포스트 검색하는 함수

        //path of all posts
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Posts");
        //get all data from this ref
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postList.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    ModelPost modelPost = ds.getValue(ModelPost.class);

                    // 제목 검색을 위해 post에서 title을 가져와 소문자로변경(tolowercase)하고 검색한 문장이 포함되어있는지 확인
                    if (modelPost.getpTitle().toLowerCase().contains(searchQuery.toLowerCase()) ||
                            modelPost.getpDescr().toLowerCase().contains(searchQuery.toLowerCase())){
                        postList.add(modelPost); // modelpost에 검색된 포스트 나열
                    }

                    //adapter
                    adapterPosts = new AdapterPosts(getActivity(), postList);
                    //set adapter to recyclerview
                    recyclerView.setAdapter(adapterPosts);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //in case of error
                Toast.makeText(getActivity(), ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void checkUserStatus(){
        //get current user
        FirebaseUser user = firebaseAuth.getCurrentUser(); // 파이어베이스에서 user가져오기
        if (user != null){
            //user is signed in stay here
            //set email of logged in user
            //mProfileTv.setText(user.getEmail());
        }
        else {
            //user not signed in, go to main acitivity
            startActivity(new Intent(getActivity(), MainActivity.class)); // 메인액티비티로 이동
            getActivity().finish();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);//to show menu option in fragment
        super.onCreate(savedInstanceState);
    }

    /*inflate options menu*/
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflating menu
        inflater.inflate(R.menu.menu_main, menu); // 메뉴에 menu_main.xml을 inflate 하는것

        //searchview to search posts by post title/description
        MenuItem item = menu.findItem(R.id.action_search); //findItem()으로 항목을 검색한 다음, MenuItem API로 속성을 수정할 수 있습니다.
        SearchView searchView = (SearchView)MenuItemCompat.getActionView(item); // MenuItemCompat.getActionView(item) : 테스트한 모든 안드로이드에서 항상 NULL값을 반환

        //search listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //called when user press search button
                if (!TextUtils.isEmpty(s)){
                    searchPosts(s);
                }
                else {
                    loadPosts();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //called as and when user press any letter
                if (!TextUtils.isEmpty(s)){
                    searchPosts(s);
                }
                else {
                    loadPosts();
                }
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    /*handle menu item clicks*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // 오른쪽 위에 있는 세개의 버튼
        //get item id
        int id = item.getItemId();
        if (id == R.id.action_logout){ // 로그아웃버튼
            firebaseAuth.signOut(); // 파이어베이스에서 로그아웃
            checkUserStatus();
        }
        else if (id == R.id.action_add_post){ // 포스트 추가버튼, +버튼
            startActivity(new Intent(getActivity(), AddPostActivity.class)); // AddpostActivity로 이동
        }
        else if (id==R.id.action_settings){ // 설정버튼
            //go to settings activity
            startActivity(new Intent(getActivity(), SettingsActivity.class)); // SettingActivity로 이동
        }
        else if (id==R.id.action_settings) { // 이력서 작성 버튼
            startActivity(new Intent(getActivity(), SettingsActivity.class)); // SettingActivity로 이동
        }

        return super.onOptionsItemSelected(item);
    }

}
