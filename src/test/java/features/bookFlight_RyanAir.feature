Feature: Booking up a flight with a wrong credit card number and catch paymeny declined message

  @ryanair
  Scenario Outline: Booking a flight with wrong credit card number and catch payment declined error message
    Given I login into myryanair account
    Then I make a booking reservation from "Brussels Charleroi" to "Wroclaw" on date "10/07/2019" for "2" adults and "3" other "teen(s)"
    And I fulfill personal info for "5" passengers: "<passengers>", and country "Spain" and mobile phone: "698785478"
    When I fulfill payment details with:
      | firstName | lastName | address        | country     | city        | postalCode | creditCardNumber | cvc | expMonth | expYear |
      | Jeramiah  | Turner   | Essex Court 87 | Switzerland | Switzerland | 12345      | 5236223720240780 | 112 | 5        | 2026    |
    Then I should get payment declined message: "As your payment was not authorised we could not complete your reservation. Please ensure that the information was correct or use a new payment to try again"

    Examples:
      | passengers                                                                                  |
      | Mr Jaime Rodriguez, Ms Marjorie Rodriguez, Mr Brayam Nunez, Ms Lola Martinez, Ms Juana Lors |