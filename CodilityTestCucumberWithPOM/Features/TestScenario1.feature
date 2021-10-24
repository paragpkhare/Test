Feature: Test addition of items to the wishlist and add to cart based on lowest item price

  Scenario: Verify if we add four different products on the wishlist we are able to see them in the wishlist page and we are also able to add the lowest priced item to the cart
    Given I add four different products to my wish list
    When I view my wishlist table
    Then I find total of four selected items in my Wishlist
    When I search for lowest price product
    And I am able to add the lowest price item to my cart
    Then I am able to verify the item in my cart
