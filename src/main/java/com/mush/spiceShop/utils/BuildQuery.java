package com.mush.spiceShop.utils;

import java.time.Instant;

public class BuildQuery {
    public static String checkAndOr(String fromQry,String searchQry){
        if (fromQry.toUpperCase().contains("WHERE")) {
            fromQry+=" AND "+searchQry;
        }else{
            fromQry+=" WHERE " + searchQry;
        }
        return fromQry;
    }

    public static String checkBetween(Instant from, Instant to, String fromQry, String column){
        if (fromQry.toUpperCase().contains("WHERE")) {
            fromQry+=" AND "+column+" BETWEEN '"+from+ "' AND '"+ to+ "'";
        }else{
            fromQry+=" WHERE "+column+" BETWEEN '"+from+ "' AND '"+ to+"'";
        }
        return fromQry;
    }
}
