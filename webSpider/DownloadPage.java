package com.sreach.spider;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class DownloadPage
{

    /**
     * grab web page content by URL
     * 
     * @param url
     * @return
     */
    public static String getContentFormUrl(String url)
    {
        /* initialize HttpClient client */
        HttpClient client = new DefaultHttpClient();
        HttpGet getHttp = new HttpGet(url);

        String content = null;

        HttpResponse response;
        try
        {
            /*get info entity*/
            response = client.execute(getHttp);
            HttpEntity entity = response.getEntity();

            VisitedUrlQueue.addElem(url);

            if (entity != null)
            {
                /* convert to text */
                content = EntityUtils.toString(entity);

                /* judge if can download to computer */
                if (FunctionUtils.isCreateFile(url)
                        && FunctionUtils.isHasGoalContent(content) != -1)
                {
                    FunctionUtils.createFile(FunctionUtils
                            .getGoalContent(content), url);
                }
            }

        } catch (ClientProtocolException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            client.getConnectionManager().shutdown();
        }
        
        return content;
    }

}