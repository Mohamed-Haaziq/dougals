Feature: Login
  Validation of the login feature

  @login
  @validLogin
  @regression
  Scenario: Verify successful login
    Given I am on Douglas login page
    And I accept cookies agreement
    And I enter valid username
    And I enter valid password
    When I click on login
    Then I should see a popup with welcome note on successful login

    @login
    @loginValidations
    @regression
  Scenario Outline: Verify login validations

      Given I am on Douglas login page
      And I accept cookies agreement
      And I enter invalid username <inValidUserName>
      And I enter invalid password <invalidPassword>
      When I click on login
      Then I should see an error message <errorMessage>

      Examples:
        | inValidUserName | invalidPassword | errorMessage |
        | deenhaaz@gmail.com   | Haaziq1! | Ihre Eingabedaten sind leider fehlerhaft, stimmen Benutzername und Passwort? |
        | deenhaaziq@gmail.com   | Haaziq1 | Ihre Eingabedaten sind leider fehlerhaft, stimmen Benutzername und Passwort? |
        | deenhaaz@gmail.com   | Haaziq1 | Ihre Eingabedaten sind leider fehlerhaft, stimmen Benutzername und Passwort? |

  @login
  @regression
  @forgotPassword
  Scenario: Verify forgot password functionality
    Given I am on Douglas login page
    And I accept cookies agreement
    When I click on forgot password
    And I enter email in forgot password popup
    And I type the recaptcha
    And I click on send forgot password email button
    Then I should see a success message with email address