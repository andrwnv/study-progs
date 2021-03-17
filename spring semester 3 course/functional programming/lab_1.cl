; Task 1.
(defun task-1 (_list)
    (caddr _list)
)

; Task 2.
(defun task-2 (_list)
    ((lambda (x) (or (= (length x) 4) (null x))) _list)
)

; Task 3.
(defun check-list (_list1 _list2 _list3 _size)
    (and 
        (>= (length _list1) _size)
        (and 
            (>= (length _list2) _size)
            (>= (length _list3) _size)
        )
    )
)

(defun task-3 (_list1 _list2 _list3)
    (cond 
        ((check-list _list1 _list2 _list3 3) 3)
        ((check-list _list1 _list2 _list3 2) 2)
        ((check-list _list1 _list2 _list3 1) 1)
        (t nil)
    )
)

; Tasks run.
(defun require-input ()
    (format t "Input task number [4 for leave]: ~%")
    (setq inp_res (read))

    (if (or (> inp_res 4) (< inp_res 0)) 4)

    inp_res
)


(setq list3 '(1 2 3 4))
(setq list4 '())
(setq list5 '(1 "q" 3 4 5))

(loop 
    (case (require-input)
        (1 
            (format t "Task 1 = ~d ~%~%" (task-1 '(0 (2 g a) 1 5)))
        )
        
        (2 
            (format t "Task 2 [LIST ~d] Result = ~d ~%"   list3 (task-2 list3))
            (format t "Task 2 [LIST ~d] Result = ~d ~%"   list4 (task-2 list4))
            (format t "Task 2 [LIST ~d] Result = ~d ~%~%" list5 (task-2 list5))
        )
        
        (3 
            (format t "Result list 1 = ~d ~%" (task-3 '() '(2 3) '(5)))
            (format t "Result list 2 = ~d ~%" (task-3 '(2) '(2) '(3)))
            (format t "Result list 3 = ~d ~%" (task-3 '(1 2 3) '(2 3) '(3 5)))
            (format t "Result list 4 = ~d ~%~%" (task-3 '(1 2 3) '(2 3 4) '(3 5 6)))
        ) 
        
        (4 (return))
    )
)
