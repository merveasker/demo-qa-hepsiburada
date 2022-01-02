Feature: Hepsiburada Login Validations With Wrong Verification Code

  Scenario: Visit Hepsiburada and navigate to Welcome Page
    Given User visits Hepsiburada
    And Navigates to Login Page
    When Enters a valid and registered mobile phone
    And Navigates to Verification Code Page
    And User gets a new verification code for entering in 3 minutes

  Scenario Outline: Try to Login with empty verification code
    When User enters "verification code" verification code
    And Confirm button stays disabled
    Examples:
      | verification code |
      | abcdef            |
      |                   |

  Scenario Outline: Try to Login with wrong verification code with 6 times in 3 minutes
    When User enters "verification code" verification code
    Then Gets "validation message" validation message
    Examples:
      | verification code | validation message                                                                   |
      | 111111            | Doğrulama kodu hatalı Lütfen kontrol edip tekrar deneyin.                            |
      | 222222            | Doğrulama kodu hatalı Lütfen kontrol edip tekrar deneyin.                            |
      | 333333            | Doğrulama kodu hatalı Lütfen kontrol edip tekrar deneyin.                            |
      | 111111            | Doğrulama kodu hatalı Lütfen kontrol edip tekrar deneyin.                            |
      | 222222            | Doğrulama kodu hatalı Lütfen kontrol edip tekrar deneyin.                            |
      | 333333            | Doğrulama kodu hatalı  Lütfen süre dolduktan sonra yeni bir kod alıp tekrar deneyin. |

  Scenario: After Wrong entiries waits 3 minutes, user resends new verification code
    Given User waits for 3 minutes
    And User can not enter any other Verification Code
    And Send New Code button becomes disabled
    When User clicks Send New Code button
    Then User gets a new verification code for entering in 3 minutes

  Scenario: Try to use old verification code
    When User enters an old verification code
    Then Gets "Doğrulama kodu hatalı Lütfen kontrol edip tekrar deneyin." validation message

  Scenario: User waits 3 minutes with no entry
    When User clicks Send New Code button
    And User waits for 3 minutes
    Then Gets "Süreniz doldu Lütfen yeni bir kod alıp tekrar deneyin." validation message






