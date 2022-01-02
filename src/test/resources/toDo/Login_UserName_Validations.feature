Feature: Hepsiburada Login Validations With Wrong Format Or Not Registered Email/Mobile Phone

  Scenario: Visit Hepsiburada and Navigate To Login Page
    Given User visits Hepsiburada
    And Navigates to Login Page
    Then User sees Login page properly

  Scenario Outline: Try To Enter an email with invalid format
    When User enters invalid "email" input
    Then User gets "validation message" validation message on the page
    When User clicks Login button
    Then Gets "validation message" validation message
    Examples:
      | email    | validation message                                       |
      |          | E-posta adresinizi veya telefon numaranızı girmelisiniz. |
      | abcdefgh | Geçerli bir e-posta adresi girmelisiniz.                 |
      | a@       | Geçerli bir e-posta adresi girmelisiniz.                 |
      | a@e      | Geçerli bir e-posta adresi girmelisiniz.                 |
      | a@e.c    | Geçerli bir e-posta adresi girmelisiniz.                 |
      | q@e.co   | Geçerli bir e-posta adresi girmelisiniz.                 |
      | abcdef   | Geçerli bir e-posta adresi girmelisiniz.                 |
      | q@abcd   | Geçerli bir e-posta adresi girmelisiniz.                 |
      | 0a       | Geçerli bir e-posta adresi girmelisiniz.                 |
      | b@1.1c   | Geçerli bir e-posta adresi girmelisiniz.                 |

  Scenario Outline: Try To Enter a mobile phone with invalid format
    When User enters invalid "mobile phone" input
    Then User gets "Geçerli bir cep telefonu girmelisiniz" validation message on the page
    When User clicks Login button
    Then Gets "Geçerli bir cep telefonu girmelisiniz" validation message
    Examples:
      | mobile phone |
      | 05           |
      | 0535663759   |
      | 053555375907 |
      | 53566375907  |
      | 01356637599  |
      | 02356637599  |
      | 03356637599  |
      | 04356637599  |
      | 06356637599  |
      | 07356637599  |
      | 08356637599  |
      | 09356637599  |
      | 1356637599   |
      | 2356637599   |
      | 3356637599   |
      | 4356637599   |
      | 6356637599   |
      | 7356637599   |
      | 8356637599   |
      | 9356637599   |
      | 5096637599   |
      | 5196637599   |
      | 5296637599   |
      | 5596637599   |
      | 5696637599   |
      | 5796637599   |
      | 5896637599   |
      | 5996637599   |

  Scenario Outline: Try To Enter a valid email but that is not registered
    When User enters valid but not registed "email" input
    Then User gets no instant validation message on the page
    When User clicks Login button
    And Gets "E-posta adresi eksik veya hatalı. Girdiğiniz bilgiler ile herhangi bir hesabı eşleştiremedik, kontrol edip tekrar deneyin." validation message
    Examples:
      | email               |
      | 1@1a.1              |
      | b@1.11              |
      | b@1.11              |
      | abcdefg123@test.com |
      | ABCDEFG123@TEST.COM |

  Scenario Outline: Try To Enter a valid mobile phone but that is not registered
    When User enters valid but not registed "mobile phone" input
    Then User gets no instant validation message on the page
    When User clicks Login button
    And Gets "Telefon numarası eksik veya hatalı. Girdiğiniz telefon numarası ile herhangi bir hesabı eşleştiremedik, kontrol edip tekrar deneyin." validation message
    Examples:
      | mobile phone |
      | 05399999099  |
      | 05499999099  |
      | 5399999099   |
      | 5499999099   |



