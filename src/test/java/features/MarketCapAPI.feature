Feature: Market Cap API

  Scenario Outline: Retrieve the ID of bitcoin (BTC), usd tether (USDT), and Ethereum (ETH)
    Given Request send to /cryptocurrency/map
    When  The status code is 200
    Then  Get the ids of "<CoinType>"
    And Convert the currency for a amount of "50"
    Examples:
      |CoinType|
      |Bitcoin|
      |Tether|
      |Ethereum|

  Scenario: Retrieve the information of Ethereum
    Given Request send to /cryptocurrency/info with id "1027"
    When  The status code is 200
    Then  Check logo url
    And Check technical doc url
    And Check the symbol
    And Check the date added
    And check  for the mineable tag

  Scenario: Retrieve the first 10 currencies from the cryptocurrency/info call (ID 1, 2, 3 … 10).
    Given Request send to /cryptocurrency/info for first 10 ids
    When  The status code is 200
    Then Check  for the mineable tag in response cryptocurrencies
    And Print mineable currency names
