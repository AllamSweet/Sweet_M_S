Feature: Sign-Up

  Scenario: :
    Given the following users exist in login sys:
      | UserName | Email        | Password | ConfirmPassword | Phone      | Age |Type|
      | dopah    | zeft1@mail.com | Allamdopah | Allamdopah | 0099887766 | 100  | admin      |
      | tshalb    |zeft2@mail.com | Allamtshalb | Allamtshalb | 0099887755 | 90  |  owner     |
      | zeft    | zeft3@mail.com | Allamzeft | Allamzeft | 0099887744 | 80  |  user     |

  Scenario Outline: Successful sign-up with valid details
    When the person signs up with the following valid details:
      | UserName | Email        | Password | ConfirmPassword | Phone      | Age |
      | <uname>  | <email>      | <pass>   | <confirmPass>   | <phone>    | <age> |
    Then the person should be go back to the login page
    Examples:
      | uname   | email         | pass       | confirmPass | phone      | age |
      | allambomeh | bomeh@mail.com  | bomeh78901  | bomeh78901   | 0000000000 | 85  |

  Scenario Outline: Unsuccessful sign-up with short password
    When the person signs up with the invalid password size :
      | UserName | Email        | Password | ConfirmPassword | Phone      | Age |
      | <uname>  | <email>      | <pass>   | <confirmPass>   | <phone>    | <age> |

    Examples:
      | uname   | email         | pass   | confirmPass | phone      | age |
      | allammdalal | mdalal@mail.com  | mdalal | mdalal      | 9988776622 | 95  |

  Scenario Outline: Unsuccessful sign-up with invalid phone number
    When the person signs up with the invalid phone size details:
      | UserName | Email        | Password | ConfirmPassword | Phone      | Age |
      | <uname>  | <email>      | <pass>   | <confirmPass>   | <phone>    | <age> |

    Examples:
      | uname   | email         | pass      | confirmPass | phone       | age |
      | allamhaywan | haywan@mail.com  |haywan78901 | haywan78901   | 12332199    | 106  |

  Scenario Outline: Unsuccessful sign-up with password mismatch
    When the person signs up with password mismatch details:
      | UserName | Email        | Password | ConfirmPassword | Phone      | Age |
      | <uname>  | <email>      | <pass>   | <confirmPass>   | <phone>    | <age> |

    Examples:
      | uname   | email         | pass      | confirmPass | phone      | age |
      | allamhabab | habab@mail.com  | habab78901 | zeft98701   | 9988774433 | 55  |

  Scenario Outline: Unsuccessful sign-up with existing username
    When the person signs up with existing username details:
      | UserName | Email        | Password | ConfirmPassword | Phone      | Age |
      | <uname>  | <email>      | <pass>   | <confirmPass>   | <phone>    | <age> |

    Examples:
      | uname   | email         | pass      | confirmPass | phone      | age |
      | dopah   | zeft1@mail.com | Allamdopahv | Allamdopah   | 0099887766 | 100   |

  Scenario Outline: Unsuccessful sign-up with empty fields
    When the person signs up with empty fields details:
      | UserName | Email | Password | ConfirmPassword | Phone | Age |
      | <uname>  | <email> | <pass>  | <confirmPass>  | <phone> | <age> |

    Examples:
      | uname   | email         | pass      | confirmPass | phone | age |
      |   allamnerd      |               | nerd78901 | nerd78901   | 8877665544 | 380  |
