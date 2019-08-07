/*
 * Singleton class for storing login information (admin or not)
 */

package com.example.vikailmoitusapp;

public class LoginInfo {

    private static LoginInfo mInstance = null;

    public boolean adminLogin = false;

    protected LoginInfo(){}

    public static synchronized LoginInfo getInstance() {
        if(null == mInstance){
            mInstance = new LoginInfo();
        }
        return mInstance;
    }
}
