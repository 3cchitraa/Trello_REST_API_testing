# Trello REST API Automation

## Overview
This repository contains automated REST API tests for [Trello](https://trello.com) to validate core board functionalities. The framework is built using Java and REST Assured, focusing on reliability, reusability, and clear reporting.

The tests cover key operations such as retrieving board details, creating lists, updating board names, deleting labels, and handling invalid scenarios.

---

## Features

- **Get Board Details**  
  Validate retrieval of board information for existing boards.

- **Create Unique Lists**  
  Send POST requests to create lists with unique names in a board.

- **Update Board Name**  
  Send PUT requests to update the name of an existing board.

- **Delete Label Name**  
  Delete labels from a board via REST API.

- **Invalid Case Handling**  
  Test retrieval of non-existent or invalid board details to ensure proper error handling.

---

## Technology Stack

- **Language:** Java  
- **Testing Framework:** TestNG  
- **API Automation:** REST Assured  
- **Reporting:** Extent Reports  
- **Logging:** Log4j  
- **Build Tool:** Maven  

---

## Test Structure

The framework follows a modular structure:

- **Services:** Separate classes for each API endpoint (GET, POST, PUT, DELETE).  
- **Test Cases:** TestNG classes with positive and negative scenarios.  
- **Utilities:** Reusable methods for headers, request specifications, and dynamic data generation.  
- **Reports:** HTML reports generated per test execution.

---

## Prerequisites

- Java 8 or higher installed
- Maven installed
- TestNG plugin
- Valid Trello API key and token
   ```bash
   git clone https://github.com/your-username/trello-api-tests.git
