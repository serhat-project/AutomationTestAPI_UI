@api

Feature: SignUp Api Feature

  User Story: When signup by api, response body must be asserted

@api1
  Scenario Outline:Signup with valid credentials in api
    When logged in with "<email>" and "<password>" in api and success message must be true
    And new signed up user log in with "<email>" and "<password>" and take id of new user
    And root user logs in with valid email "userForTest@gmail.com" and "userForTest@gmail.com1" and delete user


    Examples:
      | email                | password |
      | NewTest123456789abcdef@gmail.com | kl.2dkaB |

@api2
  Scenario Outline: Signup with invalid credentials in api
    When logged in with invalid "<email>" and "<password>" in api and success message must be false

    Examples:
      | email                                                                         | password          |
      | NewTest123@gmail.com                                                          | Serhat123!!!      |
      | test1.hotmail.com                                                             | K12345a.yuz       |
      | serhat@gmail.com                                                              | Tes               |
      | test2@hotmail                                                                 | K12345a.yuz       |
      |                                                                               | kl.2dkaB          |
      | testetetettetetetettetetettetetetetettaeteaaaasasasasatet123.AAAAAA@gmail.com | kl.2dkaB          |
      | aslan123@gmail.com                                                            |                   |
      | test1112.hotmail.com                                                          | mer ha ba Dunya1! |
