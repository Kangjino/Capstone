package com.blogspot.atifsoftwares.firebaseapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.firebaseapp.adapters.AdapterMindposts;
import com.blogspot.atifsoftwares.firebaseapp.adapters.AdapterPosts;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelMindpost;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelPost;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.

 */
public class MindPostFragment extends Fragment {

    //firebase auth
    FirebaseAuth firebaseAuth;

    RecyclerView recyclerView;
    List<ModelMindpost> MindpostList;
    AdapterMindposts adapterMindposts;


    public MindPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mind_post, container, false);

        //init
        firebaseAuth = FirebaseAuth.getInstance();

        //recycler view and its properties
        recyclerView = view.findViewById(R.id.mindpostRecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //show newest post first, for this load from last
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        //set layout to recyclerview
        recyclerView.setLayoutManager(layoutManager);

        //init post list
        MindpostList = new ArrayList<ModelMindpost>();
        loadMindPosts();

        return view;
    }

    //???????????? ????????? ????????? ??????
    private void loadMindPosts() {
        //path of all posts
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("MindPosts");
        //get all data from this ref
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                MindpostList.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    ModelMindpost modelMindpost = ds.getValue(ModelMindpost.class);

                    MindpostList.add(modelMindpost);  //???????????? ?????? ????????? ???????????? ??????

                    //???????????? ????????? ????????????
                    adapterMindposts = new AdapterMindposts(getActivity(), MindpostList);

                    //set adapter to recyclerview
                    recyclerView.setAdapter(adapterMindposts);   //???????????? ????????? recyclerViewd??? ??????
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //in case of error
                Toast.makeText(getActivity(), ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchPosts(final String searchQuery){

        //path of all posts
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("MindPosts");
        //get all data from this ref
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                MindpostList.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    ModelMindpost modelMindpost = ds.getValue(ModelMindpost.class);

                    if (modelMindpost.getmDescr().toLowerCase().contains(searchQuery.toLowerCase())){
                        MindpostList.add(modelMindpost);
                    }

                    //adapter
                    adapterMindposts = new AdapterMindposts(getActivity(), MindpostList);
                    //set adapter to recyclerview
                    recyclerView.setAdapter(adapterMindposts);
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
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null){
            //user is signed in stay here
            //set email of logged in user
            //mProfileTv.setText(user.getEmail());
        }
        else {
            //user not signed in, go to main acitivity
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);//to show menu option in fragment
        super.onCreate(savedInstanceState);


    }
    /*inflate options menu  -->????????? ?????????*/
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflating menu
        inflater.inflate(R.menu.menu_main, menu);

        //searchview to search posts by post title/description
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        //search listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //called when user press search button
                if (!TextUtils.isEmpty(s)){
                    searchPosts(s);
                }
                else {
                    loadMindPosts();
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
                    loadMindPosts();
                }
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    /*handle menu item clicks*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //get item id
        int id = item.getItemId();
        if (id == R.id.action_logout){
            firebaseAuth.signOut();
            checkUserStatus();
        }
        else if (id == R.id.action_add_post){
            startActivity(new Intent(getActivity(), AddMindpostActivity.class));
        }
        else if (id==R.id.action_settings){
            //go to settings activity
            startActivity(new Intent(getActivity(), SettingsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

}

