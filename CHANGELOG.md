# Changelog

All notable changes to this project will be documented in this file

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/)

## [0.1.0] - 03/04/2022 - Branch: feature/create-package-structure

### Added
- H2 database usage
- Package structure
- Save initial products to database with PostConstruct (Another way is to create the "Insert queries" in data.sql. There are many ways to do this. I choose PostConstruct just to show how to convert json to model, and then use this model to save it to database )
- "Product" entity. (In here we can use @JsonProperty in case we want to name the column different than the table in database)