# Project 2 - *Flixster*

**Flixster** shows the latest movies currently playing in theaters. The app utilizes the Movie Database API to display images and basic information about these movies to the user.

Time spent: **10** hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can **scroll through current movies** from the Movie Database API
* [x] Display a nice default [placeholder graphic](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#advanced-usage) for each image during loading
* [x] For each movie displayed, user can see the following details:
  * [x] Title, Poster Image, Overview (Portrait mode)
  * [x] Title, Backdrop Image, Overview (Landscape mode)
* [x] Allow user to view details of the movie including ratings and popularity within a separate activity

The following **stretch** features are implemented:

* [x] Improved the user interface by experimenting with styling and coloring.
* [x] Apply rounded corners for the poster or background images using [Glide transformations](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#transformations)
* [x] Apply the popular [View Binding annotation library](http://guides.codepath.org/android/Reducing-View-Boilerplate-with-ViewBinding) to reduce boilerplate code.
* [x] Allow video trailers to be played in full-screen using the YouTubePlayerView from the details screen.

The following **additional** features are implemented:

* [x] List anything else that you can get done to improve the app functionality!
  *  Added "view trailer" button from the "more details" page for users to easily view the trailer once they are looking at more details of movie.
  *  Added "buy tickets" button from the "more details" page for users to be redirected to Fandango to buy tickets by using WebView.
  *  Added recommended movie to display from the "more details" page. Created "read more" button to allow users to navigate directly to that recommended movie's "more details" page.
  *  Added "my notes" button on the main page to take users to new page where users can store the names and their own ratings of movies they have watched. This data persists even once users have closed the app.
  *  Sort movies on main page from highest to lowest rating.
  *  Add scroll view on movies detail page so that user can see all content easily.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

[Portrait Mode](https://imgur.com/204UxDf)

[Landscape Mode](https://imgur.com/iAmDWfx)

[Notes Addition](https://imgur.com/QzxL4XG)

## Notes

Describe any challenges encountered while building the app:
* My notes page was difficult because I had a Note class that contained the title and rating of a movie so I could do simply use the ReadLines/WriteLines function to store the data persistently. I had to be creative on how I stored and read this data from files.
* I wanted to have the "buy tickets" button link to the Fandango app, but I later discovered that the API was private so I decided to use WebView instead.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright [2020] [Ashlee Kupor]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
