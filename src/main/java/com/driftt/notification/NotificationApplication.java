package com.driftt.notification;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

public class NotificationApplication {

    public static final String ACCOUNT_SID = "ACc0fcf1db8e0874424d64c52105208fd8";
    public static final String AUTH_TOKEN = "c609ec126d0737b221e599a05a3035b1";
    public static final String FROM_NUMBER = "+19378702747";
    public static final String ACTUAL_MESSAGE = "Your agent is available to chat now";

    public static void main(String[] args) {
        post("/notify/:MobileNum", new Route() {
            public Object handle(Request request, Response response) {
                String toNumber = "+1" + request.params("MobileNum");  //+14079709510
                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

                Message message = Message.creator(new PhoneNumber(toNumber),
                                                  new PhoneNumber(FROM_NUMBER),
                                                  ACTUAL_MESSAGE).create();

                System.out.println(message.getSid());
                return message.getStatus(); // 404 Not found - unverified number\Invalid number
            }
        });
    }
}