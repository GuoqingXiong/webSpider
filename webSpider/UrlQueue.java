package com.sreach.spider;

import java.util.LinkedList;

public class UrlQueue
{
    /**hyperlink queue*/
    public static LinkedList<String> urlQueue = new LinkedList<String>();
    
    public static final int MAX_SIZE = 10000;
    
    public synchronized static void addElem(String url)
    {
        urlQueue.add(url);
    }
    
    public synchronized static String outElem()
    {
        return urlQueue.removeFirst();
    }
    
    public synchronized static boolean isEmpty()
    {
        return urlQueue.isEmpty();
    }
    
    public  static int size()
    {
        return urlQueue.size();
    }
    
    public  static boolean isContains(String url)
    {
        return urlQueue.contains(url);
    }

}