# Tennis match hierarchy
Defines components for a tennis match, which is composed by `Game` instances, `Set` instances and, finally, an enclosing `Match` object.

The aim of this little library is to be a solid base for applications treating tennis data.

The provided classes include:

* validation upon construction
* ways to establish if a game/set/match has ended
* all flavors of three-setters and five-setters (fifth set tie-breaks or fifth sets going *to the distance*)
* static helper classes
* immutability.

Everything is thoroughly tested (or, at least, it should :blush:).
