package GUI;

import Console.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IdAndPassword {

    HashMap<String,String> logininfo = new HashMap<String,String>();

    IdAndPassword(){

        logininfo.put("Bro","pizza");
        logininfo.put("Brometheus","PASSWORD");
        logininfo.put("BroCode","abc123");
    }

    public HashMap getLoginInfo(){
        return logininfo;
    }
}



