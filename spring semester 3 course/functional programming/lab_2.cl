
; Task 1. (fibonacci)
(defun task-1 (n f1 f2)
    (if (= n 0)
        f1
        (task-1 (1- n) f2 (+ f1 f2))
    )
)

;Task 2. (Count atoms)
(defun task-2 (_list)
    (if _list
        (+ (if (and (atom (car _list)) 
                    (not (equal (car _list) NIL)))
                1
                (task-2 (car _list)))
            (task-2 (cdr _list))
        )
        0
    )
)

(format t "Result of task 1 = ~d ~%~%"(task-1 15 1 1))
(format t "Result of task 2 = ~d atoms~%" (task-2 (list (list "gazelle") 1 NIL "tiger")))
(format t "Result of task 2 = ~d atoms~%" (task-2 (list)))

