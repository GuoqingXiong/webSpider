package com.sreach.spider;

public class UrlDataHanding implements Runnable
{
    /**
     * download pages and parse URL to queue
     * @param url
     */
    public void dataHanding(String url)
    {
            HrefOfPage.getHrefOfContent(DownloadPage.getContentFormUrl(url));
    }
        
    public void run()
    {
        while(!UrlQueue.isEmpty())
        {
           dataHanding(UrlQueue.outElem());
        }
    }
}