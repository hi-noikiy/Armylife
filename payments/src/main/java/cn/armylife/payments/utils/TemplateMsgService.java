package cn.armylife.payments.utils;import org.springframework.stereotype.Service;import javax.servlet.http.HttpServlet;import java.io.BufferedReader;import java.io.IOException;import java.io.InputStreamReader;import java.net.URL;import java.net.URLConnection;@Servicepublic class TemplateMsgService extends HttpServlet {    //HttpGet方法    public static String HttpGet(String URL, String GetDataStr){        String getUrl = URL+GetDataStr;        StringBuffer sb = new StringBuffer();        InputStreamReader isr = null;        BufferedReader br = null;        try        {            java.net.URL url = new URL(getUrl);            URLConnection urlConnection = url.openConnection();            urlConnection.setAllowUserInteraction(false);            isr = new InputStreamReader(url.openStream());            br = new BufferedReader(isr);            String line;            while ((line = br.readLine()) != null)            {                sb.append(line);            }        }        catch (IOException e)        {            e.printStackTrace();        }        finally        {            try {                br.close();                isr.close();            } catch (IOException e) {                e.printStackTrace();            }        }        return sb.toString();    }}