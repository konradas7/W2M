package com.konradas.where2meet.Fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.konradas.where2meet.R;
import com.konradas.where2meet.tools.DataInterface;

public class FriendButton extends Fragment{

    private int id;
    private String name;
    private Bitmap picture;
    private boolean ingroup;
    private DataInterface di;

    public FriendButton(int id, String name, Bitmap picture, DataInterface di) {
        this.id= id;
        this.name= name;
        this.picture= null;
        this.ingroup= false;
        this.di= di;
        if (picture != null) this.picture= picture;
    }

    public FriendButton(int id, String name, Bitmap picture, boolean inGroup, DataInterface di) {
        this.id= id;
        this.name= name;
        this.picture= null;
        this.ingroup= inGroup;
        this.di= di;
        if (picture != null) this.picture= picture;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_button, container, false);
        TextView txt= view.findViewById(R.id.profile_name_text);
        txt.setText(name);

        final Button btn = view.findViewById(R.id.add_profile_to_group_button);
        if (!ingroup) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("DEBUG", "Invite to party selected");
                    di.inviteToGroup(id);
                    btn.setVisibility(View.INVISIBLE);

                }
            });
        }
        else {
            btn.setVisibility(View.GONE);
        }
        final Button kick_btn = view.findViewById(R.id.remove_from_action_button);
        if (!ingroup) {
            kick_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("DEBUG", "Kick from friends selected");
                    //TODO: padaryti, kad nebebūtų friendliste
                    di.kickFromFriends(id);
                    kick_btn.setVisibility(View.INVISIBLE);
                }
            });
        }
        else {
            kick_btn.setVisibility(View.GONE);
        }
        if (di.checkIfUserIsInGroup(id)) {
            btn.setVisibility(View.GONE);
        }
        return view;
    }

    public int getPersonId() {
        return id;
    }
}
