## Table of Contents
1. [Database](#database)
1. [Author(s)](#author)
1. [Database description](#description)
1. [Challenge](#challenge)

# Database
covid_illinois

# Author(s)
Luke Austin,
Andrew Macatangay

# Challenge
A database that tracks and displays covid-19 cases and deaths specific to the Chicago/Illinois area.
We chose this topic because it is a big concern for everyone's health and well-being right now

# Database description
There is an abundance of covid-19 related data coming out of various government agencies, 
but the websites that this data is hosted on is often difficult to navigate. 
We will design a system that takes the raw data from government agencies websites, 
and presents it in a way that is easier to navigate, and provides a more complete picture of the outbreak.

The Centers for Disease Control (CDC) and the Illinois Department of Public Health (IDPH) collects covid-19 
data for each zipcode and each county in the state of Illinois, including date of test, number of positive tests, 
and number of deaths daily. This data is useful, but can often be skewed by not providing enough context to the data visualizations. 
For example, often only the number of confirmed cases for counties are listed, without including what percentage of the **local** 
population is infected.

#ER Diagram
![Covid_Illinois ER Diagram](https://github.com/AusLuke/covid_illinois/blob/master/documentation/ERDiagram.png)
