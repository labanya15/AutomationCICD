@tag
  Feature: Purchase the Order from Ecommerse Website

    Background:
      Given I landed on ECommerse Page

    @Regression
    Scenario Outline: Positive test for submitting the order
      Given Logged in with username <name> and password <password>
      When When I add product <productName> to Cart
      And Checkout <productName> and submit the order
      Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

      Examples:
      |name                 |password  |productName|
      |testframe@getnada.com|Labanya15#|ZARA COAT 3|