Feature: Hepsiburada Successful Login With a registered Mobile Phone, Verification Code and Password

  Scenario: A registered user visits Hepsiburada
    Given Merve has a valid credentials for Hepsiburada
    And User visits Hepsiburada

  Scenario: Navigates to Code Verification Page
    Given Navigates to Login Page
    When She enters registered mobile phone
    And She clicks Giriş Yap button
    Then She is on the Code Verification Page

  Scenario: Logs in to Hepsiburada
    When She enters an accurate verification code
    Then She can see her own account