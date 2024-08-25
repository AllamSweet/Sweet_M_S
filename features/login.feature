Feature: Login page


  Scenario:
    Given the following people exist in login table:
      | UserName | Password |Type|
      | 2 | Allamdopah |admin |
      | tshalb | Allamtshalb |owner |
      | zeft | Allamzeft |user |
  Scenario Outline:  : Successful login with valid input credentials
    When the person who logged in system with the following valid info:
      | UserName   | Password        |
      | <uname> | <pass>      |
    Examples:
      | uname | pass      |
      | dopah     | Allamdopah         |
      | tshalb     | Allamtshalb       |

  Scenario Outline: : Unsuccessful login with invalid input credentials
    When the person who logged in system with the following invalid details:
      | UserName   | Password        |
      | <uname> | <pass>      |
    Then the person should be still in login_page
    Examples:
      | uname | pass      |
      | Allammotanamer     | motanamerwzefet         |


  Scenario Outline: : Unsuccessful login with empty username
    When the person logged in with the following empty username details:
      | UserName   | Password        |
      | <uname> | <pass>      |
    Then the person should be still in login_page
    Examples:
      | uname | pass      |
      |      | Allamdopah         |

  Scenario Outline: : Unsuccessful login with empty password
    When the person logged in with the following empty password details:
      | UserName   | Password        |
      | <uname> | <pass>      |
    Then the person should be still in login_page
    Examples:
      | uname | pass      |
      |  dopah    |          |




  Scenario Outline: : Unsuccessful login with both fields empty
    When the person logged in with the following both empty details:
      | UserName   | Password        |
      | <uname> | <pass>      |
    Then the person should be still in login_page
    Examples:
      | uname | pass      |
      |      |          |