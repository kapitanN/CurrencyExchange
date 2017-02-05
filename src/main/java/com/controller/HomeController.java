package com.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dao.AuthenticationBean;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by nikita on 21.01.2017.
 */

@Controller
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS , value = "request")
public class HomeController {
    // essential URL structure is built using constants
    public static final String ACCESS_KEY = "9aba2b1cb3413b66cedab2f8740505aa";
    public static final String BASE_URL = "http://apilayer.net/api/";
    public static final String ENDPOINT = "live";

    private static final Logger LOGGER = Logger.getLogger(HomeController.class);

    @Autowired
    private AuthenticationBean userBean;

    // this object is used for executing requests to the (REST) API
    static CloseableHttpClient httpClient = HttpClients.createDefault();
    /**
     *
     * Notes:
     *
     * A JSON response of the form {"key":"value"} is considered a simple Java JSONObject.
     * To get a simple value from the JSONObject, use: <JSONObject identifier>.get<Type>("key");
     *
     * A JSON response of the form {"key":{"key":"value"}} is considered a complex Java JSONObject.
     * To get a complex value like another JSONObject, use: <JSONObject identifier>.getJSONObject("key")
     *
     * Values can also be JSONArray Objects. JSONArray objects are simple, consisting of multiple JSONObject Objects.
     *
     *
     */
    // sendLiveRequest() function is created to request and retrieve the data
    @RequestMapping(value = "/api")
    @ResponseBody
    public ModelAndView sendLiveRequest() throws IOException {

        LOGGER.info("In home controller");
        // The following line initializes the HttpGet Object with the URL in order to send a request
        HttpGet get = new HttpGet(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY);
        ModelAndView modelAndView = new ModelAndView();
        if(userBean.getEmail() == null){
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }else {
        modelAndView.addObject("loginUser", userBean);
        modelAndView.addObject("registrationUser", userBean);
        modelAndView.setViewName("home");
//        try {
//            CloseableHttpResponse response =  httpClient.execute(get);
//            HttpEntity entity = response.getEntity();
//
//            // the following line converts the JSON Response to an equivalent Java Object
//            JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));
//
//            System.out.println("Live Currency Exchange Rates");
//
//            // Parsed JSON Objects are accessed according to the JSON resonse's hierarchy, output strings are built
//            Date timeStampDate = new Date((long)(exchangeRates.getLong("timestamp")*1000));
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
//            String formattedDate = dateFormat.format(timeStampDate);
//            String api = exchangeRates.getString("source") + " in UAH : " + exchangeRates.getJSONObject("quotes").getDouble("USDUAH");
//            String api1 = exchangeRates.getString("source") + " in EUR : " + exchangeRates.getJSONObject("quotes").getDouble("USDEUR");
//            String api2 = exchangeRates.getString("source") + " in RUB : " + exchangeRates.getJSONObject("quotes").getDouble("USDRUB");
//            String api3 = exchangeRates.getString("source") + " in GBP : " + exchangeRates.getJSONObject("quotes").getDouble("USDGBP");
//            String api4 = exchangeRates.getString("source") + " in PLN : " + exchangeRates.getJSONObject("quotes").getDouble("USDPLN");
//            String date = "Date: " + formattedDate;
//            modelAndView.addObject("api", api);
//            modelAndView.addObject("api1", api1);
//            modelAndView.addObject("api2", api2);
//            modelAndView.addObject("api3", api3);
//            modelAndView.addObject("api4", api4);
//            modelAndView.addObject("date", date);
//            response.close();
//
//        } catch (ClientProtocolException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (JSONException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        //httpClient.close();
        return modelAndView;
        }
    }

    @RequestMapping(value = "/exit", method = RequestMethod.POST)
    public String exit(HttpSession session){
        LOGGER.info("exit");
        session.invalidate();
        return "redirect:/";
    }
}
