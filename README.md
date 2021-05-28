# CoinStackGame
Initial commit

I coded a game based on the Problem of the Week #90 of Harvard Physics Department: https://www.physics.harvard.edu/files/prob90.pdf. 
This game is pretty simple, but is also deeply strategic. To reiterate the rules of the game, "There are three piles, each of which contains 
some number of coins. Players alternate turns, each turn consisting of removing any (non-zero) number of coins from a single pile.
The goal is to be the person to remove the last coin(s). Determine the optimal strategy".

I wanted to design an AI that could potenitally solve this game, so I implemented the rules of the game and used the minimax algorithm 
so that the AI would be able to "look in to the future" and play moves that resulted in winning positions. I designed the minimax algorithm 
such that it was able to evaluate all positions differently. In other words, some winning positions are better than other winning positions,
and some losing positions are better than other losing positions. 

I am currently learning Unity, and one of my goals is to implement a this game in Unity and perhaps publish it as a game.
