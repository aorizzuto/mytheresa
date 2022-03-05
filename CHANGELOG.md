# Changelog

All notable changes to this project will be documented in this file

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/)

## [0.1.0] - 03/05/2022 - Branch: feature/create-package-structure

### Added
- H2 database usage (We can set another dbms in app.properties for another environment. I used H2 for dev environment)
- Package structure
- Save initial products to database with @EventListener (Another way is to create the "Insert queries" in data.sql. There are many ways to do this. I choose PostConstruct just to show how to convert json to model, and then use this model to save it to database )
- "Product" entity. (In here we can use @Column(name = ...) in case we want to name the column different than the table in database)
- See "initial-products-saved" file in "img-just-for-challenge" folder
- "img-just-for-challenge" was created only for this challenge to show all steps in the development.

