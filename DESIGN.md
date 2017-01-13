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

asynctask
------------
- uses HTTPrequesthelper to retrieve data
- continues to either SongListActivity or chordActivity

songlist
-----------------
- listview met alle resultaten: titel+artiest  
	onclick naar SongActivity
	
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
	
SongViewActivity
----------------
- shows text and chords of song
- add buttons to adjust speed 
- maybe add save button&transposebutton here instead of SongActivity

chordactivity
--------------
- Name searched chord in ET + diagram
- radiobutton: guitar v Ukulele V ??  
	def guitar
- list of suggestions?  
	onclick: chordactivity with new chord on top
	
SongbookActivity
--------------
- listview with all saved songs  
	onclick: SongActivity  
	onlongclick: remove from list

Design
--------
- important: must allow for use outside at day an night -> 2 settings?
- below: a sketch of the connected activities

![Sketch](/doc/DSC_0417.jpg)
