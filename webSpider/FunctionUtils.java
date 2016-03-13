package com.sreach.spider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionUtils
{

    /**
     * regular expression
     */
    private static String pat = "http://www\\.oschina\\.net/code/explore/.*/\\w+\\.[a-zA-Z]+";
    private static Pattern pattern = Pattern.compile(pat);

    private static BufferedWriter writer = null;

    /**
     * spider grab depth
     */
    public static int depth = 0;

    /**
     * split URL by ¡±/¡° to get hyperlink content
     * 
     * @param url
     * @return
     */
    public static String[] divUrl(String url)
    {
        return url.split("/");
    }

    /**
     * @param url
     * @return
     */
    public static boolean isCreateFile(String url)
    {
        Matcher matcher = pattern.matcher(url);

        return matcher.matches();
    }

    /**
     * @param content
     * @param urlPath
     */
    public static void createFile(String content, String urlPath)
    {
        String[] elems = divUrl(urlPath);
        StringBuffer path = new StringBuffer();

        File file = null;
        for (int i = 1; i < elems.length; i++)
        {
            if (i != elems.length - 1)
            {

                path.append(elems[i]);
                path.append(File.separator);
                file = new File("D:" + File.separator + path.toString());

            }

            if (i == elems.length - 1)
            {
                Pattern pattern = Pattern.compile("\\w+\\.[a-zA-Z]+");
                Matcher matcher = pattern.matcher(elems[i]);
                if ((matcher.matches()))
                {
                    if (!file.exists())
                    {
                        file.mkdirs();
                    }
                    String[] fileName = elems[i].split("\\.");
                    file = new File("D:" + File.separator + path.toString()
                            + File.separator + fileName[0] + ".txt");
                    try
                    {
                        file.createNewFile();
                        writer = new BufferedWriter(new OutputStreamWriter(
                                new FileOutputStream(file)));
                        writer.write(content);
                        writer.flush();
                        writer.close();
                        System.out.println(¡°success¡±);
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
            }

        }
    }

    /**
     * grab page hyperlink and convert to formal A label
     * 
     * @param href
     * @return
     */
    public static String getHrefOfInOut(String href)
    {
        /* convert link to complete form */
        String resultHref = null;

        /* check if out link */
        if (href.startsWith("http://"))
        {
            resultHref = href;
        } else
        {
            /* if it is inner link, then add complete address and otherwise omit it */
            if (href.startsWith("/"))
            {
                resultHref = "http://www.oschina.net" + href;
            }
        }

        return resultHref;
    }

    /**
     * grab source page content
     * 
     * @param content
     * @return
     */
    public static String getGoalContent(String content)
    {
        int sign = content.indexOf("<pre class=\"");
        String signContent = content.substring(sign);

        int start = signContent.indexOf(">");
        int end = signContent.indexOf("</pre>");

        return signContent.substring(start + 1, end);
    }

    /**
     * check if source page contains target file
     * 
     * @param content
     * @return
     */
    public static int isHasGoalContent(String content)
    {
        return content.indexOf("<pre class=\"");
    }

}