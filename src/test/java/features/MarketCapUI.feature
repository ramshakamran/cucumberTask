Feature: Market Cap API

Scenario: Select and Verify 'Show rows' dropdown value to 100
  Given Initialize the browser with chrome
  And Navigate to "https://coinmarketcap.com" website
  When User Select 'Show rows' dropdown value to "100"
  Then Verify that "100" rows are displayed
  And close browsers


Scenario: Filter and Verify records by MarketCap and Price
  Given Initialize the browser with chrome
  And Navigate to "https://coinmarketcap.com" website
  When User select Add filters
  And User Select filter records by MarketCap "1,000,000,000" and "10,000,000,000"
  And  set Price "101" and "1000"
  Then Check records displayed on page are correct as per the filter applied
  And close browsers


