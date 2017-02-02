# Chords4N00bs
The application helps musicians find the chords to a song. The user enters a keyword or title, which results in a list of relevant results. The song are displayed, and transpose and animation options are available.
**screenshot**

# Design
General flow of information
--------------
First activity (main): the user searches a song (or chord).
Second activity (songlistactivity): the user sees a list of songs and selects one.
Third activity (songactivity): the user can transpose the song, add to songbook and play the song.
Fourth activity (playactivity): the user sees the lyrics and chords to the song, and he can choose to use the automatic scroll function (with adjustable speed).

Or
Second activity: the user sees an image of the chord he has just searched

Or
Second activity: the user sees a list of the chord he has added to the songbook. Onclick - go to "Third activity"

Detailed flow of information
------------------------
If the user performs a search in the mainactivity, an asynctask is started. 
- In this asynctask the right URL is composed (by HTTPRequestHelper) 
- the information is returned to the application
- the necessary information is extracted (by JSONExtractor)
- the next activity is started, depending on what is searched for.
In case the user searched a song, the next activity shows a list containing the result. The list is composed using a listadapter, which offers an onclick option to view the song.
The songactivity parses the song first, taking all chord symbols and replacing them with a temporal symbol. If the transpose option is used, the chord symbols are either changed through adding/removing a sharp or flat, or replacing the letter. After transposing the chords, they are put back in a copy of the original song and passed on to the next activity.

The songbook is mainained in DatabaseHelper, a class containing read, write and delete options for SQL. The reason to choose SQL is to have some functionality in the application when the user is offline.

# Challenges
Autoscroll
-------------
This turned out to bea nightmare. I have spent hours and hours to find a way to do it, I have tried ten different options, each worse than the last. I have conferred with my teammates and TA's, and after over a week of frustration it turned out to be just one little function :( BUT I am really happy I found it!

Transpose
--------------
The transpose function also cost me some headache. I wrote a big (ugly) function to change the chord itself, and after that there was still the trouble of finding the chord and replacing it with the new chord. It took me a week to find out it actually didn't work (the function put the same chord in every slot). 
I have tried to improve the chord-changing function (by using UTF coding-math instead of actual letters), but unfortunately that didn't work.

Chords & Diagrams
-------------
Another issue. The reason for me to create this app was so that you don't have to look up each chord if you don't know how to play the instrument you are holding. The API had an option to return chord images, so I intended on using that, not realizing how many data (and coding nightmares) it would cost to use an API instead of images saved in the app. So when I was told this was not the way there was still quite some work to be done. Not just conding, but also manually cutting all the images...
Code-wise: Dynamically inserting views in a listview was impossible, and using a string to indicate what view to be used ("R.id.diagram" + i) is also not an option, so a rather ugly function here as well. But I am glad it works!

Chords continued
------------------
Probably the only big change in my design: the chord-activity. I wanted to give the user multiple options on how to play the chord in the chordactivity, and provide onclick options to browse through similar chords. Unfortunately the API did not cooperate in the way I hoped, the chord variations cannot be imported (unless a double asynctask is used, which would make it take a lot longer). So instead I decided to only show the requested chord.
