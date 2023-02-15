package com.antpc.app.listeners;



import com.antpc.app.roomdb.ComiconUser;

import java.util.List;

public interface GetUsersAsyncListener {
    void userListUpdated(List<ComiconUser> users);
}
