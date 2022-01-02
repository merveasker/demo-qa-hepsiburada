Feature: Login Hepsiburada and Add Product to Shopping Cart Without Login

  Scenario: Visit Hepsiburada
    Given User visits Hepsiburada

  Scenario: Searching an accurate product/category/brand name that is present in inventory and getting displayed similar named products
    When She searches for "laptop"
    Then Search page should be updated with the "laptop" list

  Scenario: Selecting a product from the search results
    When She selects a "laptop" product from the search results
    Then She can see product's details

  Scenario: Add Same Product To Cart With different Sellers(If Exists) And Check The Cart
    When She adds the product to the cart with default selected seller
    And If there is an another seller she adds same product with another seller to her cart
    And She goes to her cart
    Then She sees her cart contains all the products that she added

    Scenario: Empty the cart
      Given She empties the cart





