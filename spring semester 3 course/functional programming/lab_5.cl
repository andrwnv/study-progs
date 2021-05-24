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
                (50 ((71) (80) (17)))
            )    
        )
        (1
            (
                (110)
                (15)
            )    
        )
        (5)
    )
)

(defun check-every-item (list) 
    (if (null list)
        t
        (if (not (atom (first list)))
            nil
            (check-every-item (cdr list))
        ) ; if  
    ) ; if      
) ; defun

(defun max-breadth (lst)
    (cond
        ((atom lst) 0) ; 1
        ((check-every-item lst) (length lst)) ; 2 
        (t  
            (+
                (max-breadth (car lst))
                (max-breadth (remove-if-not #'listp (cdr lst))))
        ) ; t
    ) ; cond
) ; defun

(defun find-max-node-count (lst)
    (setq res '(0 ()))

    (mapcan 
        (lambda (x) 
                (if (< (car res) (max-breadth x))
                    (setq res (list (max-breadth x) x))
                )
        )
        (cdr _tree)
    )

    (cdr res)
)

(print (find-max-node-count _tree))
