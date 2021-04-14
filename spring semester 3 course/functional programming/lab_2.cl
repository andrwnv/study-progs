; Task 1
(defun _additional (_cur _nums _sybms)
    (cond 
        ((null _cur)
            (cons _nums (cons _sybms '()))
        )
        ((numberp (car _cur))
            ((lambda()
                (push (car _cur) _nums)
                (_additional (cdr _cur) _nums _sybms)
            ))
        )
        (t
            ((lambda()
                (push (car _cur) _sybms)
                (_additional (cdr _cur) _nums _sybms)
            ))
        )
    )
)

(defun task-1 (_list)
    (_additional _list '() '())
)

(print 
    (task-1 '(1 2 3 "b" "5")))



; Task 2
(defun is-not-num (elem)
    (not (numberp elem))
)

(defun remove-not-nums (_list)
    (cond 
        ((null _list) 
            nil)
        ((listp (car _list))
            (cons (remove-not-nums (car _list))
                  (remove-not-nums (cdr _list))))
        ((is-not-num (car _list)) 
            (remove-not-nums (cdr _list)))
        (t 
            (cons (car _list) (remove-not-nums (cdr _list))))
    )
)

(print 
    (remove-not-nums '(1 2 3 (1 "a" 3 (1 "3" 5)))))
