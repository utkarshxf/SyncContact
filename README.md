# Trainer Contact Sync App

## Overview

<img src="https://github.com/user-attachments/assets/4772185c-2a90-46e3-901a-9d119e8e2805" align="right" width="120"/>
The **Trainer Contact Sync App** is an Android application designed to help trainers effortlessly sync the contact information of users enrolled in their training sessions directly to their mobile devices. With a single tap, trainers can add all contacts from the database to their device contacts, making it easier to manage communication with users.

## Features

- **Sync Contacts:** Users can tap the "Sync Contacts" button to automatically add contact details of all users added to the database today.
- **Progress Tracking:** A progress bar displays the real-time status of the syncing operation, providing users with instant feedback.
- **Permissions Handling:** The app requests necessary permissions to read and write contacts on the user's device.
- **Composable UI:** Built with Jetpack Compose, the app offers a modern and intuitive UI/UX.

## Architecture

The application follows a clean architecture, separating concerns across different layers:

- **UI Layer:** Built with Jetpack Compose, the UI handles user interactions and displays syncing progress.
- **ViewModel:** Manages UI-related data and handles the business logic, including fetching contacts from the repository and adding them to the device.
- **Repository:** Interfaces with the backend API to fetch the contacts added today.
- **Backend API:** A RESTful API provides the data for users added to the database today. 

## Getting Started

### Prerequisites

- **Android Studio**: Install the latest version of Android Studio.
- **Kotlin**: The project is built using Kotlin.
- **Hilt**: Dependency injection is handled with Hilt.

### Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/your-username/trainer-contact-sync-app.git
