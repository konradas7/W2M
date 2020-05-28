package com.konradas.where2meet.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.konradas.where2meet.R;
import com.konradas.where2meet.obj.Filter;
import com.konradas.where2meet.obj.Group;
import com.konradas.where2meet.obj.User;

import java.util.ArrayList;
import java.util.List;

public class HTTPInterpreter {

    boolean testMode= false;
    String server1;
    String server2;
    private String currentPw;
    private int user_id;

    public HTTPInterpreter() {

    }
    public HTTPInterpreter(int user_id) {
        this.user_id= user_id;
    }


    public int sendLoginRequest(String usr, String pw) {
        //TODO: Nusiųsti serveriui requestą, kad useris nori prisijungti.
        /*Šita funkcija grąžina userio id, kuris naudojamas po to turėtų būti saugomas ir kiekvieną kartą siunčiamas su užklausa. */
        //Jeigu pavyko- grąžinam id, jeigu nepavyko, grąžinam -1.
        return -1;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setPw(String pw) {
        /* Šitą funkciją kviesti iškart sukūrus šitą objektą, kitaip jokie requestai neveiks */
        this.currentPw= pw;
    }

    public Group getCurrentGroup() {
        Group group= new Group(-1);
        //TODO: paimti userio dabartinę grupę iš serverio
        /* Jeigu useris nėra gruopėje, grąžinti tuščią group objektą su neigiamu id */
        return group;
    }

    public List<User> getFriendList() {
        List<User> friendList= new ArrayList<>();
        //TODO: paimti userio draugų sąrašą iš serverio
        /* Jeigu useris neturi draugų, grąžinti tuščią sąrašą */
        return friendList;
    }

    public User getCurrentUser() {
        Bitmap pic = null;
        User currentUser= new User(-1, "ERROR", pic);
        //TODO: paimti userio info iš serverio
        /* Jeigu useris kažkokios info neturi, elgits prikalusomai, ko jam reik:
            Jeigu tai kažkas nelabai svarbaus (Picture, vardas, etc.) ignoruoti.
            Jeigu tai kažkas svarbesnio kaip locationas, callbackinti į activity, ir ten pabandyt gauti tai.
         */
        return currentUser;
    }

    public Filter getCurrentUserFilter() {
        Filter filter= new Filter();
        //TODO: Paimti iš serverio dabartinio userio filtrą
        /* Jeigu jo nėra, kartu užsettinti ir iš sharedpreferences (ir vice versa) */
        return filter;
    }

    public Filter getUserFilter(int id) {
        Filter filter= new Filter();
        //TODO: Paimti iš serverio kito userio filtrą
        /* Jeigu jo nėra, laikyti kad visi tipai leidžiami */
        return filter;
    }

    public void clearCurrentGroup() {
        //TODO: nusiųsti į serverį užklausą kad useris išeina iš grupės
    }

    public void updatePreferences(Filter temp) {
        //TODO: nusiųsti į serverį naujus preferencus ir dar įrašyti esamus preferences į sharedpreferences
    }

    public void sendAddFriendRequest(String email) {
        //TODO: nusiųsti į serverį requestą, kad nori prisidėti draugą
    }

    public void sendInviteToGroupRequest() {
        //TODO: nusiųsti į serverį requestą, kad kviečiam žmogų į grupę
    }

    public void sendKickFromFriendsRequest() {
        //TODO: nusiusti i serveri requesta, kad zmogu ismetam is draugu
    }

    public void getCurrentGroupMedianLocation() {
        //TODO: nusiusti i serveri requesta, kuris grazi aktyvios grupes vidurio taska
    }

    public void voteForPlace(String id) {
        //TODO: nusiųsti į serverį, kad pabalsavom už kažkokią vietą
    }
}
