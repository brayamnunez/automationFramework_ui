$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("bookFlight_RyanAir.feature");
formatter.feature({
  "line": 1,
  "name": "Booking up a flight with a wrong credit card number and catch paymeny declined message",
  "description": "",
  "id": "booking-up-a-flight-with-a-wrong-credit-card-number-and-catch-paymeny-declined-message",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 4,
  "name": "Booking a flight with wrong credit card number and catch payment declined error message",
  "description": "",
  "id": "booking-up-a-flight-with-a-wrong-credit-card-number-and-catch-paymeny-declined-message;booking-a-flight-with-wrong-credit-card-number-and-catch-payment-declined-error-message",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 3,
      "name": "@ryanair"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "I login into myryanair account",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I make a booking reservation from \"Brussels Charleroi\" to \"Wroclaw\" on date \"10/07/2019\" for \"2\" adults and \"3\" other \"teen(s)\"",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "I fulfill personal info for \"5\" passengers: \"\u003cpassengers\u003e\", and country \"Spain\" and mobile phone: \"698785478\"",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "I fulfill payment details with:",
  "rows": [
    {
      "cells": [
        "firstName",
        "lastName",
        "address",
        "country",
        "city",
        "postalCode",
        "creditCardNumber",
        "cvc",
        "expMonth",
        "expYear"
      ],
      "line": 9
    },
    {
      "cells": [
        "Jeramiah",
        "Turner",
        "Essex Court 87",
        "Switzerland",
        "Switzerland",
        "12345",
        "5236223720240780",
        "112",
        "5",
        "2026"
      ],
      "line": 10
    }
  ],
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "I should get payment declined message: \"As your payment was not authorised we could not complete your reservation. Please ensure that the information was correct or use a new payment to try again\"",
  "keyword": "Then "
});
formatter.examples({
  "line": 13,
  "name": "",
  "description": "",
  "id": "booking-up-a-flight-with-a-wrong-credit-card-number-and-catch-paymeny-declined-message;booking-a-flight-with-wrong-credit-card-number-and-catch-payment-declined-error-message;",
  "rows": [
    {
      "cells": [
        "passengers"
      ],
      "line": 14,
      "id": "booking-up-a-flight-with-a-wrong-credit-card-number-and-catch-paymeny-declined-message;booking-a-flight-with-wrong-credit-card-number-and-catch-payment-declined-error-message;;1"
    },
    {
      "cells": [
        "Mr Jaime Rodriguez, Ms Marjorie Rodriguez, Mr Brayam Nunez, Ms Lola Martinez, Ms Juana Lors"
      ],
      "line": 15,
      "id": "booking-up-a-flight-with-a-wrong-credit-card-number-and-catch-paymeny-declined-message;booking-a-flight-with-wrong-credit-card-number-and-catch-payment-declined-error-message;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 21381911149,
  "status": "passed"
});
formatter.scenario({
  "line": 15,
  "name": "Booking a flight with wrong credit card number and catch payment declined error message",
  "description": "",
  "id": "booking-up-a-flight-with-a-wrong-credit-card-number-and-catch-paymeny-declined-message;booking-a-flight-with-wrong-credit-card-number-and-catch-payment-declined-error-message;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 3,
      "name": "@ryanair"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "I login into myryanair account",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I make a booking reservation from \"Brussels Charleroi\" to \"Wroclaw\" on date \"10/07/2019\" for \"2\" adults and \"3\" other \"teen(s)\"",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "I fulfill personal info for \"5\" passengers: \"Mr Jaime Rodriguez, Ms Marjorie Rodriguez, Mr Brayam Nunez, Ms Lola Martinez, Ms Juana Lors\", and country \"Spain\" and mobile phone: \"698785478\"",
  "matchedColumns": [
    0
  ],
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "I fulfill payment details with:",
  "rows": [
    {
      "cells": [
        "firstName",
        "lastName",
        "address",
        "country",
        "city",
        "postalCode",
        "creditCardNumber",
        "cvc",
        "expMonth",
        "expYear"
      ],
      "line": 9
    },
    {
      "cells": [
        "Jeramiah",
        "Turner",
        "Essex Court 87",
        "Switzerland",
        "Switzerland",
        "12345",
        "5236223720240780",
        "112",
        "5",
        "2026"
      ],
      "line": 10
    }
  ],
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "I should get payment declined message: \"As your payment was not authorised we could not complete your reservation. Please ensure that the information was correct or use a new payment to try again\"",
  "keyword": "Then "
});
formatter.match({
  "location": "BookFlight_steps.iLoginIntoMyryanairAccount()"
});
formatter.result({
  "duration": 6274991296,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Brussels Charleroi",
      "offset": 35
    },
    {
      "val": "Wroclaw",
      "offset": 59
    },
    {
      "val": "10/07/2019",
      "offset": 77
    },
    {
      "val": "2",
      "offset": 94
    },
    {
      "val": "3",
      "offset": 109
    },
    {
      "val": "teen(s)",
      "offset": 119
    }
  ],
  "location": "BookFlight_steps.iMakeABookingReservationFromToOnDateForAdults(String,String,String,String,String,String)"
});
formatter.result({
  "duration": 52606647355,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "5",
      "offset": 29
    },
    {
      "val": "Mr Jaime Rodriguez, Ms Marjorie Rodriguez, Mr Brayam Nunez, Ms Lola Martinez, Ms Juana Lors",
      "offset": 45
    },
    {
      "val": "Spain",
      "offset": 152
    },
    {
      "val": "698785478",
      "offset": 178
    }
  ],
  "location": "BookFlight_steps.iFulfillPersonalInfoForPassengersAndCountryAndMobilePhone(String,String,String,String)"
});
formatter.result({
  "duration": 20799581525,
  "status": "passed"
});
formatter.match({
  "location": "BookFlight_steps.iFulfillCardDetailsCardExpCardDateAndCVC(Passenger\u003e)"
});
formatter.result({
  "duration": 15688124213,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "As your payment was not authorised we could not complete your reservation. Please ensure that the information was correct or use a new payment to try again",
      "offset": 40
    }
  ],
  "location": "BookFlight_steps.iShouldGetPaymentDeclinedMessage(String)"
});
formatter.result({
  "duration": 5371355412,
  "status": "passed"
});
formatter.after({
  "duration": 5295603853,
  "status": "passed"
});
});