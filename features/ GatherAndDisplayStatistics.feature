Feature:  Gather and display statistics on registered users by City

  Scenario:
    Given the following users are registered:
    |Name|City|
    |Allam|jenen|
    |abdo |nablus|
    |albe     |jenen      |


    Scenario: Display user statistics
      Then the statictics should be display as follows:
      | City      | Number of users    |
      | nablus    |         1          |
      |   jenen   |         2          |
