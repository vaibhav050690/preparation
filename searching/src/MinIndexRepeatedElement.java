/*
Given an array of n elements, find the minimum index element which is repeated.
eg {1,3,2,2,3}
Output :- 3

Solution:-
Using hashing, store the element and its index in a hashmap(use 1 based indexing),if the element is repeated, negate the index.In case of
multiple repeats, continue.
Now scan the map again and search for the highest negative value.

eg, input :- {1,3,2,2,3}

Initially for 1,3,2
key    value
1       1
3       2
2       3

Now since 2 is already in map, negate its value.Similarly do it for 3 aswell.
key    value
1       1
3       -2
2       -3

Now scan the map again and search for the highest negative element, which in this case will be -2.
key    value
3       -2


 */

public class MinIndexRepeatedElement {
}