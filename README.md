# Task Auction Android Native

This is Task app for personal project demo.

## Table of Contents
* [General Information](#general-information)
* [Features](#features)
* [Technologies Used](#technologies-used)
* [Setup](#setup)
* [Structure](#structure)
* [Notes](#notes)
* [Developers](#developers)

## General Information
- Application for personal project demo
- What problem does it (intend to) solve?
  create an auction feature where users can view auction details and place bids. This
  feature should be structured into multiple layers (Presentation, Domain, Data) and follow
  Clean Architecture. The UI must be implemented using Jetpack Compose.
- What is the purpose of this project?
  implemented view auction details and place bids using jetpack compose ui also follow clean architecture

## Features
- Auction
  - View Live Auction Placeholder
  - List Auction
  - Live Chat UI 
  - Auction Detail
    - Image Product
    - Product Name
    - Description
    - Highet Bid
    - Counting Down
    - History Bid
    - Place Bid

## Technologies Used
- Kotlin - version 1.9.0
- Compose - version 1.5.0-rc01
- Accompanist - version 0.25.1
- Retrofit - version 2.9.0
- Hilt - version 2.51.1
- Coroutines - version 1.6.4
- Material3 - version 1.1.1
- Compose Reimagined Navigation -> version 1.5.0

## Setup
  No specification setup for run this project.
  - Open Project waiting until finish gradle build
  - Select run configuration and click on "Run"

## Structure
### Architecture using MVVM + Event + Intent
- App: Main entry point of the application, ties together other modules, and manages global configurations.
- buildSrc: Centralized dependency management for consistent and easy-to-maintain library versions and build scripts.
- core: Global utilities, networking, dependency injection, and reusable components that are shared across modules.
- feat: Contains individual feature auction modules that follow clean architecture with separation between data, domain, and presentation layers.

## Notes
  for The data api just dummy not real, i just generate using free access from
  [https://mockapi.io/]

## Developers
- Developer 1 - [rahmanecky.r@gmail.com](mailto:rahmanecky.r@gmail.com)
