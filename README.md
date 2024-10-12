# SwordTest

[Screen_recording_20241012_100949.webm](https://github.com/user-attachments/assets/bdceff40-7205-4b43-b6af-dd54dfe0db4a)

## Repository Discription

This repository was created with the aim to build a sample app for the Sword technical challenge.
This app was made with the goal to display a cat list, selecting and filtering by favourites and also display a specific cat details.

## Implementation Notes
The app is separated into 3 principal packages of data, domain and presentation by feature following the principles of clean architecture for single responsibility use cases, making the components more independent and easier to test. <br>
Also following MVVM principles in the presentation layer like the viewmodel acting like a bridge between the composable views and the models. <br>
The viewmodel makes use of mutable state flow to provide a reactive and efficient way of handling view interactions and updates, updating the UI when requests or user inputs are made and also diplaying error messages. <br>
For the purpose of an offline functionality Room was used to combine the save the current information and update as necessary when changes happen or an item is updated, this update happens when the app starts as the data must reflect the current status at all times. <br>

## Limitations
Api request for the list always returns a new list, so that list is always added to the database not updating the previous one.  <br>
Cats each only have one breed, so options to display breed variants information was not possible.  <br>

## Considerations
Considered adding a loading effect to some transitions. <br>
Improved UI styling, improving app theme. <br>
