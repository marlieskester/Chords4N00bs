# Chords4N00bs
The application helps musicians find the chords to a song. The user enters a keyword or title, which results in a list of relevant results. The song are displayed, and transpose and animation options are available.


<img src="/doc/Screenshot_2017-02-02-10-41-44.png" width="70">


# Design
General flow of information
--------------
First activity (main): the user searches a song (or chord).
Second activity (songlistactivity): the user sees a list of songs and selects one.
Third activity (songactivity): the user can transpose the song, add to songbook and play the song.
Fourth activity (playactivity): the user sees the lyrics and chords to the song, and he can choose to use the automatic scroll function (with adjustable speed).

Or: 
Second activity (ChordActivity): the user sees an image of the chord he has just searched

Or: 
Second activity (SongBookActivity): the user sees a list of the songs he has added to the songbook. Onclick - go to "Third activity"

Detailed flow of information
------------------------
If the user performs a search in the mainactivity, an asynctask is started. 
- In this asynctask the right URL is composed (by HTTPRequestHelper) 
- the information is returned to the application
- the necessary information is extracted (by JSONExtractor)
- the next activity is started, depending on what is searched for.
In case the user searched a song, the next activity shows a list containing the result. The list is composed using a listadapter, which offers an onclick option to view the song.
The songactivity parses the song first, taking all chord symbols and replacing them with a temporal symbol. If the transpose option is used, the chord symbols are either changed through adding/removing a sharp or flat, or replacing the letter. After transposing the chords, they are put back in a copy of the original song and passed on to the next activity.

The songbook is mainained in DatabaseHelper, a class containing read, write and delete options for SQL. The reason to choose SQL is to have some functionality in the application when the user is offline (e.g. on holiday far away at a camping site with nothing but a guitar and a phone).

<img src="/doc/dataflownieuw.png" width="600">

# Challenges
The BUG
------------------
As can be found in my ProgressBook, I have had some trouble with a strange bug: the application would terminate without showing any kind of error or warning. In the end Gracia found a solution (using another way to pass the data). 

Autoscroll
-------------
This turned out to bea nightmare. I have spent hours and hours to find a way to do it, I have tried ten different options, each worse than the last. I have conferred with my teammates and TA's, and after over a week of frustration it turned out to be just one little function :( BUT I am really happy I found it!

Transpose
--------------
The transpose function also cost me some headache. I wrote a big (ugly) function to change the chord itself, and after that there was still the trouble of finding the chord and replacing it with the new chord. It took me a week to find out it actually didn't work (the function put the same chord in every slot). 
I have tried to improve the chord-changing function (by using UTF coding-math instead of actual letters), but because of some exceptions in music it still became a very big function with a lot of if/else. Therefore I decided to stick with the more hardcoded option, because neither of the codes is shorter nor more elegant, and in my eyes the next criterion is understandibility. In the 'hardcoded' version what happens is clearly visible, whereas in the UTF-8 code you go from string to char to byte to char to string, just to do the same thing.

Chords & Diagrams
-------------
Another issue. The reason for me to create this app was so that you don't have to look up each chord if you don't know how to play the instrument you are holding. The API had an option to return chord images, so I intended on using that, not realizing how many data (and coding nightmares) it would cost to use an API instead of images saved in the app. So when I was told this was not the way there was still quite some work to be done. Not just conding, but also manually cutting all the images...
After giving up about.. three times, mainly because of time pressure, I found some time left on deadline day - and I did it!
I had to work around sharps in the chords (because these symbols are not supported in the drawable file), and it was quite a puzzle to dynamically select views, but in the end it does work. 

Chords continued
------------------
Probably the only big change in my design: the chord-activity. I wanted to give the user multiple options on how to play the chord in the chordactivity, and provide onclick options to browse through similar chords. Unfortunately the API did not cooperate in the way I hoped, the chord variations cannot be imported (unless a double asynctask is used, which would make it take a lot longer). So instead I decided to only show the requested chord.

Design
---------------------
I had the idea to implement a day and night design so that the application would be user friendly at any hour. Unfortunately I did not have time for this. The way to do this would be either a checkbox in the main activity.
