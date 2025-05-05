This is "Pokémon Purple".

This is not meant to be a game, but more so a template/engine for a pokemon game/tile based RPG.

There are a few features that are meant to be highlighted:
A title screen with animated intro
Transitions between scenes that fade in/fade out
Player migration between scenes that is tile based rather than collision based.

and my favorite features that I am most proud of:
Scrolling text since BasicGraphics also doesn't allow for text to be dynamically added
Tile based movement with a grid that can be changed via CSV and dynamically customized through anonymous inner classes
and a complete ReusableClip overhaul that both fixes the original code and adds a pausing feature.


I actually chose against using some classes as they would slow me down in this process rather than help me out:

I chose against using BasicLayout too much, since in order to keep the RPG immersion using layouts would ruin the graphical consistency and using SpriteComponent was better for every single one of my use cases.
I chose against using BasicContainer for roughly the same reason.
I chose against using BasicDialog because it's not very seamless nor does it offer any benefits, and I basically created my own dialog system anyways in the LetterHandler.java class. For an example, just see the OakLecture card/scene.

There may be some repetitious code, but that is a result of my lack of skill in computer science rather than intentional. Some parts may also be there to reduce lag, as some of my features are very memory intensive since they utilize 2d arrays of sprites. 

Other than that, I hope you enjoy!