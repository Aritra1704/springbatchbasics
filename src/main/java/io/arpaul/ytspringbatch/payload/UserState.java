package io.arpaul.ytspringbatch.payload;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class UserState implements Serializable {
    @Id
    @GeneratedValue
    private long _id;
    private String state_id;
    private String state_name;

    private int no_users;

    public UserState() {
    }

    public UserState(String state_id, String state_name) {
        this.state_id = state_id;
        this.state_name = state_name;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public int getNo_users() {
        return no_users;
    }

    public void setNo_users(int no_users) {
        this.no_users = no_users;
    }

    @Override
    public String toString() {
        return "UserState{" +
                "_id=" + _id +
                ", state_id='" + state_id + '\'' +
                ", state_name='" + state_name + '\'' +
                ", no_users=" + no_users +
                '}';
    }
}
