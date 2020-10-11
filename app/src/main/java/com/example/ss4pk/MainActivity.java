package com.example.ss4pk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<User> users = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    UserListAdapter userListAdapter;
    FrameLayout UserPanel;
    TextView NameTextView, StateTextView, AgeTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddUsersInList();
        initList();
    }

    private void AddUsersInList() {
        users.add(new User("A","Hooy",12, 1));
        users.add(new User("Sus", "piz", 15,0));
        users.add(new User("Sus", "piz", 15,2));
    }

    private void initList() {
        listView = findViewById(R.id.lv);
        context = this;
        layoutInflater = LayoutInflater.from(context);
        userListAdapter = new UserListAdapter();
        listView.setAdapter(userListAdapter);
        UserPanel = findViewById(R.id.userPanel);
        NameTextView = findViewById(R.id.NameTextView);
        StateTextView = findViewById(R.id.StateTextView);
        AgeTextView = findViewById(R.id.AgeTextView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userListAdapter.notifyDataSetChanged();
    }

    public void BackToList(View view) {
        UserVisibility(false);
    }

    private void UserVisibility(boolean visible) {
        if(visible)
            UserPanel.setVisibility(View.VISIBLE);
        else
            UserPanel.setVisibility(View.GONE);
    }

    public class UserListAdapter extends BaseAdapter {
        @NonNull
//        @Override
//        public UserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = layoutInflater.inflate(R.layout.item_user, parent, false);
//            return new ViewHolder(view);
//        }
//        @Override
//        public void onBindViewHolder(@NonNull UserListAdapter.ViewHolder holder, final int position) {
//            User user = users.get(position);
//            holder.nameView.setText(user.getName());
//            holder.stateView.setText(user.getState());
//            holder.view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(context, users.get(position).getName(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        @Override
//        public int getItemCount() {
//            return users.size();
//        }

        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public User getItem(int position) {
            return users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View currentView, ViewGroup parent) {

            User currentUser = getItem(position);
            currentView = layoutInflater.inflate(R.layout.item_user,parent,false);

             TextView nameView = currentView.findViewById(R.id.NameTextView);
             TextView stateView = currentView.findViewById(R.id.StateTextView);
             FrameLayout StateRound = currentView.findViewById(R.id.StateRound);
             switch (currentUser.getStateSignal())
             {
                 case 0:
                     StateRound.setBackgroundResource(R.drawable.back_offline);
                     break;
                 case 1:
                     StateRound.setBackgroundResource(R.drawable.back_online);
                     break;
                 case 2:
                     StateRound.setBackgroundResource(R.drawable.back_departed);
                     break;
             }
                nameView.setText(currentUser.getName());
                stateView.setText(currentUser.getState());
                currentView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        InitPanel(getItem(position));
                        UserVisibility(true);
                    }
                });
            return currentView;
        }
    }

    private void InitPanel(User item) {
        NameTextView.setText(item.getName());
        StateTextView.setText(item.getState());
        AgeTextView.setText(String.valueOf(item.getAge()));
    }
}
