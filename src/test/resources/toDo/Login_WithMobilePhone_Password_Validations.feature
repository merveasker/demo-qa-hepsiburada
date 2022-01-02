Feature: Hepsiburada Login Password Validations with Mobile Phone Login Type

  Scenario: Visit Hepsiburada and Navigate To Welcome Page
    Given User visits Hepsiburada
    And Navigates to Login Page
    And Enters valid and registered mobile phone and valid verification code
    And Navigates to Welcome Page

  Scenario: Try To Login with wrong password
    Given User enters wrong password
    When User clicks Login button
    Then Gets "Girdiğiniz şifre eksik veya hatalı. Kontrol edip tekrar deneyin." validation message

  Scenario: Try To Login with empty password
    Given User enters no password
    Then User gets "Şifrenizi girmelisiniz." validation message on the page
    And Login button becomes disabled