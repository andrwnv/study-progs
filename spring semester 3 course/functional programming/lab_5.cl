; ; 7.Определите функцию, аргументом которой является дерево. 
; ; Функция должна вернуть ветвь с максимальным количеством листьев.

(setq _tree 
    '(1
        (3
            (
                (8)
                (9)
                (10)
                (13)
                (15)
            )
        )
        (2
            (
                (5)
                (6 ((11) (12)))
                (50 ((71) (80)))
            )    
        )
        (1
            (
                (110)
                (15)
            )    
        )
        (5 
            ()
        )
    )
)

(defun concat-lists (list1 list2)
    (if (null list1)
        list2
        (cons (car list1) (concat-lists (cdr list1) list2))
    ) ; if
) ; defun



(defun get-shit (subtree) ; find subtree
    (mapcan 
        (lambda (x) 
            (if (not (= (length x) 1))
                (list x) ; (print x)
            )
        )
        subtree
    )
)

(defun test1 (tree res) ; get all subtree (6 50 ...)
    (cons  
        (mapcan
            (lambda (x)
                (cond
                    ((not (listp (car x)))
                        (get-shit (car (cdr x)))
                    )
                )
            )
            tree
        )
        res
    )
)

(defun test2 (tree res) ; get (3 2 1 ...)
    (cons  
        (mapcan
            (lambda (x)
                (list x)
            )
            tree
        )
        res
    )
)

(defun kiborg-ubiyca-2 (tree) ; find max subtree
    (setq prev_len '())
    (mapcar 
        (lambda (x)
            (cond 
                ((> (length (car (cdr x))) (length (car (cdr prev_len))))
                    (setq prev_len x)
                )
            )
        )
        (concat-lists
            (list tree)
            (concat-lists (car(test1 (cdr tree) '())) (car(test2 (cdr tree) '())))
        )
    )

    prev_len
)

(print (kiborg-ubiyca-2 _tree))
