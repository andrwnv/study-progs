% swi-prolog

calc_main_diagonal_sum(_, [], 0):-!.
calc_main_diagonal_sum(CurrIndex, [Head | Tail], Sum):-
    NewIndex is CurrIndex + 1, 
    calc_main_diagonal_sum(NewIndex, Tail, TailSum),
    nth0(CurrIndex, Head, Element), 
    Sum is TailSum + Element.

calc_list_sum([], 0).
calc_list_sum([Head | Tail], Sum) :-
   calc_list_sum(Tail, Tmp),
   Sum is Head + Tmp.

is_graph_connected([], _, 0) :- !.
is_graph_connected([Head | Tail], MainDiagSum, Res) :- 
    calc_list_sum(Head, SumOfList), 
    SumOfList >= 1, MainDiagSum =:= 0, 
        is_graph_connected(Tail, MainDiagSum, Tmp), Res is Tmp + 1, !; Res = 0, !.

calc_edges_sum([], 0) :- !.
calc_edges_sum([Head | Tail], Sum) :-
    calc_edges_sum(Tail, Res), calc_list_sum(Head, R), Sum is R + Res.

is_tree(Matrix, Res) :- 
    calc_main_diagonal_sum(0, Matrix, DiagSum), 
    is_graph_connected(Matrix, DiagSum, VisitedVertexNum),
    length(Matrix, TotalVertexNum), 
    calc_edges_sum(Matrix, TotalEdgesSum),
    EdgesNum is (TotalEdgesSum / 2) + 1,
       
    DiagSum =:= 0, VisitedVertexNum =:= TotalVertexNum, TotalVertexNum =:= EdgesNum, Res = "True", ! ; Res = "False", !.  

is_tree([[0,1,1], [1,0,1], [1,1,0]], R). % -> false
is_tree([[0,1,1], [1,0,0], [1,0,0]], R). % -> true

is_tree([
[0, 1, 1, 0, 0],
[1, 0, 0, 0, 0],
[1, 0, 0, 1, 1],
[0, 0, 1, 0, 0],
[0, 0, 1, 0, 0]], R). % -> true
is_tree([
[0, 1, 1, 0, 0],
[1, 0, 0, 0, 0],
[1, 0, 0, 1, 1],
[0, 1, 1, 0, 0],
[0, 0, 1, 0, 0]], R). % -> false
