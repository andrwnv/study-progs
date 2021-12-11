% swi-prolog

task(N, L) :-
    generate_list(N, [0, 1], L),
    without_seq(L, [0,0,0]),
    without_seq(L, [1,1,1]).


generate_list(0, _, []).
generate_list(N, Set, [Head | Tail]) :-
    N > 0,
    member(Head, Set),
    N1 is N - 1,
    generate_list(N1, Set, Tail).


without_seq([], _) :- !.
without_seq([Head | Tail], Sequence) :-
    not(append(Sequence, _, [Head | Tail])),
    !,
    without_seq(Tail, Sequence).

?- findall(L, task(4, L), Res).
