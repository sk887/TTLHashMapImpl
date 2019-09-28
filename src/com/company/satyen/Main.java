package com.company.satyen;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        TTLHashMap<String, String> ttlHashMap = new TTLHashMap<>(TimeUnit.SECONDS, 10L);

        ttlHashMap.put("a", "b");
        try{
        Thread.sleep(12000);
        }catch(Exception e){
            e.printStackTrace();
        }


        System.out.println(ttlHashMap.get("a"));
	// write your code here
    }
}
