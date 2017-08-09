package com.vitordinis.ajsonlistdetail;

/**
 * Created by haunted on 08/08/2017.
 */

public class UserModel {
    public int id;
    public String name;
    public String username;
    public String email;
    public AddressModel address;
    public String phone;
    public String website;
    public CompanyModel company;
    public MessageModel[] messages;
}

class AddressModel {
    public String street;
    public String suite;
    public String city;
    public String zipcode;
    public GeoModel geo;
}

class GeoModel {
    public String lat;
    public String lng;
}

class CompanyModel {
    public String name;
    public String catchPhrase;
    public String bs;
}

class MessageModel {
    public int userId;
    public int id;
    public String title;
    public String body;
}

/*

{
    "userId": 2,
    "id": 11,
    "title": "et ea vero quia laudantium autem",
    "body": "delectus reiciendis molestiae occaecati non minima eveniet qui voluptatibus\naccusamus in eum beatae sit\nvel qui neque voluptates ut commodi qui incidunt\nut animi commodi"
  }
* {
    "id": 1,
    "name": "Leanne Graham",
    "username": "Bret",
    "email": "Sincere@april.biz",
    "address": {
      "street": "Kulas Light",
      "suite": "Apt. 556",
      "city": "Gwenborough",
      "zipcode": "92998-3874",
      "geo": {
        "lat": "-37.3159",
        "lng": "81.1496"
      }
    },
    "phone": "1-770-736-8031 x56442",
    "website": "hildegard.org",
    "company": {
      "name": "Romaguera-Crona",
      "catchPhrase": "Multi-layered client-server neural-net",
      "bs": "harness real-time e-markets"
    }
  }
* */