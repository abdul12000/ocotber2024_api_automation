Feature: RestfulBooker
@Smoke
  Scenario: Get details of a booking
    Given I have an endpoint for retreiving a booking
    When I retrieve a bookingId "1027"
    Then firstname of "John", lastname of "Smith" and statuscode of 200 are displayed
#  @Smoke
  Scenario: Get bookingIds
    Given I have an endpoint for retreiving bookingIds
    When I send request for getBookingIds
    Then statuscode of 200 is returned

#  @Smoke
  Scenario Outline: Create a new booking
    Given I have an endpoint for creating bookings
    When I send request to create a new booking with "<firstName>", "<lastName>", "<totalPrice>", "<depositPaid>", "<CheckIn>", "<CheckOut>"  and "<additionalNeeds>"
    Then "<firstName>", "<lastName>" and statuscode of 200 are returned
    Examples:
      | firstName | lastName | totalPrice | depositPaid | CheckIn    | CheckOut   | additionalNeeds                |
      | Lateef    | Sheyi    | 200        | true        | 2025-04-01 | 2026-04-01 | Lunch and trip to Burg Khalifa |

#  @Smoke
  Scenario Outline: Update a booking using PUT and Authorization
    Given I have an endpoint for creating bookings
    When I send request to update a booking "<id>" with "<firstName>", "<lastName>", "<totalPrice>", "<depositPaid>", "<CheckIn>", "<CheckOut>"  and "<additionalNeeds>"
    Then "<firstName>", "<lastName>", "<totalPrice>", "<additionalNeeds>" and statuscode of 200 are returned for the updated booking
    Examples:
      | id   | firstName | lastName | totalPrice | depositPaid | CheckIn    | CheckOut   | additionalNeeds                |
      | 114 | LateefAA    | SheyiAA    | 207        | true        | 2025-04-01 | 2026-04-01 | Lunch  |


#      @Smoke
  Scenario: Delete bookingId
    Given I have an endpoint for deleting bookings
    When I send request to delete a booking "1027"
    Then statuscode of 201 is returned for the delete booking


  @Smoke
  Scenario Outline: Delete Booking after Creating a new booking
    Given I have an endpoint for creating bookings
    When I send request to create a new booking with "<firstName>", "<lastName>", "<totalPrice>", "<depositPaid>", "<CheckIn>", "<CheckOut>"  and "<additionalNeeds>"
    When I send request to delete a booking
    Then statuscode of 201 is returned for the delete booking
    Examples:
      | firstName | lastName | totalPrice | depositPaid | CheckIn    | CheckOut   | additionalNeeds                |
      | Lateef    | Sheyi    | 200        | true        | 2025-04-01 | 2026-04-01 | Lunch and trip to Burg Khalifa |
