package traversal.dfs.eulerianpathcircuit;

/*
Given an array of strings, find if the given strings can be chained to form a circle. A string X can be put before another string Y in circle if the last
character of X is same as first character of Y.

Examples:

Input: arr[] = {"geek", "king"}
Output: Yes, the given strings can be chained.
Note that the last character of first string is same
as first character of second string and vice versa is
also true.

Input: arr[] = {"for", "geek", "rig", "kaf"}
Output: Yes, the given strings can be chained.
The strings can be chained as "for", "rig", "geek"
and "kaf"

Input: arr[] = {"aab", "bac", "aaa", "cda"}
Output: Yes, the given strings can be chained.
The strings can be chained as "aaa", "aab", "bac"
and "cda"

Input: arr[] = {"aaa", "bbb", "baa", "aab"};
Output: Yes, the given strings can be chained.
The strings can be chained as "aaa", "aab", "bbb"
and "baa"

Input: arr[] = {"aaa"};
Output: Yes

Input: arr[] = {"aaa", "bbb"};
Output: No

Input  : arr[] = ["abc", "efg", "cde", "ghi", "ija"]
Output : Yes
These strings can be reordered as, “abc”, “cde”, “efg”,
“ghi”, “ija”

Input : arr[] = [“ijk”, “kji”, “abc”, “cba”]
Output : No




Solution:-
It can be viewed as a graph problem.
Consider Input, arr[] = ["abc", "efg", "cde", "ghi", "ija"]
Expected output :- “abc”, “cde”, “efg”,“ghi”, “ija”
Note that in order to reach from abc to cde, there should be a path a to c and similarly to reach from cde to efg, there should be path c to e.
eg, for Input, arr[] = ["abc", "efg", "cde", "ghi", "ija"]
 create a graph:-
 a->c
    |
    V
    e-->g-->i--->a
Solution:-
Create a graph with V= {a to z}
add a directed edge from first to last character for each word in the input arr.
check if the directed graph created has Eulerian circuit

*/

public class CircleChain {

}