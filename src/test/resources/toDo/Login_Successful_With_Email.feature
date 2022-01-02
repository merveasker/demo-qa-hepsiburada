Feature: Hepsiburada Successful Login With a registered EMail and Password

  Scenario: A registered user visits Hepsiburada
    Given Merve has a valid credentials for Hepsiburada
    And User visits Hepsiburada

  Scenario: Navigates to Welcome Page
    Given Navigates to Login Page
    When She enters registered email
    And She clicks Giriş Yap button
    Then She is on the Welcome Page

  Scenario: Logs in to Hepsiburada
    When She enters registered password
    And She clicks Giriş Yap button
    Then She can see her own account


