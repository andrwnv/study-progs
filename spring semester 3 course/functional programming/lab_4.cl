; 16
; Написать функцию, удаляющую из исходного списка 
; все числовые атомы. 
; Исходный список может быть многоуровневым.

(defun remove-all-numbers (_list)
    (cond 
        ((null _list) 
            nil) ; cond case 1
        
        ((listp (car _list))
            (cons (remove-all-numbers (car _list))
                  (remove-all-numbers (cdr _list)))) ; cond case 2
        
        ((numberp (car _list))
            (remove-all-numbers (remove (car _list) _list))
        ) ; cond case 3

        (t 
            (cons (car _list) (remove-all-numbers (cdr _list)))) ; cond end case
    ) ; cond
) ; defun

(print (remove-all-numbers '(1 "a" "b" ("c" 5 7) "8" (9 ("c" 11)))))
