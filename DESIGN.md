MainActivity
----------
- Ability to search for songs  
  onclick, start asynctask 
- Ability to search for artist  
	onclick, start asynctask 
- Ability to search for chords  
	onclick, start asynctask
- Go to Songbook  
	onclick, to songbookactivity
	
<img src="/doc/main.png" width="70">


asynctask
------------
- uses HTTPrequesthelper to retrieve data
- continues to either SongListActivity or chordActivity

songlist
-----------------
- listview met alle resultaten: titel+artiest  
	onclick naar SongActivity
	this same layout (with different header) will be used for songbook
	
<img src="/doc/list.png" width="70">
	
SongActivity
-------------------
- titel (uit result)
- key: X (uit result)
- play  
	onclick naar SongViewActivity
- checkbox: show chord diagrams  
	onchecked: show chord diagrams & add radiobutton:  
	Guitar v Ukulele v ??
- transpose: +   - (buttons)  
	onclick change all chords .5 up or down
- save  
	onclick save with current settings to SQL
	
<img src="/doc/songinfo.png" width="70">

PlayActivity
----------------
- shows text and chords of song
- add buttons to adjust speed 
- maybe add save button&transposebutton here instead of SongActivity

<img src="/doc/play.png" width="70">

chordactivity
--------------
- Name searched chord in ET + diagram
- radiobutton: guitar v Ukulele V ??  
	def guitar
- list of suggestions?  
	onclick: chordactivity with new chord on top
	
<img src="/doc/chordlist.png" width="70">
	
SongbookActivity
--------------
- listview with all saved songs  
	onclick: SongActivity  
	onlongclick: remove from list
	
	
Dataflow
------------------------
<img src="/doc/dataflow old.png" width="600">


Design
--------
- important: must allow for use outside at day an night -> 2 settings?


Minimum: 
------------------
At the very least, the application should show the song and chords in a structured manner.
Ideal:
------------------
It would be best to have transpose function, chorddiagrams for guitar and ukulele (and the option to hide them), an automatic scroll feature and a personal songbook in which songs canbe saved. I might also add an extra search in which you can search for a chord, which then duisplayes an image of how you can play this chord. 
This requires:
------------------
- a chords+lyrics API. 
  I have found this and it works, only it doesn;'t work as good as I would want it to. It doesn't contain transpose options, the database is rather small and the search works in a quirky way. Unfortunately this is the only API available so I do have to use it. 
- a chord-diagram API
	This exists, both for guitar and ukulele, so that should be fine.

Limitations:
------------------
I have no experience in implementing automatic scoll, so that will be a challenge, and there are some API limitations. 

