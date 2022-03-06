# Changelog

All notable changes to this project will be documented in this file

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/)

Dates in this file will have the following format: MM/DD/YYYY

1) Branch "main" was created with repository
2) I will work with branching methodology. Each branch will be one feature (one ticket in JIRA)
3) When one feature is finished, with its tests, branch will be merged with "main" branch as you can see in [github](https://github.com/aorizzuto/mytheresa)

## [0.5.0] - 03/06/2022 - feature/adding-logs
### Added
- More logs
- File "mytheresa.postman_collection.json" with all the request used for this challenge
- Local environment for H2 usage. Leaving DEV environment for another dbms.

## [0.4.0] - 03/06/2022 - feature/product-endpoint-tests
### Added
- Tests over validations
- Tests over productService
- Using Mockito annotations @Mock, @Spy, @Captor
- Using ArgumentMatcher for mocks
- Using @Test and @ParameterizedTest annotations
- MockitoUtils created to get anyObject() NOT NULL and perform some tests than accept not null parameters
- Mockito.times used to check invocations to a mock
- Tests over discountService
- Controller tests. Endpoint /products tests
- Coverage with tests of 95%. See "coverage.png" image

### Modified
- Added products.isNotEmpty() validation after database to skip discounts in case we does not have any product that matches request
- Added discount column in CategoryEnum to know if the category has a "category discount". This way the "30% discount" hardcoded is removed from discountService

## [0.3.0] - 03/05/2022 - feature/exception-handling
### Added
- GlobalExceptionHandler created to handle response in controller
- See "ExceptionBadRequest.png" image to see Exception when category field is missing
- See "ExceptionLowerThanZero.png" image to see Exception when discount is lower than zero
- ErrorCode enum was created to encapsulate all error codes in one place
- ResponseEntityBody is the DTO in response

## [0.2.0] - 03/05/2022 - Branch: feature/products-endpoint
### Added
- GET /products endpoint created
- Query param category and priceLessThan (optional) added
- ProductResponseDTO and PriceDTO created
- CurrencyEnum created to have all currencies. For the challenge I only write EUR but we can use it to add more currencies.
- ProductController created
- ProductService created
- Validate input request
- LoggerUtils
- I used @Value in ProductService to get configuration from app.properties
- ProductConverter: To convert Entity to DTO and DTO to Response with or without discount
- Discounts have been applied. See img "bootsdiscount.png" and "sku3discount.png"
- Also no discount are applied when discounts are not necessary. See img "noDiscount.png".

### NOTE
- @JsonProperty used to specify the value in the response. When the variable is called the same way than the json property is redundant and we can remove the @JsonProperty annotation but I like to let the annotation anyway just to have a better view of the code.
- PriceDTO.currency have EUR as a default value in its constructor as requested in README.md
- LoggerUtils was created to create a standard for logging "title - message". Where "title" is the class who calls the log and "message" is the message that we want to show.
- SKU = 3 has been changed just for one test (sku3discount.png)

## [0.1.0] - 03/05/2022 - Branch: feature/create-package-structure

### Added
- H2 database usage in local properties (We can set another dbms in app.properties for another environment. I used H2 for local environment)
- Package structure
- Save initial products to database with @EventListener (Another way is to create the "Insert queries" in data.sql. There are many ways to do this. I choose PostConstruct just to show how to convert json to model, and then use this model to save it to database )
- "Product" entity. (In here we can use @Column(name = ...) in case we want to name the column different than the table in database)
- See "initial-products-saved" file in "img-just-for-challenge" folder
- JsonUtils to handle all operations with Json

### NOTE
- "img-just-for-challenge" was created only for this challenge to show all steps in the development.

