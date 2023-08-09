Things Changed:
1. Ship Class:
I added new variables such as directionType which is an Enum. I also added a setPoints method which adds all the points
of the coords of the ship. The point of the change was to give more control for the ship class to access the points.
2. Coord Class:
I added an equals to method where it checks if the x and y values are the same as well as its object. Makes it easier
for checking the history volleys.
3. BattleSalvoController Class:
This is a bit different from PA03 because I combined the 2 seperate controllers in PA03 into one controller. Everything
else regarding its attributes and methods are the same.
4. HitOrMiss Class:
I added a new method called aiHitServer which is used for the AI to hit the server. It is a bit different from original
because it's using the directionType Enum to locate the hit point.
5. AiPlayer Class:
More global variables were created because the values it gets from the ProxyController needed to be accessed in the
multiple methods within the class.
6. TakeShots Class:
Had to modify the shot method by adding validation to check if the shot was already taken.

Testing:
The JaCoco will say that the code coverage is not 100%. This is because we didn't test the BattleSalvoController class
because that class is from PA03.