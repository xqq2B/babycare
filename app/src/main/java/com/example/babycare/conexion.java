package com.example.babycare;


import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import org.apache.http.util.EntityUtils;

public class conexion {


    public String r_altas(String dir,String d1, String d2,String d3)//dir o url
    {
        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpPost hp= new HttpPost(dir);
            List<NameValuePair> datos = new ArrayList<NameValuePair>();//<> igual a lo declarado
            //datos.add(new BasicNameValuePair("pack","dato"));//nombre del paquete y valor a poner cuando estaba solo
            datos.add(new BasicNameValuePair("dato1",d1));
            datos.add(new BasicNameValuePair("dato2",d2));
            datos.add(new BasicNameValuePair("dato3",d3));

            hp.setEntity(new UrlEncodedFormEntity(datos));

            HttpResponse r = client.execute(hp);
            HttpEntity ent= r.getEntity();

            String texto= EntityUtils.toString(ent);

            return texto;
        }
        catch (Exception xx)
        {
            return xx.toString();
        }
    }

    public String r_datos(String dir,String d1, String d2)
    {
        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpPost hp= new HttpPost(dir);
            List<NameValuePair> datos = new ArrayList<NameValuePair>();
            datos.add(new BasicNameValuePair("dato1",d1));
            datos.add(new BasicNameValuePair("dato2",d2));
            //datos.add(new BasicNameValuePair("dato3",d3));

            hp.setEntity(new UrlEncodedFormEntity(datos));

            HttpResponse r = client.execute(hp);
            HttpEntity ent= r.getEntity();

            String texto= EntityUtils.toString(ent);

            return texto;
        }
        catch (Exception xx)
        {
            return xx.toString();
        }
    }


    public String r_query(String dir)//dir o url
    {
        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpPost hp= new HttpPost(dir);
            List<NameValuePair> datos = new ArrayList<NameValuePair>();//<> igual a lo declarado


            hp.setEntity(new UrlEncodedFormEntity(datos));

            HttpResponse r = client.execute(hp);
            HttpEntity ent= r.getEntity();

            String texto= EntityUtils.toString(ent);

            return texto;
        }
        catch (Exception xx)
        {
            return xx.toString();
        }
    }
}
