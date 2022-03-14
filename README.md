# school-website
Simple website includes a mock server and html UI

The static website can be used to manage a list of schools, users (students and professors), and courses associated with each.

The APIs can also be directly invoked to manage the entities, and are used by the website.

TODO: include website screenshots here

# How to build and use

## build the code

`./gradlew assemble`

## run tests

`./gradlew build`

## run the server

`./gradle run`

## access the static website

# Project Structure

TODO: insert diagram here

# Object Model
![Class Hierarchy-1](https://user-images.githubusercontent.com/67568819/158113306-76194fa8-57be-45d3-8548-4d2593444aff.jpg)



# Workplan

## APIs to implement

* [ ] Add a user
* [ ] Update a user
* [ ] List the users
* [ ] Delete a user
Search APIs like below
* [ ] Find all the students in the given school
* [ ] Find Students
* [ ] Find Professors

## Additional APIs to implement

* [ ] all the above for a course
* [ ] all the above for a school
* [ ] attach or detach course to/from user
* [ ] attach or detach course to/from school
* [ ] suggestions APIs for each field:  name, email, school, course - given a string, what matches do we have in the database?
* [ ] add enrollment year to user fields
* [ ] add course year to courses

## Other things to take care of

These are roughly in priority order
* [ ] javadoc everything
* [ ] typecheck everything coming into the API
* [ ] put tests and data separate from source code
* [ ] use separate file for web server, core logic, and data-source
* [ ] use a mock library with test data to make the mock "database" / search engine
* [ ] provide validators
** [ ] make sure that the data has no special characters for security
** [ ] make sure that the data can convert to the expected type if it's an email or a year
* [ ] make the objects configurable
* [ ] drive the API and the UI off the object configuration
* [ ] use gradle build system to compile, test, and run the server
* [ ] use yaml for the configuration
* [ ] build the API with GraphQL interface
* [ ] create a diagram to show the structure of the systems and the code that powers them
* [ ] implement suggestions in the UI
