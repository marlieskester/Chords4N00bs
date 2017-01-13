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
	
![Main](/doc/main.png)

asynctask
------------
- uses HTTPrequesthelper to retrieve data
- continues to either SongListActivity or chordActivity

songlist
-----------------
- listview met alle resultaten: titel+artiest  
	onclick naar SongActivity
	this same layout (with different header) will be used for songbook
	
![List](/doc/list.png)
	
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
	
![Song](/doc/songinfo.png)
	
PlayActivity
----------------
- shows text and chords of song
- add buttons to adjust speed 
- maybe add save button&transposebutton here instead of SongActivity

![Play](/doc/play.png)

chordactivity
--------------
- Name searched chord in ET + diagram
- radiobutton: guitar v Ukulele V ??  
	def guitar
- list of suggestions?  
	onclick: chordactivity with new chord on top
	
![chord](/doc/chordlist.png)
	
SongbookActivity
--------------
- listview with all saved songs  
	onclick: SongActivity  
	onlongclick: remove from list

Design
--------
- important: must allow for use outside at day an night -> 2 settings?
