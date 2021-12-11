% swi-prolog

% filter by sickness
is_drug_for_this_sickness(Sickness, Drug) :- 
    drug(Drug, Sicknesses, _),
    member(Sickness, Sicknesses), !.

filter_by_sickness(_, [], []).
filter_by_sickness(Sickness, [Drug | AnotherDrugs], [Drug | Result]) :-
    is_drug_for_this_sickness(Sickness, Drug), 
    	filter_by_sickness(Sickness, AnotherDrugs, Result).
filter_by_sickness(Sickness, [_ | AnotherDrugs], Result) :- 
    filter_by_sickness(Sickness, AnotherDrugs, Result).

% filter by conds
is_drug_for_this_cond(Cond, Drug) :- 
    drug(Drug, _, Conds),
   	not(member(Cond, Conds)), !.

is_valid_drug_for_this_conds([], _).
is_valid_drug_for_this_conds([Cond | AnotherConds], Drug) :-
    is_drug_for_this_cond(Cond, Drug),
    	is_valid_drug_for_this_conds(AnotherConds, Drug), !.

filter_by_conds([], X, X) :- !.
filter_by_conds(_, [], []).
filter_by_conds(Conds, [Drug | AnotherDrugs], [Drug|Result]) :-  
    is_valid_drug_for_this_conds(Conds, Drug),
    	filter_by_conds(Conds, AnotherDrugs, Result).
filter_by_conds(Conds, [_ | AnotherDrugs], Result) :- 
    filter_by_conds(Conds, AnotherDrugs, Result).

% find drugs
find_drugs(Sickness, Conds, DB_FILENAME, Drugs) :-
    consult(DB_FILENAME),
    findall(X, drug(X, _, _), AllDrugs),
    filter_by_sickness(Sickness, AllDrugs, FirstFilteredDrugs),
    filter_by_conds(Conds, FirstFilteredDrugs, Drugs), !.

% find_drugs(flu, [allergy, intolerance], "drugs.txt", Res). -> [aspirin]
