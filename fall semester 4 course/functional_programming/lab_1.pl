% start with https://swish.swi-prolog.org/

merge_lists(list1, list2, result).

merge_lists([], [], []) :- !.
merge_lists(List1, [], List1) :- !.
merge_lists([], List2, List2) :- !.

merge_lists([H1|T1], [H2|T2], [H1|TailResult]) 
:- H1 < H2, !, merge_lists(T1, [H2|T2], TailResult).

merge_lists(List1, [H2|T2], [H2|TailResult])
:- merge_lists(List1, T2, TailResult).

% ?- merge_lists([1,3,7,8,9], [2,3,5,7], Z).
